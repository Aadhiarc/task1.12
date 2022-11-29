package com.example.tak1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/* loaded from: classes3.dex */
public class customAdapter extends ArrayAdapter<modelClass> {
    Context context;
    List<modelClass> objects;
    modelClass product;
    int resource;
    TextView textView1;
    TextView textView2;

    public customAdapter(Context context, int resource, List<modelClass> objects) {
        super(context, resource, objects);
        this.context = context;
        this.objects = objects;
        this.resource = resource;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(this.resource, (ViewGroup) null);
        this.textView1 = (TextView) view.findViewById(R.id.firstText);
        this.textView2 = (TextView) view.findViewById(R.id.secondText);
        modelClass modelclass = this.objects.get(position);
        this.product = modelclass;
        this.textView1.setText(modelclass.getTextView1());
        this.textView2.setText(this.product.getTextView2());
        return view;
    }
}
