package com.apptest.accenture.accentureinterview.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.apptest.accenture.accentureinterview.R;
import com.apptest.accenture.accentureinterview.model.ModelExpense;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fcost on 01/07/2018.
 */

public class ArrayAdapterExpense extends ArrayAdapter<ModelExpense> {

    private List<ModelExpense> expenses;
    private Context context;

    public ArrayAdapterExpense(Context context, int textViewResourceId, List<ModelExpense> objects) {

        super(context, textViewResourceId, objects);
        this.expenses = objects;
        this.context = context;
    }

    static class ViewHolder {
        protected TextView txtExpenseValue;
        protected TextView txtPriceValue;
        protected TextView txtExpenseDateValue;
    }

    public int getCount() {
        return this.expenses.size();
    }

    public ModelExpense getItem(int index) {
        return this.expenses.get(index);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if( convertView == null ){

            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_expense, parent, false);

            holder.txtExpenseValue =  convertView.findViewById(R.id.txtExpenseValue);
            holder.txtPriceValue =  convertView.findViewById(R.id.txtPriceValue);
            holder.txtExpenseDateValue =  convertView.findViewById(R.id.txtExpenseDateValue);
            convertView.setTag(holder);
        } else
             holder = (ViewHolder) convertView.getTag();


        holder.txtExpenseValue.setText(expenses.get(position).getExpense());
        holder.txtPriceValue.setText(expenses.get(position).getPrice());
        holder.txtExpenseDateValue.setText(expenses.get(position).getExpenseDate());

        return convertView;
    }
}
