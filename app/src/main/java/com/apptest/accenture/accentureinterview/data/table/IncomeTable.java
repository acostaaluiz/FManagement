package com.apptest.accenture.accentureinterview.data.table;

/**
 * Created by fcost on 02/07/2018.
 */

public class IncomeTable {

    public static final String TABLE_INCOME = "Income";
    public static final String COL_INCOME = "income";
    public static final String COL_INCOME_FREQUENCY = "frequency";
    public static final String COL_INCOME_DATE = "income_date";
    public static final String COL_INCOME_TODATE = "income_todate";
    public static final String COL_PRICE = "price";
    public static final String COL_INCOME_INPUT_DATE = "expense_input_date";

    public static final String DB_INCOME = "CREATE TABLE " + TABLE_INCOME + "("
            + COL_INCOME + " TEXT PRIMARY KEY, "
            + COL_INCOME_FREQUENCY + " TEXT, "
            + COL_INCOME_DATE + " TEXT, "
            + COL_INCOME_TODATE + " TEXT, "
            + COL_PRICE + " TEXT, "
            + COL_INCOME_INPUT_DATE + " TEXT " + ")";
}
