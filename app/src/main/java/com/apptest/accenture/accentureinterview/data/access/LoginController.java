package com.apptest.accenture.accentureinterview.data.access;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.apptest.accenture.accentureinterview.data.DBEntity;
import com.apptest.accenture.accentureinterview.data.SqliteHelper;
import com.apptest.accenture.accentureinterview.data.table.LoginTable;
import com.apptest.accenture.accentureinterview.data.table.UserTable;
import com.apptest.accenture.accentureinterview.model.ModelLogin;

/**
 * Created by fcost on 28/06/2018.
 */

public class LoginController extends DBEntity{

    private static final String TAG = "";
    DBEntity sqliteHelper;


    public LoginController(Context context) {
        super(context);

        sqliteHelper = new DBEntity(context);
    }

    public boolean saveLoginData(ModelLogin modelLogin){

        ContentValues values = new ContentValues();

        values.put(LoginTable.COL_USER_ID, modelLogin.getUser());
        values.put(LoginTable.COL_PASSWORD, modelLogin.getPassword());
        values.put(LoginTable.COL_REMEMBER_PASSWORD, modelLogin.isRememberPassword());
        values.put(LoginTable.COL_LAST_ACCESS, modelLogin.getLastAccess());

        return sqliteHelper.insertData(LoginTable.TABLE_LOGIN, values);
    }

    public int updateLoginData(ModelLogin modelLogin, String whereClause){
        ContentValues values = new ContentValues();
        values.put(LoginTable.COL_USER_ID, modelLogin.getUser());
        values.put(LoginTable.COL_PASSWORD, modelLogin.getPassword());
        values.put(LoginTable.COL_REMEMBER_PASSWORD, modelLogin.isRememberPassword());
        values.put(LoginTable.COL_LAST_ACCESS, modelLogin.getLastAccess());

        return sqliteHelper.updateData(LoginTable.TABLE_LOGIN, values, whereClause);
    }

    public boolean checkUser(ModelLogin modelLogin){

        String[] columns = {
                UserTable.COL_USER_ID
        };

        SQLiteDatabase db = sqliteHelper.getReadableDatabase();
        String selection = UserTable.COL_USER_ID + " =? ";

        String[] args = {modelLogin.getUser()};

        Cursor cursor = db.query(UserTable.TABLE_USER,
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
            Log.d(TAG, "return true");
            return true;
        }

        return false;
    }

    public boolean checkPassword(ModelLogin modelLogin){

        boolean passwordCheck = false;
        String passwordReal = "";
        String[] columns = {
                UserTable.COL_PASSWORD
        };

        SQLiteDatabase db = sqliteHelper.getReadableDatabase();
        String selection = UserTable.COL_USER_ID + " =? ";

        String[] args = {modelLogin.getUser()};

        Cursor cursor = db.query(UserTable.TABLE_USER,
                columns,
                selection,
                args,
                null,
                null,
                null);

        int count = cursor.getCount();


        if (count > 0){

            cursor.moveToFirst();
            passwordReal = cursor.getString(0);

            if(passwordReal.equals(modelLogin.getPassword()))
                passwordCheck = true;
        }

        cursor.close();
        db.close();

        return passwordCheck;
    }
}
