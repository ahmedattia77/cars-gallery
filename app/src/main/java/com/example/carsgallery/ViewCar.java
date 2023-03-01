package com.example.carsgallery;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.zip.Inflater;

public class ViewCar extends AppCompatActivity {

    private static final int PICK_IMAGE_CODE = 1;
    public static final int ADD_CAR_RESULT_CODE = 2;
    public static final int EDIT_CAR_RESULT_CODE = 3;
    private Uri imagePath;
    private TextInputEditText model, color, description, DPL;
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
        description = findViewById(R.id.viewCar_description_et);
        imageCar = findViewById(R.id.viewCar_imageCar_iv);

        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        carId = intent.getIntExtra(MainActivity.CAR_KEY, -1);

        dataBaseAccess = DataBaseAccess.getInstance(this);


        if (carId == -1) {
            // add
            clearFields();
            turnOnFields();
        } else {
            //display
            Car car = new Car();
            if (dataBaseAccess.openDataBase())
                car = dataBaseAccess.getCar(carId);
            dataBaseAccess.closeDataBase();
            turnOffFields();

            if (car != null) {
                fillFields(car);
            }
        }

        imageCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_IMAGE_CODE);
            }
        });
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
        getMenuInflater().inflate(R.menu.view_car_menu, menu);

        MenuItem edit = menu.findItem(R.id.view_car_menu_edit);
        MenuItem save = menu.findItem(R.id.view_car_menu_save);
        MenuItem delete = menu.findItem(R.id.view_car_menu_delete);

        if (carId == -1) {
            // case adding
            edit.setVisible(false);
            delete.setVisible(false);
            save.setVisible(true);
        } else {
            // case modifying
            edit.setVisible(true);
            delete.setVisible(false);
            save.setVisible(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String model_, color_, image_ ="", description_;
        Double DPL_;
        Boolean result;
        Car car = new Car();

        dataBaseAccess = DataBaseAccess.getInstance(this);

        switch (item.getItemId()) {

            case R.id.view_car_menu_save:
                model_ = model.getText().toString();
                color_ = color.getText().toString();
                description_ = description.getText().toString();
                DPL_ = Double.parseDouble(this.DPL.getText().toString());
                if (imagePath != null)
                    image_ = imagePath.toString();

                car = new Car(carId ,model_ , color_ , description_, image_ , DPL_);

                dataBaseAccess.openDataBase();
                if (carId == -1){
                    result = dataBaseAccess.insertCar(car);
                    if (result) {
                        setResult(13, null);
                        finish();
                    } else
                        Toast.makeText(this, "insertion failed", Toast.LENGTH_SHORT).show();

                }else {
                    result = dataBaseAccess.select(car);
                    if (result) {
                        setResult(14, null);
                        finish();
                    } else
                        Toast.makeText(this, "edit failed", Toast.LENGTH_SHORT).show();
                }
                dataBaseAccess.closeDataBase();
            return true;

            case R.id.view_car_menu_edit:
                turnOnFields();
                MenuItem edit = toolbar.getMenu().findItem(R.id.view_car_menu_edit);
                MenuItem save = toolbar.getMenu().findItem(R.id.view_car_menu_save);
                MenuItem delete = toolbar.getMenu().findItem(R.id.view_car_menu_delete);

                save.setVisible(true);
                delete.setVisible(true);
                edit.setVisible(false);
                return true;

            case R.id.view_car_menu_delete:
                dataBaseAccess.openDataBase();
                car.setId(carId);
                dataBaseAccess.delete(car);
                dataBaseAccess.closeDataBase();
                setResult(14 ,null);
                finish();
                return true;
        }
        return false;
    }

    void turnOnFields() {
        model.setEnabled(true);
        color.setEnabled(true);
        DPL.setEnabled(true);
        description.setEnabled(true);
        imageCar.setEnabled(true);
    }

    void turnOffFields() {
        model.setEnabled(false);
        color.setEnabled(false);
        DPL.setEnabled(false);
        description.setEnabled(false);
        imageCar.setEnabled(false);
    }

    void clearFields() {
        model.setText("");
        color.setText("");
        DPL.setText("");
        description.setText("");
        imageCar.setImageURI(null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                imagePath = data.getData();
                imageCar.setImageURI(data.getData());
            }
        }
    }
}