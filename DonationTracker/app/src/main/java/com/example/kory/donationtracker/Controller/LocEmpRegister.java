package com.example.kory.donationtracker.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.kory.donationtracker.Models.LocationClasses.Location;
import com.example.kory.donationtracker.Models.LocationClasses.LocationFacade;
import com.example.kory.donationtracker.Models.UserClasses.User;
import com.example.kory.donationtracker.Models.UserClasses.UserFacade;
import com.example.kory.donationtracker.R;

import java.util.ArrayList;

/**
 * location employee register view
 */
public class LocEmpRegister extends AppCompatActivity implements OnItemSelectedListener{

    /**
     * Creates the location employee location choice page
     * @param savedInstanceState the current state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loc_emp_register);

        // LocationFacade locFacade = LocationFacade.getInstance();
        ArrayList<Location> arr = (ArrayList) LocationFacade.getInstance().getList();
        Spinner spinner = findViewById(R.id.spinner);
        ArrayList<String> arr1 = new ArrayList<>();
        for (Location p : arr) {
            arr1.add(p.getAddress());
        }
//        for (String te : arr1) {
//            System.out.println(te);
//        }
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, arr1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }

    /**
     * Whenever a location is chosen, that location is set as the user's location
     * @param parent the parent Adapter View
     * @param view  the current view
     * @param position location's position in the spinner
     * @param id the id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String p = (String) parent.getItemAtPosition(position);
        UserFacade u = UserFacade.getInstance();
        User user = u.getCurrentUser();
        LocationFacade locFacade = LocationFacade.getInstance();
        ArrayList<Location> arr = (ArrayList) locFacade.getList();
        for (Location l : arr) {
            String a = l.getAddress();
            if (a.equals(p)) {
                user.setEmployeeLocation(a);
//                u.uploadToDB();
            }
        }
        // u.uploadToDB();
    }

    /**
     * If nothing is selected, do nothing
     * @param parent the parent Adapter View
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /**
     * Loads the start up screen
     * @param view Current view
     */
    public void toStartUp(View view) {
        Intent randomIntent = new Intent(this, StartUp.class);
        startActivity(randomIntent);
    }

    /**
     * If login is selected, it opens the home page
     * @param view Current view
     */
    public void onLoginClick(View view) {
        Intent randomIntent = new Intent(this, Home.class);
        startActivity(randomIntent);
    }
}

