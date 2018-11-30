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
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.kory.donationtracker.Models.LocationClasses.InventoryClasses.Inventory;
import com.example.kory.donationtracker.Models.LocationClasses.InventoryClasses.Item;
import com.example.kory.donationtracker.Models.LocationClasses.InventoryClasses.ItemType;
import com.example.kory.donationtracker.Models.LocationClasses.Location;
import com.example.kory.donationtracker.Models.LocationClasses.LocationFacade;
import com.example.kory.donationtracker.Models.UserClasses.User;
import com.example.kory.donationtracker.Models.UserClasses.UserFacade;
import com.example.kory.donationtracker.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Search by Category Activity
 */
public class SearchByCat extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    // --Commented out by Inspection (11/13/18, 1:04 PM):private UserType ut;
    private List<Inventory> masterInventory;
    private ArrayList<Item> view;
    private Location locationToBeSearched;
    private ItemType itemToBeSearched;

    /**
     * Loads the item category search page
     * @param savedInstanceState the current state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_cat);
        view = new ArrayList<>();
        masterInventory = new ArrayList<>();
        populateGlobalInventoryList();
        populateSpinner();
        populateSpinner1();
        loadMenus();
    }

    /**
     * Loads the item's information
     * @param view the current view
     * @param pos index of the desired item
     */
    private void loadItem(View view, int pos) {
        setContentView(R.layout.activity_select_item);
        loadMenus();
        UserFacade userFacade = UserFacade.getInstance();
        User user = userFacade.getCurrentUser();
        // Location loc = user.get_employeeLocation();
        // Location loc = LocationFacade.getInstance().getLocation(user.getEmployeeLocation());
        LocationFacade locF = LocationFacade.getInstance();
        String addr = user.getEmployeeLocation();
        Location loc = locF.getLocation(addr);
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
     * populates the global inventory
     */
    private void populateGlobalInventoryList() {
        LocationFacade locF = LocationFacade.getInstance();
        List<Location> locs = locF.getList();
        for (Location l : locs) {
            Inventory inv = l.getInventory();
            masterInventory.add(inv);
        }
    }

    /**
     * Populates the spinner with location names
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
     * Populates this spinner with itemTypes
     */
    private void populateSpinner1() {
        LocationFacade locF = LocationFacade.getInstance();
        ArrayList<Location> locs = (ArrayList<Location>) locF.getList();
        Spinner spinner = findViewById(R.id.spinner4);
        ArrayAdapter<String> adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, ItemType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    /**
     * Sets search to either global or specific location
     * @param parent the parent AdapterView
     * @param view current view
     * @param position the index
     * @param id the id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner) parent;
        if(spinner.getId() == R.id.spinner3) {
            String p = (String) parent.getItemAtPosition(position);
            LocationFacade locF = LocationFacade.getInstance();
            List<Location> locations = locF.getList();
            for (Location l : locations) {
                if (p.equals(l.getName())) {
                    locationToBeSearched = l;
                    break;
                } else {
                    locationToBeSearched = null;
                }
            }
        } else if (spinner.getId() == R.id.spinner4) {
            ItemType p = (ItemType) parent.getItemAtPosition(position);
            for (ItemType i : ItemType.values()) {
                if (i.equals(p)) {
                    itemToBeSearched = i;
                }
            }
        }
    }

    /**
     * If nothing is selected, do nothing
     * @param parent the parent's AdapterView
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /**
     * Load the desired items
     * @param viewOfTheScreen current view
     */
    public void onSearchClicked(View viewOfTheScreen) {
        view.clear();
        if (locationToBeSearched == null) {
            // do global search
            LocationFacade locF = LocationFacade.getInstance();
            List<Location> locations = locF.getList();
            for (Location l : locations) {
                locF.setCurrentLocation(l);
                Inventory inv = l.getInventory();
                List<Item> listInv = inv.getInventory();
                for (Item i : listInv) {
                    String itemType = i.getItemType();
                    itemType = itemType.toUpperCase();
                    String searched = itemToBeSearched.toString();
                    if (itemType.equals(searched)) {
                        view.add(i);
                    }
                }
            }
        } else {
            // do local search

            Inventory inv = locationToBeSearched.getInventory();
            List<Item> list = inv.getInventory();
            for (Item i : list) {
                String itemType = i.getItemType();
                itemType = itemType.toUpperCase();
                String searched = itemToBeSearched.toString();
                if (itemType.equals(searched)) {
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
     * Load the menus for the slide out menu
     */
    private void loadMenus(){
        mDrawerLayout = findViewById(R.id.drawer_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.menuhome);

        final NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.inflateMenu(R.menu.home_from_search1);
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
                            reloadHome(navigationView);
                        } else if (id == R.id.nav_camera1) {
                            goToSearch();
                        }

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });
    }

    /**
     * Load the home page
     * @param view current view
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void reloadHome(View view) {
        Intent randomIntent = new Intent(this, Home.class);
        startActivity(randomIntent);
    }

    /**
     * Load the original search page
     */
    private void goToSearch() {
        Intent randomIntent = new Intent(this, Search.class);
        startActivity(randomIntent);
    }
}
