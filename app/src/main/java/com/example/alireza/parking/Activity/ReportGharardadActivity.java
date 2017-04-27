package com.example.alireza.parking.Activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alireza.parking.DataBase.DataBaseHandler;
import com.example.alireza.parking.Model.Gharardad;
import com.example.alireza.parking.R;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

public class ReportGharardadActivity extends AppCompatActivity {
    int id;
    DataBaseHandler handler;
    Gharardad gharardad;
    TextView reportText;
    Typeface font;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_gharardad);
        init();
        if (id != -1){
            gharardad = handler.getGharardadBySTId(id);
            reportText.setText("آقای/خانم "+gharardad.getName()+" دارنده خودروی "+gharardad.getCar_Type()+" به شماره پلاک "+gharardad.getCar_pelak()+" بلا مانع میباشد."+"\n"+" مبلغ قرارداد "+gharardad.getMablagh()+"\n"+" ریال میباشد.");
        }

    }

    private void init() {
        Intent intent = getIntent();
        handler = new DataBaseHandler(ReportGharardadActivity.this);
        id = intent.getIntExtra("id",-1);
        font = Typeface.createFromAsset(this.getAssets(), "fonts/byekan+.ttf");
        reportText = (TextView)findViewById(R.id.report_textView);
        reportText.setTypeface(font);
        reportText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeScreenshot();
            }
        });
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
            Toast.makeText(this, e.getMessage()+"", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(ReportGharardadActivity.this, "No App Available", Toast.LENGTH_SHORT).show();
        }
    }
}
