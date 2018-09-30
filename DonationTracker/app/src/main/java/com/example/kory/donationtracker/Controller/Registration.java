package com.example.kory.donationtracker.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kory.donationtracker.R;

public class Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    public void toStartUp(View view) {
        Intent randomIntent = new Intent(this, StartUp.class);
        startActivity(randomIntent);
    }

    public void onRegisterClick(View view) {

        final EditText firstName =  (EditText) findViewById(R.id.editText4);
        final EditText lastName =  (EditText) findViewById(R.id.editText7);
        final EditText email =  (EditText) findViewById(R.id.editText6);

        String firstString = (String) firstName.getText().toString();
        String lastString = (String) lastName.getText().toString();
        String emailString = (String) email.getText().toString();

        if (firstString.equals("")) {
            CharSequence text = ("First is null");
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
        } else if (lastString.equals("")) {
            CharSequence text = ("Last is null");
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
        } else if (emailString.equals("")) {
            CharSequence text = ("Email is null");
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
        } else {
            Intent randomIntent = new Intent(this, StartUp.class);
            startActivity(randomIntent);
        }



    }




}
