package com.yahoo.myapp.proximity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class UserLandingActivity extends ActionBarActivity {

    List<String> mPlaces;
    PlacesAdapter mPlacesAdapter;
    ListView mLvPlaces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_landing);
        mLvPlaces = (ListView) findViewById(R.id.lvPlaces);
        mPlaces = new ArrayList<String>();
        mPlaces.add("Place#1");
        mPlaces.add("Place#2");
        mPlacesAdapter = new PlacesAdapter(this, R.layout.item_place, mPlaces);
        mLvPlaces.setAdapter(mPlacesAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_landing, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}
