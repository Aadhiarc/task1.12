package com.example.tak1;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;


public class customDialogBox {
    public static void alertTheUser(String Title, String Message, Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom_dialog_box);
        TextView TITLE = (TextView) dialog.findViewById(R.id.TITLE);
        TITLE.setText(Title);
        TextView MESSAGE = (TextView) dialog.findViewById(R.id.MESSAGE);
        MESSAGE.setText(Message);
        AppCompatButton OK = (AppCompatButton) dialog.findViewById(R.id.Ok);
        OK.setOnClickListener(new View.OnClickListener() { // from class: com.example.task1.customDialogBox$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
