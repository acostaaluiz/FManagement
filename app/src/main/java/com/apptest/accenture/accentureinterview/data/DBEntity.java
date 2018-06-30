package com.apptest.accenture.accentureinterview.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by fcost on 28/06/2018.
 */

public class DBEntity extends SqliteHelper{

    private SqliteHelper mDbHelper;

    protected SQLiteDatabase mDb;

    public DBEntity(Context context) {
        super(context);

    }

    public boolean isOpen() {
        return this.mDb != null && this.mDb.isOpen();
    }

    /**
     * Abertura do Banco de Dados
     *
     * @return DBEntity
     * @throws SQLException
     */
    protected void open() throws SQLException {

        if (!isOpen()) {
            try {
                this.mDb = this.getWritableDatabase();
                //this.mDb = mDbHelper.getWritableDatabase();
            } catch (Exception e) {
                Log.e(e.getClass().getCanonicalName(), e.getMessage());
            }
        }
    }

    /**
     * Fechamento do banco de dados
     */
    public void close() {
        if (isOpen()) {
            this.mDb.close();
            this.mDb = null;
            if (mDbHelper != null) {
                mDbHelper.close();
                mDbHelper = null;
            }
        }
//		this.mDb.close();
    }

    /**
     * Inserção no banco de dados com os métodos de abertura e fechamento do
     * banco implícitos
     *
     * @param table
     * @param nullColumnHack
     * @param values
     * @return
     */
    protected boolean insert(String table, String nullColumnHack,
                             ContentValues values) {
        boolean inserted = false;
        try {
            open();
            inserted = mDb.insert(table, nullColumnHack, values) > 0;
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            close();
        }
        return inserted;
    }

    /**
     * Deleção no banco de dados com os métodos de abertura e fechamento do
     * banco implícitos
     *
     * @param table
     * @param whereClause
     * @param whereArgs
     * @return
     */
    protected boolean delete(String table, String whereClause,
                             String[] whereArgs) {
        try {
            open();
            boolean deleted = mDb.delete(table, whereClause, whereArgs) > 0;
            return deleted;
        } finally {
            close();
        }
    }

    /**
     * Atualização no banco de dados com os métodos de abertura e fechamento do
     * banco implícitos
     *
     * @param table
     * @param values
     * @param whereClause
     * @param whereArgs
     * @return
     */
    protected boolean update(String table, ContentValues values,
                             String whereClause, String[] whereArgs) {
        boolean updated = false;
        try {
            open();
            updated = mDb.update(table, values, whereClause, whereArgs) > 0;
        } catch(Exception e){
            Log.e(e.getClass().getCanonicalName(), e.getMessage());
        }
        finally {
            close();
        }
        return updated;
    }

    protected boolean existsTable(String tableName){
        boolean exists = false;
        Cursor cursor = null;
        try {
            open();
            cursor = mDb.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '" + tableName + "'", null);

            if (cursor != null && cursor.getCount() > 0) {
                exists = true;
            }
        } catch (Exception e) {
            Log.e(e.getClass().getCanonicalName(), e.getMessage());
        }
        finally {
            if (cursor != null) {
                cursor.close();
            }
            close();
        }
        return exists;
    }

    public void beginTransaction() {
        if(mDb!=null)
            mDb.beginTransaction();
    }

    public void setTransactionSuccessful() {
        if(mDb!=null)
            mDb.setTransactionSuccessful();
    }

    public void endTransaction() {
        if(mDb!=null)
            mDb.endTransaction();
    }
}
