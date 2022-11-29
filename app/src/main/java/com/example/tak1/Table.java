package com.example.tak1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Table extends AppCompatActivity {

    //ON CREATE METHOD
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabel);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //MOVE TO NEXT ACTIVITY
        Intent intent=new Intent(Table.this,profileActivity.class);
        startActivity(intent);
    }
}