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

public class LocEmpRegister extends AppCompatActivity implements OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loc_emp_register);

        LocationFacade locFacade = LocationFacade.getInstance();
        ArrayList<Location> arr = (ArrayList) locFacade.getList();
        Spinner spinner = findViewById(R.id.spinner);
        ArrayList<String> arr1 = new ArrayList<>();
        for (Location p : arr) {
            arr1.add(p.getAddress().toString());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, arr1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String p = (String) parent.getItemAtPosition(position);
        System.out.println(p);
        UserFacade u = UserFacade.getInstance();
        User user = u.getCurrentUser();
        LocationFacade locFacade = LocationFacade.getInstance();
        ArrayList<Location> arr = (ArrayList) locFacade.getList();
        for (Location l : arr) {
            String a = l.getAddress().toString();
            if (a.equals(p)) {
                System.out.println("SLKDJFLKSDJFLKSDJFLSDKFLSF");
                user.set_employeeLocation(a);
//                u.uploadToDB();
            }
        }
        u.uploadToDB();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void toStartUp(View view) {
        Intent randomIntent = new Intent(this, StartUp.class);
        startActivity(randomIntent);
    }

    public void onLoginClick(View view) {
        Intent randomIntent = new Intent(this, Home.class);
        startActivity(randomIntent);
    }
}

