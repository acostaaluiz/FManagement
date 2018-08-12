package com.apptest.accenture.accentureinterview.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.apptest.accenture.accentureinterview.R;
import com.apptest.accenture.accentureinterview.model.ModelIncome;

import java.util.List;

public class ArrayAdapterIncome extends ArrayAdapter<ModelIncome> {

    private Context context;
    private List<ModelIncome> incomes;

    public ArrayAdapterIncome(Context context, int textViewResourceId, List<ModelIncome> objects){
        super(context, textViewResourceId, objects);

        this.context = context;
        this.incomes = objects;
    }

    static class ViewHolder {
        protected TextView txtIncomeValue;
        protected TextView txtIncomePriceValue;
        protected TextView txtIncomeDateValue;
    }

    public int getCount() {
        return this.incomes.size();
    }

    public ModelIncome getItem(int index) {
        return this.incomes.get(index);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if( convertView == null ){

            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_income, parent, false);

            holder.txtIncomeValue =  convertView.findViewById(R.id.txtIncomeValue);
            holder.txtIncomePriceValue =  convertView.findViewById(R.id.txtIncomePriceValue);
            holder.txtIncomeDateValue =  convertView.findViewById(R.id.txtIncomeDateValue);

            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();


        holder.txtIncomeValue.setText(incomes.get(position).getIncome());
        holder.txtIncomePriceValue.setText(incomes.get(position).getPrice());
        holder.txtIncomeDateValue.setText(incomes.get(position).getIncomeDate());

        return convertView;
    }
}
