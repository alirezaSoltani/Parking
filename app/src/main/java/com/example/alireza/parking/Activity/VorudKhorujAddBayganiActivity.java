package com.example.alireza.parking.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alireza.parking.DataBase.DataBaseHandler;
import com.example.alireza.parking.Model.Gharardad;
import com.example.alireza.parking.Model.VorudKhoruj;
import com.example.alireza.parking.R;
import com.example.alireza.parking.SetAppFont;

import java.util.ArrayList;

public class VorudKhorujAddBayganiActivity extends AppCompatActivity {
    EditText status;
    Button save;
    DataBaseHandler handler;
    ArrayList<VorudKhoruj> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vorud_khoruj_add_baygani);
        init();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.BayganiVorudKhoruj(list,status.getText().toString());
                Toast.makeText(VorudKhorujAddBayganiActivity.this, "ورود و خروج ها با موفقیت بایگانی شدند", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void init() {
        status = (EditText)findViewById(R.id.vorud_khoruj_baygani_status);
        save = (Button)findViewById(R.id.vorud_khoruj_baygani_save_btn);
        handler = new DataBaseHandler(VorudKhorujAddBayganiActivity.this);
        list = handler.getAllVorudKhorujNBaygani();
        final ViewGroup mContainer = (ViewGroup) findViewById(
                android.R.id.content).getRootView();
        new SetAppFont(this,mContainer);
    }
}
