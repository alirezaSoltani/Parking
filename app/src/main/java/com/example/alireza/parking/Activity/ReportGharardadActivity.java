package com.example.alireza.parking.Activity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alireza.parking.BitmapSaver;
import com.example.alireza.parking.DataBase.DataBaseHandler;
import com.example.alireza.parking.Model.Gharardad;
import com.example.alireza.parking.R;
import com.example.alireza.parking.SetAppFont;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class ReportGharardadActivity extends AppCompatActivity  {
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
            reportText.setText(" تردد آقای/خانم "+gharardad.getName()+" دارنده خودروی "+gharardad.getCar_Type()+" به شماره پلاک "+gharardad.getCar_pelak()+" بلا مانع میباشد."+"\n"+" مبلغ قرارداد "+gharardad.getMablagh()+"\n"+" ریال میباشد.");
        }

    }

    public static final int REQUEST_EXTERNAL_PERMISSION_CODE = 666;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public static final String[] PERMISSIONS_EXTERNAL_STORAGE = {
            READ_EXTERNAL_STORAGE,
            WRITE_EXTERNAL_STORAGE
    };

    public boolean checkExternalStoragePermission(Activity activity) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            return true;
        }

        int readStoragePermissionState = ContextCompat.checkSelfPermission(activity, READ_EXTERNAL_STORAGE);
        int writeStoragePermissionState = ContextCompat.checkSelfPermission(activity, WRITE_EXTERNAL_STORAGE);
        boolean externalStoragePermissionGranted = readStoragePermissionState == PackageManager.PERMISSION_GRANTED &&
                writeStoragePermissionState == PackageManager.PERMISSION_GRANTED;
        if (!externalStoragePermissionGranted) {
            requestPermissions(PERMISSIONS_EXTERNAL_STORAGE, REQUEST_EXTERNAL_PERMISSION_CODE);
        }

        return externalStoragePermissionGranted;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (requestCode == REQUEST_EXTERNAL_PERMISSION_CODE) {
                if (checkExternalStoragePermission(ReportGharardadActivity.this)) {
                    // Continue with your action after permission request succeed
                    Toast.makeText(this, "yes", Toast.LENGTH_SHORT).show();
                }
            }
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
                takeScreenshot2();
            }
        });
        final ViewGroup mContainer = (ViewGroup) findViewById(
                android.R.id.content).getRootView();
        new SetAppFont(this,mContainer);
    }
    private void takeScreenshot2() {
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
            System.out.println(e.getMessage()+"");
            e.printStackTrace();
        }
    }
    private void takeScreenshot() {
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        try {
            // image naming and path  to include sd card  appending name you choose for file
                String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg";
//            String mPath = ReportGharardadActivity.this.getCacheDir().toString() + "/" + now + ".jpg";

//            File cachePath = new File(ReportGharardadActivity.this.getCacheDir(), "images");
//            String mPath =
//            cachePath.mkdirs(); // don't forget to make the directory
            // create bitmap screen capture
            View v1 = getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);
//
            File imageFile = new File(mPath);
            imageFile.mkdirs();
            FileOutputStream outputStream = new FileOutputStream(imageFile,false);

            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();
            shareImage(imageFile);

//            SaveImage(bitmap);
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//            byte[] byteArray = stream.toByteArray();
//            saveImageOnExternalData(mPath,byteArray);

        } catch (Throwable e) {
            // Several error may come out with file handling or OOM
            Toast.makeText(this, e.getMessage()+"", Toast.LENGTH_SHORT).show();
            System.out.println(e.getMessage()+"");
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
    private void shareBitmap (Bitmap bitmap,String fileName) {
        try {
            File file = new File(ReportGharardadActivity.this.getCacheDir(), fileName + ".png");
            file.setReadable(true, false);
            FileOutputStream fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
            file.setReadable(true, false);
            final Intent intent = new Intent(     android.content.Intent.ACTION_SEND);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            intent.setType("image/png");
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void SaveImage(Bitmap finalBitmap,String filename) throws IOException {

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/saved_images");
        myDir.mkdirs();

        String fname = filename;
        File file = new File(myDir, fname);
        if (file.exists()) file.delete();
        file.createNewFile();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void SaveImage(Bitmap finalBitmap) {

        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
        File myDir = new File(root + "/saved_images");
        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Image-" + n + ".jpg";
        final File file = new File(myDir, fname);

        if (file.exists())
            file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

            Toast.makeText(this, "yess", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }


        // Tell the media scanner about the new file so that it is
        // immediately available to the user.
        MediaScannerConnection.scanFile(this, new String[]{file.toString()}, null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String path, Uri uri) {
                        Log.i("ExternalStorage", "Scanned " + path + ":");
                        Log.i("ExternalStorage", "-> uri=" + uri);
                        shareImage(file);
                    }
                });
    }
    public boolean saveImageOnExternalData(String filePath, byte[] fileData) {

        boolean isFileSaved = false;
        try {
            File f = new File(filePath);
            if (f.exists())
                f.delete();
            f.createNewFile();
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(fileData);
            fos.flush();
            fos.close();
            isFileSaved = true;
            shareImage(f);
            // File Saved
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException");
            e.printStackTrace();
        }

        Toast.makeText(this,isFileSaved+ "", Toast.LENGTH_SHORT).show();
        return isFileSaved;
        // File Not Saved
    }

}
