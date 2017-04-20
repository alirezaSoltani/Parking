package com.example.alireza.parking.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.alireza.parking.DataBase.DataBaseHandler;
import com.example.alireza.parking.Model.Gharardad;
import com.example.alireza.parking.R;

public class MainActivity extends AppCompatActivity {
    TextView daramad,gharardad,vorudkhoruj,newGharadad,sob,asr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

//        DataBaseHandler dataBaseHandler = new DataBaseHandler(MainActivity.this);
//        for (int i = 0;i<15 ;i++){
//
//            Gharardad g = new Gharardad("1","1","1","1","1","1");
//
//            dataBaseHandler.insertGharardad(g);
//        }
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String sob = preferences.getString("sob", null);
        String asr = preferences.getString("asr", null);
        if (sob!=null){
            this.sob.setText("درآمد صبح :"+"\n"+sob);
        }
        if (asr!=null){
            this.asr.setText("درآمد بعد از ظهر :"+"\n"+asr);
        }
        daramad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ManageDaramadActivity.class);
                startActivity(intent);
            }
        });
        gharardad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ManageGharardadActivity.class);
                startActivity(intent);
            }
        });
        vorudkhoruj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ManageVorudKhorujActivity.class);
                startActivity(intent);
            }
        });
        newGharadad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewGharadadActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String sob = preferences.getString("sob", null);
        String asr = preferences.getString("asr", null);
        if (sob!=null){
            this.sob.setText("درآمد صبح :"+"\n"+sob);
        }
        if (asr!=null){
            this.asr.setText("درآمد بعد از ظهر :"+"\n"+asr);
        }
    }

    private void init() {
        daramad = (TextView)findViewById(R.id.manage_daramad_txt);
        gharardad = (TextView)findViewById(R.id.manage_gharardad_txt);
        vorudkhoruj = (TextView)findViewById(R.id.manage_vorud_khoruj_txt);
        newGharadad = (TextView)findViewById(R.id.new_gharardad_txt);
        sob = (TextView) findViewById(R.id.main_sob);
        asr = (TextView) findViewById(R.id.main_asr);
    }
}
