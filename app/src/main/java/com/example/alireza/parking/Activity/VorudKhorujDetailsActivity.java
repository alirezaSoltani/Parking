package com.example.alireza.parking.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alireza.parking.DataBase.DataBaseHandler;
import com.example.alireza.parking.Model.VorudKhoruj;
import com.example.alireza.parking.R;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.time.RadialPickerLayout;

public class VorudKhorujDetailsActivity extends AppCompatActivity {
    int id;
    VorudKhoruj vorudKhoruj;
    DataBaseHandler handler;
    TextView shift , shomare_khodro , saat_vorud , saat_khoruj , mablagh , des;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vorud_khoruj_details);
        init();
    }

    private void init() {
        try {
            Intent intent = getIntent();
            id = intent.getIntExtra("id",-1);
            handler = new DataBaseHandler(VorudKhorujDetailsActivity.this);
            vorudKhoruj = handler.getVorudKhorujById(id);
            shift = (TextView)findViewById(R.id.vorud_khoruj_details_shift);
            shomare_khodro = (TextView)findViewById(R.id.vorud_khoruj_details_shomare_khodro);
            saat_vorud = (TextView)findViewById(R.id.vorud_khoruj_details_saat_vorud);
            saat_khoruj = (TextView)findViewById(R.id.vorud_khoruj_details_saat_khoruj);
            mablagh = (TextView)findViewById(R.id.vorud_khoruj_details_mablagh);
            des = (TextView)findViewById(R.id.vorud_khoruj_details_des);
            shift.setText(vorudKhoruj.getShift());
            shomare_khodro.setText(vorudKhoruj.getShomareKhodro());
            saat_vorud.setText(vorudKhoruj.getSaatVorud());
            saat_khoruj.setText(vorudKhoruj.getSaatKhoruj());
            mablagh.setText(vorudKhoruj.getMablagh());
            des.setText(vorudKhoruj.getDes());
        }catch (Exception e){
            Toast.makeText(this, e.getMessage()+"", Toast.LENGTH_SHORT).show();
        }

    }


}
