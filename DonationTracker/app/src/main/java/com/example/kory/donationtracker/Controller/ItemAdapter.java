package com.example.kory.donationtracker.Controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kory.donationtracker.Models.LocationClasses.InventoryClasses.Inventory;
import com.example.kory.donationtracker.Models.LocationClasses.InventoryClasses.Item;
import com.example.kory.donationtracker.Models.LocationClasses.Location;
import com.example.kory.donationtracker.Models.LocationClasses.LocationFacade;
import com.example.kory.donationtracker.Models.UserClasses.User;
import com.example.kory.donationtracker.Models.UserClasses.UserFacade;
import com.example.kory.donationtracker.R;

import java.util.ArrayList;
import java.util.List;

class ItemAdapter extends BaseAdapter {
    private Context context;
    ArrayList<String> data;
    ArrayList<String> address;
    ArrayList<String> type;
    private LayoutInflater inflter;
    private ArrayList<Item> itemList;

    /**
     * Constructor for the item adapter
     * @param applicationContext the context of the applicatoin
     * @param itemList1 if this is null, then the item list is gonna be global
     */
    public ItemAdapter(Context applicationContext, ArrayList<Item> itemList1) {
        this.context = context;
        inflter = (LayoutInflater.from(applicationContext));
        itemList = itemList1;
    }

    /**
     * Returns the number of items to generate
     * @return the number of items
     */
    @Override
    public int getCount() {
//        LocationFacade locFacade = LocationFacade.getInstance();
////        Location loc = locFacade.getCurrentLocation();
////        Inventory arr = loc.getInventory();
////
////        ArrayList<Item> arr1 = (ArrayList) arr.getInventory();
        if (itemList == null) {
            ArrayList<Item> arr1 = (ArrayList) LocationFacade.getInstance().getCurrentLocation().getInventory().getInventory();
            return arr1.size();
        } else {
            return itemList.size();
        }
    }

    /**
     * Gets the item at i
     * @param i gets the item at that index
     * @return null object
     */
    @Override
    public Object getItem(int i) {
        return null;
    }

    /**
     * Gets the id of the item at i
     * @param i the index to get the item id
     * @return 0
     */
    @Override
    public long getItemId(int i) {
        return 0;
    }

    /**
     * Loads the item list, either global or specific location, depending on if itemList is null
     * @param i the desired item
     * @param view the current view
     * @param viewGroup the current viewGroup
     * @return view
     */
    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (itemList == null) {
            UserFacade userFacade = UserFacade.getInstance();
            User user = userFacade.getCurrentUser();
            // Location loc = LocationFacade.getInstance().getLocation(user.get_employeeLocation());
            Location loc = LocationFacade.getInstance().getCurrentLocation();
            Inventory inv = loc.getInventory();
            List<Item> list = inv.getInventory();
            Item p = list.get(i);
            view = inflter.inflate(R.layout.item, null);
            TextView name = view.findViewById(R.id.textView10);
            name.setText(p.getShort());
            TextView address = view.findViewById(R.id.textView11);
            double temp = p.getValue();
            String temp1 = Double.toString(temp);
            address.setText(temp1);
            TextView type = view.findViewById(R.id.textView12);
            String type2 = p.getItemType();
            type.setText(type2);
        } else {
            Item p = itemList.get(i);
            view = inflter.inflate(R.layout.item, null);
            TextView name = view.findViewById(R.id.textView10);
            name.setText(p.getShort());
            TextView address = view.findViewById(R.id.textView11);
            double temp = p.getValue();
            String temp1 = Double.toString(temp);
            address.setText(temp1);
            TextView type = view.findViewById(R.id.textView12);
            String type2 = p.getItemType();
            type.setText(type2);
        }

        return view;
    }

}
