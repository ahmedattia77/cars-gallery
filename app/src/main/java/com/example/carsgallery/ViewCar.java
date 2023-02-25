package com.example.carsgallery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.zip.Inflater;

public class ViewCar extends AppCompatActivity {

    private TextInputEditText model , color , description , DPL;
    private Toolbar toolbar;
    private ImageView imageCar;
    private int carId = -1;
    private DataBaseAccess dataBaseAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_car_activity);

        toolbar = findViewById(R.id.viewCar_toolbar_tb);
        model = findViewById(R.id.viewCar_model_et);
        color = findViewById(R.id.viewCar_color_et);
        DPL = findViewById(R.id.viewCar_distancePerLiter_et);
        description = findViewById(R.id.viewCar_decription_et);
        imageCar = findViewById(R.id.viewCar_imageCar_iv);

        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        carId = intent.getIntExtra(MainActivity.CAR_KEY , -1);

        dataBaseAccess = DataBaseAccess.getInstance(this);


            if (carId == -1) {
                // add
                clearFields();
                turnOnFields();
            }else{
                //display
                Car car = new Car();
                if (dataBaseAccess.openDataBase())
                    car = dataBaseAccess.getCar(carId);
                dataBaseAccess.closeDataBase();
                turnOffFields();

                if (car!=null){
                    fillFields(car);
                }


            }

    }

    private void fillFields(Car car) {

        model.setText(car.getModel());
        color.setText(car.getColor());
        DPL.setText(String.valueOf(car.getDPL()));
        description.setText(car.getDescription());
        //imageCar.setImageURI(Uri.parse(car.getImage()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.view_car_menu ,menu);

        MenuItem edit = menu.findItem(R.id.view_car_menu_edit);
        MenuItem save = menu.findItem(R.id.view_car_menu_save);
        MenuItem delete = menu.findItem(R.id.view_car_menu_delete);

        if (carId == -1){
            edit.setVisible(false);
            delete.setVisible(false);
            save.setVisible(true);
        }else{
            edit.setVisible(true);
            delete.setVisible(true);
            save.setVisible(false);
        }

        return true;
    }

    void turnOnFields(){
        model.setEnabled(true);
        color.setEnabled(true);
        DPL.setEnabled(true);
        description.setEnabled(true);
        imageCar.setEnabled(true);
    }
    void turnOffFields(){
        model.setEnabled(false);
        color.setEnabled(false);
        DPL.setEnabled(false);
        description.setEnabled(false);
        imageCar.setEnabled(false);
    }
    void clearFields(){
        model.setText("");
        color.setText("");
        DPL.setText("");
        description.setText("");
        imageCar.setImageURI(null);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.view_car_menu_save :
                // code
            case R.id.view_car_menu_edit :
                // code
            case R.id.view_car_menu_delete :
                // code

        }
        return false;
    }
}