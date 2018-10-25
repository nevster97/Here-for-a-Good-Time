package com.example.kory.donationtracker.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.kory.donationtracker.R;

public class StartUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);


    }

    public void toLogin(View view) {
        // Create an Intent to start the second activity
        Intent randomIntent = new Intent(this, Login.class);

        // Start the new activity.
        startActivity(randomIntent);
    }

    public void toRegistration(View view) {
        Intent randomIntent = new Intent(this, Registration.class);
        startActivity(randomIntent);
    }

    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
        Intent randomIntent = new Intent(this, StartUp.class);
        startActivity(randomIntent);
    }

//    /**
//     * Show a toast
//     * @param view -- the view that is clicked
//     */
//    public void toastMe(View view){
//        // Toast myToast = Toast.makeText(this, message, duration);
//        Toast myToast = Toast.makeText(this, "Incorrect Username/Password!",
//                Toast.LENGTH_SHORT);
//        myToast.show();
//    }
}
