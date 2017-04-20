package com.example.alireza.parking.Activity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alireza.parking.DataBase.DataBaseHandler;
import com.example.alireza.parking.Model.Gharardad;
import com.example.alireza.parking.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    TextView daramad, gharardad, vorudkhoruj, newGharadad, sob1, asr;
    Bitmap mbitmap;

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
        final String sob = preferences.getString("sob", null);
        String asr = preferences.getString("asr", null);
        if (sob != null) {
            this.sob1.setText("درآمد صبح :" + "\n" + sob);
        }
        if (asr != null) {
            this.asr.setText("درآمد بعد از ظهر :" + "\n" + asr);
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
        this.sob1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeScreenshot();

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String sob = preferences.getString("sob", null);
        String asr = preferences.getString("asr", null);
        if (sob != null) {
            this.sob1.setText("درآمد صبح :" + "\n" + sob);
        }
        if (asr != null) {
            this.asr.setText("درآمد بعد از ظهر :" + "\n" + asr);
        }
    }

    private void init() {
        daramad = (TextView) findViewById(R.id.manage_daramad_txt);
        gharardad = (TextView) findViewById(R.id.manage_gharardad_txt);
        vorudkhoruj = (TextView) findViewById(R.id.manage_vorud_khoruj_txt);
        newGharadad = (TextView) findViewById(R.id.new_gharardad_txt);
        sob1 = (TextView) findViewById(R.id.main_sob);
        asr = (TextView) findViewById(R.id.main_asr);
    }

    private void takeScreenshot() {
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        try {
            // image naming and path  to include sd card  appending name you choose for file
            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg";

            // create bitmap screen capture
            View v1 = getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);

            File imageFile = new File(mPath);

            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();

            shareImage(imageFile);
        } catch (Throwable e) {
            // Several error may come out with file handling or OOM
            e.printStackTrace();
        }
    }
    private void shareImage(File file){
        Uri uri = Uri.fromFile(file);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");

        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, "");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        try {
            startActivity(Intent.createChooser(intent, "Share Screenshot"));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(MainActivity.this, "No App Available", Toast.LENGTH_SHORT).show();
        }
    }
}

