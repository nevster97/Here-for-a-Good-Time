package com.example.kory.donationtracker.Controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kory.donationtracker.Models.LocationClasses.Address;
import com.example.kory.donationtracker.Models.LocationClasses.InventoryClasses.Item;
import com.example.kory.donationtracker.Models.LocationClasses.Location;
import com.example.kory.donationtracker.Models.LocationClasses.LocationFacade;
import com.example.kory.donationtracker.Models.LocationClasses.LocationType;
import com.example.kory.donationtracker.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> data;
    ArrayList<String> address;
    ArrayList<String> type;
    LayoutInflater inflter;

    public CustomAdapter(Context applicationContext) {
        this.context = context;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        LocationFacade locFacade = LocationFacade.getInstance();
        ArrayList<Location> locList = (ArrayList) locFacade.getList();
        return locList.size();
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
        LocationFacade locFacade = LocationFacade.getInstance();
        ArrayList<Location> locList = (ArrayList) locFacade.getList();
        Location l = locList.get(i);
        view = inflter.inflate(R.layout.item, null);
        TextView name = (TextView) view.findViewById(R.id.textView10);
        name.setText(l.getName());
        TextView address = (TextView) view.findViewById(R.id.textView11);
        // Address a = l.getAddress();
        // address.setText(a.toString());
        address.setText(l.getAddress());
        TextView type = (TextView) view.findViewById(R.id.textView12);
        LocationType t = l.getType();
        type.setText(t.getStringType());
        return view;
    }

}
