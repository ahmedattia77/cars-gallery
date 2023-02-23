package com.example.carsgallery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

public class  DataBase extends SQLiteAssetHelper {

    public static final String NAME_DS = "cars.db";
    public static final int VERSION_DS = 1;

    public static final String TABLE_NAME = "car";
    public static final String CAR_CLN_TABLE_ID = "id";
    public static final String CAR_CLN_TABLE_MODEL = "model";

    public static final String CAR_CLN_TABLE_DESCRIPTION = "description";
    public static final String CAR_CLN_TABLE_COLOR = "color";
    public static final String CAR_CLN_TABLE_IMAGE = "image";
    public static final String CAR_CLN_TABLE_DPL = "DistancePerLetter";

    public DataBase (Context context){
        super(context,NAME_DS,null,VERSION_DS);

    }

//    @Override
//    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        sqLiteDatabase.execSQL("CREATE table "+TABLE_NAME+" ("+CAR_CLN_TABLE_ID+" INTEGER primary key autoincrement," +
//                ""+CAR_CLN_TABLE_MODEL+" TEXT UNIQUE , "+CAR_CLN_TABLE_COLOR+" TEXT ,"+CAR_CLN_TABLE_DESCRIPTION+" TEXT ,"+CAR_CLN_TABLE_IMAGE+" TEXT, "+CAR_CLN_TABLE_DPL+" REAL) ");
//    }

//    @Override
//    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        sqLiteDatabase.execSQL("drop table if exists "+ TABLE_NAME);
//        onCreate(sqLiteDatabase);
//    }


}
