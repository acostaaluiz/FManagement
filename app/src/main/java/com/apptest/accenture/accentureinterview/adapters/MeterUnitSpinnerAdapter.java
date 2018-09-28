package com.apptest.accenture.accentureinterview.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.apptest.accenture.accentureinterview.model.ModelMeterUnit;

import java.util.ArrayList;

public class MeterUnitSpinnerAdapter extends ArrayAdapter<ModelMeterUnit> {

    private Context context;
    private ArrayList<ModelMeterUnit> values;

    public MeterUnitSpinnerAdapter(Context context, int textViewResourceId,
                                   ArrayList<ModelMeterUnit> values) {

        super(context, textViewResourceId, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public int getCount(){
        return values.size();
    }

    @Override
    public ModelMeterUnit getItem(int position){
        return values.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView label = (TextView) super.getView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        label.setText(values.get(position).getMeterUnitName());

        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        label.setText(values.get(position).getMeterUnitName());

        return label;
    }
}
