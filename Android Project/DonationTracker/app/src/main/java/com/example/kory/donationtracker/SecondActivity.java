package com.example.kory.donationtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

    }

    public void onButtonPress2(View view) {
        // Create an Intent to start the second activity
        Intent randomIntent = new Intent(this, MainActivity.class);

        // Start the new activity.
        startActivity(randomIntent);

    }

    public void onButtonPress3(View view) {

        final EditText edit =  (EditText) findViewById(R.id.editText);
        final EditText edit1 =  (EditText) findViewById(R.id.editText2);
        String emailString = (String) edit.getText().toString();
        String emailString1 = (String) edit1.getText().toString();

        if (emailString.equals("HFAGT") && emailString1.equals("1234")) {
            Intent randomIntent = new Intent(this, ThirdActivity.class);
            startActivity(randomIntent);
        } else {
            Toast myToast = Toast.makeText(this, "Incorrect Username/Password!",
                    Toast.LENGTH_SHORT);
            myToast.show();

        }
    }


}

