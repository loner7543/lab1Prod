package com.lab1prod;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class CustomActivity extends AppCompatActivity {
    private static final String MainActivityKey = "MainActivity";
    private static final String NUMBERS = "Numbers";
    private static final  String CALLS = "Calls";
    private static final String GROUPS = "Groups";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        setTitle(R.string.custom_activity);

        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);

        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("tag1");
        tabSpec.setContent(R.id.linearLayout);
        TabHost.TabSpec tabSpec1 = tabSpec.setIndicator(NUMBERS);
        //tabSpec1.setIndicator(View v);
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag2");
        tabSpec.setContent(R.id.linearLayout2);
        TabHost.TabSpec tabSpec2 = tabSpec.setIndicator(CALLS);
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag3");
        tabSpec.setContent(R.id.linearLayout3);
        TabHost.TabSpec tabSpec3 = tabSpec.setIndicator(GROUPS);
        tabHost.addTab(tabSpec);
        tabHost.setCurrentTab(0);
    }
}
