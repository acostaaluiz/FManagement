package com.apptest.accenture.accentureinterview.data.table;

/**
 * Created by fcost on 30/06/2018.
 */

public class CategoryTable {

    public static final String TABLE_CATEGORY = "Category";
    public static final String COL_CATEGORY = "category";
    public static final String COL_CATEGORY_INPUT_DATE = "categoryInputDate";

    public static final String DB_CATEGORY = "CREATE TABLE " + TABLE_CATEGORY + "("
            + COL_CATEGORY + " TEXT PRIMARY KEY, "
            + COL_CATEGORY_INPUT_DATE + " TEXT " + ")";
}
