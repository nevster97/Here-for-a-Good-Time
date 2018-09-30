package com.example.kory.donationtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void onButtonPress1(View view) {
        // Create an Intent to start the second activity
        Intent randomIntent = new Intent(this, SecondActivity.class);

        // Start the new activity.
        startActivity(randomIntent);
    }

    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
        Intent randomIntent = new Intent(this, MainActivity.class);
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
