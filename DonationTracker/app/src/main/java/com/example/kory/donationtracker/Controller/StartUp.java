package com.example.kory.donationtracker.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

// app backend
import com.example.kory.donationtracker.Models.LocationClasses.Address;
import com.example.kory.donationtracker.Models.LocationClasses.Location;
import com.example.kory.donationtracker.Models.LocationClasses.LocationFacade;
import com.example.kory.donationtracker.Models.UserClasses.UserFacade;
import com.example.kory.donationtracker.R;

// file reader stuff
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class StartUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
        getContent();

    }

    public void toLogin(View view) {
        // Create an Intent to start the second activity
        Intent randomIntent = new Intent(this, Login.class);

        // Start the new activity.
        startActivity(randomIntent);
    }

    public void toRegistration(View view) {
        Intent randomIntent = new Intent(this, Registration.class);
        startActivity(randomIntent);
    }

    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
        Intent randomIntent = new Intent(this, StartUp.class);
        startActivity(randomIntent);
    }

    public void getContent() {
        UserFacade.getInstance().refresh();
        // LocationFacade.getInstance().refresh(); or something
        // firstRead();
        LocationFacade.getInstance().refresh();
        return;
    }

    public void firstRead() {
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
                // Address address = new Address(street, city, state, zip);
                String address = street + ", " + city + ", " + state + " " + zip;
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
