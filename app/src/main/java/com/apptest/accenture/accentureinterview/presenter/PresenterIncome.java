package com.apptest.accenture.accentureinterview.presenter;

import android.content.Context;
import android.os.AsyncTask;

import com.apptest.accenture.accentureinterview.data.access.IncomeController;
import com.apptest.accenture.accentureinterview.data.api.CategoryIncomeRestAPI;
import com.apptest.accenture.accentureinterview.data.api.DateRestAPI;
import com.apptest.accenture.accentureinterview.data.api.FrequencyRestAPI;
import com.apptest.accenture.accentureinterview.data.api.IncomeRestAPI;
import com.apptest.accenture.accentureinterview.model.ModelCategoryIncome;
import com.apptest.accenture.accentureinterview.model.ModelDate;
import com.apptest.accenture.accentureinterview.model.ModelFrequency;
import com.apptest.accenture.accentureinterview.model.ModelIncome;
import com.apptest.accenture.accentureinterview.utility.DateHelper;
import com.apptest.accenture.accentureinterview.view.Income;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by fcost on 02/07/2018.
 */

public class PresenterIncome implements Income.Presenter {

    private Income.View fragmenteIncome;
    private FrequencyRestAPI frequencyRestAPI;
    private CategoryIncomeRestAPI categoryIncomeRestAPI;
    private IncomeRestAPI incomeRestAPI;
    private ArrayList<ModelIncome> incomes;
    private ArrayList<ModelFrequency> frequencies;
    private ArrayList<ModelCategoryIncome> incomeCategories;
    private ModelIncome myModelIncome;

    public PresenterIncome(Income.View fragmenteIncome, Context context){

        this.fragmenteIncome = fragmenteIncome;
        this.frequencyRestAPI = new FrequencyRestAPI();
        this.categoryIncomeRestAPI = new CategoryIncomeRestAPI();
        this.incomeRestAPI = new IncomeRestAPI();
    }

    @Override
    public void creationIncomeProcess(ModelIncome modelIncome) {
        this.myModelIncome = modelIncome;

        CreateIncome createIncome = new CreateIncome();
        createIncome.execute();
    }

    @Override
    public void deleteIncomeData(ModelIncome modelIncome) {
        //incomeController.deleteIncomeData(modelIncome);
    }

    @Override
    public void initInterface() {
        InitInterface initInterface = new InitInterface();
        initInterface.execute();
    }

    @Override
    public void convertPickerBtnDate(int year, int month, int day) {

        String date = String.valueOf(day) + "/" +
                      String.valueOf(month) + "/" +
                      String.valueOf(year);

        fragmenteIncome.setTxtIncomeDate(date);
    }

    @Override
    public void convertPickerBtnToDate(int year, int month, int day) {

        String date = String.valueOf(day) + "/" +
                String.valueOf(month) + "/" +
                String.valueOf(year);

        fragmenteIncome.setTxtIncomeToDate(date);

    }

    @Override
    public void initDatePickerDialog(String txtId) {

        Calendar dateTimeNow = Calendar.getInstance();

        fragmenteIncome.showDatePickerDialog(
                dateTimeNow.get(dateTimeNow.YEAR),
                dateTimeNow.get(dateTimeNow.MONTH),
                dateTimeNow.get(dateTimeNow.DAY_OF_MONTH),
                txtId);
    }

    private class InitInterface extends
            AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            fragmenteIncome.initLoadProgressBar();
        }

        @Override
        protected String doInBackground(String... strings) {

            frequencies = new ArrayList<>();
            incomeCategories = new ArrayList<>();

            try {

                frequencies = frequencyRestAPI.loadAllFrequencies().execute().body();
                incomeCategories = categoryIncomeRestAPI.getAllIncomeCategories().execute().body();
                incomes = incomeRestAPI.getAllIncomes().execute().body();

            } catch (IOException e) {
                return e.toString();
            }

            return "OK";
        }

        @Override
        protected void onPostExecute(String result) {

            if(result.equals("OK")) {
                fragmenteIncome.successfullyRegister(incomes);
                fragmenteIncome.loadIncomeFrequency(frequencies);
                fragmenteIncome.loadIncomeCategories(incomeCategories);
            } else
                fragmenteIncome.connectionServerError(result);

            fragmenteIncome.finishLoadProgressBar();
        }
    }

    private class CreateIncome extends
            AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            fragmenteIncome.initLoadProgressBar();
        }

        @Override
        protected String doInBackground(String... strings) {

            String result = "";

            try {

                if(myModelIncome.getIncome().isEmpty())
                    return "INCOME_EMPTY";
                else if(myModelIncome.getIncomeFrequency().isEmpty())
                    return "FREQUENCY_EMPTY";
                else if(myModelIncome.getIncomeDate().isEmpty())
                    return "INCOME_DATE_EMPTY";
                else if(myModelIncome.getIncomeToDate().isEmpty())
                    return "INCOME_TODATE_EMPTY";
                else if(myModelIncome.getCategoryIncome().isEmpty())
                    return "CATEGORYINCOME_EMPTY";
                else if(myModelIncome.getPrice().isEmpty())
                    return "PRICE_EMPTY";
                else {

                    DateHelper myDateHelper = new DateHelper(myModelIncome.getIncomeDate());
                    myModelIncome.setIncomeDate(myDateHelper.getDate());

                    myDateHelper = new DateHelper(myModelIncome.getIncomeToDate());
                    myModelIncome.setIncomeToDate(myDateHelper.getDate());

                    myModelIncome = incomeRestAPI.saveIncome(myModelIncome).execute().body();
                    result = myModelIncome.getResponse();

                }
            } catch (IOException e) {
               result = e.toString();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String result) {

            if(result.equals("INCOME_EMPTY"))
                fragmenteIncome.incomeEmptyError();
            else if(result.equals("FREQUENCY_EMPTY"))
                fragmenteIncome.frequencyEmptyError();
            else if(result.equals("INCOME_DATE_EMPTY"))
                fragmenteIncome.incomeDateEmptyError();
            else if(result.equals("INCOME_TODATE_EMPTY"))
                fragmenteIncome.incomeToDateEmptyError();
            else if(result.equals("CATEGORYINCOME_EMPTY"))
                fragmenteIncome.categoryIncomeEmptyError();
            else if(result.equals("PRICE_EMPTY"))
                fragmenteIncome.priceEmptyError();
            else if(result.equals("INVALID_INCOME"))
                fragmenteIncome.incomeAlreadyExists();
            else if(result.equals("OK")) {
                fragmenteIncome.finishLoadProgressBar();

                InitInterface initInterface = new InitInterface();
                initInterface.execute();
            }
            else
                fragmenteIncome.connectionServerError(result);

            fragmenteIncome.finishLoadProgressBar();
        }
    }
}
