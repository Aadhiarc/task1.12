package com.example.tak1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class profileActivity extends AppCompatActivity {
    //VIEW DECLARATION
    Button logout;

    //ON CREATE METHOD
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //METHOD INITIALIZATION
        initialization();
        //METHOD MOVE TO NEXT ACTIVITY
        nextActivity();
    }

    //METHOD MOVE TO NEXT ACTIVITY
    private void nextActivity() {
        Intent intent =new Intent(profileActivity.this,bottomNavigationView.class);
        startActivity(intent);
    }

    //METHOD INITIALIZATION VIEWS
    private void initialization() {
        logout=findViewById(R.id.logout);
    }
}
