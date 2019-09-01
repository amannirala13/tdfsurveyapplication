package com.tdf.tdfapplication.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "TDFData";

    DatabaseManager databaseManager;


    public DatabaseHelper(Context context, DatabaseManager databaseManager) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.databaseManager = databaseManager;
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(databaseManager.getCreateTable());
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + databaseManager.getTableName());
        // Create tables again
        onCreate(db);
    }
}
