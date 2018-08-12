package com.apptest.accenture.accentureinterview.data.table;

/**
 * Created by fcost on 01/07/2018.
 */

public class ExpenseTable {

    public static final String TABLE_EXPENSE = "Expense";
    public static final String COL_EXPENSE = "expense";
    public static final String COL_EXPENSE_FREQUENCY = "frequency";
    public static final String COL_EXPENSE_DATE = "expense_date";
    public static final String COL_CATEGORY = "category";
    public static final String COL_PRICE = "price";
    public static final String COL_EXPENSE_INPUT_DATE = "expense_input_date";

    public static final String DB_EXPENSE = "CREATE TABLE " + TABLE_EXPENSE + "("
            + COL_EXPENSE + " TEXT PRIMARY KEY, "
            + COL_EXPENSE_FREQUENCY + " TEXT, "
            + COL_EXPENSE_DATE + " TEXT, "
            + COL_CATEGORY + " TEXT, "
            + COL_PRICE + " TEXT, "
            + COL_EXPENSE_INPUT_DATE + " TEXT " + ")";
}
