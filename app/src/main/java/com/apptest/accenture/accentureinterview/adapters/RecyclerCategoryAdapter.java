package com.apptest.accenture.accentureinterview.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.apptest.accenture.accentureinterview.R;
import com.apptest.accenture.accentureinterview.model.ModelCategory;

import java.util.ArrayList;

public class RecyclerCategoryAdapter extends RecyclerView.Adapter<RecyclerCategoryAdapter.RecyclerCategoryHolder> {

    private ArrayList<ModelCategory> myCategories;

    public RecyclerCategoryAdapter(ArrayList<ModelCategory> myCategories){

        this.myCategories = myCategories;

    }

    @Override
    public RecyclerCategoryHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_category, viewGroup, false);

        RecyclerCategoryHolder rch = new RecyclerCategoryHolder(v);

        return rch;
    }

    @Override
    public void onBindViewHolder(RecyclerCategoryHolder recyclerCategoryHolder, int i) {

        ModelCategory myCategory = this.myCategories.get(i);

        recyclerCategoryHolder.txtCategoryValue.setText(myCategory.getCategory());

    }

    @Override
    public int getItemCount() {
        return myCategories.size();
    }

    public static class RecyclerCategoryHolder extends RecyclerView.ViewHolder{

        public TextView txtCategoryValue;
        public TextView txtPriceValue;

        public RecyclerCategoryHolder(View itemView) {
            super(itemView);

            txtCategoryValue = itemView.findViewById(R.id.txtCategoryValue);
            txtPriceValue = itemView.findViewById(R.id.txtPriceValue);
        }
    }
}
