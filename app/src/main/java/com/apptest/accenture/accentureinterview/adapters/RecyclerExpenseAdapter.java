package com.apptest.accenture.accentureinterview.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.apptest.accenture.accentureinterview.R;
import com.apptest.accenture.accentureinterview.model.ModelExpense;

import java.util.ArrayList;

public class RecyclerExpenseAdapter extends RecyclerView.Adapter<RecyclerExpenseAdapter.RecyclerExpenseHolder> {

    private ArrayList<ModelExpense> myExpenses;

    public RecyclerExpenseAdapter(ArrayList<ModelExpense> myExpenses){
        this.myExpenses = myExpenses;
    }

    @Override
    public RecyclerExpenseHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_expense, viewGroup, false);

        RecyclerExpenseHolder rch = new RecyclerExpenseHolder(v);

        return rch;
    }

    @Override
    public void onBindViewHolder(RecyclerExpenseHolder recyclerExpenseHolder, int i) {

        ModelExpense myModelExpense = this.myExpenses.get(i);

        recyclerExpenseHolder.txtExpenseValue.setText(myModelExpense.getExpense());
        recyclerExpenseHolder.txtPriceValue.setText(myModelExpense.getPrice());
        recyclerExpenseHolder.txtExpenseDateValue.setText(myModelExpense.getExpenseDate());
    }

    @Override
    public int getItemCount() {
        return myExpenses.size();
    }

    public static class RecyclerExpenseHolder extends RecyclerView.ViewHolder{

        public TextView txtExpenseValue;
        public TextView txtPriceValue;
        public TextView txtExpenseDateValue;

        public RecyclerExpenseHolder(View itemView) {
            super(itemView);

            txtExpenseValue = itemView.findViewById(R.id.txtExpenseValue);
            txtPriceValue = itemView.findViewById(R.id.txtPriceValue);
            txtExpenseDateValue = itemView.findViewById(R.id.txtExpenseDateValue);
        }
    }
}
