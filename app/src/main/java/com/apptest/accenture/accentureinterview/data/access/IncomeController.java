package com.apptest.accenture.accentureinterview.data.access;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.apptest.accenture.accentureinterview.data.SqliteHelper;
import com.apptest.accenture.accentureinterview.data.table.IncomeTable;
import com.apptest.accenture.accentureinterview.model.ModelIncome;

import java.util.ArrayList;

/**
 * Created by fcost on 02/07/2018.
 */

public class IncomeController extends SqliteHelper {

    SqliteHelper sqliteHelper;

    public IncomeController(Context context){
        super(context);

        sqliteHelper = new SqliteHelper(context);
    }

    public boolean saveIncomeData(ModelIncome modelIncome){

        ContentValues values = new ContentValues();

        values.put(IncomeTable.COL_INCOME, modelIncome.getIncome());
        values.put(IncomeTable.COL_INCOME_FREQUENCY, modelIncome.getIncomeFrequency());
        values.put(IncomeTable.COL_INCOME_TODATE, modelIncome.getIncomeToDate());
        values.put(IncomeTable.COL_PRICE, modelIncome.getPrice());
        values.put(IncomeTable.COL_INCOME_TODATE, "");

        return sqliteHelper.insertData(IncomeTable.TABLE_INCOME, values);
    }

    public boolean deleteIncomeData(ModelIncome modelIncome){

        String[] args = {modelIncome.getIncome()};

        return sqliteHelper.deleteData(IncomeTable.TABLE_INCOME,
                IncomeTable.COL_INCOME + " = ?", args);
    }

    public boolean checkIncome(ModelIncome modelIncome){

        String[] columns = {
                IncomeTable.COL_INCOME
        };

        SQLiteDatabase db = sqliteHelper.getReadableDatabase();
        String selection = IncomeTable.COL_INCOME + " =? ";

        String[] args = {modelIncome.getIncome()};

        Cursor cursor = db.query(IncomeTable.TABLE_INCOME,
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

    public ArrayList<ModelIncome> getAllIncomes(){

        ArrayList<ModelIncome> incomes = new ArrayList<>();

        String[] columns = {
                IncomeTable.COL_INCOME,
                IncomeTable.COL_INCOME_FREQUENCY,
                IncomeTable.COL_INCOME_TODATE,
                IncomeTable.COL_PRICE,
        };

        SQLiteDatabase db = sqliteHelper.getReadableDatabase();
        String selection = null;

        String[] args = null;

        Cursor cursor = db.query(IncomeTable.TABLE_INCOME,
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

                incomes.add(new ModelIncome(
                        cursor.getString(cursor.getColumnIndex(IncomeTable.COL_INCOME)),
                        cursor.getString(cursor.getColumnIndex(IncomeTable.COL_INCOME_FREQUENCY)),
                        cursor.getString(cursor.getColumnIndex(IncomeTable.COL_INCOME_TODATE)),
                        cursor.getString(cursor.getColumnIndex(IncomeTable.COL_PRICE))));

                cursor.moveToNext();
            }
        }

        cursor.close();
        db.close();

        return incomes;
    }
}
