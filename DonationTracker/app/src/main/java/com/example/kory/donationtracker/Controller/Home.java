package com.example.kory.donationtracker.Controller;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kory.donationtracker.Models.LocationClasses.Address;
import com.example.kory.donationtracker.Models.LocationClasses.InventoryClasses.Inventory;
import com.example.kory.donationtracker.Models.LocationClasses.InventoryClasses.Item;
import com.example.kory.donationtracker.Models.LocationClasses.InventoryClasses.ItemType;
import com.example.kory.donationtracker.Models.LocationClasses.Location;
import com.example.kory.donationtracker.Models.LocationClasses.LocationFacade;
import com.example.kory.donationtracker.Models.LocationClasses.LocationType;
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

// importing LocationClasses package

public class Home extends AppCompatActivity {

    private ArrayList<String> dummy;
    private ArrayList<String> address = new ArrayList<>();
    private ArrayList<String> type = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DrawerLayout mDrawerLayout;
    private UserType ut;
    private ArrayList<String> myDataSet;
    private ItemType tempStringForItem;
    private Spinner typeSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // populateLocations();

        UserFacade facade = UserFacade.getInstance();
        User user = facade.getCurrentUser();
        String name = user.get_name();
        // TextView nameView = (TextView)findViewById(R.id.textView6);
        // nameView.setText(name);
        ut = user.get_type();
        loadMenus();

        LocationFacade locFacade = LocationFacade.getInstance();
//        if (locFacade.checkIfEmpty()) {
//            populateLocations();
//        }

        ListView simpleList = (ListView) findViewById(R.id.listView);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext());
        simpleList.setAdapter(customAdapter);
        simpleList.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?>adapter,View v, int position, long id){
                // Intent intent;
                loadLocation(v, position);
            }

        });


    }

    public void loadMenus(){
        if (ut.equals(UserType.EMPLOYEE)) {
            mDrawerLayout = findViewById(R.id.drawer_layout);

            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            ActionBar actionbar = getSupportActionBar();
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeAsUpIndicator(R.drawable.menuhome);
            UserFacade userF = UserFacade.getInstance();
            User user = userF.getCurrentUser();
            // Location loc = user.get_employeeLocation();
            Location loc = LocationFacade.getInstance().getLocation(user.get_employeeLocation());
            LocationFacade locF = LocationFacade.getInstance();
            locF.setCurrentLocation(loc);

            final NavigationView navigationView = findViewById(R.id.nav_view);
            navigationView.inflateMenu(R.menu.employee_view);
            navigationView.setNavigationItemSelectedListener(
                    new NavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(MenuItem menuItem) {
                            // set item as selected to persist highlight
                            menuItem.setChecked(true);
                            // close drawer when item is tapped
                            mDrawerLayout.closeDrawers();

                            int id = menuItem.getItemId();
                            if (id == R.id.nav_camera4) {
                                backToHome(navigationView);
                            } else if (id == R.id.nav_camera) {
                                reloadHome(navigationView);
                            } else if (id == R.id.nav_camera1) {
                                loadInventory(navigationView);
                            }

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

            final NavigationView navigationView = findViewById(R.id.nav_view);
            navigationView.inflateMenu(R.menu.manager_view);
            navigationView.setNavigationItemSelectedListener(
                    new NavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(MenuItem menuItem) {
                            // set item as selected to persist highlight
                            menuItem.setChecked(true);
                            // close drawer when item is tapped
                            mDrawerLayout.closeDrawers();

                            int id = menuItem.getItemId();
                            if (id == R.id.nav_camera4) {
                                backToHome(navigationView);
                            } else if (id == R.id.nav_camera) {
                                reloadHome(navigationView);
                            }

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

            final NavigationView navigationView = findViewById(R.id.nav_view);
            navigationView.inflateMenu(R.menu.admin_view);
            navigationView.setNavigationItemSelectedListener(
                    new NavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(MenuItem menuItem) {
                            // set item as selected to persist highlight
                            menuItem.setChecked(true);
                            // close drawer when item is tapped
                            mDrawerLayout.closeDrawers();

                            int id = menuItem.getItemId();
                            if (id == R.id.nav_camera4) {
                                backToHome(navigationView);
                            } else if (id == R.id.nav_camer) {
                                reloadHome(navigationView);
                            }

                            // Add code here to update the UI based on the item selected
                            // For example, swap UI fragments here

                            return true;
                        }
                    });
        } else {
            mDrawerLayout = findViewById(R.id.drawer_layout);

            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            ActionBar actionbar = getSupportActionBar();
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeAsUpIndicator(R.drawable.menuhome);

            final NavigationView navigationView = findViewById(R.id.nav_view);
            navigationView.inflateMenu(R.menu.user_view);
            navigationView.setNavigationItemSelectedListener(
                    new NavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(MenuItem menuItem) {
                            // set item as selected to persist highlight
                            menuItem.setChecked(true);
                            // close drawer when item is tapped
                            mDrawerLayout.closeDrawers();

                            int id = menuItem.getItemId();
                            if (id == R.id.nav_camera1) {
                                backToHome(navigationView);
                            } else if (id == R.id.nav_camera) {
                                reloadHome(navigationView);
                            }

                            // Add code here to update the UI based on the item selected
                            // For example, swap UI fragments here

                            return true;
                        }
                    });

        }
    }

    public void loadInventory(View view) {
//        LocationFacade locFacade = LocationFacade.getInstance();
//        UserFacade userFacade = UserFacade.getInstance();
//        User user = userFacade.getCurrentUser();
//        Location loc = user.get_employeeLocation();
//        Inventory inv = loc.getInventory();
//        ArrayList<Item> items = (ArrayList) inv.getInventory();
        // LocationFacade.getInstance().send();
        setContentView(R.layout.activity_home_employee);
        loadMenus();
        ListView simpleList = (ListView) findViewById(R.id.listView);
        ItemAdapter customAdapter = new ItemAdapter(getApplicationContext());
        simpleList.setAdapter(customAdapter);
        simpleList.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?>adapter,View v, int position, long id){
                // Intent intent;
                loadItem(v, position);
            }

        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItemClick(view);
            }
        });
    }

    public void addItemClick(View view) {
        setContentView(R.layout.add_item);
        loadMenus();
        typeSpinner = findViewById(R.id.typespinner);
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, ItemType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);
    }

    public void addItemClickInside(View view) {
        EditText val = findViewById(R.id.valuetext);
        String value = val.getText().toString();
        try {
            Double s = Double.parseDouble(value);
            EditText sho = findViewById(R.id.shortext);
            String shorttext = sho.getText().toString();
            EditText lon = findViewById(R.id.longtext);
            String longtext = lon.getText().toString();

            ItemType it = (ItemType) typeSpinner.getSelectedItem();


            UserFacade userFacade = UserFacade.getInstance();
            User user = userFacade.getCurrentUser();
            // Location l = user.get_employeeLocation();
            Location l = LocationFacade.getInstance().getLocation(user.get_employeeLocation());
            Inventory inv = l.getInventory();
            Item i = new Item(l, shorttext, longtext, value, it.getStringType());
            inv.addItem(i);
            LocationFacade.getInstance().send();
            loadInventory(view);
            // LocationFacade.getInstance().send();
            // UserFacade.getInstance().refresh();
        } catch (Exception e){
            Toast myToast = Toast.makeText(this, "Value must be a number!",
                    Toast.LENGTH_SHORT);
            myToast.show();
        }
    }



    public void loadItem(View view, int pos) {
        setContentView(R.layout.activity_select_item);
        loadMenus();
        UserFacade userFacade = UserFacade.getInstance();
        User user = userFacade.getCurrentUser();
        // Location loc = user.get_employeeLocation();
        Location loc = LocationFacade.getInstance().getLocation(user.get_employeeLocation());
        LocationFacade locFacade = LocationFacade.getInstance();
        locFacade.setCurrentLocation(loc);
        Inventory inv = loc.getInventory();
        ArrayList<Item> items = (ArrayList) inv.getInventory();
        Item temp = items.get(pos);
        TextView short1 = findViewById(R.id.short1);
        TextView long1 = findViewById(R.id.long1);
        TextView type = findViewById(R.id.type);
        TextView value = findViewById(R.id.value);

        short1.setText(temp.getShort());
        long1.setText(temp.getFull());
        ItemType it = ItemType.valueOf(temp.getItemType());
        type.setText(it.getStringType());
        value.setText(Double.toString(temp.getValue()));
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void loadLocation(View view, int pos) {
        setContentView(R.layout.activity_select_location);
        loadMenus();
        LocationFacade locFacade = LocationFacade.getInstance();
        ArrayList<Location> temp = (ArrayList) locFacade.getList();
        Location location = temp.get(pos);
        locFacade.setCurrentLocation(location);
        TextView tv0 = findViewById(R.id.name);
        TextView tv1 = findViewById(R.id.latitude);
        TextView tv2 = findViewById(R.id.longitude);
        TextView tv3 = findViewById(R.id.address);
        TextView tv4 = findViewById(R.id.type);

        tv0.setText(location.getName());
        tv1.setText(location.getLat());
        tv2.setText(location.getLon());
        tv3.setText(location.getAddress().toString());
        tv4.setText(location.getType().getStringType());

        ListView simpleList = (ListView) findViewById(R.id.listView1);
        ItemAdapter customAdapter = new ItemAdapter(getApplicationContext());
        simpleList.setAdapter(customAdapter);
        simpleList.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?>adapter,View v, int position, long id){
                // Intent intent;
                loadItem(v, position);
            }

        });
    }
    public void backToHome(View view) {
        // logs the current user out of the system
        UserFacade facade = UserFacade.getInstance();
        facade.logout();

        LocationFacade locFacade = LocationFacade.getInstance();

        Intent randomIntent = new Intent(this, StartUp.class);
        startActivity(randomIntent);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void reloadHome(View view) {
        // logs the current user out of the system
//        Intent randomIntent = new Intent(this, Home.class);
//        startActivity(randomIntent);

        setContentView(R.layout.activity_home);
        loadMenus();
        ListView simpleList = (ListView) findViewById(R.id.listView);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext());
        simpleList.setAdapter(customAdapter);
        simpleList.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?>adapter,View v, int position, long id){
                // Intent intent;
                loadLocation(v, position);
            }

        });
    }

//    @Override
//    public boolean equals(Object o) {
//
//    }


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
    public void populateSpinner() {
        ArrayList<String> temp = new ArrayList<>();
        LocationFacade locFacade = LocationFacade.getInstance();
        ArrayList<Location> locations = (ArrayList) locFacade.getList();

        for (int i = 0; i < locations.size(); i++) {
            Location l = locations.get(i);
            String name = l.getName();
            // Address address1 = l.getAddress();
            // String address = address1.toString();
            String address = l.getAddress();
            LocationType type1 = l.getType();
            String type = type1.getStringType();
        }

    }

    @Override
    protected void onDestroy() {
        LocationFacade.getInstance().send();
        super.onDestroy();
    }

}


