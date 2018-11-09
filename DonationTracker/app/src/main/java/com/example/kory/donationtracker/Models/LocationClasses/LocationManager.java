package com.example.kory.donationtracker.Models.LocationClasses;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.example.kory.donationtracker.Models.LocationClasses.InventoryClasses.Item;

class LocationManager {

    private static Map<String, Location> locations;
    private DatabaseReference db = FirebaseDatabase.getInstance().getReference("locations");

    /**
     * constructs the location manager
     */
    public LocationManager() {
        locations = new HashMap<>();
    }

    /**
     * adds a location to the location manager
     * @param location the new location
     * @return true if add is successful, otherwise false
     */
    public boolean addLocation(Location location) {
        if (locations.containsKey(location.getAddress())) {
            return false;
        }
        locations.put(location.getAddress(), location);
        db.setValue(locations);
        return true;
    }

    /**
     * removes a location from the manager
     * @param location location to be removed
     */
    public void removeLocation(Location location) {
        locations.remove(location.getAddress());
        db.setValue(locations);
    }

    /**
     * gets a location from a given address
     * @param address the address of the removed location
     * @return the location if found
     */
    public Location getLocation(String address) {
        return locations.get(address);
    }

    /**
     * checks if the manager is empty
     * @return true if the manager is empty
     */
    public boolean checkIfEmpty() {
        return locations.isEmpty();
    }

    /**
     * returns the locations in the manager in list form
     * @return the list of locations
     */
    public ArrayList<Location> getList() {
        return new ArrayList(locations.values());
    }

    /**
     * sends location manager to firebase
     * @param location a location
     */
    public void update(Location location) {
        //db.child(location.getAddress()).setValue(location);
        db.setValue(locations);
    }

    /**
     * adds listener to firebase, provides instructions on how to create locations n stuff
     */
    public void setup() {
        db.addValueEventListener(new ValueEventListener() {
            /**
             * provides instructions on how to create locations from firebase data
             * @param dataSnapshot a dataSnapshot from firebase
             */
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                db.setValue(locations);
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String name = (String) ds.child("name").getValue();
                    String lat = (String) ds.child("lat").getValue();
                    String lon = (String) ds.child("lon").getValue();
                    String addr = (String) ds.child("address").getValue();
                    String type = (String) ds.child("type").getValue();
                    String phone = (String) ds.child("phone").getValue();
                    String website = (String) ds.child("website").getValue();

                    Location loc = new Location(name, lat, lon, addr, type, phone, website);
                    boolean catchThisBoolJuulFool = addLocation(loc);
                    Map<String, Object> map = (Map) ds.child("inventory").getValue();

                    Object obj = map.get("inventory");
                    if (obj != null) {
                        for (Map<String, Object> item : (ArrayList<Map>) obj) {
                            String s = (String) item.get("short");
                            String f = (String) item.get("full");
                            String t = (String) item.get("itemType");
                            String v;

                            if (item.get("value") instanceof Long) {
                                Long num = Long.valueOf((Long) item.get("value"));
                                v = Double.toString(num.doubleValue());
                            } else if (item.get("value") instanceof Double) {
                                Double num = Double.valueOf((Double) item.get("value"));
                                v = Double.toString(num);
                            } else {
                                v = "0";
                            }

                            // this is for my littlest sister <3
                            Item oliviasVariable = new Item(s, f, v, t);
                            loc.addItem(oliviasVariable);

                        }
                    }
                }
            }

            /**
             * this function "handles" firebase's errors
             * @param databaseError the databaseError generated
             */
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // System.out.println("Error -> it's Firebase's fault");
                Log.d("LocationManager.java", "Error -> it's Firebase's fault");
            }
        });
    }
}
