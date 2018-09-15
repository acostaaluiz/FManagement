package com.apptest.accenture.accentureinterview.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.apptest.accenture.accentureinterview.R;
import com.apptest.accenture.accentureinterview.model.ModelItem;

import java.util.ArrayList;

public class RecyclerItemAdapter extends RecyclerView.Adapter<RecyclerItemAdapter.RecyclerItemHolder>{

    private ArrayList<ModelItem> myItems;

    public RecyclerItemAdapter(ArrayList<ModelItem> myItems){
        this.myItems = myItems;
    }

    @Override
    public RecyclerItemHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_item, viewGroup, false);

        RecyclerItemHolder rch = new RecyclerItemHolder(v);

        return rch;
    }

    @Override
    public void onBindViewHolder(RecyclerItemHolder recyclerItemHolder, int i) {

        ModelItem myModelItem = this.myItems.get(i);

        recyclerItemHolder.txtItemValue.setText(myModelItem.getItem());
        recyclerItemHolder.txtItemNameValue.setText(myModelItem.getItemName());
        recyclerItemHolder.txtItemPriceValue.setText(myModelItem.getItemPrice());
        recyclerItemHolder.txtItemStoredQuantityValue.setText(myModelItem.getItemStoredQuantity());
    }

    @Override
    public int getItemCount() {
        return this.myItems.size();
    }

    public static class RecyclerItemHolder extends RecyclerView.ViewHolder{

        public TextView txtItemValue;
        public TextView txtItemNameValue;
        public TextView txtItemPriceValue;
        public TextView txtItemStoredQuantityValue;

        public RecyclerItemHolder(View itemView) {
            super(itemView);

            txtItemValue = itemView.findViewById(R.id.txtItemValue);
            txtItemNameValue = itemView.findViewById(R.id.txtItemNameValue);
            txtItemPriceValue = itemView.findViewById(R.id.txtItemPriceValue);
            txtItemStoredQuantityValue = itemView.findViewById(R.id.txtItemStoredQuantityValue);
        }
    }
}
