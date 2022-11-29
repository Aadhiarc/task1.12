package com.example.tak1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class radio_button extends AppCompatActivity {

    //VIEWS DECLARATION
    Button next;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_button);
        //VIEWS INITIALIZATION METHOD
        initializationMethod();
        //METHOD TO MOVE TO NEXT ACTIVITY
        moveToNextActivity();
    }

    private void moveToNextActivity() {
        this.next.setOnLongClickListener(view -> {
            //METHOD TO MOVE TO ACCORDION VIEW ACTIVITY
             nextActivity();
             return true;
         });
    }

    //METHOD TO MOVE TO ACCORDION VIEW ACTIVITY
    private void nextActivity() {
        Intent intent = new Intent(this, accordionView.class);
        startActivity(intent);
    }

    //VIEWS INITIALIZATION METHOD
    void initializationMethod() {

        this.next = (Button) findViewById(R.id.continueBtn);
    }
}
