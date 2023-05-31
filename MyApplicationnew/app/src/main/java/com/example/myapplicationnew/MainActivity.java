package com.example.myapplicationnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText ed_ip;
    Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed_ip=findViewById(R.id.editTextTextPersonName);
        btn_submit=findViewById(R.id.button);
        btn_submit.setOnClickListener(this);



           }

    @Override
    public void onClick(View v) {

        String ip = ed_ip.getText().toString();
        if (ip.length() == 0) {
            ed_ip.setError("Please enter the ipaddress");
        } else {

            SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor ed = sh.edit();
            ed.putString("ip", ip);
            ed.commit();


            Intent ins = new Intent(getApplicationContext(), login.class);
            startActivity(ins);

        }
    }
}