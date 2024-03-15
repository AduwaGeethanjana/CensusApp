package com.example.censusap_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ViewActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    RecyclerView.LayoutManager mLayoutManager;
    MyDatabaseHelper myDB;
    ArrayList<String> person_id,name,age,gender;
    CustomAdapter customAdapter;

    Button submit;

    private FirebaseFirestore firebaseDB = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);




     myDB = new MyDatabaseHelper(ViewActivity.this);
     person_id = new ArrayList<>();
     name = new ArrayList<>();
     age = new ArrayList<>();
     gender = new ArrayList<>();


     storeDatainArray();
     recyclerView.setHasFixedSize(true);
     mLayoutManager = new LinearLayoutManager(this);
     customAdapter =new CustomAdapter(ViewActivity.this,person_id,name,age,gender);

     recyclerView.setLayoutManager(mLayoutManager);
     recyclerView.setAdapter(customAdapter);


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
              gender.add(cursor.getString(3));

            }
        }
    }

    public void upadateClick(View view) {
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount()==0) {
            Toast.makeText(ViewActivity.this, "No data entered", Toast.LENGTH_SHORT).show();
            return;
        }else{

            Map<String,Object> data = new HashMap<>();
            while (cursor.moveToNext()){
                String username = cursor.getString(1);
                data.put("Name",cursor.getString(1)+"\n");
                data.put("Age",cursor.getString(2)+"\n");
                        data.put("Gender",cursor.getString(3)+"\n");
//                        data.put("Profile Photo",cursor.getString(3)+"\n");


                firebaseDB.collection("Cencus APP").document(username).set(data)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(ViewActivity.this, "Saved to cloud", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ViewActivity.this, "Error in saving to cloud!", Toast.LENGTH_SHORT).show();

                            }
                        });
            }
        }


    }
}