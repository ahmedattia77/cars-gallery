package com.example.carsgallery;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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
    private static final int ADD_CAR_REQUEST_CODE = 1 ;
    private static final int DELETE_CAR_REQUEST_CODE = 11 ;
    public static final String CAR_KEY = "car_key" ;
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private FloatingActionButton floatingButton;
    private RecycleViewAdapter recyclerAdapter;
    private DataBaseAccess dataBaseAccess;
    private ArrayList<Car> listOfCars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.main_recycleView_rv);
        toolbar = findViewById(R.id.main_toolbar_tb);
        floatingButton = findViewById(R.id.main_floatingActionButton_fb);
        setSupportActionBar(toolbar);

        dataBaseAccess = DataBaseAccess.getInstance(this);
        if (dataBaseAccess.openDataBase())
                listOfCars = dataBaseAccess.getCars();
        dataBaseAccess.closeDataBase();
        recyclerAdapter = new RecycleViewAdapter(listOfCars);
        recyclerView.setAdapter(recyclerAdapter);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this , 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        //layoutManager.smoothScrollToPosition(recyclerView , getDefaultViewModelCreationExtras(),2000  );

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
}