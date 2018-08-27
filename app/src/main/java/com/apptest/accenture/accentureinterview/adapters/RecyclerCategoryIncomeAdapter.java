package com.apptest.accenture.accentureinterview.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.apptest.accenture.accentureinterview.R;
import com.apptest.accenture.accentureinterview.model.ModelCategoryIncome;

import java.util.ArrayList;

public class RecyclerCategoryIncomeAdapter  extends RecyclerView.Adapter<RecyclerCategoryIncomeAdapter.RecyclerCategoryIncomeHolder> {

    private ArrayList<ModelCategoryIncome> myIncomeCategories;

    public RecyclerCategoryIncomeAdapter(ArrayList<ModelCategoryIncome> myIncomeCategories){
        this.myIncomeCategories = myIncomeCategories;
    }

    @Override
    public RecyclerCategoryIncomeHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_categoryincome, viewGroup, false);

        RecyclerCategoryIncomeHolder rch = new RecyclerCategoryIncomeHolder(v);

        return rch;
    }

    @Override
    public void onBindViewHolder(RecyclerCategoryIncomeHolder recyclerCategoryIncomeHolder, int i) {

        ModelCategoryIncome myModelCategoryIncome = this.myIncomeCategories.get(i);

        recyclerCategoryIncomeHolder.txtCategoryIncomeValue.setText(myModelCategoryIncome.getCategoryIncome());

    }

    @Override
    public int getItemCount() {
        return myIncomeCategories.size();
    }

    public static class RecyclerCategoryIncomeHolder extends RecyclerView.ViewHolder{

        public TextView txtCategoryIncomeValue;
        public TextView txtPriceValue;

        public RecyclerCategoryIncomeHolder(View itemView) {
            super(itemView);

            txtCategoryIncomeValue = itemView.findViewById(R.id.txtCategoryIncomeValue);
            txtPriceValue = itemView.findViewById(R.id.txtPriceValue);
        }
    }
}
