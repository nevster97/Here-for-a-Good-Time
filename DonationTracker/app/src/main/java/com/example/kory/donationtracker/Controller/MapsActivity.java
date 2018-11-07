package com.example.kory.donationtracker.Controller;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.kory.donationtracker.Models.LocationClasses.Location;
import com.example.kory.donationtracker.Models.LocationClasses.LocationFacade;
import com.example.kory.donationtracker.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    /**
     * Creates the google maps page
     * @param savedInstanceState the current state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Adds the locations to the map
     * @param googleMap the google map
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        // LatLng sydney = new LatLng(-34, 151);
        // mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        for (Location l : LocationFacade.getInstance().getList()) {
            LatLng temp = new LatLng(Double.parseDouble(l.getLat()), Double.parseDouble(l.getLon()));
            mMap.addMarker(new MarkerOptions().position(temp).title(l.getName()).snippet(l.getPhone()));
        }
        LatLng atl = new LatLng(33.7490, -84.3880);
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(atl));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(atl, 10));
    }
}
