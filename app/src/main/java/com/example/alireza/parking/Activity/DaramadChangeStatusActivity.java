package com.example.alireza.parking.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alireza.parking.DataBase.DataBaseHandler;
import com.example.alireza.parking.Model.DaramadRuzane;
import com.example.alireza.parking.R;
import com.example.alireza.parking.SetAppFont;

import java.util.ArrayList;

public class DaramadChangeStatusActivity extends AppCompatActivity {
    EditText editText;
    Button btn;
    DataBaseHandler handler;
    ArrayList<DaramadRuzane> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daramad_change_status);
        final ViewGroup mContainer = (ViewGroup) findViewById(
                android.R.id.content).getRootView();
        new SetAppFont(this,mContainer);
        list = new ArrayList<>();
        handler = new DataBaseHandler(DaramadChangeStatusActivity.this);
        btn = (Button)findViewById(R.id.change_daramad_status_btn);
        Intent intent = getIntent();
        final String status = intent.getStringExtra("status");
        editText = (EditText)findViewById(R.id.change_daramad_status);
        if (status!=null){
            editText.setHint(status);
            list.addAll( handler.getAllDaramadByStatus(status));
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.BayganiDaramad(list,editText.getText().toString());
                finish();
            }
        });
    }
}
