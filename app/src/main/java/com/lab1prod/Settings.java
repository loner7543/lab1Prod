package com.lab1prod;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Settings extends AppCompatActivity implements View.OnClickListener {
    private TextView DateTextView;
    private TextView SoundValue;
    private Button ColorButton;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout);
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#222222")));
        setTitle(R.string.settingsActivity);
        SoundValue = (TextView) findViewById(R.id.def_sound);
        DateTextView = (TextView) findViewById(R.id.curr_date);
        ColorButton = (Button) findViewById(R.id.btn_scan_qr);
    }

    public void onSetText(View view){
        DialogFragment dateDialog = new com.lab1prod.DatePicker();
        dateDialog.show(getSupportFragmentManager(), "datePicker");
    }

    //sound dialog
    public void OnInputSound(View view){
        LayoutInflater layoutInflater = LayoutInflater.from(Settings.this);
        View promptView = layoutInflater.inflate(R.layout.text_input_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Settings.this);
        alertDialogBuilder.setView(promptView);

        final EditText editText = (EditText) promptView.findViewById(R.id.edittext);
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SoundValue.setText(editText.getText());
                    }
                })
                .setNegativeButton(R.string.btn_camcel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    //colo dialog
    public void onSetColor(View view){
        LayoutInflater layoutInflater = LayoutInflater.from(Settings.this);
        View promptView = layoutInflater.inflate(R.layout.color_dialog, null);
        final  Button yButton = (Button) promptView.findViewById(R.id.yellow_color);
        final Button rButton = (Button) promptView.findViewById(R.id.red_color);
        yButton.setOnClickListener(this);
        rButton.setOnClickListener(this);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Settings.this);
        alertDialogBuilder.setTitle(R.string.color_dialog);
        alertDialogBuilder.setView(promptView);
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton(R.string.btn_camcel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    @Override
    public void onClick(View view) {
        int c = R.drawable.btn_yellow;
        int r = R.drawable.color_red;
        switch (view.getId()){
            case R.id.yellow_color:{
                ColorButton.setBackgroundResource(c);
                break;
            }
            case R.id.red_color:{
                ColorButton.setBackgroundResource(r);
                break;
            }
        }
    }
}
