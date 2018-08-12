package com.apptest.accenture.accentureinterview.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.apptest.accenture.accentureinterview.R;
import com.apptest.accenture.accentureinterview.model.ModelCreditCard;

import java.util.List;

public class ArrayAdapterCreditCard extends ArrayAdapter<ModelCreditCard> {

    private Context context;
    private List<ModelCreditCard> myCreditCards;

    public ArrayAdapterCreditCard(Context context, int textViewResourceId, List<ModelCreditCard> objects){
        super(context, textViewResourceId, objects);

        this.context = context;
        this.myCreditCards = objects;
    }

    static class ViewHolder {
        protected TextView txtCreditCardValue;
        protected TextView txtBank;
        protected TextView txtCreditCardFlagValue;
        protected TextView txtCreditCardLimitValue;
    }

    public int getCount() {
        return this.myCreditCards.size();
    }

    public ModelCreditCard getItem(int index) {
        return this.myCreditCards.get(index);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if( convertView == null ){

            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_creditcard, parent, false);

            holder.txtCreditCardValue =  convertView.findViewById(R.id.txtCreditCardValue);
            holder.txtBank =  convertView.findViewById(R.id.txtBankValue);
            holder.txtCreditCardFlagValue =  convertView.findViewById(R.id.txtCreditCardFlagValue);
            holder.txtCreditCardLimitValue =  convertView.findViewById(R.id.txtCreditCardLimitValue);

            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();


        holder.txtCreditCardValue.setText(myCreditCards.get(position).getCreditCard());
        holder.txtBank.setText(myCreditCards.get(position).getBank());
        holder.txtCreditCardFlagValue.setText(myCreditCards.get(position).getCreditCardFlag());
        holder.txtCreditCardLimitValue.setText(myCreditCards.get(position).getCreditCardLimit());

        return convertView;
    }
}
