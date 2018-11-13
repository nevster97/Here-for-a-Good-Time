package com.example.kory.donationtracker.Controller;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.kory.donationtracker.Models.LocationClasses.InventoryClasses.Inventory;
import com.example.kory.donationtracker.Models.LocationClasses.InventoryClasses.Item;
import com.example.kory.donationtracker.Models.LocationClasses.InventoryClasses.ItemType;
import com.example.kory.donationtracker.Models.LocationClasses.Location;
import com.example.kory.donationtracker.Models.LocationClasses.LocationFacade;
import com.example.kory.donationtracker.Models.LocationClasses.LocationType;
import com.example.kory.donationtracker.Models.UserClasses.User;
import com.example.kory.donationtracker.Models.UserClasses.UserFacade;
import com.example.kory.donationtracker.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Search activity
 */
public class Search extends AppCompatActivity implements OnItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    // private UserType ut;
    private List<Inventory> masterInventory;
    private ArrayList<Item> view;
    private Location locationToBeSearched;

    /**
     * Loads the search items page
     * @param savedInstanceState the current state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_search);
        loadMenus();
        super.onCreate(savedInstanceState);
        view = new ArrayList<>();
        masterInventory = new ArrayList<>();
        populateGlobalInventoryList();
        populateSpinner();

    }

    /**
     * Loads the location's list
     * @param view Current view
     * @param pos the location's index
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void loadLocation(View view, int pos) {
        setContentView(R.layout.activity_select_location);
        loadMenus();
        LocationFacade locFacade = LocationFacade.getInstance();
        List<Location> temp = locFacade.getList();
        Location location = temp.get(pos);
        locFacade.setCurrentLocation(location);

        String nam = location.getName();
        String lat = location.getLat();
        String lon = location.getLon();
        String add = location.getAddress();
        LocationType lt = location.getType();
        String stringType = lt.getStringType();

        TextView tv0 = findViewById(R.id.name);
        TextView tv1 = findViewById(R.id.latitude);
        TextView tv2 = findViewById(R.id.longitude);
        TextView tv3 = findViewById(R.id.address);
        TextView tv4 = findViewById(R.id.type);

        tv0.setText(nam);
        tv1.setText(lat);
        tv2.setText(lon);
        tv3.setText(add);
        tv4.setText(stringType);

        ListView simpleList = findViewById(R.id.listView1);
        ListAdapter customAdapter = new ItemAdapter(getApplicationContext(), null);
        simpleList.setAdapter(customAdapter);
        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?>adapter,View v, int position, long id){
                // Intent intent;
                loadItem(v, position);
            }

        });
    }

    /**
     * Loads the item
     * @param view current view
     * @param pos index of the item
     */
    private void loadItem(View view, int pos) {
        setContentView(R.layout.activity_select_item);
        loadMenus();
        UserFacade userFacade = UserFacade.getInstance();
        User user = userFacade.getCurrentUser();
        // Location loc = user.get_employeeLocation();
        LocationFacade locF = LocationFacade.getInstance();
        String address = user.getEmployeeLocation();
        Location loc = locF.getLocation(address);
        LocationFacade locFacade = LocationFacade.getInstance();
        locFacade.setCurrentLocation(loc);
        Inventory inv = loc.getInventory();
        List<Item> items = inv.getInventory();
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

    /**
     * populates every locations inventory into the master inventory
     */
    private void populateGlobalInventoryList() {
        LocationFacade locF = LocationFacade.getInstance();
        List<Location> list = locF.getList();
        for (Location l : list) {
            masterInventory.add(l.getInventory());
        }
    }

    /**
     * Loads the spinner with the list of locations
     */
    private void populateSpinner() {
        LocationFacade locF = LocationFacade.getInstance();
        Iterable<Location> locs = locF.getList();
        Spinner spinner = findViewById(R.id.spinner3);
        ArrayList<String> names = new ArrayList<>();
        names.add("Global Search");
        for (Location l : locs) {
            names.add(l.getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    /**
     * Loads either specific location's inventory or the global inventory
     * @param parent the parent AdapaterView
     * @param view the current view
     * @param position index
     * @param id id of the item
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String p = (String) parent.getItemAtPosition(position);
        LocationFacade locF = LocationFacade.getInstance();
        List<Location> list = locF.getList();
        for (Location l : list) {
            if (p.equals(l.getName())) {
                locationToBeSearched = l;
                break;
            } else {
                locationToBeSearched = null;
            }
        }
    }

    /**
     * If nothing is selected, do nothing
     * @param parent the parent AdapterView
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /**
     * Loads the desired items
     * @param viewOfTheScreen current View
     */
    public void onSearchClicked(View viewOfTheScreen) {
        view.clear();
        final EditText q = findViewById(R.id.editText8);
        Editable qt = q.getText();
        String query = qt.toString();
        if (locationToBeSearched == null) {
            // do global search
            LocationFacade locF = LocationFacade.getInstance();
            List<Location> list = locF.getList();
            for (Location l : list) {
                locF.setCurrentLocation(l);
                Inventory inv = l.getInventory();
                List<Item> items = inv.getInventory();
                for (Item i : items) {
                    String shorty = i.getShort();
                    if (shorty.contains(query)) {
                        view.add(i);
                    }
                }
            }
        } else {
            // do local search
            Inventory inv = locationToBeSearched.getInventory();
            List<Item> items = inv.getInventory();
            for (Item i : items) {
                String shorty = i.getShort();
                if (shorty.contains(query)) {
                    view.add(i);
                }
            }
        }
        ListView simpleList = findViewById(R.id.listView911);
        ListAdapter customAdapter = new ItemAdapter(getApplicationContext(), view);
        simpleList.setAdapter(customAdapter);
        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?>adapter,View v, int position, long id){
                // Intent intent;
                loadItem(v, position);
            }

        });


    }

    /**
     * Loads the menu for the slide out menu bar
     */
    private void loadMenus(){
        mDrawerLayout = findViewById(R.id.drawer_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.menuhome);

        final NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.inflateMenu(R.menu.home_from_search);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        int id = menuItem.getItemId();
                        if (id == R.id.nav_camera) {
                            reloadHome();
                        } else if (id == R.id.nav_camera1) {
                            goToSearchByCat();
                        }

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });
    }

    /**
     * Loads the home page
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void reloadHome() {
        Intent randomIntent = new Intent(this, Home.class);
        startActivity(randomIntent);
    }

    /**
     * Loads the item category search page
     */
    private void goToSearchByCat() {
        Intent randomIntent = new Intent(this, SearchByCat.class);
        startActivity(randomIntent);
    }

}
