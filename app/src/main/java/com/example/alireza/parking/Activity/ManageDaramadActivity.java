package com.example.alireza.parking.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.alireza.parking.Adapter.DaramadRuzaneListViewAdapter;
import com.example.alireza.parking.DataBase.DataBaseHandler;
import com.example.alireza.parking.Model.DaramadRuzane;
import com.example.alireza.parking.R;

import java.util.ArrayList;

public class ManageDaramadActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<DaramadRuzane> list;
    DaramadRuzaneListViewAdapter adapter;
    DataBaseHandler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_daramad);
        init();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ManageDaramadActivity.this,DaramadRuzaneDetailsActivity.class);
                intent.putExtra("id",list.get(position).getId());
                startActivity(intent);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ManageDaramadActivity.this);
                builder.setMessage("آیا میخواهید این مورد را حذف کنید؟");
                builder.setPositiveButton("بله", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        handler.deleteDaramadRuzaneByID(list.get(position).getId());
                        Toast.makeText(ManageDaramadActivity.this, "مورد با موفقیت حذف شد", Toast.LENGTH_SHORT).show();
                        list.clear();
                        list.addAll(handler.getAllDaramadRuzaneNBaygani());
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
    }


    private void init() {
        listView = (ListView)findViewById(R.id.manage_daramad_list_view);
        list = new ArrayList<>();
        handler = new DataBaseHandler(ManageDaramadActivity.this);
        list.addAll(handler.getAllDaramadRuzaneNBaygani());
        adapter = new DaramadRuzaneListViewAdapter(ManageDaramadActivity.this,list);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater findMenuItems = getMenuInflater();
        findMenuItems.inflate(R.menu.daramad_ruzane_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.daramad_ruzane_add_menu) {

            Intent intent = new Intent(ManageDaramadActivity.this,NewDaramadActivity.class);
            startActivity(intent);
            return true;
        }
        else if (id==R.id.daramad_ruzane_set_baygani_menu){
            if(list.size()==0){
                Toast.makeText(this, "درامدی برای بایگانی کردن ثبت نشده است", Toast.LENGTH_SHORT).show();
            }else{
                Intent intent = new Intent(ManageDaramadActivity.this,DaramadAddBayganiActivity.class);
                startActivity(intent);
            }

        }
        else if (id==R.id.daramad_ruzane_show_baygani_menu){
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.addAll(handler.getAllDaramaddStatusBaygani());
            if(arrayList.size()==0){
                Toast.makeText(this, "درآمد بایگانی شده ای وجود ندارد", Toast.LENGTH_SHORT).show();
            }else{
                Intent intent = new Intent(ManageDaramadActivity.this,DaramadBayganiStatusActivity.class);
                startActivity(intent);
            }

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        list.clear();
        list.addAll(handler.getAllDaramadRuzaneNBaygani());
        adapter.notifyDataSetChanged();
    }
}
