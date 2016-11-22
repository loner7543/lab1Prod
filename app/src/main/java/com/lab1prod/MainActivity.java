package com.lab1prod;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {

    private static final String AboutInfo = "Labaratory work #1"+"\n"+"Author:alma"+"\n"+"Group:6122"+"\n"+"University:SSAU";
    private Button GoToCustom;
    private Intent intent;
    private Toast toast;
    private AlertDialog.Builder ad;
    private Context context;
    private String[] data = {"one", "two", "three", "four", "five"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        toolbar.setBackground(new ColorDrawable(Color.parseColor("#222222")));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        Spinner spinner = (Spinner) findViewById(R.id.share_spinner);
        spinner.setAdapter(adapter);
        GoToCustom = (Button) findViewById(R.id.CustomActivity);
        GoToCustom.setOnClickListener(this);
        context = MainActivity.this;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean res = false;
        int selectedId = item.getItemId();
        switch (selectedId){
            case R.id.buy:{
                res = true;
                toast = Toast.makeText(getApplicationContext(),R.string.ToastText,Toast.LENGTH_LONG);
                toast.show();
                break;
            }
            case R.id.about:{
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.about_dialog)
                        .setIcon(R.drawable.ic_action_about)
                        .setMessage(AboutInfo)
                        .setCancelable(false)
                        .setNegativeButton("ОК",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();}});
                AlertDialog alert = builder.create();
                alert.show();
                break;
            }
            case R.id.exit:{
                res = true;
                ad = new AlertDialog.Builder(context);
                ad.setTitle(R.string.Exit_header);  // заголовок
                ad.setMessage(R.string.Exit_desc); // сообщение
                ad.setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {
                        finish();
                        System.exit(0);
                    }
                });
                ad.setNegativeButton(R.string.btn_camcel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {
                        Toast.makeText(context, R.string.good_choice, Toast.LENGTH_LONG)
                                .show();
                    }
                });
                ad.show();
                break;
            }
            default:{
                break;
            }
        }
        return res;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {
            intent = new Intent(getApplicationContext(),Settings.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {
        intent = new Intent(getApplicationContext(),CustomActivity.class);
        startActivity(intent);
    }

    public void onCustomPhoneClick(View view){
        Intent intent = new Intent(getApplicationContext(),PhoneActivity.class);
        startActivity(intent);
    }
}
