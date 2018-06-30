package com.apptest.accenture.accentureinterview.presenter;

import android.content.Context;
import android.os.AsyncTask;

import com.apptest.accenture.accentureinterview.data.access.CategoryController;
import com.apptest.accenture.accentureinterview.model.ModelCategory;
import com.apptest.accenture.accentureinterview.view.Category;

import java.util.ArrayList;

/**
 * Created by fcost on 30/06/2018.
 */

public class CategoryPresenter implements Category.Presenter{

    private Category.View fragmentCategory;
    private CategoryController categoryController;
    ArrayList<ModelCategory> categories;

    public CategoryPresenter(Category.View fragmentCategory, Context context){

        this.fragmentCategory = fragmentCategory;
        this.categoryController = new CategoryController(context);
    }

    @Override
    public void saveExpenseCategory(ModelCategory modelCategory) {

        if(modelCategory.getCategory().isEmpty())
            fragmentCategory.expenseCategoryEmptyError();
        else if(categoryController.checkCategory(modelCategory))
            fragmentCategory.expenseCategoryAlreadyExists();
        else {

            if(!categoryController.saveCategoryData(modelCategory))
                fragmentCategory.errorRegister();
            else{
                LoadCategories loadCategories = new LoadCategories();
                loadCategories.execute();
            }

        }
    }

    @Override
    public void loadAllExpenseCategory() {
        LoadCategories loadCategories = new LoadCategories();
        loadCategories.execute();
    }

    private class LoadCategories extends
            AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... strings) {


            categories = categoryController.getAllCategories();

            return "OK";
        }


        @Override
        protected void onPostExecute(String result) {

            if(result.equals("OK"))
                fragmentCategory.successfullyRegister(categories);

        }
    }


}
