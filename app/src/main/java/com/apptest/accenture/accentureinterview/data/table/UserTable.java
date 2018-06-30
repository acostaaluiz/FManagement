package com.apptest.accenture.accentureinterview.data.table;

/**
 * Created by fcost on 28/06/2018.
 */

public class UserTable {

    public static final String TABLE_USER = "User";
    public static final String COL_USER_ID = "user";
    public static final String COL_PASSWORD = "password";
    public static final String COL_EMAIL = "email";
    public static final String COL_TELEPHONE = "telephone";
    public static final String COL_LAST_UPDATE_DATA = "last_update_data";

    public static final String DB_USER = "CREATE TABLE " + TABLE_USER + "("
            + COL_USER_ID + " TEXT PRIMARY KEY, "
            + COL_PASSWORD + " TEXT, "
            + COL_EMAIL + " TEXT, "
            + COL_TELEPHONE + " TEXT, "
            + COL_LAST_UPDATE_DATA + " TEXT " + ")";
}
