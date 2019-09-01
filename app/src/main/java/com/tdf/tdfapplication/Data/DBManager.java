package com.tdf.tdfapplication.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBManager {
    public String TABLE_NAME;
    public String CREATE_TABLE;
    public String VALUES;
    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c,String TABLE_NAME) {
        context = c;
        this.TABLE_NAME = TABLE_NAME;
        VALUES = " ";

    }

    public void setCREATE_TABLE(boolean primary,String ... values) {
        CREATE_TABLE = "create table "+TABLE_NAME+" ("+values[0]+" varchar(20) PRIMARY KEY,";
        for(int i=1;i<values.length;i++){
            CREATE_TABLE = CREATE_TABLE+" "+values[i]+" varchar(20)";
        }
        CREATE_TABLE = CREATE_TABLE.substring(0,CREATE_TABLE.length()-1)+")";
        Log.i("create table",CREATE_TABLE);

    }

    public void setCREATE_TABLE(String ... values){
        CREATE_TABLE = "create table "+TABLE_NAME+" (";
        for(int i=0;i<values.length;i++){
            CREATE_TABLE = CREATE_TABLE+" "+values[i]+" varchar(20),";
        }
        CREATE_TABLE = CREATE_TABLE.substring(0,CREATE_TABLE.length()-1)+")";
        Log.i("create table",CREATE_TABLE);

    }

    public void setVALUES(String ... values){
        VALUES = "'";
        for (int i=0;i<values.length;i++){
            VALUES+=values[i]+"','";
        }
        VALUES = VALUES.substring(0,VALUES.length()-2);
        Log.i("values",VALUES);
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context,this);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String ... values) {
        setVALUES(values);
        String query = "INSERT INTO "+TABLE_NAME+" values("+VALUES+")";
        database.execSQL(query);
        Log.i("Insert query",query);
    }

   public Cursor fetch() {
        String query = "select * from "+TABLE_NAME;
        Log.i("Query for selection",query);
        Cursor cursor = database.rawQuery(query,null);
        /*if (cursor != null) {
            cursor.move();
        }*/
        return cursor;
    }

    public void showDetails(){
        Cursor cursor =fetch();
        Log.i("Num rows",Integer.toString(cursor.getCount()));
        while (cursor.moveToNext()) {
            String col_values = " ";
            for (int i =0;i<cursor.getColumnCount();i++){
                col_values+=cursor.getString(i)+",";
            }
            Log.i("row: ",col_values);
        }


    }

}
