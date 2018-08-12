package com.apptest.accenture.accentureinterview.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.apptest.accenture.accentureinterview.data.table.CategoryTable;
import com.apptest.accenture.accentureinterview.data.table.ExpenseTable;
import com.apptest.accenture.accentureinterview.data.table.IncomeTable;
import com.apptest.accenture.accentureinterview.data.table.LoginTable;
import com.apptest.accenture.accentureinterview.data.table.UserTable;

/**
 * Created by fcost on 28/06/2018.
 */

public class SqliteHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "FManagement.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TAG = SqliteHelper.class.getSimpleName();

    public SqliteHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(LoginTable.DB_LOGIN);
        db.execSQL(UserTable.DB_USER);
        db.execSQL(CategoryTable.DB_CATEGORY);
        db.execSQL(ExpenseTable.DB_EXPENSE);
        db.execSQL(IncomeTable.DB_INCOME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + LoginTable.TABLE_LOGIN);
        db.execSQL("DROP TABLE IF EXISTS " + UserTable.TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + CategoryTable.TABLE_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + ExpenseTable.TABLE_EXPENSE);
        db.execSQL("DROP TABLE IF EXISTS " + IncomeTable.TABLE_INCOME);
        onCreate(db);
    }

    public boolean insertData(String table, ContentValues values){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.insert(table, null, values);
        if (result == -1){
            Log.d(TAG, "failed to save data!");
            return false;
        }else{
            Log.d(TAG, "save data successful");
            return true;
        }
    }

    public int updateData(String table, ContentValues values, String whereClause){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.update(table, values,
                whereClause,
                new String[]{whereClause});
    }

    public boolean deleteData(String table, String whereClause, String[] whereArgs){
        SQLiteDatabase db = this.getWritableDatabase();
        return  db.delete(table, whereClause, whereArgs) > 0;
    }

}
