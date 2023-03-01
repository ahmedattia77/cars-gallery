package com.example.carsgallery;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.QuickContactBadge;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    private static final int ADD_CAR_REQUEST_CODE = 11 ;
    private static final int EDIT_CAR_REQUEST_CODE = 24 ;
    private static final int PERMISSION_REQUEST_CODE = 56 ;

    public static final String CAR_KEY = "car_key" ;
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private FloatingActionButton floatingButton;
    private RecycleViewAdapter recyclerAdapter;
    private DataBaseAccess dataBaseAccess;
    //private ArrayList<Car> listOfCars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.main_recycleView_rv);
        toolbar = findViewById(R.id.main_toolbar_tb);
        floatingButton = findViewById(R.id.main_floatingActionButton_fb);
        setSupportActionBar(toolbar);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this , new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}
            , PERMISSION_REQUEST_CODE);
        }

        dataBaseAccess = DataBaseAccess.getInstance(this);

            dataBaseAccess.openDataBase();
            ArrayList <Car> cars = new ArrayList<>();
            cars = dataBaseAccess.getAllCars();
            dataBaseAccess.closeDataBase();


        recyclerAdapter = new RecycleViewAdapter(cars, new RecycleAdapterOnClickListener() {
            @Override
            public void onClick(int carId) {
                Intent intent = new Intent(getBaseContext() , ViewCar.class);
                intent.putExtra(CAR_KEY , carId);
                startActivityForResult(intent,EDIT_CAR_REQUEST_CODE);
            }
        });

        recyclerView.setAdapter(recyclerAdapter);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext() , ViewCar.class);
                startActivityForResult(intent , ADD_CAR_REQUEST_CODE);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu , menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.mainMenu_search).getActionView();
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MainActivity.this, "submit", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(MainActivity.this, "on change ", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                Toast.makeText(MainActivity.this, "close", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_CAR_REQUEST_CODE && resultCode == 13 || requestCode == EDIT_CAR_REQUEST_CODE && resultCode == 14) {
            dataBaseAccess = DataBaseAccess.getInstance(this);
            dataBaseAccess.openDataBase();
            ArrayList<Car> cars = new ArrayList<>();
            cars = dataBaseAccess.getAllCars();
            dataBaseAccess.closeDataBase();
            recyclerAdapter.setCars(cars);
            recyclerAdapter.notifyDataSetChanged();
            Toast.makeText(this, "added/modified successfully", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                }
        }
    }
}