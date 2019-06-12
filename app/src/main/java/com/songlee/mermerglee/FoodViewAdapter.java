package com.songlee.mermerglee;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class FoodViewAdapter extends BaseAdapter {
    private Activity activity;
    private String[] strings;
    private String[] images;
    List selectedPositions;

    FoodViewAdapter(String[] arrStrings, String[] arrImages, Activity activity) {
        this.strings = arrStrings;
        this.images = arrImages;
        this.activity = activity;
        selectedPositions = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return strings.length;
    }

    @Override
    public Object getItem(int position) {
        return strings[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FoodItemView customView = (convertView == null) ? new FoodItemView(activity) : (FoodItemView) convertView;
        customView.display(strings[position], images[position], selectedPositions.contains(position));

        return customView;
    }
}
