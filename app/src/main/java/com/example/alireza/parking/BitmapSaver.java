package com.example.alireza.parking;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.Toast;

import com.example.alireza.parking.Activity.ReportGharardadActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by AliReza on 4/27/2017.
 */

public class BitmapSaver extends AsyncTask<Void, Void, Void>
{
    public static final String TAG ="BitmapSaver";

    private Bitmap bmp;

    private Context ctx;

    private File pictureFile;

    public BitmapSaver(Context paramContext , Bitmap paramBitmap)
    {
        ctx = paramContext;

        bmp = paramBitmap;
    }

    /** Create a File for saving an image or video */
    private  File getOutputMediaFile()
    {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory()
                + "/Android/data/"
                + ctx.getPackageName()
                + "/Files");

        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmm").format(new Date());
        File mediaFile;
        String mImageName="MI_"+ timeStamp +".jpg";
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + mImageName);
        return mediaFile;

    }
    protected Void doInBackground(Void... paramVarArgs)
    {
        this.pictureFile = getOutputMediaFile();

        if (this.pictureFile == null) { return null; }

        try
        {
            FileOutputStream localFileOutputStream = new FileOutputStream(this.pictureFile);
            this.bmp.compress(Bitmap.CompressFormat.PNG, 90, localFileOutputStream);
            localFileOutputStream.close();
        }
        catch (FileNotFoundException localFileNotFoundException)
        {
            return null;
        }
        catch (IOException localIOException)
        {
        }
        return null;
    }

    protected void onPostExecute(Void paramVoid)
    {
        super.onPostExecute(paramVoid);

        try
        {
            //it will help you broadcast and view the saved bitmap in Gallery
            this.ctx.sendBroadcast(new Intent("android.intent.action.MEDIA_MOUNTED", Uri
                    .parse("file://" + Environment.getExternalStorageDirectory())));

            Toast.makeText(this.ctx, "File saved", Toast.LENGTH_LONG).show();
            shareImage(pictureFile);
            return;
        }
        catch (Exception localException1)
        {
            try
            {
                Context localContext = this.ctx;
                String[] arrayOfString = new String[1];
                arrayOfString[0] = this.pictureFile.toString();
                MediaScannerConnection.scanFile(localContext, arrayOfString, null,
                        new MediaScannerConnection.OnScanCompletedListener()
                        {
                            public void onScanCompleted(String paramAnonymousString ,
                                                        Uri paramAnonymousUri)
                            {
                            }
                        });
                return;
            }
            catch (Exception localException2)
            {
            }
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
            ctx.startActivity(Intent.createChooser(intent, "Share Screenshot"));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(ctx, "No App Available", Toast.LENGTH_SHORT).show();
        }
    }
}

