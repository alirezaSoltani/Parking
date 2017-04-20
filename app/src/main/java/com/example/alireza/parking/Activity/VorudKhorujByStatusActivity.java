package com.example.alireza.parking.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.alireza.parking.Adapter.GharardadListViewAdapter;
import com.example.alireza.parking.Adapter.VorudKhorujListViewAdapter;
import com.example.alireza.parking.DataBase.DataBaseHandler;
import com.example.alireza.parking.Model.Gharardad;
import com.example.alireza.parking.Model.VorudKhoruj;
import com.example.alireza.parking.R;

import java.util.ArrayList;

public class VorudKhorujByStatusActivity extends AppCompatActivity {
    ListView listView;
    VorudKhorujListViewAdapter adapter;
    ArrayList<VorudKhoruj> list;
    DataBaseHandler handler;
    String status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vorud_khoruj_by_status);
        init();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(VorudKhorujByStatusActivity.this,VorudKhorujDetailsActivity.class);
                intent.putExtra("id",list.get(position).getId());
                System.out.println(list.get(position).getId());
                startActivity(intent);
            }
        });
    }

    private void init() {
        Intent intent = getIntent();
        status = intent.getStringExtra("status");
        handler = new DataBaseHandler(VorudKhorujByStatusActivity.this);
        list = new ArrayList<>();
        if (status!=null){
            list.addAll(handler.getAllVorudKhorujByStatus(status));
            adapter = new VorudKhorujListViewAdapter(VorudKhorujByStatusActivity.this,list);
            listView = (ListView)findViewById(R.id.Vorud_khoruj_list_view_by_status);
            listView.setAdapter(adapter);
        }
    }
}
