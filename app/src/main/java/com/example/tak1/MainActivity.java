package com.example.tak1;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.format.Formatter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import com.google.gson.JsonObject;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity  implements LocationListener  {
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
     LocationManager locationManager;
     //USER PROFILE MODEL
    UserProfileModel userProfileModel;
    //ON CREATE METHOD
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            //API CALL
            apiCall();
            //VIEWS INITIALIZATION
            initialization();
            //ON CLICK OF VIEWS
            onClickMethods();
            //TO GET THE USER LOCATION
            try {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        getLocation();
                        handler.postDelayed(this, 1000); //now is every 2 minutes
                    }
                }, 1000); //Every 120000 ms (2 minutes)
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    //API IMPLEMENTATION INTO THE VIEWS
    private void implementationApi() {
        fullName.setText(userProfileModel.getFirstName());
        email.setText(userProfileModel.getEmail());
    }


    //ON CLICK OF VIEWS
    private void onClickMethods() {
        try {
            next.setOnClickListener(view -> {
                //FOR VALIDATING EACH VIEWS
                validation();

                //TO SEE THE MOBILE DATA
                System.out.println(InsertMobileParameters(getApplicationContext()));


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
            fullName = findViewById(R.id.fullNameEditText);
            email = findViewById(R.id.emailEditText);
            annualIncome = findViewById(R.id.annualIncomeEditText);
            education = findViewById(R.id.educationSpinner);
            occupation = findViewById(R.id.occupationSpinner);
            maritalStatus = findViewById(R.id.maritalStatusEditText);
            city = findViewById(R.id.cityEditText);
            pinCode = findViewById(R.id.pinCodeEditText);
            next = findViewById(R.id.continueBtn);

        } catch (Exception e) {
            //EXCEPTION
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
                //REGEX PATTERN FOR PIN_CODE VALIDATION
            } else if (this.pinCode.getText().toString().matches("[1-9][0-9]{5}|[1-9][0-9]{3}\\\\s[0-9]{3}")) {
                Toast.makeText(this, "validation done successfully", Toast.LENGTH_SHORT).show();
            } else {
                customDialogBox.alertTheUser(getString(R.string.pincodeDialogTag1), getString(R.string.pincodeDialogValue1), this);
            }
        } catch (Exception e) {
            //EXCEPTION
            e.printStackTrace();
        }
    }


     //TO GET MOBILE INFORMATION
     public static String InsertMobileParameters(Context context) {
         try
         {

             String rooteddevice;
             if(RootUtil.isDeviceRooted())
             {
                 rooteddevice = "1";
             }
             else
             {
                 rooteddevice = "0";
             }
             String androidOS = Build.VERSION.RELEASE;

             String model = Build.MANUFACTURER + " - " + Build.MODEL;
             @SuppressLint("HardwareIds")
             final String uniqueId = Settings.Secure.getString(context.getContentResolver(),
                     Settings.Secure.ANDROID_ID);
             SharedPreferences locationPref = context.getSharedPreferences("LocationPref", MODE_PRIVATE);
             final String address1 = locationPref.getString("Latitude", null) + "," + locationPref.getString("Longitude", null);
             //TO GET IN ADDRESS OF THE USER
             WifiManager wm = (WifiManager) context.getApplicationContext().getSystemService(WIFI_SERVICE);
             String ipaddress = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
             //TO CONVERT STRING TO JSON OBJECTS
             JsonObject mobileParameters = new JsonObject();
             mobileParameters.addProperty("imeino1", uniqueId);
             System.out.println(uniqueId);
             mobileParameters.addProperty("imeino2", (String) null);
             mobileParameters.addProperty("timezone", TimeZone.getDefault().getDisplayName());
             mobileParameters.addProperty("currentdatetime", java.text.DateFormat.getDateTimeInstance().format(new Date()));
             mobileParameters.addProperty("Address", address1);
             mobileParameters.addProperty("latitude", locationPref.getString("Latitude", null));
             mobileParameters.addProperty("longitude", locationPref.getString("Longitude", null));
             mobileParameters.addProperty("IpAddress", ipaddress);
             mobileParameters.addProperty("mobileType", "Android");
             mobileParameters.addProperty("fireBaseuserid", "123456");
             mobileParameters.addProperty("mobileModel", model);
             mobileParameters.addProperty("mobileOSVersion", androidOS);
             mobileParameters.addProperty("appVersion", "1.0.6");
             mobileParameters.addProperty("IsJailBroken", rooteddevice);
             return mobileParameters.toString();
         }
         catch (Exception ex)
         {
             ex.printStackTrace();
             return "";
         }
     }


     //TO GET THE USER LOCATION
     public void getLocation() {
         try {
             //ASKING PERMISSION FOR LOCATION ACCESS
             if (Build.VERSION.SDK_INT >= 23) {
                 if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        //TO GET USER LOCATION
                     locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                     locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000L, 5F, (LocationListener) this);

                 } else {
                     //TO REQUEST LOCATION PERMISSION TO USER
                     ActivityCompat.requestPermissions(this, new String[]{
                             Manifest.permission.ACCESS_FINE_LOCATION,
                             Manifest.permission.ACCESS_COARSE_LOCATION,}, 1);
                 }
             }

         } catch (SecurityException ex) {
             ex.printStackTrace();
         }
         catch (Exception ex) {
             ex.printStackTrace();
         }
     }

     //WHEN THE USER LOCATION CHANGED
    @Override
    public void onLocationChanged(@NonNull Location location) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                Geocoder geocoder;
                List<Address> addresses;
                geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                System.out.println(latitude);
                System.out.println(longitude);
                SharedPreferences sharedpreferences = getSharedPreferences("LocationPref", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("Latitude", String.valueOf(latitude));
                editor.putString("Longitude", String.valueOf(longitude));
                editor.commit();
                try {

                    addresses = geocoder.getFromLocation(latitude, longitude, 1);

                    if (addresses != null && addresses.size() > 0) {
                        Address address = addresses.get(0);
                        String addressDet = address.getAddressLine(0);
                        SharedPreferences locashared = getSharedPreferences("LocationCurrent", MODE_PRIVATE);
                        SharedPreferences.Editor editorloca = locashared.edit();
                        editorloca.putString("Address", addressDet);
                        editorloca.commit();

                    }

                } catch (IOException ex) {
                    // TODO Auto-generated catch block
                    ex.printStackTrace();
                }

            }
        });
    }

    void apiCall() {
       Thread thread = new Thread(() -> {
           String postURL = getString(R.string.apiURL);
           final MediaType json
                   = MediaType.parse("application/json; charset=utf-8");
           //BUILD A OKHTTP CLIENT
           OkHttpClient client = new OkHttpClient.Builder()
                   .connectTimeout(120, TimeUnit.SECONDS)
                   .writeTimeout(120, TimeUnit.SECONDS)
                   .readTimeout(120, TimeUnit.SECONDS)
                   .build();
           JsonObject Details = new JsonObject();
           String insertString = Details.toString();
           RequestBody body = RequestBody.create(json, insertString);
           Request request = new Request.Builder()
                   .url(postURL)
                   .get()
                   .header("fingerprint", "56695a532918188a")
                   .header("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IjE4NTcwMzk5QzM0MjlDMUFDNjk3MTk5MzZCNDI3Q0Y5OUU2MDExQUQiLCJ0eXAiOiJKV1QifQ.eyJzZXNzaW9uSUQiOiJkYTdjM2U5ZC00YzNkLTQ4MjQtODJkMi1lMDU0Zjg5NmM4ZTUiLCJuYmYiOjE2Njk4MTQ2MDMsImV4cCI6MTY2OTgxODIwMywiaWF0IjoxNjY5ODE0NjAzLCJpc3MiOiJCQzcyRTczQUNBQkY0NzcyOEE3RUQ2MTlDREM3OUMwMSIsImF1ZCI6IjRENTIxMkI3QzA3NTQ0OTJCNjZDRDNCRDM1QzFGNzJBIn0.40tjld26YC5j67aox-uMNg20As_mwFp9iwf3AG5fo9ZFyQ-sxFmL_rzSTn5TTbNNX84lVpp2LmgT6NQF97wYC55V3mIpCzWnn-kamixK7iviTvB3VkzcdmJ7zrzA2XvIe4ZsYsIoD7HZG3mQ3r3UX6TSflISdqhQdaGCL1cuxyjxhScmDL4BCji6lCMLr_jOKGvmZYS1-71n8HRkCCmDr0YocR1nS3UjXzTx15Mv62Rj6jrFh3sz4pVSlszWt__hucEnpD6xs_RuwreaPIS_DNqHJPZLnLQPxPF5planLj_VpGqIw--ULM2Tl47o4SatNPKBG9MJElGk6a_TVYjjJaozGJb6NDmlrK6piwTs-D8y6BmGXetygdj4aMS_h2HFVToeBXC28bbblIuoM1vVa3x-E0NBKQltXGgDKvi3vrYEZFa37kOWHvGuuOlKpoOy3fRQmRE2juMA6OMOqyUm1kEnKaGj6TmE2FMOx3fk3AVyuWS-nTF6Np_AOet8ybUqySU_HlKu6qfAt8h9G_CUcMy6GfVC_CSgNzH73mgysf1n-xuFAnktlAUxfwJemVfXIjbjt281I3nzQQwTE1EkaErXVt0HJZHFevEA3WU0cxst6Cb5rOW6gc9CBLMh-QAhl-seTkVG3jc_pxhYdH5LzJbf9Y3NM3gq9778fRm0or4")
                   .header("clientinfo", "{\"deviceID\":\"56695a532918188a\",\"deviceID2\":\"56695a532918188a\",\"deviceTimeZone\":\"India Standard Time\",\"deviceDateTime\":\"30-Nov-2022 15:23:53\",\"deviceIpAddress\":\"0.0.0.0\",\"deviceLatitude\":\"12.7091008\",\"deviceLongitude\":\"77.8310627\",\"deviceType\":\"Android\",\"deviceModel\":\"realme - RMX3241\",\"deviceVersion\":\"12\",\"deviceUserID\":\"123456\",\"deviceAppVersion\":\"1.0.6\",\"deviceIsJailBroken\":false}")
                   .post(body)
                   .build();

           Response staticResponse = null;

           try {

               staticResponse = client.newCall(request).execute();
               int statuscode = staticResponse.code();
               if (statuscode == 401) {
                   runOnUiThread(new Runnable() {
                       @Override
                       public void run() {
                           System.out.println("error");
                           return;
                       }
                   });
               }
               else if(statuscode==200){
                   String response=staticResponse.body().string();
                   JSONObject obj = new JSONObject(response).getJSONObject("rObj");
                   JSONObject USER=obj.getJSONObject("getUserProfile");
                  String firstName= USER.getString("firstName");
                  String lastName= USER.getString("lastName");
                  String email= USER.getString("email");
                  String profileID=USER.getString("profileID");
                  String userProfile= USER.getString("isUserProfile");
                   //ADD INTO USER PROFILE MODEL
                   userProfileModel=new UserProfileModel(firstName,lastName,email,profileID,userProfile);
                   //API DATA IMPLEMENTATION
                   implementationApi();

               }
               //IN CASE OF RESPONSE ERROR
               else if(statuscode==500){
                   String response=staticResponse.body().string();
                   // Extracting the message from the api response.
                   JSONObject obj = new JSONObject(response).getJSONObject("rmsg");
                   customDialogBox.alertTheUser(getString(R.string.error_api_tag), getString(R.string.error_tag_value),getApplicationContext());
               } else {
                   // Alert the user if there is any another response with default message.




               }
           }catch (Exception e){

           }
       });
        thread.start();
    }
    }