package com.example.alireza.parking.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alireza.parking.DataBase.DataBaseHandler;
import com.example.alireza.parking.Model.Gharardad;
import com.example.alireza.parking.R;

public class GharardadDetailsActivity extends AppCompatActivity {
    int id;
    TextView name , tel , car_type , pelak , mablagh , des;
    Gharardad gharardad;
    DataBaseHandler database;
    Button report;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gharardad_details);
        init();
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GharardadDetailsActivity.this,ReportGharardadActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
    }

    private void init() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id",-1);
        database = new DataBaseHandler(GharardadDetailsActivity.this);
        name = (TextView)findViewById(R.id.gharardad_details_name);
        tel = (TextView)findViewById(R.id.gharardad_details_tel);
        car_type = (TextView)findViewById(R.id.gharardad_details_car_type);
        pelak = (TextView)findViewById(R.id.gharardad_details_pelak);
        mablagh = (TextView)findViewById(R.id.gharardad_details_mablagh);
        des = (TextView)findViewById(R.id.gharardad_details_des);
        gharardad = database.getGharardadBySTId(id);
        report = (Button)findViewById(R.id.gharardad_report_btn);
        name.setText(gharardad.getName());
        tel.setText(gharardad.getTel());
        car_type.setText(gharardad.getCar_Type());
        pelak.setText(gharardad.getCar_pelak());
        mablagh.setText(gharardad.getMablagh());
        des.setText(gharardad.getDes());
    }

}
