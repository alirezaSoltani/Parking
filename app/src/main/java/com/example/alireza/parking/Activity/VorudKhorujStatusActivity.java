package com.example.alireza.parking.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.alireza.parking.Adapter.BayganiAdapter;
import com.example.alireza.parking.DataBase.DataBaseHandler;
import com.example.alireza.parking.R;

import java.util.ArrayList;

public class VorudKhorujStatusActivity extends AppCompatActivity {
    ListView listView;
    DataBaseHandler handler;
    ArrayList<String> list;
    BayganiAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vorud_khoruj_status);
        init();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(VorudKhorujStatusActivity.this,VorudKhorujByStatusActivity.class);
                intent.putExtra("status",list.get(position));
                startActivity(intent);
            }
        });
    }

    private void init() {
        list = new ArrayList<>();
        handler = new DataBaseHandler(VorudKhorujStatusActivity.this);
        listView = (ListView)findViewById(R.id.vorud_khoruj_list_view_status);
        list.addAll(handler.getAllVorudKhorujStatusBaygani());
        for (int i=0;i<list.size();i++){
            if (list.get(i)=="null"){
                list.remove(i);
                Toast.makeText(this, "yes", Toast.LENGTH_SHORT).show();
            }
        }
        System.out.println("list.size"+"/"+list.get(0)+"/");
//        Toast.makeText(this,list.get(0)+ "", Toast.LENGTH_SHORT).show();
        adapter = new BayganiAdapter(list,VorudKhorujStatusActivity.this);
        listView.setAdapter(adapter);
    }
}
