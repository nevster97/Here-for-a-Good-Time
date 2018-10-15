package com.example.kory.donationtracker.Controller;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.kory.donationtracker.Models.UserClasses.User;
import com.example.kory.donationtracker.Models.UserClasses.UserFacade;
import com.example.kory.donationtracker.Models.UserClasses.UserType;
import com.example.kory.donationtracker.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Home extends AppCompatActivity implements OnItemSelectedListener {

    private ArrayList<String> dummy;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DrawerLayout mDrawerLayout;
    private UserType ut;
    private ArrayList<String> myDataSet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        dummy = populateSpinner();
        Spinner majorSpinner = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, dummy);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        majorSpinner.setAdapter(adapter);
        majorSpinner.setOnItemSelectedListener(this);

        UserFacade facade = UserFacade.getInstance();
        User user = facade.getCurrentUser();
        String name = user.get_name();
        // TextView nameView = (TextView)findViewById(R.id.textView6);
        // nameView.setText(name);
        ut = user.get_type();

        if (ut.equals(UserType.EMPLOYEE)) {
            mDrawerLayout = findViewById(R.id.drawer_layout);

            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            ActionBar actionbar = getSupportActionBar();
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeAsUpIndicator(R.drawable.menuhome);

            NavigationView navigationView = findViewById(R.id.nav_view);
            navigationView.inflateMenu(R.menu.employee_view);
            navigationView.setNavigationItemSelectedListener(
                    new NavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(MenuItem menuItem) {
                            // set item as selected to persist highlight
                            menuItem.setChecked(true);
                            // close drawer when item is tapped
                            mDrawerLayout.closeDrawers();

                            // Add code here to update the UI based on the item selected
                            // For example, swap UI fragments here

                            return true;
                        }
                    });
        } else if (ut.equals(UserType.MANAGER)) {
            mDrawerLayout = findViewById(R.id.drawer_layout);

            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            ActionBar actionbar = getSupportActionBar();
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeAsUpIndicator(R.drawable.menuhome);

            NavigationView navigationView = findViewById(R.id.nav_view);
            navigationView.inflateMenu(R.menu.manager_view);
            navigationView.setNavigationItemSelectedListener(
                    new NavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(MenuItem menuItem) {
                            // set item as selected to persist highlight
                            menuItem.setChecked(true);
                            // close drawer when item is tapped
                            mDrawerLayout.closeDrawers();

                            // Add code here to update the UI based on the item selected
                            // For example, swap UI fragments here

                            return true;
                        }
                    });
        } else if (ut.equals(UserType.ADMIN)) {
            mDrawerLayout = findViewById(R.id.drawer_layout);

            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            ActionBar actionbar = getSupportActionBar();
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeAsUpIndicator(R.drawable.menuhome);

            NavigationView navigationView = findViewById(R.id.nav_view);
            navigationView.inflateMenu(R.menu.admin_view);
            navigationView.setNavigationItemSelectedListener(
                    new NavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(MenuItem menuItem) {
                            // set item as selected to persist highlight
                            menuItem.setChecked(true);
                            // close drawer when item is tapped
                            mDrawerLayout.closeDrawers();

                            // Add code here to update the UI based on the item selected
                            // For example, swap UI fragments here

                            return true;
                        }
                    });
        }
    }

    public void backToHome(View view) {
        // logs the current user out of the system
        UserFacade facade = UserFacade.getInstance();
        facade.logout();

        Intent randomIntent = new Intent(this, StartUp.class);
        startActivity(randomIntent);
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        String a = (String) parent.getItemAtPosition(pos);
        changeTheView(pos);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
        // logs the current user out of the system
        UserFacade facade = UserFacade.getInstance();
        facade.logout();

        Intent randomIntent = new Intent(this, StartUp.class);
        startActivity(randomIntent);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public ArrayList<String> populateSpinner() {


//        String dir = getApplicationInfo().dataDir;
//        System.out.println("DIR: " + dir);
//
//        URL url = getClass().getResource("/Users/kory/Desktop/Here-for-a-Good-Time/DonationTracker/app/src/main/java/com/example/kory/donationtracker/Controller");
//        File file = new File(url.getPath());
//
//        Scanner scan = new Scanner("Apple");
        ArrayList<String> temp = new ArrayList<>();
        try {
            //Open a stream on the raw file
            InputStream is = getResources().openRawResource(R.raw.locationdata);
            //From here we probably should call a model method and pass the InputStream
            //Wrap it in a BufferedReader so that we get the readLine() method
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

            String line;
            br.readLine(); //get rid of header line
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                temp.add(tokens[1]);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error");
        }
        return temp;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void changeTheView(int pos) {
        ArrayList<String> temp = new ArrayList<>();
        try {
            //Open a stream on the raw file
            InputStream is = getResources().openRawResource(R.raw.locationdata);
            //From here we probably should call a model method and pass the InputStream
            //Wrap it in a BufferedReader so that we get the readLine() method
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

            String line;
            String[] tokens = new String[10];
            br.readLine(); //get rid of header line
            for (int i = 0; i < pos + 1; i++) {
                line = br.readLine();
                tokens = line.split(",");
            }
            TextView tv = findViewById(R.id.textView3);
            tv.setText(tokens[4]);
            br.close();



        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}


