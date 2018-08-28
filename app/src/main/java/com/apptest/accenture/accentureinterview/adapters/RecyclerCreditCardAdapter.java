package com.apptest.accenture.accentureinterview.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.apptest.accenture.accentureinterview.R;
import com.apptest.accenture.accentureinterview.model.ModelCreditCard;

import java.util.ArrayList;

public class RecyclerCreditCardAdapter extends RecyclerView.Adapter<RecyclerCreditCardAdapter.RecyclerCreditCardHolder> {

    private ArrayList<ModelCreditCard> myCreditCards;

    public RecyclerCreditCardAdapter(ArrayList<ModelCreditCard> myCreditCards){
        this.myCreditCards = myCreditCards;
    }

    @Override
    public RecyclerCreditCardHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_creditcard, viewGroup, false);

        RecyclerCreditCardHolder rch = new RecyclerCreditCardHolder(v);

        return rch;
    }

    @Override
    public void onBindViewHolder(RecyclerCreditCardHolder recyclerCreditCardHolder, int i) {

        ModelCreditCard myModelCreditCard = this.myCreditCards.get(i);

        recyclerCreditCardHolder.txtCreditCardValue.setText(myModelCreditCard.getCreditCard());
        recyclerCreditCardHolder.txtBankValue.setText(myModelCreditCard.getBank());
        recyclerCreditCardHolder.txtCreditCardFlagValue.setText(myModelCreditCard.getCreditCardFlag());
        recyclerCreditCardHolder.txtCreditCardLimitValue.setText(myModelCreditCard.getCreditCardLimit());
    }

    @Override
    public int getItemCount() {
        return this.myCreditCards.size();
    }

    public static class RecyclerCreditCardHolder extends RecyclerView.ViewHolder{

        public TextView txtCreditCardValue;
        public TextView txtBankValue;
        public TextView txtCreditCardFlagValue;
        public TextView txtCreditCardLimitValue;

        public RecyclerCreditCardHolder(View itemView) {
            super(itemView);

            txtCreditCardValue = itemView.findViewById(R.id.txtCreditCardValue);
            txtBankValue = itemView.findViewById(R.id.txtBankValue);
            txtCreditCardFlagValue = itemView.findViewById(R.id.txtCreditCardFlagValue);
            txtCreditCardLimitValue = itemView.findViewById(R.id.txtCreditCardLimitValue);
        }
    }
}
