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
import com.example.kory.donationtracker.Models.LocationClasses.InventoryClasses.ItemType;
import com.example.kory.donationtracker.Models.LocationClasses.Location;
import com.example.kory.donationtracker.Models.LocationClasses.LocationFacade;
import com.example.kory.donationtracker.Models.UserClasses.User;
import com.example.kory.donationtracker.Models.UserClasses.UserFacade;
import com.example.kory.donationtracker.R;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> data;
    ArrayList<String> address;
    ArrayList<String> type;
    LayoutInflater inflter;

    public ItemAdapter(Context applicationContext) {
        this.context = context;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        LocationFacade locFacade = LocationFacade.getInstance();
        Location loc = locFacade.getCurrentLocation();
        Inventory arr = loc.getInventory();
        ArrayList<Item> arr1 = (ArrayList) arr.getInventory();
        return arr1.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        UserFacade userFacade = UserFacade.getInstance();
        User user = userFacade.getCurrentUser();
        // Location loc = LocationFacade.getInstance().getLocation(user.get_employeeLocation());
        Location loc = LocationFacade.getInstance().getCurrentLocation();
        Inventory inv = loc.getInventory();
        List<Item> list = inv.getInventory();
        Item p = list.get(i);
        view = inflter.inflate(R.layout.item, null);
        TextView name = (TextView) view.findViewById(R.id.textView10);
        name.setText(p.getShort());
        TextView address = (TextView) view.findViewById(R.id.textView11);
        double temp = p.getValue();
        String temp1 = Double.toString(temp);
        address.setText(temp1);
        TextView type = (TextView) view.findViewById(R.id.textView12);
        String type2 = p.getItemType();
        type.setText(type2);
        return view;
    }

}
