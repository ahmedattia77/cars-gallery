package com.example.carsgallery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataBaseAccess {

    private SQLiteDatabase sqLiteDatabase;
    private SQLiteOpenHelper sqLiteOpenHelper;
    private static DataBaseAccess instance;

    private DataBaseAccess (Context context){
    sqLiteOpenHelper = new DataBase(context);
    }

    public static DataBaseAccess getInstance (Context context){
        if (instance == null)
            instance = new DataBaseAccess(context);

        return instance;
    }

    public Boolean openDataBase(){
        sqLiteDatabase = sqLiteOpenHelper.getWritableDatabase();
        return true;
    }

    public void closeDataBase(){
        assert (sqLiteDatabase != null);
        sqLiteDatabase.close();
    }


    public boolean insertCar (Car car){

        ContentValues values = new ContentValues();

        values.put(DataBase.CAR_CLN_TABLE_MODEL , car.getModel());
        values.put(DataBase.CAR_CLN_TABLE_COLOR , car.getColor());
        values.put(DataBase.CAR_CLN_TABLE_DESCRIPTION , car.getDescription());
        values.put(DataBase.CAR_CLN_TABLE_IMAGE , car.getImage());
        values.put(DataBase.CAR_CLN_TABLE_DPL , car.getDPL());

        long result = sqLiteDatabase.insert(DataBase.TABLE_NAME,null,values);

        return result != -1;
    }

    public boolean select (Car car){

        ContentValues values = new ContentValues();

        values.put(DataBase.CAR_CLN_TABLE_MODEL , car.getModel());
        values.put(DataBase.CAR_CLN_TABLE_COLOR , car.getColor());
        values.put(DataBase.CAR_CLN_TABLE_DESCRIPTION , car.getDescription());
        values.put(DataBase.CAR_CLN_TABLE_IMAGE , car.getImage());
        values.put(DataBase.CAR_CLN_TABLE_DPL , car.getDPL());

        String args [] ={car.getId()+""};
        int result = sqLiteDatabase.update(DataBase.TABLE_NAME,values,"id=?",args);

        return result > 0;
    }


    public long count(){

        return DatabaseUtils.queryNumEntries(sqLiteDatabase ,DataBase.NAME_DS);
    }

    public boolean delete (Car car){

        ContentValues values = new ContentValues();
        // sending dataInformation in the arr and send the array as a condition
        // we can't send the condition directly because it can be manipulated
        String args [] ={car.getId()+""};
        int result = sqLiteDatabase.delete(DataBase.TABLE_NAME,"id=? AND color",args);

        return result > 0;
    }


    public ArrayList<Car> getCars() {
        ArrayList<Car> cars = new ArrayList<>();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+DataBase.TABLE_NAME,null);

        if (cursor.moveToFirst()){
            do {
                int id = cursor.getInt(0);
                String model = cursor.getString(1);
                String color = cursor.getString(2);
                String description = cursor.getString(3);
                String image = cursor.getString(4);
                double DPL = cursor.getDouble(5);

                Car car = new Car(id,model,color,description,image,DPL);
                cars.add(car);
            }
            while (cursor.moveToNext());
            cursor.close();
        }

        return cars;
    }

    public Car getCar(int carId) {
        //ArrayList<Car> cars = new ArrayList<>();
        Car car = new Car();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+DataBase.TABLE_NAME+" WHERE "+DataBase.CAR_CLN_TABLE_ID+"=?",new String[]{String.valueOf(carId)});

        if (cursor.moveToFirst()){

                int id = cursor.getInt(0);
                String model = cursor.getString(1);
                String color = cursor.getString(2);
                String description = cursor.getString(3);
                String image = cursor.getString(4);
                double DPL = cursor.getDouble(5);

                car = new Car(id,model,color,description,image,DPL);
            cursor.close();
            return  car;
        }

        return null;
    }

    public ArrayList<Car>  searchCars(String modelSearch) {
        ArrayList<Car> cars = new ArrayList<>();
        //String [] arg = {model};
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+DataBase.TABLE_NAME+" WHERE "+DataBase.CAR_CLN_TABLE_MODEL+"=?",new String[]{modelSearch});

        if (cursor.moveToFirst()){
            do {
                int id = cursor.getInt(0);
                String model = cursor.getString(1);
                String color = cursor.getString(2);
                String description = cursor.getString(3);
                String image = cursor.getString(4);
                double DPL = cursor.getDouble(5);

                Car car = new Car(id,model,color,description,image,DPL);
                cars.add(car);

            }
            while (cursor.moveToNext());
            cursor.close();
        }

        return cars;
    }
}
