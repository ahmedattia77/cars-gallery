package com.example.carsgallery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_car_activity);

        toolbar = findViewById(R.id.viewCar_toolbar_tb);
        model = findViewById(R.id.viewCar_model_et);
        color = findViewById(R.id.viewCar_color_et);
        DPL = findViewById(R.id.viewCar_distancePerLiter_et);
        description = findViewById(R.id.viewCar_decription_et);

        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.view_car_menu ,menu);
        return true;
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