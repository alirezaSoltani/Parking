package com.example.alireza.parking.Activity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alireza.parking.DataBase.DataBaseHandler;
import com.example.alireza.parking.Model.DaramadRuzane;
import com.example.alireza.parking.Model.Gharardad;
import com.example.alireza.parking.R;
import com.example.alireza.parking.SetAppFont;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity {
    TextView daramad, gharardad, vorudkhoruj, newGharadad, sob1, asr;
    Typeface font;
    DataBaseHandler handler;
    private static final int TIME_INTERVAL = 500; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;
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
        ArrayList<DaramadRuzane> arrayList = new ArrayList<>();
        arrayList.addAll(handler.getAllDaramadRuzaneNBaygani());
        for (int i = arrayList.size()-1;i>=0;i--){

            if (arrayList.get(i).getShift().equals("صبح")) {

                sob1.setText("آخرین درآمد صبح :" + "\n" + arrayList.get(i).getMablagh());
                break;
            }
        }
        for (int i = arrayList.size()-1;i>=0;i--){
            System.out.println(arrayList.get(i).getShift());
            if (arrayList.get(i).getShift().equals("بعد از ظهر")) {
                System.out.println("yes");

                this.asr.setText("آخرین درآمد بعد از ظهر :" + "\n" + arrayList.get(i).getMablagh());
                break;
            }
        }
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
//        final String sob = preferences.getString("sob", null);
//        String asr = preferences.getString("asr", null);
//        if (sob != null) {
//            this.sob1.setText("درآمد صبح :" + "\n" + sob);
//        }
//        if (asr != null) {
//            this.asr.setText("درآمد بعد از ظهر :" + "\n" + asr);
//        }
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
        ArrayList<DaramadRuzane> arrayList = new ArrayList<>();
        arrayList.addAll(handler.getAllDaramadRuzaneNBaygani());
        for (int i = arrayList.size()-1;i>=0;i--){

            if (arrayList.get(i).getShift().equals("صبح")) {

                sob1.setText("آخرین درآمد صبح :" + "\n" + arrayList.get(i).getMablagh());
                break;
            }
        }
        for (int i = arrayList.size()-1;i>=0;i--){
            System.out.println(arrayList.get(i).getShift());
            if (arrayList.get(i).getShift().equals("بعد از ظهر")) {
                System.out.println("yes");

                this.asr.setText("آخرین درآمد بعد از ظهر :" + "\n" + arrayList.get(i).getMablagh());
                break;
            }
        }
    }
    @Override
    public void onBackPressed()
    {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis())
        {
            super.onBackPressed();
            return;
        }
        else { Toast.makeText(getBaseContext(), "برای خروج دو بار ضربه بزنید", Toast.LENGTH_SHORT).show(); }

        mBackPressed = System.currentTimeMillis();
    }
    private void init() {
        //
        handler = new DataBaseHandler(MainActivity.this);
        font = Typeface.createFromAsset(this.getAssets(), "fonts/byekan+.ttf");
        daramad = (TextView) findViewById(R.id.manage_daramad_txt);
        gharardad = (TextView) findViewById(R.id.manage_gharardad_txt);
        vorudkhoruj = (TextView) findViewById(R.id.manage_vorud_khoruj_txt);
        newGharadad = (TextView) findViewById(R.id.new_gharardad_txt);
        sob1 = (TextView) findViewById(R.id.main_sob);
        asr = (TextView) findViewById(R.id.main_asr);
        daramad.setTypeface(font);
        gharardad.setTypeface(font);
        vorudkhoruj.setTypeface(font);
        newGharadad.setTypeface(font);
        sob1.setTypeface(font);
        asr.setTypeface(font);
        final ViewGroup mContainer = (ViewGroup) findViewById(
                android.R.id.content).getRootView();
        new SetAppFont(this,mContainer);
    }


}

