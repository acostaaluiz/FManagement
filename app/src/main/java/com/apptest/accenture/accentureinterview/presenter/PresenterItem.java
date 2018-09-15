package com.apptest.accenture.accentureinterview.presenter;

import android.content.Context;
import android.os.AsyncTask;

import com.apptest.accenture.accentureinterview.data.api.ItemRestAPI;
import com.apptest.accenture.accentureinterview.model.ModelItem;
import com.apptest.accenture.accentureinterview.view.Item;

import java.io.IOException;
import java.util.ArrayList;

public class PresenterItem implements Item.Presenter {

    private Item.View fragmentItem;
    private ItemRestAPI itemRestAPI;
    private ArrayList<ModelItem> myItems;
    private ModelItem myModelItem;

    public PresenterItem(Item.View fragmentItem, Context context){

        this.fragmentItem = fragmentItem;
        this.itemRestAPI = new ItemRestAPI();
    }

    @Override
    public void initInterface() {

        InitInterface initInterface = new InitInterface();
        initInterface.execute();
    }

    @Override
    public void creationItemProcess(ModelItem modelItem) {

        this.myModelItem = modelItem;

        CreateItem createItem = new CreateItem();
        createItem.execute();
    }

    @Override
    public void deleteItemData(ModelItem modelItem) {

    }

    private class InitInterface extends
            AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            fragmentItem.initLoadProgressBar();
        }

        @Override
        protected String doInBackground(String... strings) {

            try {

                myItems = itemRestAPI.getAllItems(fragmentItem.getMyApplication().getJwtToken()).execute().body();

            } catch (IOException e) {
                return e.toString();
            }

            return "OK";
        }

        @Override
        protected void onPostExecute(String result) {

            if(result.equals("OK"))
                fragmentItem.successfullyRegister(myItems);
            else
                fragmentItem.connectionServerError(result);

            fragmentItem.finishLoadProgressBar();
        }
    }

    private class CreateItem extends
            AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            fragmentItem.initLoadProgressBar();
        }

        @Override
        protected String doInBackground(String... strings) {

            String result;

            try {

                if(myModelItem.getItem().isEmpty())
                    return "ITEM_EMPTY";
                else if(myModelItem.getItemName().isEmpty())
                    return "ITEM_NAME_EMPTY";
                else if(myModelItem.getItemPrice().isEmpty())
                    return "ITEM_PRICE_EMPTY";
                else if(myModelItem.getItemStoredQuantity().isEmpty())
                    return "ITEM_STORED_QUANTITY_EMPTY";
                else {

                    myModelItem = itemRestAPI.saveItem(myModelItem, fragmentItem.getMyApplication().getJwtToken()).execute().body();
                    result = myModelItem.getResponse();

                }
            } catch (IOException e) {
                result = e.toString();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String result) {

            if(result.equals("ITEM_EMPTY"))
                fragmentItem.itemEmptyError();
            else if(result.equals("ITEM_NAME_EMPTY"))
                fragmentItem.itemNameEmptyError();
            else if(result.equals("ITEM_PRICE_EMPTY"))
                fragmentItem.itemPriceEmptyError();
            else if(result.equals("ITEM_STORED_QUANTITY_EMPTY"))
                fragmentItem.itemStoredQuantityEmptyError();
            else if(result.equals("INVALID_ITEM"))
                fragmentItem.itemAlReadyExists();
            else if(result.equals("OK"))
                fragmentItem.finishLoadProgressBar();
            else
                fragmentItem.connectionServerError(result);

            fragmentItem.finishLoadProgressBar();
        }
    }
}
