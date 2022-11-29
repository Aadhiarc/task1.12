package com.example.tak1;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class bottomNavigationView extends AppCompatActivity {
   //VIEWS DECLARATION
    BottomNavigationView bottomNavigationView;


    //ON CREATE METHOD
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation_view);
        //VIEWS INITIALIZATION METHOD
        viewsInitialization();
        //ON CLICK OF VIEWS METHOD
        onClickMethod();
    }

    private void onClickMethod() {
         //WHEN ITEM SELECTED IN THE BOTTOM NAVIGATION BAR
     onItemSelected();
    }

     //VIEWS INITIALIZATION METHOD
    private void viewsInitialization() {
        bottomNavigationView=findViewById(R.id.bottomNavigationView);
    }

    //ON ITEM SELECTED IN THE BOTTOM NAVIGATION BAR
    void onItemSelected(){
        bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.Policiesnav:
                    //MOVE TO TABLE ACTIVITY CLASS
                    Intent intent =new Intent(com.example.tak1.bottomNavigationView.this, Table.class);
                    startActivity(intent);
                    break;

            }
            return false;
        });


    }
}
