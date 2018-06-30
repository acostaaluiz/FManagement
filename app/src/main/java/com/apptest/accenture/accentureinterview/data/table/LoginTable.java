package com.apptest.accenture.accentureinterview.data.table;

/**
 * Created by fcost on 28/06/2018.
 */

public class LoginTable {

    public static final String TABLE_LOGIN = "Login";
    public static final String COL_USER_ID = "user";
    public static final String COL_PASSWORD = "password";
    public static final String COL_REMEMBER_PASSWORD = "remember_password";
    public static final String COL_LAST_ACCESS = "last_access";

    public static final String DB_LOGIN = "CREATE TABLE " + TABLE_LOGIN + "("
            + COL_USER_ID + " TEXT PRIMARY KEY, "
            + COL_PASSWORD + " TEXT, "
            + COL_REMEMBER_PASSWORD + " TEXT, "
            + COL_LAST_ACCESS + " TEXT " + ")";

}
