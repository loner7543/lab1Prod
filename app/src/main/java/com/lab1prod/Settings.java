package com.lab1prod;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Calendar;

public class Settings extends AppCompatActivity implements View.OnClickListener {
    private static final String SETTINGS_KEY = "Settings Activity:";
    public static final String APP_PREFERENCES = "mysettings";
    private TextView DateTextView;
    private TextView SoundValue;//на Activity
    private EditText DialogSound;
    private Button ColorButton;
    private ActionBar actionBar;
    private int GlobalColor = 2130837582;//последний выбранный цвет
    private String StoredDate;
    private  DialogFragment dloateDiag;
    private Switch NotificationSwitch;
    private Switch AdvSwitch;
    private android.widget.DatePicker DatePickerAndroid;
    private String CurrentDate;
    private SharedPreferences mSettings;
    private SharedPreferences.Editor editor;
    private String defSound = "la la la";
    private int mn;
    private String textSong = "";
    private boolean dialogState = false;
    private boolean DateDialogState = false;
    private boolean soundDialogState = false;

    private int day;
    private int mounth;
    private int year;
    {
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        mounth = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

    }

    //цвета int
    private static final int rED_Color = 25500;
    private static final  int YELLOW = 24825548;

    private static final int SEA_COLOr = 4225536;//0255169
    private static final int PUrPLE = 25560239;
    private static final int BLUE = 210255;

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
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        mn = mounth+1;
        CurrentDate = day + "." + mn + "." + year;
        DateTextView.setText(CurrentDate);
        if (DialogSound!=null){
            textSong = DialogSound.getText().toString();
        }
    }

    public void onSetText(View view){
        DateDialogState = true;
        onSetDateDialog(day,mounth,year);
        /*dateDialog = new com.lab1prod.DatePicker(DateTextView);
        dateDialog.show(getSupportFragmentManager(), "datePicker");
        dateDialog.setRetainInstance(true);*/
    }

    //sound dialog
    public void OnInputSound(View view){
        soundDialogState = true;
        onShowSoundDialog(SoundValue.getText().toString());
    }

    //colo dialog
    public void onSetColor(View view){
        dialogState = true;
        OnViewColorDialog();
    }

    @Override
    public void onClick(View view) {
        int c = R.color.yellow_color;
            int r = R.color.red_color;
        int sea_color = R.color.sea_color;
        int blue = R.color.blue;
        int purple = R.color.purple;
        switch (view.getId()){
            case R.id.yellow_color:{
                ColorButton.setBackgroundResource(c);
                GlobalColor = c;
                break;
            }
            case R.id.red_color:{
                ColorButton.setBackgroundResource(r);
                //ColorButton.setTag(1,r);
                GlobalColor = r;
                break;
            }
            case R.id.sea_color:{
                ColorButton.setBackgroundResource(sea_color);
               //ColorButton.setTag(2,sea_color);
                GlobalColor = sea_color;
                break;
            }
            case R.id.white_btn:{
                ColorButton.setBackgroundResource(blue);
                //ColorButton.setTag(3,blue);
                GlobalColor = blue;
                break;
            }
            case R.id.purple_btn:{
                ColorButton.setBackgroundResource(purple);
                //ColorButton.setTag(4,purple);
                GlobalColor = purple;
                break;
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {//смотреть что именно не стоит: цвет или дата
        ColorDrawable buttonColor = (ColorDrawable) ColorButton.getBackground();
        int colorId = buttonColor.getColor();
        int red = (colorId >> 16) & 0xFF;
        int green = (colorId >> 8) & 0xFF;
        int blue = (colorId >> 0) & 0xFF;
        String stringColor = String.valueOf(red)+String.valueOf(green)+String.valueOf(blue);
        int CInt = Integer.valueOf(stringColor);
        //int c=Integer.parseInt(ColorButton.getTag().toString());
        /*editor = mSettings.edit();
        editor.clear();
        editor.commit();*/

        //sound
        if (SoundValue.getText().toString()==null){
            outState.putString("StoredSound",mSettings.getString("StoredSound", ""));
        }
        else {
            outState.putString("StoredSound",SoundValue.getText().toString());

            editor = mSettings.edit();
            editor.putString("StoredSound", SoundValue.getText().toString());
            editor.apply();
        }
        outState.putString("textSong",textSong);

        //color
        if (ColorButton.getBackground()==null){
            outState.putInt("Color",mSettings.getInt("Color", 0));
        }
        else
        {
            outState.putInt("Color",CInt);//взять цвет с кнопки квк int
            editor = mSettings.edit();
            editor.putString("Color", SoundValue.getText().toString());
            editor.apply();
        }

        if (DateTextView.getText().toString()==null)
        {
            outState.putString("Date",mSettings.getString("Date",""));
        }
        else
        {
            outState.putString("Date",DateTextView.getText().toString());
            editor = mSettings.edit();
            editor.putString("Date", DateTextView.getText().toString());
            editor.apply();
        }
        //outState.putString("Date",StoredDate);
        Log.d(SETTINGS_KEY,"Data add to bundle");
        //диалоги флаги
        outState.putBoolean("colorDialog",dialogState);
        outState.putBoolean("dateDialog",DateDialogState);
        outState.putBoolean("soundDialog",soundDialogState);

        if (DatePickerAndroid!=null){//если мы не открывали диалог - dp будет null
            outState.putInt("day",DatePickerAndroid.getDayOfMonth());
            outState.putInt("month",DatePickerAndroid.getMonth());
            outState.putInt("year",DatePickerAndroid.getYear());
        }

        super.onSaveInstanceState(outState);
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        int restoreColor = savedInstanceState.getInt("Color");
        String restoreDate = savedInstanceState.getString("Date");
        String restoreSound = savedInstanceState.getString("StoredSound");

        textSong = savedInstanceState.getString("textSong");
        soundDialogState = savedInstanceState.getBoolean("soundDialog");
        if (soundDialogState){
            onShowSoundDialog(textSong);
        }
        SoundValue.setText(restoreSound);
        switch (restoreColor){
            case rED_Color:{
                ColorButton.setBackgroundResource(R.color.red_color);
                break;
            }
            case YELLOW:{
                ColorButton.setBackgroundResource(R.color.yellow_color);
                break;
            }
            case PUrPLE:{
                ColorButton.setBackgroundResource(R.color.purple);
                break;
            }
            case SEA_COLOr:{
                ColorButton.setBackgroundResource(R.color.sea_color);
                break;
            }
            case BLUE:{
                ColorButton.setBackgroundResource(R.color.blue);
                break;
            }
        }
        DateTextView.setText(restoreDate);
        Log.d(SETTINGS_KEY,"Reset sucs");
        dialogState = savedInstanceState.getBoolean("colorDialog");
        if (dialogState)
        {
            OnViewColorDialog();
        }
        DateDialogState = savedInstanceState.getBoolean("dateDialog");
        int savedDay = savedInstanceState.getInt("day");
        int savedMonth = savedInstanceState.getInt("month");
        int savedYear = savedInstanceState.getInt("year");
        if (DateDialogState){
            onSetDateDialog(savedDay,savedMonth,savedYear);
        }
        super.onRestoreInstanceState(savedInstanceState);
    }

    private void OnViewColorDialog(){
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
                .setNegativeButton(R.string.btn_ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialogState = false;
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    public void onSetDateDialog(final int d,final int m,final int y){
        LayoutInflater layoutInflater = LayoutInflater.from(Settings.this);
        View promptView = layoutInflater.inflate(R.layout.date_dialog, null);
        DatePickerAndroid = (android.widget.DatePicker) promptView.findViewById(R.id.datePicker);
        DatePickerAndroid.init(y,m,d,null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Settings.this);
        alertDialogBuilder.setView(promptView);
        alertDialogBuilder.setCancelable(false)
               /* .setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        int day = DatePickerAndroid.getDayOfMonth();
                        int month = DatePickerAndroid.getMonth() + 1;
                        int year = DatePickerAndroid.getYear();
                        StoredDate = day + "." + month + "." + year;
                        DateTextView.setText(StoredDate);
                        DateDialogState = false;
                    }
                })*/
                .setNegativeButton(R.string.btn_ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                int day = DatePickerAndroid.getDayOfMonth();
                                int month = DatePickerAndroid.getMonth() + 1;
                                int year = DatePickerAndroid.getYear();
                                StoredDate = day + "." + month + "." + year;
                                DateTextView.setText(StoredDate);
                                DateDialogState = false;
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    public void onShowSoundDialog(final String song){
        LayoutInflater layoutInflater = LayoutInflater.from(Settings.this);
        View promptView = layoutInflater.inflate(R.layout.text_input_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Settings.this);
        alertDialogBuilder.setView(promptView);

        DialogSound  = (EditText) promptView.findViewById(R.id.edittext);
        DialogSound.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(i==KeyEvent.KEYCODE_ENTER){
                    textSong =DialogSound.getText().toString();
                    return true;
                }
                return false;
            }
        });
        DialogSound.setText(song);
        alertDialogBuilder.setCancelable(false)
                .setNegativeButton(R.string.btn_ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                SoundValue.setText(DialogSound.getText());//на аквивити
                                textSong = DialogSound.getText().toString();
                                soundDialogState = false;
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }
}
