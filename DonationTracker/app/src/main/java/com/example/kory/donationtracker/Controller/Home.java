package com.example.kory.donationtracker.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.kory.donationtracker.R;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
    }

    public void backToHome(View view) {
        Intent randomIntent = new Intent(this, StartUp.class);
        startActivity(randomIntent);
    }

    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
        Intent randomIntent = new Intent(this, StartUp.class);
        startActivity(randomIntent);
    }
}
