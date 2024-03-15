package com.example.censusap_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button adddata =(Button) findViewById(R.id.adddata);
        Button preferences =(Button) findViewById(R.id.preferences);
        Button viewdata =(Button) findViewById(R.id.viewdata);

        adddata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  Intent adddataintent = new Intent(HomeActivity.this, DataActivity.class);
                    startActivity(adddataintent);

            }
        });

        preferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent preferencesintent = new Intent(HomeActivity.this, PreferencesActivity.class);
                startActivity(preferencesintent);

            }
        });

        viewdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewintent = new Intent(HomeActivity.this, ViewActivity.class);
                startActivity(viewintent);

            }
        });
    }
}