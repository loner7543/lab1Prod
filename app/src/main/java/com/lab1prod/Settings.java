package com.lab1prod;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;

import java.util.Calendar;

public class Settings extends AppCompatActivity implements View.OnClickListener {
    private static final String SETTINGS_KEY = "Settings Activity:";
    public static final String APP_PREFERENCES = "mysettings";
    private TextView DateTextView;
    private TextView SoundValue;
    private Button ColorButton;
    private ActionBar actionBar;
    private int GlobalColor;
    private String StoredDate;
    private  DialogFragment dloateDiag;
    private Switch NotificationSwitch;
    private Switch AdvSwitch;
    private android.widget.DatePicker DatePickerAndroid;
    private String CurrentDate;
    private SharedPreferences mSettings;
    private SharedPreferences.Editor editor;
    public static final String APP_PREFERENCES_COUNTER = "counter";

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
        NotificationSwitch = (Switch) findViewById(R.id.notofications_switch);
        NotificationSwitch.setChecked(true);
        AdvSwitch = (Switch) findViewById(R.id.buy_switch);
        AdvSwitch.setChecked(false);
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH)+1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        CurrentDate = day + "." + month + "." + year;
        DateTextView.setText(CurrentDate);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    public void onSetText(View view){
        LayoutInflater layoutInflater = LayoutInflater.from(Settings.this);
        View promptView = layoutInflater.inflate(R.layout.date_dialog, null);
        DatePickerAndroid = (android.widget.DatePicker) promptView.findViewById(R.id.datePicker);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Settings.this);
        alertDialogBuilder.setView(promptView);
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        int day = DatePickerAndroid.getDayOfMonth();
                        int month = DatePickerAndroid.getMonth() + 1;
                        int year = DatePickerAndroid.getYear();
                        StoredDate = day + "." + month + "." + year;
                        DateTextView.setText(StoredDate);
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

        /*dateDialog = new com.lab1prod.DatePicker(DateTextView);
        dateDialog.show(getSupportFragmentManager(), "datePicker");
        dateDialog.setRetainInstance(true);*/
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
        outState.putString("Date",StoredDate);
        Log.d(SETTINGS_KEY,"Data add to bundle");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        int restoreColor = savedInstanceState.getInt("Color");
        ColorButton.setBackgroundResource(restoreColor);
        String restoreDate = savedInstanceState.getString("Date");
        DateTextView.setText(restoreDate);
        Log.d(SETTINGS_KEY,"Reset sucs");
        super.onRestoreInstanceState(savedInstanceState);
    }
}
