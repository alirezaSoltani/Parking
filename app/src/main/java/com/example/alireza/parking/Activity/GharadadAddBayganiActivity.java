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
import com.example.alireza.parking.R;
import com.example.alireza.parking.SetAppFont;

import java.util.ArrayList;

public class GharadadAddBayganiActivity extends AppCompatActivity {
    EditText status;
    Button save;
    DataBaseHandler handler;
    ArrayList<Gharardad> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_baygani);
        init();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.BayganiGharardads(list,status.getText().toString());
                Toast.makeText(GharadadAddBayganiActivity.this, "قرارداد ها با موفقیت بایگانی شدند", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void init() {
        status = (EditText)findViewById(R.id.baygani_status);
        save = (Button)findViewById(R.id.baygani_save);
        handler = new DataBaseHandler(GharadadAddBayganiActivity.this);
        list = handler.getAllGharardadNBaygani();
        final ViewGroup mContainer = (ViewGroup) findViewById(
                android.R.id.content).getRootView();
        new SetAppFont(this,mContainer);
    }
}
