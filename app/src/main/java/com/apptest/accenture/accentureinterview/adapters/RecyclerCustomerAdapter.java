package com.apptest.accenture.accentureinterview.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.apptest.accenture.accentureinterview.R;
import com.apptest.accenture.accentureinterview.model.ModelCustomer;

import java.util.ArrayList;

public class RecyclerCustomerAdapter extends RecyclerView.Adapter<RecyclerCustomerAdapter.RecyclerCustomerHolder> {

    private ArrayList<ModelCustomer> myCustomers;

    public RecyclerCustomerAdapter(ArrayList<ModelCustomer> myCustomers){
        this.myCustomers = myCustomers;
    }

    @Override
    public RecyclerCustomerHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_customer, viewGroup, false);

        RecyclerCustomerHolder rch = new RecyclerCustomerHolder(v);

        return rch;
    }

    @Override
    public void onBindViewHolder(RecyclerCustomerHolder recyclerCustomerHolder, int i) {

        ModelCustomer myModelCustomer = this.myCustomers.get(i);

        recyclerCustomerHolder.txtCustomerNameValue.setText(myModelCustomer.getCustomerName());
        recyclerCustomerHolder.txtAddressValue.setText(myModelCustomer.getAddress());
        recyclerCustomerHolder.txtCepValue.setText(myModelCustomer.getCep());
        recyclerCustomerHolder.txtNeighborhoodValue.setText(myModelCustomer.getNeighborhood());
        recyclerCustomerHolder.txtTelephone1Value.setText(myModelCustomer.getTelephone1());
        recyclerCustomerHolder.txtTelephone2Value.setText(myModelCustomer.getTelephone2());
    }

    @Override
    public int getItemCount() {
        return this.myCustomers.size();
    }

    public static class RecyclerCustomerHolder extends RecyclerView.ViewHolder{

        public TextView txtCustomerNameValue;
        public TextView txtAddressValue;
        public TextView txtCepValue;
        public TextView txtNeighborhoodValue;
        public TextView txtTelephone1Value;
        public TextView txtTelephone2Value;

        public RecyclerCustomerHolder(View itemView) {
            super(itemView);

            txtCustomerNameValue = itemView.findViewById(R.id.txtCustomerNameValue);
            txtAddressValue = itemView.findViewById(R.id.txtAddressValue);
            txtCepValue = itemView.findViewById(R.id.txtCepValue);
            txtNeighborhoodValue = itemView.findViewById(R.id.txtNeighborhoodValue);
            txtTelephone1Value = itemView.findViewById(R.id.txtTelephone1Value);
            txtTelephone2Value = itemView.findViewById(R.id.txtTelephone2Value);
        }
    }
}
