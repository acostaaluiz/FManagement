package com.apptest.accenture.accentureinterview.data.access;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.apptest.accenture.accentureinterview.data.SqliteHelper;
import com.apptest.accenture.accentureinterview.data.table.CategoryTable;
import com.apptest.accenture.accentureinterview.model.ModelCategory;

import java.util.ArrayList;

/**
 * Created by fcost on 30/06/2018.
 */

public class CategoryController extends SqliteHelper {

    SqliteHelper sqliteHelper;

    public CategoryController(Context context) {
        super(context);
        sqliteHelper = new SqliteHelper(context);
    }

    public boolean saveCategoryData(ModelCategory modelCategory){

        ContentValues values = new ContentValues();

        values.put(CategoryTable.COL_CATEGORY, modelCategory.getCategory());
        //values.put(CategoryTable.COL_CATEGORY_INPUT_DATE, modelCategory.getCategoryInputDate());

        return sqliteHelper.insertData(CategoryTable.TABLE_CATEGORY, values);
    }

    public boolean deleteCategoryData(ModelCategory modelCategory){

        String[] args = {modelCategory.getCategory()};

        return sqliteHelper.deleteData(CategoryTable.TABLE_CATEGORY,
                CategoryTable.COL_CATEGORY + " = ?", args);
    }

    public boolean checkCategory(ModelCategory modelCategory){

        String[] columns = {
                CategoryTable.COL_CATEGORY
        };

        SQLiteDatabase db = sqliteHelper.getReadableDatabase();
        String selection = CategoryTable.COL_CATEGORY + " =? ";

        String[] args = {modelCategory.getCategory()};

        Cursor cursor = db.query(CategoryTable.TABLE_CATEGORY,
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

    public ArrayList<ModelCategory> getAllCategories(){

        ArrayList<ModelCategory> categories = new ArrayList<>();

        String[] columns = {
                CategoryTable.COL_CATEGORY,
                CategoryTable.COL_CATEGORY_INPUT_DATE
        };

        SQLiteDatabase db = sqliteHelper.getReadableDatabase();
        String selection = null;

        String[] args = null;

        Cursor cursor = db.query(CategoryTable.TABLE_CATEGORY,
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

                categories.add(new ModelCategory(
                        cursor.getString(cursor.getColumnIndex(CategoryTable.COL_CATEGORY)),
                        cursor.getString(cursor.getColumnIndex(CategoryTable.COL_CATEGORY))));

                cursor.moveToNext();
            }
        }

        cursor.close();
        db.close();

        return categories;
    }
}



