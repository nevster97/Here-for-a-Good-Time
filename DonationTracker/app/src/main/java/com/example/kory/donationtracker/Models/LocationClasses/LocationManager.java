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
    private static Map<String, Location> locations;
    private DatabaseReference db = FirebaseDatabase.getInstance().getReference("locations");
//    private FirebaseDatabase database;
//    private DatabaseReference ref;

    public LocationManager() {
//        database = FirebaseDatabase.getInstance();
//        ref = database.getReference("locations");
        locations = new HashMap<>();
//        db.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                db.setValue(locations);
//                for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                    String name = (String) ds.child("name").getValue();
//                    String lat = (String) ds.child("lat").getValue();
//                    String lon = (String) ds.child("lon").getValue();
//                    String addr = (String) ds.child("address").getValue();
//                    String type = (String) ds.child("type").getValue();
//                    String phone = (String) ds.child("phone").getValue();
//                    String website = (String) ds.child("website").getValue();
//
//                    Location loc = new Location(name, lat, lon, addr, type, phone, website);
//                    boolean catchThisBoolJuulFool = addLocation(loc);
//                    Map<String, Object> map = (Map) ds.child("inventory").getValue();
//
////                    if (map.get("inventory") != null) {
////                        Object obj = map.get("inventory")
////                    }
//                    Object obj = map.get("inventory");
//                    if (obj != null) {
//                        for (Map<String, Object> item : (ArrayList<Map>) obj) {
//                            String s = (String) item.get("short");
//                            String f = (String) item.get("full");
//                            String t = (String) item.get("itemType");
//                            String v;
//
//                            if (item.get("value") instanceof Long) {
//                                Long num = Long.valueOf((Long) item.get("value"));
//                                v = Double.toString(num.doubleValue());
//                            } else if (item.get("value") instanceof Double) {
//                                Double num = Double.valueOf((Double) item.get("value"));
//                                v = Double.toString(num);
//                            } else {
//                                v = "0";
//                            }
//
//                            // this is for my littlest sister <3
//                            Item oliviasVariable = new Item(s, f, t, v);
//                            loc.addItem(oliviasVariable);
//
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                System.out.println("Error -> it's Firebase's fault");
//            }
//        });
    }

    public boolean addLocation(Location location) {
        if (locations.containsKey(location.getAddress())) {
            return false;
        }
        locations.put(location.getAddress(), location);
        // uploadToDB();
        db.setValue(locations);
        return true;
    }

    public void removeLocation(Location location) {
        locations.remove(location.getAddress());
        db.setValue(locations);
        // db.child(location.getAddress()).setValue(null);
        // uploadToDB();
    }

    public Location getLocation(String address) {
        return locations.get(address);
    }

    public boolean checkIfEmpty() {
        return locations.isEmpty();
    }

    public ArrayList<Location> getList() {
        return new ArrayList(locations.values());
    }

    public void update(Location location) {
        db.child(location.getAddress()).setValue(location);
    }

    public void setup() {
        db.addValueEventListener(new ValueEventListener() {
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

//                    if (map.get("inventory") != null) {
//                        Object obj = map.get("inventory")
//                    }
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

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Error -> it's Firebase's fault");
            }
        });
    }

//    public void uploadToDB() {
//        // ref.setValue(_locations);
//        ref.setValue(_locations);
//    }

//    public void downloadFromDB() {
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
////                System.out.println(dataSnapshot.toString());
////                System.exit(0);
////                System.out.println(dataSnapshot.getChildren().toString());
//                for (DataSnapshot ds : dataSnapshot.getChildren()) {
//
//                    //System.out.println(ds); //TODO RIGHT HERE
//                    String name = (String) ds.child("name").getValue();
//                    String lat = (String) ds.child("lat").getValue();
//                    String lon = (String) ds.child("lon").getValue();
//                    String addr = (String) ds.child("address").getValue();
//                    String type = (String) ds.child("type").getValue();
//                    String phone = (String) ds.child("phone").getValue();
//                    String website = (String) ds.child("website").getValue();
//
//                    // inventory todo
//                    Location loc = new Location(name, lat, lon, addr, type, phone, website);
//                    boolean catcher = addLocation(loc);
//
//
//                    Map<String, Object> map = (Map) ds.child("inventory").getValue();
//                    loc.setInventory(new Inventory(loc, 0, 0, new ArrayList<Item>()));
//
//                    if (map.get("inventory") != null) {
//                        Object obj = map.get("inventory");
//                        for (Map<String, Object> map2 : (ArrayList<HashMap>) obj) {
//
//                            String s = (String) map2.get("short");
//                            String f = (String) map2.get("full");
//                            String t = (String) map2.get("itemType");
//                            String v;
//
//                            if (map2.get("value") instanceof Long) {
//                                Long l = Long.valueOf((Long) map2.get("value"));
//                                v = Double.toString(l.doubleValue());
//                            } else if (map2.get("value") instanceof Double) {
//                                Double d = Double.valueOf((Double) map2.get("value"));
//                                v = Double.toString(d);
//                            } else {
//                                v = "0";
//                            }
//
//                            Item item = new Item(null, s, f, v, t);
//                            loc.getInventory().addItem(item);
//                            System.gc();
//                        }
//                        obj = null;
//                    }
//                    map = null;
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//    }
}
