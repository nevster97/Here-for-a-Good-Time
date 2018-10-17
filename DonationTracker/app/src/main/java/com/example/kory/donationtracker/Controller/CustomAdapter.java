package com.example.kory.donationtracker.Controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kory.donationtracker.R;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> data;
    ArrayList<String> address;
    ArrayList<String> type;
    LayoutInflater inflter;

    public CustomAdapter(Context applicationContext, ArrayList<String> s, ArrayList<String> a, ArrayList<String> t) {
        this.context = context;
        this.data = s;
        this.address = a;
        this.type = t;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return data.size();
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
        view = inflter.inflate(R.layout.item, null);
        TextView country = (TextView) view.findViewById(R.id.textView10);
        country.setText(data.get(i));
        TextView country1 = (TextView) view.findViewById(R.id.textView11);
        country1.setText(address.get(i));
        TextView country2 = (TextView) view.findViewById(R.id.textView12);
        country2.setText(type.get(i));
        return view;
    }

}
