package com.lab1prod;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by Александр on 17.10.2016.
 */

public class DatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    public String DateText;
    private TextView TextView;
    public DatePicker(TextView textView){
       this.TextView = textView;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        Dialog picker = new DatePickerDialog(getActivity(), this,year, month, day);
        picker.setTitle(getResources().getString(R.string.choose_date));
        return picker;
    }

    @Override
    public void onStart() {
        super.onStart();
        Button nButton =  ((AlertDialog) getDialog()).getButton(DialogInterface.BUTTON_POSITIVE);
        nButton.setText(getResources().getString(R.string.ready));
    }

    @Override
    public void onDateSet(android.widget.DatePicker datePicker, int year, int month, int day) {
        int mn = month+1;
        DateText = day + "." + mn + "." + year;
        TextView.setText(day + "." + mn + "." + year);
    }

  /* @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("Data",DateText);
        super.onSaveInstanceState(outState);
    }*/
    public String getDTAASTring(){return DateText;}
}
