package com.example.alireza.parking.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.alireza.parking.DataBase.DataBaseHandler;
import com.example.alireza.parking.Model.Gharardad;
import com.example.alireza.parking.R;

public class ReportGharardadActivity extends AppCompatActivity {
    int id;
    DataBaseHandler handler;
    Gharardad gharardad;
    TextView reportText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_gharardad);
        init();
    }

    private void init() {
        Intent intent = getIntent();
        handler = new DataBaseHandler(ReportGharardadActivity.this);
        id = intent.getIntExtra("id",-1);
        reportText = (TextView)findViewById(R.id.report_textView);
        if (id != -1){
            gharardad = handler.getGharardadBySTId(id);
            reportText.setText("شماره قرارداد :"+gharardad.getId()+"\n"+"نام مالک :"+gharardad.getName()+"\n"+"نوع خودرو :"+gharardad.getCar_Type()+"\n"+"شماره پلاک :"+gharardad.getCar_pelak()+"\n"+"مبلغ :"+gharardad.getMablagh()+"\n");
        }
    }
}
