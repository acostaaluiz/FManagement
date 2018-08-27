package com.apptest.accenture.accentureinterview.presenter;

import android.content.Context;
import android.os.AsyncTask;

import com.apptest.accenture.accentureinterview.data.api.CategoryIncomeRestAPI;
import com.apptest.accenture.accentureinterview.model.ModelCategoryIncome;
import com.apptest.accenture.accentureinterview.utility.NetworkConnectionTest;
import com.apptest.accenture.accentureinterview.view.CategoryIncome;

import java.io.IOException;
import java.util.ArrayList;

public class PresenterCategoryIncome implements CategoryIncome.Presenter {

    private CategoryIncomeRestAPI categoryIncomeRestAPI;
    private CategoryIncome.View fragmentCategoryIncome;
    private ModelCategoryIncome myModelCategoryIncome;
    private ArrayList<ModelCategoryIncome> myIncomeCategories;
    private NetworkConnectionTest networkConnectionTest;

    public PresenterCategoryIncome(CategoryIncome.View fragmentCategoryIncome, Context context){

        this.categoryIncomeRestAPI = new CategoryIncomeRestAPI();
        this.fragmentCategoryIncome = fragmentCategoryIncome;
        this.networkConnectionTest = new NetworkConnectionTest(context);
    }

    @Override
    public void creationIncomeCategoryProcess(ModelCategoryIncome modelCategoryIncome) {

        this.myModelCategoryIncome = modelCategoryIncome;

        CreateIncomeCategory createIncomeCategory = new CreateIncomeCategory();
        createIncomeCategory.execute();
    }

    @Override
    public void deleteIncomeCategory(ModelCategoryIncome modelCategoryIncome) {

    }

    @Override
    public void loadAllIncomeCategories() {

        LoadAllIncomeCategories loadAllIncomeCategories = new LoadAllIncomeCategories();
        loadAllIncomeCategories.execute();

    }

    private class CreateIncomeCategory extends
            AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            fragmentCategoryIncome.initLoadProgressBar();
        }

        @Override
        protected String doInBackground(String... strings) {

            String result = "";

            try {

                if(myModelCategoryIncome.getCategoryIncome().isEmpty())
                    result = "CATEGORY_INCOME_EMPTY";
                else if(!networkConnectionTest.isNetworkAvailable())
                    result = "WITHOUT_CONNECTION";
                else {
                        myModelCategoryIncome = categoryIncomeRestAPI.saveIncome(
                                myModelCategoryIncome,
                                fragmentCategoryIncome.getMyApplication().getJwtToken()
                        ).execute().body();
                        result = myModelCategoryIncome.getResponse();
                }

            } catch (IOException e) {
                result = e.toString();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String result) {

            if(result.equals("CATEGORY_INCOME_EMPTY"))
                fragmentCategoryIncome.incomeCategoryEmptyError();
            else if(result.equals("WITHOUT_CONNECTION"))
                fragmentCategoryIncome.thereIsNoInternetConnection();
            else if(result.equals("INVALID_CATEGORYINCOME"))
                fragmentCategoryIncome.incomeCategoryAlreadyExists();
            else if (result.equals("OK")) {
                fragmentCategoryIncome.finishLoadProgressBar();

                LoadAllIncomeCategories loadAllIncomeCategories = new LoadAllIncomeCategories();
                loadAllIncomeCategories.execute();
            } else
                fragmentCategoryIncome.connectionServerError(result);

            fragmentCategoryIncome.finishLoadProgressBar();
        }
    }

    private class LoadAllIncomeCategories extends
            AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            fragmentCategoryIncome.initLoadProgressBar();
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                    myIncomeCategories = categoryIncomeRestAPI.getAllIncomeCategories(fragmentCategoryIncome.getMyApplication().getJwtToken()).execute().body();

            } catch (IOException e) {

                return e.toString();
            }

            return "OK";
        }

        @Override
        protected void onPostExecute(String result) {

            if (result.equals("OK"))
                fragmentCategoryIncome.successfullyRegister(myIncomeCategories);

            fragmentCategoryIncome.finishLoadProgressBar();
        }
    }
}
