package com.example.alireza.parking.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alireza.parking.DataBase.DataBaseHandler;
import com.example.alireza.parking.Model.Gharardad;
import com.example.alireza.parking.R;

public class NewGharadadActivity extends AppCompatActivity {
    EditText Name,Tel,CarType,Pelak,Mablagh,Des;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_gharadad);
        init();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gharardad gharardad = new Gharardad(Name.getText().toString(),Tel.getText().toString(),CarType.getText().toString(),Pelak.getText().toString(),Mablagh.getText().toString(),Des.getText().toString() , "false" , "null");
                try{
                    DataBaseHandler database = new DataBaseHandler(NewGharadadActivity.this);
                    database.insertGharardad(gharardad);
                    Toast.makeText(NewGharadadActivity.this, "اطلاعات با موفقیت ذخیره شد", Toast.LENGTH_SHORT).show();
                    finish();
                }catch (Exception e){
                    Toast.makeText(NewGharadadActivity.this,e.getMessage()+ "", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void init() {
        Name = (EditText)findViewById(R.id.new_gharardad_name);
        Tel = (EditText)findViewById(R.id.new_gharardad_tel);
        CarType = (EditText)findViewById(R.id.new_gharardad_car_type);
        Pelak = (EditText)findViewById(R.id.new_gharardad_pelak);
        Mablagh = (EditText)findViewById(R.id.new_gharardad_mablagh);
        Des = (EditText)findViewById(R.id.new_gharardad_des);
        save = (Button)findViewById(R.id.new_gharardad_save);

    }
}
