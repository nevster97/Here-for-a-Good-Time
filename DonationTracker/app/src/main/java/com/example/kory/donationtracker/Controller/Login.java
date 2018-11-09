package com.example.kory.donationtracker.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.kory.donationtracker.Models.UserClasses.UserFacade;

import com.example.kory.donationtracker.R;

/**
 * login activity
 */
public class Login extends AppCompatActivity {

    /**
     * Creates the login page
     * @param savedInstanceState the current state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    /**
     * Loads the opening screen
     * @param view Current View
     */
    public void toStartUp(View view) {
        // Create an Intent to start the second activity
        Intent randomIntent = new Intent(this, StartUp.class);

        // Start the new activity.
        startActivity(randomIntent);

    }

    /**
     * Logs the user in and loads the home page
     * @param view Current view
     */
    public void onLoginClick(View view) {

        final EditText ET_username = findViewById(R.id.editText);
        final EditText ET_password = findViewById(R.id.editText3);
        String username = ET_username.getText().toString();
        String password = ET_password.getText().toString();

        UserFacade facade = UserFacade.getInstance();
        if (facade.login(username, password)) {
            Intent randomIntent = new Intent(this, Home.class);
            startActivity(randomIntent);
        } else {
            Toast myToast = Toast.makeText(this, "Incorrect Username/Password!",
                    Toast.LENGTH_SHORT);
            myToast.show();
        }

    }

    /**
     * Loads the opening screen
     * @param view Current view
     */
    public void onButtonPress4(View view) {
        Intent randomIntent = new Intent(this, StartUp.class);
        startActivity(randomIntent);
    }



}

