package com.yahoo.myapp.proximity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class ListPlacesActivity extends ActionBarActivity {

    List<String> mPlaces;
    PlacesAdapter mPlacesAdapter;
    ListView mLvPlaces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_places);
        mLvPlaces = (ListView) findViewById(R.id.lvPlaces);
        mPlaces = new ArrayList<String>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Places");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                for (ParseObject parseObject : parseObjects) {
                    if (!mPlaces.contains((String) parseObject.get("Name"))) {
                        mPlaces.add((String) parseObject.get("Name"));
                    }
                    mPlacesAdapter = new PlacesAdapter(ListPlacesActivity.this,
                            R.layout.item_place, mPlaces);
                    mLvPlaces.setAdapter(mPlacesAdapter);
                }
            }
        });

        mLvPlaces.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ListPlacesActivity.this, StatsActivity.class);
                i.putExtra("placetitle", mPlaces.get(position));
                startActivity(i);
            }
        });
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
