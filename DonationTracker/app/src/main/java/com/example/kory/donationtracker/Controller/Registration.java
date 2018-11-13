package com.example.kory.donationtracker.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kory.donationtracker.Models.LocationClasses.LocationFacade;
import com.example.kory.donationtracker.Models.UserClasses.User;
import com.example.kory.donationtracker.Models.UserClasses.UserFacade;
import com.example.kory.donationtracker.Models.UserClasses.UserType;
import com.example.kory.donationtracker.R;

/**
 * Registration activity
 */
public class Registration extends AppCompatActivity {

    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText password;
    private EditText confirmPassword;
    private Spinner typeSpinner;

    /**
     * Loads the registration page
     * @param savedInstanceState the current state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        firstName = findViewById(R.id.editText4);
        lastName = findViewById(R.id.editText7);
        email = findViewById(R.id.editText6);
        password = findViewById(R.id.editText2);
        confirmPassword = findViewById(R.id.editText5);
        typeSpinner = findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, UserType.getList());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);
    }

    /**
     * Loads the opening page
     * @param view Current view
     */
    public void toStartUp(View view) {
        Intent randomIntent = new Intent(this, StartUp.class);
        startActivity(randomIntent);
    }

    /**
     * Registers the user and takes them to the home page or to the LocEmpRegister page
     * if they are a location employee
     * @param view Current view
     */
    public void onRegisterClick(View view) {

//        final EditText firstName =  (EditText) findViewById(R.id.editText4);
//        final EditText lastName =  (EditText) findViewById(R.id.editText7);
//        final EditText password = (EditText) findViewById(R.id.editText2);
//        final EditText confirmPassword = (EditText) findViewById(R.id.editText5);
//        final EditText email =  (EditText) findViewById(R.id.editText6);

        Editable first = firstName.getText();
        Editable last = lastName.getText();

        String firstString = first.toString();
        String lastString = last.toString();

        firstString = firstString.trim();
        lastString = lastString.trim();

//        String firstString = firstName.getText().toString().trim();
//        String lastString = lastName.getText().toString().trim();

        Editable p1 = password.getText();
        Editable p2 = confirmPassword.getText();
        Editable e1 = email.getText();

        String pass1 = p1.toString();
        String pass2 = p2.toString();

        String nameString = firstString + " " + lastString;
        String emailString = e1.toString();

        String type = ((UserType) typeSpinner.getSelectedItem()).getStringType();

        if ("".equals(firstString) || "".equals(lastString)
                || "".equals(emailString) || "".equals(pass1) || "".equals(pass2)) {
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
                LocationFacade locFacade = LocationFacade.getInstance();
//                if (locFacade.checkIfEmpty()) {
//                    //populateLocations();
//
//                }
                User u = facade.getCurrentUser();
                UserType ut = u.getUserType();
                if (ut.equals(UserType.EMPLOYEE)) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread current = Thread.currentThread();
                        current.interrupt();
                        // Thread.currentThread().interrupt();
                    }
                    Intent randomIntent = new Intent(this, LocEmpRegister.class);
                    startActivity(randomIntent);
                } else {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread current = Thread.currentThread();
                        current.interrupt();
                        // Thread.currentThread().interrupt();
                    }
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

}
