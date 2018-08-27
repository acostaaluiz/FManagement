package com.apptest.accenture.accentureinterview.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.apptest.accenture.accentureinterview.R;
import com.apptest.accenture.accentureinterview.model.ModelIncome;

import java.util.ArrayList;

public class RecyclerIncomeAdapter extends RecyclerView.Adapter<RecyclerIncomeAdapter.RecyclerIncomeHolder> {

    private ArrayList<ModelIncome> myIncomes;

    public RecyclerIncomeAdapter(ArrayList<ModelIncome> myIncomes){
        this.myIncomes = myIncomes;
    }

    @Override
    public RecyclerIncomeHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_income, viewGroup, false);

        RecyclerIncomeHolder rch = new RecyclerIncomeHolder(v);

        return rch;
    }

    @Override
    public void onBindViewHolder(RecyclerIncomeHolder recyclerExpenseHolder, int i) {

        ModelIncome myModelIncome = this.myIncomes.get(i);

        recyclerExpenseHolder.txtIncomeValue.setText(myModelIncome.getIncome());
        recyclerExpenseHolder.txtIncomePriceValue.setText(myModelIncome.getPrice());
        recyclerExpenseHolder.txtIncomeDateValue.setText(myModelIncome.getIncomeDate());
    }

    @Override
    public int getItemCount() {
        return myIncomes.size();
    }

    public static class RecyclerIncomeHolder extends RecyclerView.ViewHolder{

    public TextView txtIncomeValue;
    public TextView txtIncomePriceValue;
    public TextView txtIncomeDateValue;

        public RecyclerIncomeHolder(View itemView) {
            super(itemView);

            txtIncomeValue = itemView.findViewById(R.id.txtIncomeValue);
            txtIncomePriceValue = itemView.findViewById(R.id.txtIncomePriceValue);
            txtIncomeDateValue = itemView.findViewById(R.id.txtIncomeDateValue);
        }
    }
}
