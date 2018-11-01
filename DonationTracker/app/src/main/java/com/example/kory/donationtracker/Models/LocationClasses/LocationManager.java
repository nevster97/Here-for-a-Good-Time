package com.example.kory.donationtracker.Models.LocationClasses;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.example.kory.donationtracker.Models.LocationClasses.InventoryClasses.Item;
import com.example.kory.donationtracker.Models.LocationClasses.InventoryClasses.Inventory;

public class LocationManager {

    // private static Map<Address, Location> _locations;
    private static Map<String, Location> _locations;
    private FirebaseDatabase database;
    private DatabaseReference ref;

    public LocationManager() {
        _locations = new HashMap<>();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("locations");
    }

    public boolean addLocation(Location location) {
        if (_locations.containsKey(location.getAddress())) {
            return false;
        }
        _locations.put(location.getAddress(), location);
        // uploadToDB();
        ref.setValue(_locations);
        return true;
    }

    public void removeLocation(Location location) {
        _locations.remove(location.getAddress());
        uploadToDB();
    }

    public Location getLocation(String address) {
        return _locations.get(address);
    }

    public boolean checkIfEmpty() {
        return _locations.isEmpty();
    }

    public ArrayList<Location> getList() {
        return new ArrayList(_locations.values());
    }

    public void uploadToDB() {
        // ref.setValue(_locations);
        ref.setValue(_locations);
    }

    public void downloadFromDB() {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                System.out.println(dataSnapshot.toString());
//                System.exit(0);
//                System.out.println(dataSnapshot.getChildren().toString());
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    //System.out.println(ds); //TODO RIGHT HERE
                    String name = (String) ds.child("name").getValue();
                    String lat = (String) ds.child("lat").getValue();
                    String lon = (String) ds.child("lon").getValue();
                    String addr = (String) ds.child("address").getValue();
                    String type = (String) ds.child("type").getValue();
                    String phone = (String) ds.child("phone").getValue();
                    String website = (String) ds.child("website").getValue();

                    // inventory todo
                    Location loc = new Location(name, lat, lon, addr, type, phone, website);
                    boolean catcher = addLocation(loc);


                    Map<String, Object> map = (Map) ds.child("inventory").getValue();
                    loc.setInventory(new Inventory(loc, 0, 0, new ArrayList<Item>()));

                    if (map.get("inventory") != null) {
                        Object obj = map.get("inventory");
                        for (Map<String, Object> map2 : (ArrayList<HashMap>) obj) {

                            String s = (String) map2.get("short");
                            String f = (String) map2.get("full");
                            String t = (String) map2.get("itemType");
                            String v;

                            if (map2.get("value") instanceof Long) {
                                Long l = Long.valueOf((Long) map2.get("value"));
                                v = Double.toString(l.doubleValue());
                            } else if (map2.get("value") instanceof Double) {
                                Double d = Double.valueOf((Double) map2.get("value"));
                                v = Double.toString(d);
                            } else {
                                v = "0";
                            }

                            Item item = new Item(null, s, f, v, t);
                            loc.getInventory().addItem(item);
                            System.gc();
                        }
                        obj = null;
                    }
                    map = null;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
