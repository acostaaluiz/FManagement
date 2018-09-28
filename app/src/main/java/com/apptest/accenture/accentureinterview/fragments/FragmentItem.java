package com.apptest.accenture.accentureinterview.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.apptest.accenture.accentureinterview.R;
import com.apptest.accenture.accentureinterview.activities.ErrorMessageActivity;
import com.apptest.accenture.accentureinterview.activities.ListItemActivity;
import com.apptest.accenture.accentureinterview.adapters.MeterUnitSpinnerAdapter;
import com.apptest.accenture.accentureinterview.app.MyApplication;
import com.apptest.accenture.accentureinterview.model.ModelItem;
import com.apptest.accenture.accentureinterview.model.ModelMeterUnit;
import com.apptest.accenture.accentureinterview.presenter.PresenterItem;
import com.apptest.accenture.accentureinterview.utility.ProgressDialog;
import com.apptest.accenture.accentureinterview.view.Item;

import java.util.ArrayList;

public class FragmentItem extends Fragment implements Item.View{

    private EditText txtItemValue;
    private EditText txtItemNameValue;
    private EditText txtItemPriceValue;
    private EditText txtItemStoredQuantityValue;
    private Button btnRegister;
    private TextView txtViewList;
    private Spinner spnMeterUnit;
    private ProgressDialog progressDialog;
    private Item.Presenter itemPresenter;
    private ModelItem myModelItem;
    private ModelMeterUnit myModelMeterUnit;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View vw = inflater.inflate(R.layout.fragment_item, container, false);

        txtItemValue = vw.findViewById(R.id.txtItemValue);
        txtItemNameValue = vw.findViewById(R.id.txtItemNameValue);
        txtItemPriceValue = vw.findViewById(R.id.txtItemPriceValue);
        txtItemStoredQuantityValue = vw.findViewById(R.id.txtItemStoredQuantityValue);
        btnRegister = vw.findViewById(R.id.btnRegister);
        txtViewList = vw.findViewById(R.id.txtViewList);
        spnMeterUnit = vw.findViewById(R.id.spnMeterUnit);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String item = txtItemValue.getText().toString();
                String itemName = txtItemNameValue.getText().toString();
                String itemPrice = txtItemPriceValue.getText().toString();
                String itemStoredQuantity = txtItemStoredQuantityValue.getText().toString();

                myModelItem = new ModelItem(item, itemName, itemPrice, itemStoredQuantity, myModelMeterUnit.getMeterUnit());

                itemPresenter.creationItemProcess(myModelItem);

            }
        });

        txtViewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(getActivity(), ListItemActivity.class);
                startActivity(myIntent);
            }
        });

        spnMeterUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                MeterUnitSpinnerAdapter spinnerAdapter = (MeterUnitSpinnerAdapter) spnMeterUnit.getAdapter();

                myModelMeterUnit = spinnerAdapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });

        itemPresenter = new PresenterItem(this, getContext());
        itemPresenter.initInterface();

        return vw;
    }

    @Override
    public MyApplication getMyApplication() {
        return (MyApplication) getActivity().getApplication();
    }

    @Override
    public void itemEmptyError() {
        callErrorMessageActivity(
                getResources().getString(R.string.attention),
                getResources().getString(R.string.item_empty));
    }

    @Override
    public void itemNameEmptyError() {
        callErrorMessageActivity(
                getResources().getString(R.string.attention),
                getResources().getString(R.string.item_name_empty));
    }

    @Override
    public void itemPriceEmptyError() {
        callErrorMessageActivity(
                getResources().getString(R.string.attention),
                getResources().getString(R.string.item_price_empty));
    }

    @Override
    public void itemStoredQuantityEmptyError() {
        callErrorMessageActivity(
                getResources().getString(R.string.attention),
                getResources().getString(R.string.item_stored_quantity_empty));
    }

    @Override
    public void itemAlReadyExists() {
        callErrorMessageActivity(
                getResources().getString(R.string.attention),
                getResources().getString(R.string.item_already_exists));
    }

    @Override
    public void connectionServerError(String error) {
        callErrorMessageActivity(
                getResources().getString(R.string.error),
                error);
    }

    @Override
    public void loadMeterUnitSpinner(ArrayList<ModelMeterUnit> meterUnits) {

        ArrayAdapter<ModelMeterUnit> adapter = new MeterUnitSpinnerAdapter(
                getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                meterUnits
        );

        spnMeterUnit.setAdapter(adapter);
    }

    @Override
    public void initLoadProgressBar() {
        progressDialog = new ProgressDialog();
        progressDialog.show(getActivity().getSupportFragmentManager(),
                getResources().getString(R.string.loading));
    }

    @Override
    public void finishLoadProgressBar() {
        progressDialog.dismiss();
    }

    @Override
    public void successfullyRegister(ArrayList<ModelItem> incomes) {

    }

    public void callErrorMessageActivity(String errorType, String errorMessage) {

        Intent myIntent = new Intent(getActivity(), ErrorMessageActivity.class);

        myIntent.putExtra("errorType", errorType);
        myIntent.putExtra("errorMessage", errorMessage);

        startActivity(myIntent);
    }
}
