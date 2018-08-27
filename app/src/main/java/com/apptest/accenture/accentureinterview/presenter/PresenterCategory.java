package com.apptest.accenture.accentureinterview.presenter;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import com.apptest.accenture.accentureinterview.app.MyApplication;
import com.apptest.accenture.accentureinterview.data.access.CategoryController;
import com.apptest.accenture.accentureinterview.data.api.CategoryRestAPI;
import com.apptest.accenture.accentureinterview.model.ModelCategory;
import com.apptest.accenture.accentureinterview.utility.NetworkConnectionTest;
import com.apptest.accenture.accentureinterview.view.Category;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by fcost on 30/06/2018.
 */

public class PresenterCategory implements Category.Presenter{

    private CategoryRestAPI categoryRestAPI;
    private Category.View fragmentCategory;
    private CategoryController categoryController;
    private ModelCategory myModelCategory;
    private ArrayList<ModelCategory> myCategories;
    private NetworkConnectionTest networkConnectionTest;

    public PresenterCategory(Category.View fragmentCategory, Context context){

        this.fragmentCategory = fragmentCategory;
        this.categoryController = new CategoryController(context);
        this.categoryRestAPI = new CategoryRestAPI();
        this.networkConnectionTest = new NetworkConnectionTest(context);
    }

    @Override
    public void creationExpenseCategoryProcess(ModelCategory modelCategory) {

        this.myModelCategory = modelCategory;

        CreateCategory createCategory = new CreateCategory();
        createCategory.execute();
    }

    @Override
    public void deleteExpenseCategory(ModelCategory modelCategory) {
        if(categoryController.deleteCategoryData(modelCategory))
            loadAllExpenseCategory();
    }

    @Override
    public void loadAllExpenseCategory() {

        InitInterface initInterface = new InitInterface();
        initInterface.execute();
    }

    private class InitInterface extends
            AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            fragmentCategory.initLoadProgressBar();
        }

        @Override
        protected String doInBackground(String... strings) {

            myCategories = new ArrayList<>();

            try {
                myCategories = categoryRestAPI.getAllCategories(fragmentCategory.getMyApplication().getJwtToken()).execute().body();
            } catch (IOException e) {
                return e.toString();
            }

            return "OK";
        }

        @Override
        protected void onPostExecute(String result) {

            if(result.equals("OK")) {
                fragmentCategory.successfullyRegister(myCategories);
            } else
                fragmentCategory.connectionServerError(result);

            fragmentCategory.finishLoadProgressBar();
        }
    }

    private class CreateCategory extends
            AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            fragmentCategory.initLoadProgressBar();
        }

        @Override
        protected String doInBackground(String... strings) {

            String result = "";

            try {

                if(myModelCategory.getCategory().isEmpty())
                    result = "CATEGORY_EMPTY";
                else if(!networkConnectionTest.isNetworkAvailable())
                    result = "WITHOUT_CONNECTION";
                else {
                        myModelCategory = categoryRestAPI.saveCategory(
                                myModelCategory,
                                fragmentCategory.getMyApplication().getJwtToken()
                        ).execute().body();

                        result = myModelCategory.getResponse();
                }
            } catch (IOException e) {
                result = e.toString();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String result) {

            if(result.equals("CATEGORY_EMPTY"))
                fragmentCategory.expenseCategoryEmptyError();
            else if(result.equals("WITHOUT_CONNECTION"))
                fragmentCategory.thereIsNoInternetConnection();
            else if(result.equals("INVALID_CATEGORY"))
                fragmentCategory.expenseCategoryAlreadyExists();
            else if(result.equals("OK")){

                fragmentCategory.finishLoadProgressBar();

                InitInterface initInterface = new InitInterface();
                initInterface.execute();

            } else
                fragmentCategory.connectionServerError(result);

            fragmentCategory.finishLoadProgressBar();
        }
    }
}
