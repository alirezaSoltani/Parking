package com.example.alireza.parking.Activity;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alireza.parking.DataBase.DataBaseHandler;
import com.example.alireza.parking.Model.DaramadRuzane;
import com.example.alireza.parking.R;
import com.example.alireza.parking.SetAppFont;

public class DaramadRuzaneDetailsActivity extends AppCompatActivity {
    int id;
    DaramadRuzane daramadRuzane;
    DataBaseHandler handler;
    TextView shift , day , date , mablagh , des;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daramad_ruzane_details);
        init();
    }

    private void init() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id",-1);
        handler = new DataBaseHandler(DaramadRuzaneDetailsActivity.this);
        daramadRuzane = handler.getDaramadRuzaneById(id);
        shift = (TextView)findViewById(R.id.daramad_ruzane_details_shift);
        day = (TextView)findViewById(R.id.daramad_ruzane_details_day);
        date = (TextView)findViewById(R.id.daramad_ruzane_details_date);
        mablagh = (TextView)findViewById(R.id.daramad_ruzane_details_mablagh);
        des = (TextView)findViewById(R.id.daramad_ruzane_details_des);
        shift.setText("شیفت : "+daramadRuzane.getShift());
        day.setText("روز : "+daramadRuzane.getDay());
        date.setText("تاریخ : "+daramadRuzane.getDate());
        mablagh.setText("مبلغ : "+daramadRuzane.getMablagh());
        des.setText("توضیحات : "+daramadRuzane.getDes());
        final ViewGroup mContainer = (ViewGroup) findViewById(
                android.R.id.content).getRootView();
        new SetAppFont(this,mContainer);
    }
}
