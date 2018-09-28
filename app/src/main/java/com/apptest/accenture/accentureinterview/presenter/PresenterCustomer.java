package com.apptest.accenture.accentureinterview.presenter;

import android.os.AsyncTask;

import com.apptest.accenture.accentureinterview.data.api.CustomerRestAPI;
import com.apptest.accenture.accentureinterview.model.ModelCustomer;
import com.apptest.accenture.accentureinterview.view.Customer;

import java.io.IOException;

public class PresenterCustomer implements Customer.Presenter {

    private Customer.View fragmentCustomer;
    private CustomerRestAPI customerRestAPI;
    private ModelCustomer myModelCustomer;

    public PresenterCustomer(Customer.View fragmentCustomer){
        this.fragmentCustomer = fragmentCustomer;
        this.customerRestAPI = new CustomerRestAPI();
    }

    @Override
    public void creationCustomerProcess(ModelCustomer modelCustomer) {

        this.myModelCustomer = modelCustomer;

        CreationCustomerProcess initInterface = new CreationCustomerProcess();
        initInterface.execute();
    }

    private class CreationCustomerProcess extends
            AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            fragmentCustomer.initLoadProgressBar();
        }

        @Override
        protected String doInBackground(String... strings) {

            try {

                myModelCustomer = customerRestAPI.saveCustomer(myModelCustomer, fragmentCustomer.getMyApplication().getJwtToken()).execute().body();

            } catch (IOException e) {
                return e.toString();
            }

            return myModelCustomer.getResponse();
        }

        @Override
        protected void onPostExecute(String result) {

            if(result.equals("OK"))
                fragmentCustomer.successfullyRegister();
            else if(result.equals("INVALID_CUSTOMER"))
                fragmentCustomer.customerAlreadyExists();
            else
                fragmentCustomer.connectionServerError(result);

            fragmentCustomer.finishLoadProgressBar();
        }
    }
}
