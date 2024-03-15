package com.example.censusap_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class DataActivity extends AppCompatActivity {

    EditText name,age;
    RadioGroup radioGroup;
    Button camera,submit;
    ImageView imageView;

    RadioButton genderradioButton;
    String encodedImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        name =findViewById(R.id.name);
        age =findViewById(R.id.age);

        radioGroup = findViewById(R.id.radioGroup);

        camera =findViewById(R.id.camera_button);
        submit =findViewById(R.id.submitbutton);

imageView = findViewById(R.id.imageView);

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((ActivityCompat.checkSelfPermission(
                        DataActivity.this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED)){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{
                                Manifest.permission.CAMERA,
                        },123);
                    }
                }
                else{
                    Intent cameraIntent=new Intent();
                    cameraIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent,118);
                }

            }

        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedId = radioGroup.getCheckedRadioButtonId();
                genderradioButton = (RadioButton) findViewById(selectedId);
                if(selectedId==-1){
                    Toast.makeText(DataActivity.this, "Select your gender", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DataActivity.this, "", Toast.LENGTH_SHORT).show();
                    MyDatabaseHelper myDB =new MyDatabaseHelper(DataActivity.this);
                    myDB.addPerson(name.getText().toString().trim(),
                            Integer.valueOf(age.getText().toString().trim()),genderradioButton.getText().toString()
                    );
                }
                clearForm();
                
            }
        });



}

    private void clearForm() {
        EditText nameField = findViewById(R.id.name);
        EditText ageField = findViewById(R.id.age);
        RadioGroup genderRadioGroup = findViewById(R.id.radioGroup);
        genderRadioGroup.clearCheck();
        nameField.setText("");
        ageField.setText("");


        Toast.makeText(this, "Form cleared", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==118&& resultCode==RESULT_OK){
            Bitmap photo= (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] b = baos.toByteArray();
            encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
            Toast.makeText(this, "Image saved in SharedPreferences", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "could not captured", Toast.LENGTH_SHORT).show();
        }
    }
}