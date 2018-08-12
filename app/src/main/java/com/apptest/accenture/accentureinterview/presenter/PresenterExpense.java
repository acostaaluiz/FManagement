package com.apptest.accenture.accentureinterview.presenter;

import android.content.Context;
import android.os.AsyncTask;

import com.apptest.accenture.accentureinterview.data.access.ExpenseController;
import com.apptest.accenture.accentureinterview.data.api.CategoryRestAPI;
import com.apptest.accenture.accentureinterview.data.api.CreditCardRestAPI;
import com.apptest.accenture.accentureinterview.data.api.ExpenseRestAPI;
import com.apptest.accenture.accentureinterview.data.api.FrequencyRestAPI;
import com.apptest.accenture.accentureinterview.model.ModelCategory;
import com.apptest.accenture.accentureinterview.model.ModelCreditCard;
import com.apptest.accenture.accentureinterview.model.ModelExpense;
import com.apptest.accenture.accentureinterview.model.ModelFrequency;
import com.apptest.accenture.accentureinterview.utility.DateHelper;
import com.apptest.accenture.accentureinterview.view.Expense;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by fcost on 01/07/2018.
 */

public class PresenterExpense implements Expense.Presenter {

    private Expense.View fragmentExpense;
    private ExpenseController expenseController;
    private FrequencyRestAPI frequencyRestAPI;
    private CategoryRestAPI categoryRestAPI;
    private ExpenseRestAPI expenseRestAPI;
    private CreditCardRestAPI creditCardRestAPI;
    private ArrayList<ModelExpense> myExpenses;
    private ArrayList<ModelCreditCard> myCreditCards;
    private ArrayList<ModelCategory> myCategories;
    private ArrayList<ModelFrequency> myFrequencies;
    private ModelExpense myModelExpense;

    public PresenterExpense(Expense.View fragmentExpense, Context context){

        this.fragmentExpense = fragmentExpense;
        this.expenseController = new ExpenseController(context);
        this.frequencyRestAPI = new FrequencyRestAPI();
        this.categoryRestAPI = new CategoryRestAPI();
        this.expenseRestAPI = new ExpenseRestAPI();
        this.creditCardRestAPI = new CreditCardRestAPI();
    }

    @Override
    public void creationExpenseProcess(ModelExpense modelExpense) {

        this.myModelExpense = modelExpense;

        CreateExpense createExpense = new CreateExpense();
        createExpense.execute();

    }

    @Override
    public void deleteExpense(ModelExpense modelExpense) {
        expenseController.deleteExpenseData(modelExpense);
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

        fragmentExpense.setTxtExpenseDate(date);

    }

    @Override
    public void initDatePickerDialog() {

        Calendar dateTimeNow = Calendar.getInstance();

        fragmentExpense.showDatePickerDialog(
                dateTimeNow.get(dateTimeNow.YEAR),
                dateTimeNow.get(dateTimeNow.MONTH),
                dateTimeNow.get(dateTimeNow.DAY_OF_MONTH));

    }

    private class InitInterface extends
            AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {

            fragmentExpense.initLoadProgressBar();

        }

        @Override
        protected String doInBackground(String... strings) {

            try {

                myCreditCards = creditCardRestAPI.getAllCreditCards().execute().body();
                myFrequencies = frequencyRestAPI.loadAllFrequencies().execute().body();
                myCategories = categoryRestAPI.getAllCategories().execute().body();
                myExpenses = expenseRestAPI.getAllExpenses().execute().body();

            } catch (IOException e) {

                return e.toString();

            }

            return "OK";
        }


        @Override
        protected void onPostExecute(String result) {

            if (result.equals("OK")) {

                fragmentExpense.loadCreditCardSpinner(myCreditCards);
                fragmentExpense.loadFrequencySpinner(myFrequencies);
                fragmentExpense.loadCategorySpinner(myCategories);
                fragmentExpense.loadExpenses(myExpenses);
            }

            fragmentExpense.finishLoadProgressBar();
        }
    }

    private class CreateExpense extends
            AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {

            fragmentExpense.initLoadProgressBar();

        }

        @Override
        protected String doInBackground(String... strings) {

            String result = "";

            try {

                if(myModelExpense.getExpense().isEmpty())
                    result = "EXPENSE_EMPTY";
                else if(myModelExpense.getCategory().isEmpty())
                    result = "CATEGORY_EMPTY";
                else if(myModelExpense.getExpenseFrequency().isEmpty())
                    result = "FREQUENCY_EMPTY";
                else if(myModelExpense.getExpenseDate().isEmpty())
                    result = "EXPENSE_DATE_EMPTY";
                else if(myModelExpense.getPrice().isEmpty())
                    result = "PRICE_EMPTY";
                else {
                    DateHelper myDateHelper = new DateHelper(myModelExpense.getExpenseDate());

                    myModelExpense.setExpenseDate(myDateHelper.getDate());
                    myModelExpense = expenseRestAPI.saveExpense(myModelExpense).execute().body();

                    result = myModelExpense.getResponse();
                }

            } catch (IOException e) {

                return e.toString();

            }

            return result;
        }


        @Override
        protected void onPostExecute(String result) {

            if(result.equals("EXPENSE_EMPTY"))
                fragmentExpense.expenseEmptyError();
            else if(result.equals("CATEGORY_EMPTY"))
                fragmentExpense.categoryEmptyError();
            else if(result.equals("FREQUENCY_EMPTY"))
                fragmentExpense.frequencyEmptyError();
            else if(result.equals("EXPENSE_DATE_EMPTY"))
                fragmentExpense.expenseDateEmptyError();
            else if(result.equals("PRICE_EMPTY"))
                fragmentExpense.priceEmptyError();
            else if(result.equals("INVALID_EXPENSE"))
                fragmentExpense.expenseAlreadyExists();
            else if (result.equals("OK")) {

                fragmentExpense.finishLoadProgressBar();

                InitInterface initInterface = new InitInterface();
                initInterface.execute();
            }

            fragmentExpense.finishLoadProgressBar();
        }
    }
}
