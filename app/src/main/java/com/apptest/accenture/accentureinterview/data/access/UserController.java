package com.apptest.accenture.accentureinterview.data.access;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.apptest.accenture.accentureinterview.data.SqliteHelper;
import com.apptest.accenture.accentureinterview.data.table.LoginTable;
import com.apptest.accenture.accentureinterview.data.table.UserTable;
import com.apptest.accenture.accentureinterview.model.ModelLogin;
import com.apptest.accenture.accentureinterview.model.ModelUser;

/**
 * Created by fcost on 28/06/2018.
 */

public class UserController extends SqliteHelper {

    private static final String TAG = SqliteHelper.class.getSimpleName();
    SqliteHelper sqliteHelper;

    public UserController(Context context) {
        super(context);
        sqliteHelper = new SqliteHelper(context);
    }

    public boolean saveUserData(ModelUser modelUser){

        ContentValues values = new ContentValues();

        values.put(UserTable.COL_USER_ID, modelUser.getUser());
        values.put(UserTable.COL_PASSWORD, modelUser.getPassword());
        values.put(UserTable.COL_EMAIL, modelUser.getEmail());
        values.put(UserTable.COL_TELEPHONE, modelUser.getTelephone());
        //values.put(UserTable.COL_LAST_UPDATE_DATA, modelUser.getLastUpdateData());

        return sqliteHelper.insertData(UserTable.TABLE_USER, values);
    }

    public int updateUserData(ModelUser modelUser, String whereClause){
        ContentValues values = new ContentValues();

        values.put(UserTable.COL_USER_ID, modelUser.getUser());
        values.put(UserTable.COL_PASSWORD, modelUser.getPassword());
        values.put(UserTable.COL_EMAIL, modelUser.getEmail());
        values.put(UserTable.COL_TELEPHONE, modelUser.getTelephone());
        //values.put(UserTable.COL_LAST_UPDATE_DATA, modelUser.getLastUpdateData());

        return sqliteHelper.updateData(UserTable.TABLE_USER, values, whereClause);
    }

    public boolean checkUser(ModelUser modelUser){

        String[] columns = {
                LoginTable.COL_USER_ID
        };

        SQLiteDatabase db = sqliteHelper.getReadableDatabase();
        String selection = UserTable.COL_USER_ID + " =? ";

        String[] args = {modelUser.getUser()};

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
}
