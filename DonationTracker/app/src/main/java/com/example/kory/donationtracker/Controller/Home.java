package com.example.kory.donationtracker.Controller;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.List;

// importing LocationClasses package

/**
 * Home class
 */
public class Home extends AppCompatActivity {

    // private ArrayList<String> dummy;
    // private final ArrayList<String> address = new ArrayList<>();
    // private final ArrayList<String> type = new ArrayList<>();
    // private RecyclerView mRecyclerView;
    // private RecyclerView.Adapter mAdapter;
    // private RecyclerView.LayoutManager mLayoutManager;
    private DrawerLayout mDrawerLayout;
    private UserType ut;
    // private ArrayList<String> myDataSet;
    // private ItemType tempStringForItem;
    private Spinner typeSpinner;

    /**
     * Creates the initial layout for the Home screen
     * @param savedInstanceState the current state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // populateLocations();

        UserFacade facade = UserFacade.getInstance();
        User user = facade.getCurrentUser();
        // String name = user.getName();
        // TextView nameView = (TextView)findViewById(R.id.textView6);
        // nameView.setText(name);
        ut = user.getUserType();
        loadMenus();

        // LocationFacade locFacade = LocationFacade.getInstance();
//        if (locFacade.checkIfEmpty()) {
//            populateLocations();
//        }

        ListView simpleList = findViewById(R.id.listView);
        // CustomAdapter customAdapter = new CustomAdapter(getApplicationContext());
        ListAdapter customAdapter = new CustomAdapter(getApplicationContext());
        simpleList.setAdapter(customAdapter);
        simpleList.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?>adapter,View v, int position, long id){
                // Intent intent;
                loadLocation(position);
            }

        });


    }

    /**
     * Loads the menus into the slide-out menu
     */
    private void loadMenus(){
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
            // Location loc = LocationFacade.getInstance().getLocation(user.getEmployeeLocation());
            LocationFacade locF = LocationFacade.getInstance();
            String address = user.getEmployeeLocation();
            Location loc = locF.getLocation(address);
            locF.setCurrentLocation(loc);

            final NavigationView navigationView = findViewById(R.id.nav_view);
            navigationView.inflateMenu(R.menu.employee_view);
            navigationView.setNavigationItemSelectedListener(
                    new NavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                            // set item as selected to persist highlight
                            // menuItem.setChecked(true);
                            // close drawer when item is tapped
                            mDrawerLayout.closeDrawers();

                            int id = menuItem.getItemId();
                            if (id == R.id.nav_camera4) {
                                backToHome(navigationView);
                            } else if (id == R.id.nav_camera) {
                                reloadHome(navigationView);
                            } else if (id == R.id.nav_camera1) {
                                loadInventory(navigationView);
                            } else if (id == R.id.search_menu) {
                                loadSearchPage(navigationView);
                            } else if (id == R.id.map_menu) {
                                loadMapPage(navigationView);
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
                        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                            // set item as selected to persist highlight
                            // menuItem.setChecked(true);
                            // close drawer when item is tapped
                            mDrawerLayout.closeDrawers();

                            int id = menuItem.getItemId();
                            if (id == R.id.nav_camera4) {
                                backToHome(navigationView);
                            } else if (id == R.id.nav_camera) {
                                reloadHome(navigationView);
                            } else if (id == R.id.search_menu) {
                                loadSearchPage(navigationView);
                            } else if (id == R.id.map_menu) {
                                loadMapPage(navigationView);
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
                        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                            // set item as selected to persist highlight
                            // menuItem.setChecked(true);
                            // close drawer when item is tapped
                            mDrawerLayout.closeDrawers();

                            int id = menuItem.getItemId();
                            if (id == R.id.nav_camera4) {
                                backToHome(navigationView);
                            } else if (id == R.id.nav_camer) {
                                reloadHome(navigationView);
                            } else if (id == R.id.search_menu) {
                                loadSearchPage(navigationView);
                            } else if (id == R.id.map_menu) {
                                loadMapPage(navigationView);
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
                        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                            // set item as selected to persist highlight
                            // menuItem.setChecked(true);
                            // close drawer when item is tapped
                            mDrawerLayout.closeDrawers();

                            int id = menuItem.getItemId();
                            if (id == R.id.nav_camera1) {
                                backToHome(navigationView);
                            } else if (id == R.id.nav_camera) {
                                reloadHome(navigationView);
                            } else if (id == R.id.search_menu) {
                                loadSearchPage(navigationView);
                            } else if (id == R.id.map_menu) {
                                loadMapPage(navigationView);
                            }

                            // Add code here to update the UI based on the item selected
                            // For example, swap UI fragments here

                            return true;
                        }
                    });

        }
    }

    /**
     * Opens the maps page through the MapsActivity class
     * @param view Current view
     */
    private void loadMapPage(View view) {
        Intent randomIntent = new Intent(this, MapsActivity.class);
        startActivity(randomIntent);
    }

    /**
     * Opens the search page through the Search class
     * @param view Current view
     */
    private void loadSearchPage(View view) {
        Intent randomIntent = new Intent(this, Search.class);
        startActivity(randomIntent);
    }

    /**
     * Loads the location employee's location's inventory
     * @param view Current view
     */
    private void loadInventory(View view) {
//        LocationFacade locFacade = LocationFacade.getInstance();
//        UserFacade userFacade = UserFacade.getInstance();
//        User user = userFacade.getCurrentUser();
//        Location loc = user.get_employeeLocation();
//        Inventory inv = loc.getInventory();
//        ArrayList<Item> items = (ArrayList) inv.getInventory();
        // LocationFacade.getInstance().send();
        Log.d("Home.java", view.toString());
        setContentView(R.layout.activity_home_employee);
        loadMenus();
        ListView simpleList = findViewById(R.id.listView);
        ListAdapter customAdapter = new ItemAdapter(getApplicationContext(), null);
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

    /**
     * Loads the item's information
     * @param view Current view
     */
    private void addItemClick(View view) {
        Log.d("Home.java", view.toString());
        setContentView(R.layout.add_item);
        loadMenus();
        typeSpinner = findViewById(R.id.typespinner);
        ArrayAdapter<String> adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, ItemType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);
    }

    /**
     * Adds the item to the location's inventory
     * @param view Current view
     */
    public void addItemClickInside(View view) {
        EditText val = findViewById(R.id.valuetext);
        Editable ed = val.getText();
        String value = ed.toString();
        try {
            // Double s = Double.parseDouble(value);
            EditText sho = findViewById(R.id.shortext);
            Editable sho_ed = sho.getText();
            String shorttext = sho_ed.toString();
            EditText lon = findViewById(R.id.longtext);
            Editable lon_ed = lon.getText();
            String longtext = lon_ed.toString();

            ItemType it = (ItemType) typeSpinner.getSelectedItem();


            UserFacade userFacade = UserFacade.getInstance();
            User user = userFacade.getCurrentUser();
            // Location l = user.get_employeeLocation();
            // Location l = LocationFacade.getInstance().getLocation(user.getEmployeeLocation());
            // Inventory inv = l.getInventory();
            Item i = new Item(shorttext, longtext, value, it.getStringType());
            // LocationFacade.getInstance().getLocation(user.getEmployeeLocation()).addItem(i);
            LocationFacade locF = LocationFacade.getInstance();
            String address = user.getEmployeeLocation();
            Location location = locF.getLocation(address);
            location.addItem(i);
            // inv.addItem(i);
            // LocationFacade.getInstance().send();
            loadInventory(view);
            // LocationFacade.getInstance().send();
            // UserFacade.getInstance().refresh();
        } catch (Exception e){
            Toast myToast = Toast.makeText(this, "Value must be a number!",
                    Toast.LENGTH_SHORT);
            myToast.show();
        }
    }


    /**
     * Loads the wanted item
     * @param view Current view
     * @param pos Item's position to load
     */
    private void loadItem(View view, int pos) {
        Log.d("Home.java", view.toString());
        setContentView(R.layout.activity_select_item);
        loadMenus();
        // UserFacade userFacade = UserFacade.getInstance();
        // User user = userFacade.getCurrentUser();
        // Location loc = user.get_employeeLocation();
        // Location loc = LocationFacade.getInstance().getLocation(user.getEmployeeLocation());
        LocationFacade locFacade = LocationFacade.getInstance();
        Location loc = locFacade.getCurrentLocation();
        Inventory inv = loc.getInventory();
        // encapsulation issue, android requires a list for this view
        List<Item> items = inv.getInventory();
        Item temp = items.get(pos);
        TextView short1 = findViewById(R.id.short1);
        TextView long1 = findViewById(R.id.long1);
        TextView type = findViewById(R.id.type);
        TextView value = findViewById(R.id.value);

        short1.setText(temp.getShort());
        long1.setText(temp.getFull());
        // ItemType it = ItemType.valueOf(temp.getItemType().toUpperCase());
        String type1 = temp.getItemType();
        type1 = type1.toUpperCase();
        ItemType it = ItemType.valueOf(type1);
        type.setText(it.getStringType());
        value.setText(Double.toString(temp.getValue()));
    }

    /**
     * Loads the desired location's information
     * @param pos position of the location to load
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void loadLocation(int pos) {
        setContentView(R.layout.activity_select_location);
        loadMenus();
        LocationFacade locFacade = LocationFacade.getInstance();
        List<Location> temp = locFacade.getList();
        Location location = temp.get(pos);
        locFacade.setCurrentLocation(location);
        locFacade.setCurrentLocation(location);
        TextView tv0 = findViewById(R.id.name);
        TextView tv1 = findViewById(R.id.latitude);
        TextView tv2 = findViewById(R.id.longitude);
        TextView tv3 = findViewById(R.id.address);
        TextView tv4 = findViewById(R.id.type);

        tv0.setText(location.getName());
        tv1.setText(location.getLat());
        tv2.setText(location.getLon());
        tv3.setText(location.getAddress());
        LocationType locType = location.getType();
        tv4.setText(locType.getStringType());

        ListView simpleList = findViewById(R.id.listView1);
        ListAdapter customAdapter = new ItemAdapter(getApplicationContext(), null);
        simpleList.setAdapter(customAdapter);
        simpleList.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?>adapter,View v, int position, long id){
                // Intent intent;
                loadItem(v, position);
            }

        });
    }

    /**
     * Logs the user out and opens the initial opening screen
     * @param view Current view
     */
    private void backToHome(View view) {
        // logs the current user out of the system
        Log.d("Home.java", view.toString());
        UserFacade facade = UserFacade.getInstance();
        facade.logout();

        // LocationFacade locFacade = LocationFacade.getInstance();

        Intent randomIntent = new Intent(this, StartUp.class);
        startActivity(randomIntent);
    }

    /**
     * Loads the home page
     * @param view Current view
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void reloadHome(View view) {
        // logs the current user out of the system
//        Intent randomIntent = new Intent(this, Home.class);
//        startActivity(randomIntent);

        setContentView(R.layout.activity_home);
        loadMenus();
        ListView simpleList = findViewById(R.id.listView);
        ListAdapter customAdapter = new CustomAdapter(getApplicationContext());
        simpleList.setAdapter(customAdapter);
        simpleList.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?>adapter,View v, int position, long id){
                // Intent intent;
                loadLocation(position);
            }

        });
    }

    /**
     * Logs the user out if they press the back button on the phone
     */
    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
        // logs the current user out of the system
        UserFacade facade = UserFacade.getInstance();
        facade.logout();

        Intent randomIntent = new Intent(this, StartUp.class);
        startActivity(randomIntent);
    }

//    /**
//     * Loads the locations into the spinner if the user registers as a Location Employee
//     */
//    @TargetApi(Build.VERSION_CODES.KITKAT)
//    public void populateSpinner() {
//        // ArrayList<String> temp = new ArrayList<>();
//        LocationFacade locFacade = LocationFacade.getInstance();
//        List<Location> locations = locFacade.getList();
//
//        for (int i = 0; i < locations.size(); i++) {
//            Location l = locations.get(i);
//            // String name = l.getName();
//            // Address address1 = l.getAddress();
//            // String address = address1.toString();
//            // String address = l.getAddress();
//            // LocationType type1 = l.getType();
//            // String type = type1.getStringType();
//        }
//
//    }
}


