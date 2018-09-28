package com.apptest.accenture.accentureinterview.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.apptest.accenture.accentureinterview.R;
import com.apptest.accenture.accentureinterview.activities.ErrorMessageActivity;
import com.apptest.accenture.accentureinterview.activities.ListCustomerActivity;
import com.apptest.accenture.accentureinterview.app.MyApplication;
import com.apptest.accenture.accentureinterview.model.ModelCustomer;
import com.apptest.accenture.accentureinterview.presenter.PresenterCustomer;
import com.apptest.accenture.accentureinterview.utility.ProgressDialog;
import com.apptest.accenture.accentureinterview.view.Customer;

public class FragmentCustomer extends Fragment implements Customer.View {

    private ProgressDialog progressDialog;
    private EditText txtCustomerValue;
    private EditText txtCustomerNameValue;
    private EditText txtCepValue;
    private EditText txtAddressValue;
    private EditText txtNumberValue;
    private EditText txtNeighborhoodValue;
    private EditText txtCityValue;
    private EditText txtStateValue;
    private EditText txtTelephone1Value;
    private EditText txtTelephone2Value;
    private EditText txtEmailValue;
    private EditText txtNotesValue;
    private Button btnRegister;
    private TextView txtViewList;
    private Customer.Presenter customerPresenter;
    private ModelCustomer myModelCustomer;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View vw = inflater.inflate(R.layout.fragment_customer, container, false);

        txtCustomerValue = vw.findViewById(R.id.txtCustomerValue);
        txtCustomerNameValue = vw.findViewById(R.id.txtCustomerNameValue);
        txtCepValue = vw.findViewById(R.id.txtCepValue);
        txtAddressValue = vw.findViewById(R.id.txtAddressValue);
        txtNumberValue = vw.findViewById(R.id.txtNumberValue);
        txtNeighborhoodValue = vw.findViewById(R.id.txtNeighborhoodValue);
        txtCityValue = vw.findViewById(R.id.txtCityValue);
        txtStateValue = vw.findViewById(R.id.txtStateValue);
        txtTelephone1Value = vw.findViewById(R.id.txtTelephone1Value);
        txtTelephone2Value = vw.findViewById(R.id.txtTelephone2Value);
        txtEmailValue = vw.findViewById(R.id.txtEmailValue);
        txtNotesValue = vw.findViewById(R.id.txtNotesValue);
        btnRegister = vw.findViewById(R.id.btnRegister);
        txtViewList = vw.findViewById(R.id.txtViewList);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String customer = txtCustomerValue.getText().toString();
                String customerName = txtCustomerNameValue.getText().toString();
                String cep = txtCepValue.getText().toString();
                String address = txtAddressValue.getText().toString();
                String number = txtNumberValue.getText().toString();
                String neighborhood = txtNeighborhoodValue.getText().toString();
                String city = txtCityValue.getText().toString();
                String state = txtStateValue.getText().toString();
                String telephone1 = txtTelephone1Value.getText().toString();
                String telephone2 = txtTelephone2Value.getText().toString();
                String email = txtEmailValue.getText().toString();
                String notes = txtNotesValue.getText().toString();

                myModelCustomer = new ModelCustomer(customer, customerName, cep, address, number, neighborhood, city, state, telephone1, telephone2, email, notes);

                customerPresenter.creationCustomerProcess(myModelCustomer);
            }
        });

        txtViewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(getActivity(), ListCustomerActivity.class);
                startActivity(myIntent);
            }
        });

        customerPresenter = new PresenterCustomer(this);

        return vw;

    }

    @Override
    public MyApplication getMyApplication() {
        return (MyApplication) getActivity().getApplication();
    }

    @Override
    public void customerEmptyError() {
        callErrorMessageActivity(
                getResources().getString(R.string.attention),
                getResources().getString(R.string.customer_empty));
    }

    @Override
    public void customerNameEmptyError() {
        callErrorMessageActivity(
                getResources().getString(R.string.attention),
                getResources().getString(R.string.customer_name_empty));
    }

    @Override
    public void cepEmptyError() {
        callErrorMessageActivity(
                getResources().getString(R.string.attention),
                getResources().getString(R.string.cep_empty));
    }

    @Override
    public void addressEmptyError() {
        callErrorMessageActivity(
                getResources().getString(R.string.attention),
                getResources().getString(R.string.address_empty));
    }

    @Override
    public void numberEmptyError() {
        callErrorMessageActivity(
                getResources().getString(R.string.attention),
                getResources().getString(R.string.number_empty));
    }

    @Override
    public void neighborhoodEmptyError() {
        callErrorMessageActivity(
                getResources().getString(R.string.attention),
                getResources().getString(R.string.neighborhood_empty));
    }

    @Override
    public void cityEmptyError() {
        callErrorMessageActivity(
                getResources().getString(R.string.attention),
                getResources().getString(R.string.city_empty));
    }

    @Override
    public void stateEmptyError() {
        callErrorMessageActivity(
                getResources().getString(R.string.attention),
                getResources().getString(R.string.state_empty));
    }

    @Override
    public void telephone1EmptyError() {
        callErrorMessageActivity(
                getResources().getString(R.string.attention),
                getResources().getString(R.string.telephone1_empty));
    }

    @Override
    public void emailEmptyError() {
        callErrorMessageActivity(
                getResources().getString(R.string.attention),
                getResources().getString(R.string.empty_email));
    }


    @Override
    public void customerAlreadyExists() {
        callErrorMessageActivity(
                getResources().getString(R.string.attention),
                getResources().getString(R.string.customer_already_exists));
    }

    @Override
    public void connectionServerError(String error) {
        callErrorMessageActivity(
                getResources().getString(R.string.error),
                error);
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
    public void successfullyRegister() {
        Toast toast = Toast.makeText(getMyApplication(), getResources().getString(R.string.successfully_register_customer), Toast.LENGTH_SHORT);
        toast.show();
    }

    public void callErrorMessageActivity(String errorType, String errorMessage) {

        Intent myIntent = new Intent(getActivity(), ErrorMessageActivity.class);

        myIntent.putExtra("errorType", errorType);
        myIntent.putExtra("errorMessage", errorMessage);

        startActivity(myIntent);
    }
}
