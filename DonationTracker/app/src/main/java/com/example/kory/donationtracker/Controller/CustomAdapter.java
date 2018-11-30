package com.example.kory.donationtracker.Controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kory.donationtracker.Models.LocationClasses.Location;
import com.example.kory.donationtracker.Models.LocationClasses.LocationFacade;
import com.example.kory.donationtracker.Models.LocationClasses.LocationType;
import com.example.kory.donationtracker.R;

import java.util.List;

class CustomAdapter extends BaseAdapter {
    // private Context context;
    // ArrayList<String> data;
    // ArrayList<String> address;
    // ArrayList<String> type;
    private final LayoutInflater inflter;

    /**
     * Constructor for the custom adapter
     * @param applicationContext context of the application
     */
    public CustomAdapter(Context applicationContext) {
        // this.context = context;
        inflter = (LayoutInflater.from(applicationContext));
    }

    /**
     * Gets how many sections inside the listView to create
     * @return size of the listView for the adapter
     */
    @Override
    public int getCount() {
        LocationFacade locFacade = LocationFacade.getInstance();
        List<Location> locList = locFacade.getList();
        return locList.size();
    }

    /**
     * Gets the item
     * @param i the number in the list
     * @return a null object
     */
    @Override
    public Object getItem(int i) {
        return null;
    }

    /**
     * Gets the id of the item
     * @param i the item to get
     * @return 0
     */
    @Override
    public long getItemId(int i) {
        return 0;
    }

    /**
     * Sets each of the textView inside a location to the desired location's information
     * @param i the item in the list to get
     * @param view the current view
     * @param viewGroup the current viewGroup
     * @return the updated view
     */
    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LocationFacade locFacade = LocationFacade.getInstance();
        List<Location> locList = locFacade.getList();
        Location l = locList.get(i);
        view = inflter.inflate(R.layout.item, null);
        TextView name = view.findViewById(R.id.textView10);
        name.setText(l.getName());
        TextView address = view.findViewById(R.id.textView11);
        address.setText(l.getAddress());
        TextView type = view.findViewById(R.id.textView12);
        LocationType t = l.getType();
        type.setText(t.getStringType());
        return view;
    }

}
