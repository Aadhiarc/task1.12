package com.example.tak1;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //VIEWS DECLARATION
    EditText annualIncome;
    EditText city;
    Spinner education;
    EditText email;
    EditText fullName;
    EditText maritalStatus;
    Button next;
    Spinner occupation;
    EditText pinCode;

    //ON CREATE METHOD
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            //VIEWS INITIALIZATION
            initialization();
            //ON CLICK OF VIEWS
            onClickMethods();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    //ON CLICK OF VIEWS
    private void onClickMethods() {
        try {
            next.setOnClickListener(view -> {
                //FOR VALIDATING EACH VIEWS
                validation();
            });
            next.setOnLongClickListener(view -> {
                //CREATING A INTENT TO MOVE RADIO BUTTON ACTIVITY CLASS
                Intent intent = new Intent(MainActivity.this, radio_button.class);
                startActivity(intent);
                return true;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //VIEWS INITIALIZATION METHOD
    private void initialization() {
        try {
            fullName = (EditText) findViewById(R.id.fullNameEditText);
            email = (EditText) findViewById(R.id.emailEditText);
            annualIncome = (EditText) findViewById(R.id.annualIncomeEditText);
            education = (Spinner) findViewById(R.id.educationSpinner);
            occupation = (Spinner) findViewById(R.id.occupationSpinner);
            maritalStatus = (EditText) findViewById(R.id.maritalStatusEditText);
            city = (EditText) findViewById(R.id.cityEditText);
            pinCode = (EditText) findViewById(R.id.pinCodeEditText);
            next = (Button) findViewById(R.id.continueBtn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //METHOD FOR VIEW VALIDATION
    private void validation() {
        try {
            if (this.fullName.getText().toString().isEmpty()) {
                //CUSTOM DIALOG BOX
                customDialogBox.alertTheUser(getString(R.string.nameDialogBoxTag), getString(R.string.fullnameDialogValue), this);
                //REGEX PATTERN FOR NAME VALIDATION
            } else if (!this.fullName.getText().toString().matches("[a-zA-Z]{2,}")) {
                customDialogBox.alertTheUser(getString(R.string.nameDialogBoxTag1), getString(R.string.fullnameDialogValue1), this);
            } else if (this.email.getText().toString().isEmpty()) {
                customDialogBox.alertTheUser(getString(R.string.emailDialogTag), getString(R.string.emailDialogTagValue), this);
                //REGEX PATTERN FOR EMAIL VALIDATION
            } else if (!this.email.getText().toString().matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
                customDialogBox.alertTheUser(getString(R.string.useremailDialogTag1), getString(R.string.useremailDialogTagvalue1), this);
            } else if (this.annualIncome.getText().toString().isEmpty()) {
                customDialogBox.alertTheUser(getString(R.string.annualDialogTag), getString(R.string.annaualincomeDialogValue), this);
            } else if (this.maritalStatus.getText().toString().isEmpty()) {
                customDialogBox.alertTheUser(getString(R.string.maritalStatusDialogTag), getString(R.string.maritalStatusDialogValue), this);
            } else if (this.city.getText().toString().isEmpty()) {
                customDialogBox.alertTheUser(getString(R.string.cityFieldDialogTag), getString(R.string.cityFieldDialogValue), this);
            } else if (this.pinCode.getText().toString().isEmpty()) {
                customDialogBox.alertTheUser(getString(R.string.pincodeDialogTag), getString(R.string.pincodeDialogValue), this);
                //REGEX PATTERN FOR PINCODE VALIDATION
            } else if (this.pinCode.getText().toString().matches("[1-9][0-9]{5}|[1-9][0-9]{3}\\\\s[0-9]{3}")) {
                Toast.makeText(this, "validation done successfully", Toast.LENGTH_SHORT).show();
            } else {
                customDialogBox.alertTheUser(getString(R.string.pincodeDialogTag1), getString(R.string.pincodeDialogValue1), this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
