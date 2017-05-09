package com.example.alireza.parking.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.alireza.parking.Adapter.BayganiAdapter;
import com.example.alireza.parking.DataBase.DataBaseHandler;
import com.example.alireza.parking.Model.DaramadRuzane;
import com.example.alireza.parking.Model.Gharardad;
import com.example.alireza.parking.R;
import com.example.alireza.parking.SetAppFont;

import java.util.ArrayList;

public class GharardadBayganiStatusActivity extends AppCompatActivity {
    ListView listView;
    DataBaseHandler handler;
    ArrayList<String> list;
    BayganiAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baygani_status);
        init();
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder =new AlertDialog.Builder(GharardadBayganiStatusActivity.this);
                builder.setMessage("لطفا انتخاب نمایید");
                builder.setNegativeButton("تغییر نام بایگانی", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(GharardadBayganiStatusActivity.this,GharardadChangeStatusActivity.class);
                        intent.putExtra("status",list.get(position));
                        startActivity(intent);
                    }
                });
                builder.setPositiveButton("حذف بایگانی", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ArrayList<Gharardad> arrayList = handler.getAllGharardadByStatus(list.get(position));
                        for (int i=0;i<arrayList.size();i++){
                            handler.deleteGaharardadByID(arrayList.get(i).getId());
                        }
                        list.clear();
                        list.addAll(handler.getAllGharardadStatusBaygani());
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.show();
                return false;

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(GharardadBayganiStatusActivity.this,GharadadByStatusActivity.class);
                intent.putExtra("status",list.get(position));
                startActivity(intent);
            }
        });

    }

    private void init() {
        list = new ArrayList<>();
        handler = new DataBaseHandler(GharardadBayganiStatusActivity.this);
        listView = (ListView)findViewById(R.id.gharardad_list_view_status);
        list.addAll(handler.getAllGharardadStatusBaygani());
        for (int i=0;i<list.size();i++){
            if (list.get(i)=="null"){
                list.remove(i);
                Toast.makeText(this, "yes", Toast.LENGTH_SHORT).show();
            }
        }
        System.out.println("list.size"+"/"+list.get(0)+"/");
//        Toast.makeText(this,list.get(0)+ "", Toast.LENGTH_SHORT).show();
        adapter = new BayganiAdapter(list,GharardadBayganiStatusActivity.this);
        listView.setAdapter(adapter);
        final ViewGroup mContainer = (ViewGroup) findViewById(
                android.R.id.content).getRootView();
        new SetAppFont(this,mContainer);
    }
    @Override
    protected void onResume() {
        super.onResume();
        list.clear();
        list.addAll(handler.getAllGharardadStatusBaygani());
        adapter.notifyDataSetChanged();
    }
}
