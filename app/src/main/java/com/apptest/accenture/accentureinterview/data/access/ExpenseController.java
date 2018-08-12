package com.apptest.accenture.accentureinterview.data.access;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.apptest.accenture.accentureinterview.data.SqliteHelper;
import com.apptest.accenture.accentureinterview.data.table.ExpenseTable;
import com.apptest.accenture.accentureinterview.model.ModelExpense;

import java.util.ArrayList;

/**
 * Created by fcost on 01/07/2018.
 */

public class ExpenseController  extends SqliteHelper {

    SqliteHelper sqliteHelper;

    public ExpenseController(Context context){
        super(context);

        sqliteHelper = new SqliteHelper(context);
    }

    public boolean saveExpenseData(ModelExpense modelExpense){

        ContentValues values = new ContentValues();

        values.put(ExpenseTable.COL_EXPENSE, modelExpense.getExpense());
        values.put(ExpenseTable.COL_EXPENSE_FREQUENCY, modelExpense.getExpenseFrequency().toString());
        values.put(ExpenseTable.COL_EXPENSE_DATE, modelExpense.getExpenseDate());
        values.put(ExpenseTable.COL_CATEGORY, modelExpense.getCategory());
        values.put(ExpenseTable.COL_PRICE, modelExpense.getPrice());

        return sqliteHelper.insertData(ExpenseTable.TABLE_EXPENSE, values);
    }

    public boolean deleteExpenseData(ModelExpense modelExpense){

        String[] args = {modelExpense.getExpense()};

        return sqliteHelper.deleteData(ExpenseTable.TABLE_EXPENSE,
                ExpenseTable.COL_EXPENSE + " = ?", args);
    }

    public boolean checkExpense(ModelExpense modelExpense){

        String[] columns = {
                ExpenseTable.COL_EXPENSE
        };

        SQLiteDatabase db = sqliteHelper.getReadableDatabase();
        String selection = ExpenseTable.COL_EXPENSE + " =? ";

        String[] args = {modelExpense.getExpense()};

        Cursor cursor = db.query(ExpenseTable.TABLE_EXPENSE,
                columns,
                selection,
                args,
                null,
                null,
                null);

        int count = cursor.getCount();
        cursor.close();
        db.close();
        if (count > 0){

            return true;
        }

        return false;
    }

    /*public ArrayList<ModelExpense> getAllExpenses(){

        ArrayList<ModelExpense> expenses = new ArrayList<>();

        String[] columns = {
                ExpenseTable.COL_EXPENSE,
                ExpenseTable.COL_EXPENSE_FREQUENCY,
                ExpenseTable.COL_EXPENSE_DATE,
                ExpenseTable.COL_CATEGORY,
                ExpenseTable.COL_PRICE,
        };

        SQLiteDatabase db = sqliteHelper.getReadableDatabase();
        String selection = null;

        String[] args = null;

        Cursor cursor = db.query(ExpenseTable.TABLE_EXPENSE,
                columns,
                selection,
                args,
                null,
                null,
                null);

        int count = cursor.getCount();
        if (count > 0){

            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {

                expenses.add(new ModelExpense(
                        cursor.getString(cursor.getColumnIndex(ExpenseTable.COL_EXPENSE)),
                        cursor.getString(cursor.getColumnIndex(ExpenseTable.COL_EXPENSE_DATE)),
                        cursor.getString(cursor.getColumnIndex(ExpenseTable.COL_CATEGORY)),
                        cursor.getString(cursor.getColumnIndex(ExpenseTable.COL_PRICE)),
                        ModelExpense.ExpenseFrequency.DAY)
                );

                cursor.moveToNext();
            }
        }

        cursor.close();
        db.close();

        return expenses;
    }*/
}
