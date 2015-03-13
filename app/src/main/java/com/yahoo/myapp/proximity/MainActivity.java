package com.yahoo.myapp.proximity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void customer(View v) {
        Intent i = new Intent(this, UserLandingActivity.class);
        startActivity(i);
    }

    public void owner(View v) {
        Intent i = new Intent(this, StatsActivity.class);
        startActivity(i);
    }
}
