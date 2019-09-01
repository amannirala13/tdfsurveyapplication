package com.tdf.tdfapplication.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DatabaseManager {

    private String tableName;
    private String createTable;
    private String values;
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DatabaseManager(Context c, String TABLE_NAME) {
        context = c;
        this.tableName = TABLE_NAME;
        values = " ";

    }

    public String getCreateTable() {
        return createTable;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setcreateTable(boolean primary, String... values) {
        StringBuilder builder = new StringBuilder();
        builder.append("create table ")
                .append(tableName)
                .append(" (")
                .append(values[0])
                .append(" varchar(20) PRIMARY KEY,");
        for (int i = 1; i < values.length; i++) {
            builder.append(values[i]);
            builder.append(" varchar(20)");
        }
        createTable = builder.toString();
        createTable = createTable.substring(0, createTable.length() - 1) + ")";
        Log.i("create table", createTable);

    }

    public void setCreateTable(String... values) {
        StringBuilder builder = new StringBuilder();
        builder.append("create table ").append(tableName).append(" (");
        for (String value : values) {
            builder.append(value);
            builder.append(" varchar(20),");
        }
        createTable = builder.toString();
        createTable = createTable.substring(0, createTable.length() - 1) + ")";
        Log.i("create table", createTable);

    }

    public void setValues(String... values) {
        StringBuilder builder = new StringBuilder();
        builder.append("'");
        for (String value : values) {
            builder.append(value);
            builder.append("','");
        }
        this.values = builder.toString();
        this.values = this.values.substring(0, this.values.length() - 2);
        Log.i("values", this.values);
    }

    public DatabaseManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context, this);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String... values) {
        setValues(values);
        String query = new StringBuilder()
                .append("INSERT INTO ")
                .append(tableName)
                .append(" values(")
                .append(this.values)
                .append(")")
                .toString();
        database.execSQL(query);
        Log.i("Insert query", query);
    }

    public Cursor fetch() {
        String query = "select * from " + tableName;
        Log.i("Query for selection", query);
        Cursor cursor = database.rawQuery(query, null);
        /*if (cursor != null) {
            cursor.move();
        }*/
        return cursor;
    }

    public void showDetails() {
        Cursor cursor = fetch();
        Log.i("Num rows", Integer.toString(cursor.getCount()));
        while (cursor.moveToNext()) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < cursor.getColumnCount(); i++) {
                builder.append(cursor.getString(i));
                builder.append(",");
            }
            String columnValues = builder.toString();
            Log.i("row: ", columnValues);
        }


    }
}
