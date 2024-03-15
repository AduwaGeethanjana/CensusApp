package com.example.censusap_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import yuku.ambilwarna.AmbilWarnaDialog;

public class PreferencesActivity extends AppCompatActivity {

    LinearLayout preflayout;
    int mDefaultColor;
    Button colorbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);


        preflayout = (LinearLayout) findViewById(R.id.preferenceslayout);
        mDefaultColor = ContextCompat.getColor(PreferencesActivity.this, com.google.android.material.R.color.design_default_color_primary);
        colorbtn = (Button) findViewById(R.id.colorbtn);

        colorbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openColorPicker();
            }
        });



    }

    public void openColorPicker(){
        AmbilWarnaDialog colorPicker =new AmbilWarnaDialog(this, mDefaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
              mDefaultColor =color;
              preflayout.setBackgroundColor(mDefaultColor);
            }
        });

        colorPicker.show();
    }
}

















/*Button redbutton,greenbutton;
        LinearLayout preflayout;

        redbutton = findViewById(R.id.redbtn);
        greenbutton = findViewById(R.id.greenbtn);

        preflayout = findViewById(R.id.preferenceslayout);

        redbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 preflayout.setBackgroundColor(Color.RED);


            }
        });


        greenbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preflayout.setBackgroundColor(Color.GREEN);


            }
        });*/