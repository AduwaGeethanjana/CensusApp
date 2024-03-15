package com.example.censusap_1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

public class DataActivity extends AppCompatActivity {

    EditText name,age;
    RadioButton male ,female;
    Button camera,submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        name =findViewById(R.id.name);
        age =findViewById(R.id.age);

        male=findViewById(R.id.male);
        female=findViewById(R.id.female);

        camera =findViewById(R.id.camera_button);
        submit =findViewById(R.id.submitbutton);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB =new MyDatabaseHelper(DataActivity.this);
                myDB.addPerson(name.getText().toString().trim(),
                        Integer.valueOf(age.getText().toString().trim()));
            }
        });








}
}