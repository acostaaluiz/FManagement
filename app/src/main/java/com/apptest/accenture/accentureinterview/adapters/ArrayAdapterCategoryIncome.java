package com.apptest.accenture.accentureinterview.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.apptest.accenture.accentureinterview.R;
import com.apptest.accenture.accentureinterview.model.ModelCategoryIncome;

import java.util.ArrayList;
import java.util.List;

public class ArrayAdapterCategoryIncome extends ArrayAdapter{

    private List<ModelCategoryIncome> categories = new ArrayList<>();

    public ArrayAdapterCategoryIncome(Context context, int textViewResourceId, List<ModelCategoryIncome> objects) {

        super(context, textViewResourceId, objects);

        this.categories = objects;

    }

    static class ViewHolder {
        protected TextView txtCategoryValue;
    }

    public int getCount() {
        return this.categories.size();
    }

    public ModelCategoryIncome getItem(int index) {
        return this.categories.get(index);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ModelCategoryIncome modelCategory = getItem(position);

        if (row == null) {

            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.listview_category, parent, false);

            final ViewHolder viewHolder = new ViewHolder();

            viewHolder.txtCategoryValue =  row.findViewById(R.id.txtCategoryValue);

            row.setTag(viewHolder);

        }

        ViewHolder holder = (ViewHolder) row.getTag();
        holder.txtCategoryValue.setText(modelCategory.getCategoryIncome());

        return row;
    }
}
