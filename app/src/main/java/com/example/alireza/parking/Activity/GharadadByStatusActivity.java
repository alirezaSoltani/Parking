package com.example.alireza.parking.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.alireza.parking.Adapter.GharardadListViewAdapter;
import com.example.alireza.parking.DataBase.DataBaseHandler;
import com.example.alireza.parking.Model.Gharardad;
import com.example.alireza.parking.R;
import com.example.alireza.parking.SetAppFont;

import java.util.ArrayList;

public class GharadadByStatusActivity extends AppCompatActivity {
    ListView listView;
    GharardadListViewAdapter adapter;
    ArrayList<Gharardad> list;
    DataBaseHandler handler;
    String status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gharadad_by_status);
        init();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(GharadadByStatusActivity.this,GharardadDetailsActivity.class);
                intent.putExtra("id",list.get(position).getId());
                System.out.println(list.get(position).getId());
                startActivity(intent);
            }
        });
    }

    private void init() {
        Intent intent = getIntent();
        status = intent.getStringExtra("status");
        handler = new DataBaseHandler(GharadadByStatusActivity.this);
        list = new ArrayList<>();
        if (status!=null){
            list.addAll(handler.getAllGharardadByStatus(status));
            adapter = new GharardadListViewAdapter(GharadadByStatusActivity.this,list);
            listView = (ListView)findViewById(R.id.gharardad_list_view_by_status);
            listView.setAdapter(adapter);
        }
        final ViewGroup mContainer = (ViewGroup) findViewById(
                android.R.id.content).getRootView();
        new SetAppFont(this,mContainer);
    }
}
