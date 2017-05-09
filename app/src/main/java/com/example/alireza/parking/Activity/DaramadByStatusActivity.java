package com.example.alireza.parking.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.alireza.parking.Adapter.DaramadRuzaneListViewAdapter;
import com.example.alireza.parking.Adapter.GharardadListViewAdapter;
import com.example.alireza.parking.DataBase.DataBaseHandler;
import com.example.alireza.parking.Model.DaramadRuzane;
import com.example.alireza.parking.Model.Gharardad;
import com.example.alireza.parking.R;
import com.example.alireza.parking.SetAppFont;

import java.util.ArrayList;

public class DaramadByStatusActivity extends AppCompatActivity {
    ListView listView;
    DaramadRuzaneListViewAdapter adapter;
    ArrayList<DaramadRuzane> list;
    DataBaseHandler handler;
    String status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daramad_by_status);
        init();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DaramadByStatusActivity.this,DaramadRuzaneDetailsActivity.class);
                intent.putExtra("id",list.get(position).getId());
                System.out.println(list.get(position).getId());
                startActivity(intent);
            }
        });
    }

    private void init() {
        Intent intent = getIntent();
        status = intent.getStringExtra("status");
        handler = new DataBaseHandler(DaramadByStatusActivity.this);
        list = new ArrayList<>();
        if (status!=null){
            list.addAll(handler.getAllDaramadByStatus(status));
            adapter = new DaramadRuzaneListViewAdapter(DaramadByStatusActivity.this,list);
            listView = (ListView)findViewById(R.id.daramad_list_view_by_status);
            listView.setAdapter(adapter);
        }
        final ViewGroup mContainer = (ViewGroup) findViewById(
                android.R.id.content).getRootView();
        new SetAppFont(this,mContainer);
    }


}
