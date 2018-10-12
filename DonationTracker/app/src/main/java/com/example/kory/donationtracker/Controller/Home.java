package com.example.kory.donationtracker.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.kory.donationtracker.Models.UserClasses.User;
import com.example.kory.donationtracker.Models.UserClasses.UserFacade;
import com.example.kory.donationtracker.R;

public class Home extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        UserFacade facade = UserFacade.getInstance();
        User user = facade.getCurrentUser();
        String name = user.get_name();
        TextView nameView = (TextView)findViewById(R.id.textView6);
        nameView.setText(name);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });
    }

    public void backToHome(View view) {
        // logs the current user out of the system
        UserFacade facade = UserFacade.getInstance();
        facade.logout();

        Intent randomIntent = new Intent(this, StartUp.class);
        startActivity(randomIntent);
    }

    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
        // logs the current user out of the system
        UserFacade facade = UserFacade.getInstance();
        facade.logout();

        Intent randomIntent = new Intent(this, StartUp.class);
        startActivity(randomIntent);
    }
}
