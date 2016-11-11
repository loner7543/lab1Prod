package com.lab1prod;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Settings extends AppCompatActivity implements View.OnClickListener {
    private static final String SETTINGS_KEY = "Settings Activity:";
    private TextView DateTextView;
    private TextView SoundValue;
    private Button ColorButton;
    private ActionBar actionBar;
    private int GlobalColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout);
        actionBar = getSupportActionBar();
        actionBar.hide();
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
        final Button seaButton = (Button) promptView.findViewById(R.id.sea_color);
        final Button whiteutton = (Button) promptView.findViewById(R.id.white_btn);
        final Button purpleutton = (Button) promptView.findViewById(R.id.purple_btn);
        whiteutton.setOnClickListener(this);
        seaButton.setOnClickListener(this);
        yButton.setOnClickListener(this);
        rButton.setOnClickListener(this);
        purpleutton.setOnClickListener(this);
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
        int sea_color = R.drawable.btn_sea;
        int blue = R.drawable.btn_white;
        int purple = R.drawable.btn_purple;
        switch (view.getId()){
            case R.id.yellow_color:{
                ColorButton.setBackgroundResource(c);
                GlobalColor = c;
                break;
            }
            case R.id.red_color:{
                ColorButton.setBackgroundResource(r);
                GlobalColor = r;
                break;
            }
            case R.id.sea_color:{
                ColorButton.setBackgroundResource(sea_color);
                GlobalColor = sea_color;
                break;
            }
            case R.id.white_btn:{
                ColorButton.setBackgroundResource(blue);
                GlobalColor = blue;
                break;
            }
            case R.id.purple_btn:{
                ColorButton.setBackgroundResource(purple);
                GlobalColor = purple;
                break;
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("Color",GlobalColor);
        Log.d(SETTINGS_KEY,"Data add to bundle");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        int restoreColor = savedInstanceState.getInt("Color");
        ColorButton.setBackgroundResource(restoreColor);
        Log.d(SETTINGS_KEY,"Reset sucs");
        super.onRestoreInstanceState(savedInstanceState);
    }
}
