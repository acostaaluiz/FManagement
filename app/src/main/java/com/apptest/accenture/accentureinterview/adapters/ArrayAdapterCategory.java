package com.apptest.accenture.accentureinterview.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.apptest.accenture.accentureinterview.R;
import com.apptest.accenture.accentureinterview.model.ModelCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fcost on 30/06/2018.
 */

public class ArrayAdapterCategory extends ArrayAdapter<ModelCategory> {

    private List<ModelCategory> categories = new ArrayList<>();

    public ArrayAdapterCategory(Context context, int textViewResourceId, List<ModelCategory> objects) {

        super(context, textViewResourceId, objects);

        this.categories = objects;

    }

    static class ViewHolder {
        protected TextView txtCategoryValue;
    }

    public int getCount() {
        return this.categories.size();
    }

    public ModelCategory getItem(int index) {
        return this.categories.get(index);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ModelCategory modelCategory = getItem(position);

        if (row == null) {

            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.listview_category, parent, false);

            final ViewHolder viewHolder = new ViewHolder();

            viewHolder.txtCategoryValue =  row.findViewById(R.id.txtCategoryValue);

            row.setTag(viewHolder);

        }

        ViewHolder holder = (ViewHolder) row.getTag();
        holder.txtCategoryValue.setText(modelCategory.getCategory());

        return row;
    }
}
