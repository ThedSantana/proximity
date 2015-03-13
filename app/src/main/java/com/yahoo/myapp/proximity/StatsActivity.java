package com.yahoo.myapp.proximity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.w3c.dom.Text;


public class StatsActivity extends ActionBarActivity {

    View mLayoutAddGeofence;
    private GraphicalView mChart;

    private XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset();

    private XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();

    private XYSeries mCurrentSeries;

    private XYSeriesRenderer mCurrentRenderer;

    private int mCount = 30;

    private TextView mTvPlaceTitle;

//    /** Colors to be used for the pie slices. */
//    private static int[] COLORS = new int[] { Color.GREEN, Color.BLUE, Color.MAGENTA, Color.CYAN };
//    /** The main series that will include all the data. */
//    private CategorySeries mSeries = new CategorySeries("");
//    /** The main renderer for the main dataset. */
//    private DefaultRenderer mRenderer1 = new DefaultRenderer();

    private void initChart() {
        mCurrentSeries = new XYSeries("People Data");
        mDataset.addSeries(mCurrentSeries);
        mCurrentRenderer = new XYSeriesRenderer();
        mRenderer.setBarWidth(50);
        mRenderer.setAxesColor(Color.WHITE);
        mRenderer.setScale(1);
        mRenderer.addSeriesRenderer(mCurrentRenderer);
    }

    private void addSampleData() {
        mCurrentSeries.add(1, 50); //max on left border
        mCurrentSeries.add(2, mCount);
        mCurrentSeries.add(3, 1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.chart);
        if (mChart == null) {
            initChart();
            addSampleData();
            mChart = ChartFactory.getBarChartView(this, mDataset, mRenderer, BarChart.Type.DEFAULT);
            frameLayout.addView(mChart);
        } else {
            mChart.repaint();
        }
        mTvPlaceTitle = (TextView) findViewById(R.id.placeTitle);

        if (getIntent() != null) {
            String extra = getIntent().getStringExtra("placetitle");
            if (extra != null) {
                mTvPlaceTitle.setText(extra);
                retrieveParseObject(extra);
                mCurrentSeries.clear();
                addSampleData();
                mChart.repaint();
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_stats, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_geofence) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(item.getTitle());
            mLayoutAddGeofence = LayoutInflater.from(this).inflate(R.layout.add_geofence, null);
            builder.setView(mLayoutAddGeofence);

            final EditText nameEt = (EditText) mLayoutAddGeofence.findViewById(R.id.editText);
            nameEt.requestFocus();
            final EditText radiusEt = (EditText) mLayoutAddGeofence.findViewById(R.id.editText2);
            radiusEt.setInputType(InputType.TYPE_CLASS_NUMBER);
            radiusEt.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);

            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String name = nameEt.getText().toString();
                    int radius = Integer.parseInt(radiusEt.getText().toString());
                    EditText timeEt = (EditText) mLayoutAddGeofence.findViewById(R.id.editText3);
                    String time = timeEt.getText().toString();

                    ParseObject parseObject = new ParseObject("Places");
                    parseObject.put("Name", name);
                    parseObject.put("Radius", radius);
                    parseObject.saveInBackground();

                    Intent i = new Intent(StatsActivity.this, MapActivity.class);
                    i.putExtra("Place", name);
                    i.putExtra("Radius", radius);
                    startActivity(i);

                    dialog.dismiss();
                }
            });
            builder.show();
        } else if (id == R.id.action_add_offer) {

        }
        return true;
    }

    private void retrieveParseObject(String place) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("PeopleCount");
        query.whereEqualTo("Place", place);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    mCount = object.getInt("People");
                } else {
                    //
                }
            }
        });
    }
}
