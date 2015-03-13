package com.yahoo.myapp.proximity;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class PlacesAdapter extends ArrayAdapter<String> {

    public PlacesAdapter(Context context, int resource,
            List<String> objects) {
        super(context, R.layout.item_place, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_place, parent, false);
        }
        TextView tvPlaceName = (TextView) convertView.findViewById(R.id.tvPlaceName);
        tvPlaceName.setText(getItem(position));

        return convertView;
    }
}
