package com.tdf.tdfapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;


public class DBUtils {
    SQLiteDatabase db=null;
    Cursor cr;
    DBUtils(){
        db=openOrCreateDatabase(R.string.app_name+"/databases/TDFdta.db", null,null);

    }
    void createTables(){
        db.execSQL("create table if not exists person_details(person_id varchar(10) primary key,  name varchar(30),isBeneficiary varchar(5), gender varchar(10), village varchar(20), year date, contact_no varchar(15))");
        db.execSQL("create table if not exists household_info(person_id varchar(10),religion varchar(10), caste varchar(20), tribe varchar(20), income decimal, no_of_members int, MANREGA_card int, ration_car int, adhaar_card int, FOREIGN KEY(person_id) REFERENCES person_details(person_id) )");
        db.execSQL("create table if not exists farm_assets(personid varchar(10),\n" +
                "    asset_name varchar(30),\n" +
                "    before_ij varchar(10),\n" +
                "    before_no varchar(3),\n" +
                "    after_ij varchar(10),\n" +
                "    after_no varchar(3),\n" +
                "    current_value varchar(10),\n" +
                "    FOREIGN KEY(person_id) REFERENCES person_details(person_id)\n" +
                "    );");

    }
    void insertValues(String tablename,String values){
        String query = "insert into "+tablename+" values("+values+")";
        db.execSQL(query);
    }
    void closeConn(){
        db.close();
    }
    void displayData(String tablename){
        cr = db.rawQuery("Select * from "+tablename,null);
        while (cr.moveToNext()){
            Log.i("person id:",cr.getString(1));

        }

    }


}
