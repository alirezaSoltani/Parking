package com.example.alireza.parking.Activity;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.alireza.parking.Adapter.GharardadListViewAdapter;
import com.example.alireza.parking.DataBase.DataBaseHandler;
import com.example.alireza.parking.Model.Gharardad;
import com.example.alireza.parking.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ManageGharardadActivity extends AppCompatActivity {
    static ListView listView;
    GharardadListViewAdapter adapter;
    DataBaseHandler dataBaseHandler;
    ArrayList<Gharardad> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_gharardad);
        init();
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ManageGharardadActivity.this);
                builder.setMessage("آیا میخواهید این مورد را حذف کنید؟");
                builder.setPositiveButton("بله", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dataBaseHandler.deleteGaharardadByID(list.get(position).getId());
                        Toast.makeText(ManageGharardadActivity.this, "قرار داد با موفقیت حذف شد", Toast.LENGTH_SHORT).show();
                        list.clear();
                        list.addAll(dataBaseHandler.getAllGharardadNBaygani());
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("خیر", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
                return false;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ManageGharardadActivity.this,GharardadDetailsActivity.class);
                intent.putExtra("id",list.get(position).getId());
                startActivity(intent);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater findMenuItems = getMenuInflater();
        findMenuItems.inflate(R.menu.gharardad_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.gharardad_baygani_menu) {

            Intent intent = new Intent(ManageGharardadActivity.this,GharadadAddBayganiActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.gharardad_show_baygani_menu){
            Intent intent = new Intent(ManageGharardadActivity.this,GharardadBayganiStatusActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.gharardad_report_baygani_menu){
            getWholeListViewItemsToBitmap();
        }

            return super.onOptionsItemSelected(item);
    }
    private void init() {
        list = new ArrayList<>();
        listView = (ListView)findViewById(R.id.gharardad_list_view);
        dataBaseHandler = new DataBaseHandler(ManageGharardadActivity.this);
        list.addAll(dataBaseHandler.getAllGharardadNBaygani());
        adapter = new GharardadListViewAdapter(ManageGharardadActivity.this,list);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        list.clear();
        list.addAll(dataBaseHandler.getAllGharardadNBaygani());
        adapter.notifyDataSetChanged();
    }




    public void getWholeListViewItemsToBitmap() {

        ListView listview    = ManageGharardadActivity.listView;
        ListAdapter adapter  = listview.getAdapter();
        int itemscount       = adapter.getCount();
        int allitemsheight   = 0;
        List<Bitmap> bmps    = new ArrayList<Bitmap>();

        for (int i = 0; i < itemscount; i++) {

            View childView      = adapter.getView(i, null, listview);
            childView.measure(View.MeasureSpec.makeMeasureSpec(listview.getWidth(), View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

            childView.layout(0, 0, childView.getMeasuredWidth(), childView.getMeasuredHeight());
            childView.setDrawingCacheEnabled(true);
            childView.buildDrawingCache();
            bmps.add(childView.getDrawingCache());
            allitemsheight+=childView.getMeasuredHeight();
        }
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg";
        Bitmap bigbitmap    = Bitmap.createBitmap(listview.getMeasuredWidth(), allitemsheight, Bitmap.Config.ARGB_8888);
        Canvas bigcanvas    = new Canvas(bigbitmap);

        Paint paint = new Paint();
        int iHeight = 0;

        for (int i = 0; i < bmps.size(); i++) {
            Bitmap bmp = bmps.get(i);
            bigcanvas.drawBitmap(bmp, 0, iHeight, paint);
            iHeight+=bmp.getHeight();

            bmp.recycle();
            bmp=null;
        }

        File imageFile = new File(mPath);

        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bigbitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();
            Toast.makeText(ManageGharardadActivity.this, "asdasdasdas", Toast.LENGTH_SHORT).show();
            shareImage(imageFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
            Toast.makeText(ManageGharardadActivity.this, "No App Available", Toast.LENGTH_SHORT).show();
        }
    }


}
