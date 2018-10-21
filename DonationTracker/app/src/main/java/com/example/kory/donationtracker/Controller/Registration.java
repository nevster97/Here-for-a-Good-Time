package com.example.kory.donationtracker.Controller;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kory.donationtracker.Models.LocationClasses.Address;
import com.example.kory.donationtracker.Models.LocationClasses.Location;
import com.example.kory.donationtracker.Models.LocationClasses.LocationFacade;
import com.example.kory.donationtracker.Models.UserClasses.User;
import com.example.kory.donationtracker.Models.UserClasses.UserFacade;
import com.example.kory.donationtracker.Models.UserClasses.UserType;
import com.example.kory.donationtracker.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Registration extends AppCompatActivity {

    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText password;
    private EditText confirmPassword;
    private Spinner typeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        firstName = (EditText) findViewById(R.id.editText4);
        lastName = (EditText) findViewById(R.id.editText7);
        email =  (EditText) findViewById(R.id.editText6);
        password = (EditText) findViewById(R.id.editText2);
        confirmPassword = (EditText) findViewById(R.id.editText5);
        typeSpinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, UserType.getList());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);
    }

    public void toStartUp(View view) {
        Intent randomIntent = new Intent(this, StartUp.class);
        startActivity(randomIntent);
    }

    public void onRegisterClick(View view) {

//        final EditText firstName =  (EditText) findViewById(R.id.editText4);
//        final EditText lastName =  (EditText) findViewById(R.id.editText7);
//        final EditText password = (EditText) findViewById(R.id.editText2);
//        final EditText confirmPassword = (EditText) findViewById(R.id.editText5);
//        final EditText email =  (EditText) findViewById(R.id.editText6);

        String firstString = (String) firstName.getText().toString().trim();
        String lastString = (String) lastName.getText().toString().trim();

        String pass1 = (String) password.getText().toString();
        String pass2 = (String) confirmPassword.getText().toString();

        String nameString = firstString + " " + lastString;
        String emailString = (String) email.getText().toString();

        UserType type = (UserType) typeSpinner.getSelectedItem();


//        } else if (emailString.equals("")) {
//            CharSequence text = ("Email is null");
//            int duration = Toast.LENGTH_SHORT;
//            Toast toast = Toast.makeText(this, text, duration);
//            toast.show();
//        } else {
//            Intent randomIntent = new Intent(this, StartUp.class);
//            startActivity(randomIntent);
//        }

        if (firstString.equals("") || lastString.equals("")
                || emailString.equals("") || pass1.equals("") || pass2.equals("")) {
            // handle empty fields
            CharSequence text = ("Please ensure that all fields are completed");
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
        } else if (!pass1.equals(pass2)) {
            // handle different passwords
            CharSequence text = ("Passwords do not match");
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
        } else {
            UserFacade facade = UserFacade.getInstance();
            if (facade.register(emailString, pass1, nameString, emailString, type)) {
                boolean b = facade.login(emailString, pass1);
                populateLocations();
                User u = facade.getCurrentUser();
                UserType ut = u.get_type();
                if (ut.equals(UserType.EMPLOYEE)) {
                    Intent randomIntent = new Intent(this, LocEmpRegister.class);
                    startActivity(randomIntent);
                } else {
                    Intent randomIntent = new Intent(this, Home.class);
                    startActivity(randomIntent);
                }
            } else {
                CharSequence text = ("Username already taken");
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(this, text, duration);
                toast.show();
            }
        }



    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void populateLocations() {
        try {
            //Open a stream on the raw file
            InputStream is = getResources().openRawResource(R.raw.locationdata);
            //From here we probably should call a model method and pass the InputStream
            //Wrap it in a BufferedReader so that we get the readLine() method
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            LocationFacade locFacade = LocationFacade.getInstance();
            String line;
            String[] tokens = new String[10];
            br.readLine(); //get rid of header line

            while ((line = br.readLine()) != null) {
                //line = br.readLine();
                tokens = line.split(",");
                String name = tokens[1];
                String lat = tokens[2];
                String lon = tokens[3];
                // address
                String street = tokens[4];
                String city = tokens[5];
                String state = tokens[6];
                String zip = tokens[7];
                Address address = new Address(street, city, state, zip);
                String type = tokens[8];
                String phone = tokens[9];
                String website = tokens[10];

                Location location = new Location(name, lat, lon, address, type, phone, website);
                locFacade.addLocation(location);
            }
            br.close();



        } catch (IOException e) {
            System.out.println("Error");
        }
    }




}
