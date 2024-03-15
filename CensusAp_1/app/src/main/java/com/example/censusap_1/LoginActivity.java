package com.example.censusap_1;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    private EditText editText;
    private String password;
    private int errorCount=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onclick(View view) {
        editText = (EditText) findViewById(R.id.password);
        SharedPreferences sharedPreferences = getSharedPreferences("SharedPref",MODE_PRIVATE);
        password = sharedPreferences.getString("password","");

        if(password.equals(editText.getText().toString())){
            Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
        }else {
            editText.setError("Wrong password");
            errorCount++;
            if(errorCount>=3){
                Context context = getApplicationContext();
                CharSequence text = "Wrong password, Closing App";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                LoginActivity.this.finish();
                System.exit(0);
            }
        }
    }
}