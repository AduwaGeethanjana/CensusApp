package com.example.censusap_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {

    RecyclerView recyclerView;


    MyDatabaseHelper myDB;
    ArrayList<String> person_id,name,age;
    CustomAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recyclerView =findViewById(R.id.recyclerview);
        /*addbutton =findViewById(R.id.addbutton);

        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewActivity.this, DataActivity.class);
                startActivity(intent);
            }
        });*/

     myDB = new MyDatabaseHelper(ViewActivity.this);
     person_id = new ArrayList<>();
     name = new ArrayList<>();
     age = new ArrayList<>();

     storeDatainArray();

     customAdapter =new CustomAdapter(ViewActivity.this,person_id,name,age);

     recyclerView.setAdapter(customAdapter);
     recyclerView.setLayoutManager(new LinearLayoutManager(ViewActivity.this));

    }

    void storeDatainArray(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
        else{
            while (cursor.moveToNext()){

              person_id.add(cursor.getString(0));
              name.add(cursor.getString(1));
              age.add(cursor.getString(2));

            }
        }
    }
}