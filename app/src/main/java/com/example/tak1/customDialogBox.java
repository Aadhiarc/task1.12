package com.example.tak1;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;

//CUSTOM DIALOG BOX CLASS
public class customDialogBox {
    public static void alertTheUser(String Title, String Message, Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom_dialog_box);
        TextView TITLE = (TextView) dialog.findViewById(R.id.TITLE);
        TITLE.setText(Title);
        TextView MESSAGE = (TextView) dialog.findViewById(R.id.MESSAGE);
        MESSAGE.setText(Message);
        AppCompatButton OK = (AppCompatButton) dialog.findViewById(R.id.Ok);
        OK.setOnClickListener(view -> dialog.dismiss());
        dialog.show();
    }
}
