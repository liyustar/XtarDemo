package com.xtar.demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;

public class DialogActivity extends Activity implements OnKeyListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        createDialog().show();
    }

    private Dialog createDialog() {
        // This example shows how to add a custom layout to an AlertDialog
        LayoutInflater factory = LayoutInflater.from(this);
        final View textEntryView = factory.inflate(R.layout.alert_dialog_passwd_entry, null);
        return new AlertDialog.Builder(DialogActivity.this)
                .setIconAttribute(android.R.attr.alertDialogIcon)
                .setInverseBackgroundForced(false)
                .setTitle("Please enter the password.").setView(textEntryView)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        /* User clicked OK so do some stuff */
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        /* User clicked cancel so do some stuff */
                    }
                }).setOnKeyListener(DialogActivity.this).create();

    }

    @Override
    public boolean onKey(DialogInterface arg0, int keycode, KeyEvent arg2) {
        boolean ok = false;
        Log.i("DialogKey", "keycode:" + keycode + "  KeyEvent:" + arg2);
        switch (keycode) {
            case KeyEvent.KEYCODE_BACK:
                ok = true;
                break;
        }
        return ok;
    }
}
