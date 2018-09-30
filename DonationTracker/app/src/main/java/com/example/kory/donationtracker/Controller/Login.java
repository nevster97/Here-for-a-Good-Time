package com.example.kory.donationtracker.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kory.donationtracker.R;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

    }

    public void toStartUp(View view) {
        // Create an Intent to start the second activity
        Intent randomIntent = new Intent(this, StartUp.class);

        // Start the new activity.
        startActivity(randomIntent);

    }

    public void onLoginClick(View view) {

        final EditText edit =  (EditText) findViewById(R.id.editText);
        final EditText edit1 =  (EditText) findViewById(R.id.editText3);
        String emailString = (String) edit.getText().toString();
        String emailString1 = (String) edit1.getText().toString();

        if (emailString.equals("user") && emailString1.equals("pass")) {
            Intent randomIntent = new Intent(this, Home.class);
            startActivity(randomIntent);
        } else {
            Toast myToast = Toast.makeText(this, "Incorrect Username/Password!",
                    Toast.LENGTH_SHORT);
            myToast.show();

        }
    }

    public void onButtonPress4(View view) {
        Intent randomIntent = new Intent(this, StartUp.class);
        startActivity(randomIntent);
    }



}

