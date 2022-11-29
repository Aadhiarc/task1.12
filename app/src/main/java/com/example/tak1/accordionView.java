package com.example.tak1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class accordionView extends AppCompatActivity {

    //DECLARATION
    Button continueBtn;
    RelativeLayout details;
    RelativeLayout details2;
    RelativeLayout details3;
    ImageButton dropDown;
    ImageButton dropDown2;
    ImageButton dropDown3;
    Animation fab_down;
    Animation fab_up;
    boolean isOpen = true;
    boolean isOpen1 = true;
    boolean isOpen2 = true;
    ListView listView;
    ArrayList<modelClass> modelClasses;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accordion_view);
        try {
            //INITIALIZATION METHOD
            initializationMethod();
            //ACCORDION VIEW FUNCTION METHOD
            accordionViewImageBtn();
            //ADAPTER CALLING METHOD
            adapterCalling();
            //MOVE TO NEXT ACTIVITY METHOD
            moveToNextActivity();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

     //METHOD TO MOVE TO PROFILE ACTIVITY
    private void moveToNextActivity() {
        this.continueBtn.setOnLongClickListener(view -> {
            //INTENT TO MOVE TO NEXT ACTIVITY
            Intent intent = new Intent(accordionView.this, profileActivity.class);
            startActivity(intent);
            return false;
        });
    }


      //METHOD FOR ADAPTER CALLING
    private void adapterCalling() {
        ArrayList<modelClass> arrayList = new ArrayList<>();
        modelClasses = arrayList;
        try {
            arrayList.add(new modelClass(getString(R.string.listViewTag1), getString(R.string.listViewTagValue1)));
            modelClasses.add(new modelClass(getString(R.string.listViewTag2), getString(R.string.listViewTagValue2)));
            modelClasses.add(new modelClass(getString(R.string.listViewTag3), getString(R.string.listViewTagValue3)));
            modelClasses.add(new modelClass(getString(R.string.listViewTag4), getString(R.string.listViewTagValue4)));
            modelClasses.add(new modelClass(getString(R.string.listViewTag5), getString(R.string.listViewTagValue5)));
            modelClasses.add(new modelClass(getString(R.string.listViewTag6), getString(R.string.listViewTagValue6)));
            modelClasses.add(new modelClass(getString(R.string.listViewTag17), getString(R.string.listViewTagValue7)));
            modelClasses.add(new modelClass(getString(R.string.listViewTag8), getString(R.string.listViewTagValue8)));
            modelClasses.add(new modelClass(getString(R.string.listViewTag9), getString(R.string.listViewTagValue9)));
            //CUSTOM ADAPTER FOR LIST VIEW
            customAdapter customadapter = new customAdapter(this, R.layout.custom_layout, this.modelClasses);
            //LIST VIEW ADAPTER
            listView.setAdapter(customadapter);
            //TO LIST VIEW HEIGHT DYNAMICALLY
            setListViewHeightBasedOnChildren(listView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

      //METHOD FOR ACCORDION VIEW BUTTON
    private void accordionViewImageBtn() {
        this.fab_up = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_up);
        this.fab_down = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_down);
        try {
            this.dropDown.setOnClickListener(view -> {
                //FIRST IMAGE BUTTON
              accordionViewImageBtn1();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.dropDown2.setOnClickListener(view -> {
                //SECOND IMAGE BUTTON
                accordionViewImageBtn2();
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            this.dropDown3.setOnClickListener(view -> {
                //THIRD IMAGE BUTTON
                accordionViewImageBtn3();
            });
        } catch (Exception e3) {
            e3.printStackTrace();

        }
    }

      //METHOD FOR FIRST ACCORDION VIEW IMAGE BUTTON ANIMATION
    public  void accordionViewImageBtn1() {
        if (isOpen) {
            dropDown.setImageResource(R.drawable.arrow);
            details.setVisibility(View.VISIBLE);
            details.startAnimation(fab_up);
            ViewGroup.LayoutParams params = details.getLayoutParams();
            params.width = -1;
            params.height = -2;
            details.setLayoutParams(params);
            isOpen = false;
            return;
        }
        dropDown.setImageResource(R.drawable.up);
        details.startAnimation(fab_down);
        details.setVisibility(View.INVISIBLE);
        ViewGroup.LayoutParams params2 = details.getLayoutParams();
        params2.width = -1;
        params2.height = 0;
        details.setLayoutParams(params2);
        isOpen = true;
    }

    //METHOD FOR SECOND ACCORDION VIEW IMAGE BUTTON ANIMATION
    public  void  accordionViewImageBtn2(){
        if (isOpen1) {
            dropDown2.setImageResource(R.drawable.arrow);
            details2.setVisibility(View.VISIBLE);
            details2.startAnimation(this.fab_up);
            ViewGroup.LayoutParams params = this.details2.getLayoutParams();
            params.width = -1;
            params.height = -2;
            details2.setLayoutParams(params);
            isOpen1 = false;
            return;
        }
        dropDown2.setImageResource(R.drawable.up);
        details2.startAnimation(fab_down);
        details2.setVisibility(View.INVISIBLE);
        ViewGroup.LayoutParams params2 = details2.getLayoutParams();
        params2.width = -1;
        params2.height = 0;
        details2.setLayoutParams(params2);
        isOpen1 = true;
    }

    //METHOD FOR THIRD ACCORDION VIEW IMAGE BUTTON ANIMATION
    public void accordionViewImageBtn3() {
        if (isOpen2) {
            dropDown3.setImageResource(R.drawable.arrow);
            details3.setVisibility(View.VISIBLE);
            details3.startAnimation(fab_up);
            ViewGroup.LayoutParams params = details3.getLayoutParams();
            params.width = -1;
            params.height = -2;
            details3.setLayoutParams(params);
            isOpen2 = false;
            return;
        }
        dropDown3.setImageResource(R.drawable.up);
        details3.startAnimation(fab_down);
        details3.setVisibility(View.INVISIBLE);
        ViewGroup.LayoutParams params2 = details3.getLayoutParams();
        params2.width = -1;
        params2.height = 0;
        details3.setLayoutParams(params2);
        isOpen2 = true;
    }


    //METHOD FOR INITIALIZATION
    private void initializationMethod() {
        dropDown = (ImageButton) findViewById(R.id.dropDownBtn);
        dropDown2 = (ImageButton) findViewById(R.id.dropDownBtn2);
        dropDown3 = (ImageButton) findViewById(R.id.dropDownBtn3);
        details = (RelativeLayout) findViewById(R.id.details);
        details2 = (RelativeLayout) findViewById(R.id.details2);
        details3 = (RelativeLayout) findViewById(R.id.details3);
        listView = (ListView) findViewById(R.id.listView);
        continueBtn = (Button) findViewById(R.id.continueBtn);
    }

    //METHOD TO CHANGE THE LAYOUT HEIGHT DYNAMICALLY
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = (listView.getDividerHeight() * (listAdapter.getCount() - 1)) + totalHeight;
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
