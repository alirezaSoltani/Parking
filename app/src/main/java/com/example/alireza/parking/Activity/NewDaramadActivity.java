package com.example.alireza.parking.Activity;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alireza.parking.DataBase.DataBaseHandler;
import com.example.alireza.parking.Model.DaramadRuzane;
import com.example.alireza.parking.R;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.time.RadialPickerLayout;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

import java.text.SimpleDateFormat;

public class NewDaramadActivity extends AppCompatActivity implements com.mohamadamin.persianmaterialdatetimepicker.time.TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener  {
    RadioButton asr,sob;
    Spinner day;
    EditText mablagh , des;
    TextView date;
    Button save;
    DataBaseHandler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_daramad);
        init();
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersianCalendar persianCalendar = new PersianCalendar();

                DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
                        NewDaramadActivity.this,
                        persianCalendar.getPersianYear(),
                        persianCalendar.getPersianMonth(),
                        persianCalendar.getPersianDay()
                );
                datePickerDialog.show(getFragmentManager(), "Datepickerdialog");
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shift;
                if (sob.isChecked()==true){
                    shift = "صبح";
                }else{
                    shift="بعد از ظهر";
                }
                DaramadRuzane daramadRuzane = new DaramadRuzane(day.getSelectedItem().toString(),date.getText().toString(),mablagh.getText().toString(),des.getText().toString(),shift , "false" ,"");
                handler.insertDaramadRuzane(daramadRuzane);
                Toast.makeText(NewDaramadActivity.this,  "اطلاعات با موفقیت ذخیره شد", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void init() {
        handler = new DataBaseHandler(NewDaramadActivity.this);
        asr = (RadioButton)findViewById(R.id.daramad_asr);
        sob = (RadioButton)findViewById(R.id.daramad_sob);
        day = (Spinner)findViewById(R.id.spinner);
        mablagh = (EditText)findViewById(R.id.daramad_mablgha);
        des = (EditText)findViewById(R.id.daramad_des);
        date = (TextView)findViewById(R.id.daramad_date);
        save = (Button)findViewById(R.id.daramad_save);
        com.example.alireza.parking.PersianCalendar calendar = new com.example.alireza.parking.PersianCalendar();
        date.setText(calendar.getIranianDate());
        String[] dat = date.getText().toString().split("/");
//        if (Integer.parseInt(dat[1]) < 10) {
//            dat[1] = "0" + (dat[1]+1);
//        }
//        if (Integer.parseInt(dat[2]) < 10) {
//            dat[2] = "0" + dat[2];
//        }
//
//        date.setText(dat[0] + "/" + dat[1] + "/" + dat[2]);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        monthOfYear = monthOfYear+1;
        date.setText(year + "/" + monthOfYear + "/" + dayOfMonth);
//        String[] dat = date.getText().toString().split("/");
//        if (Integer.parseInt(dat[1]) < 10) {
//            dat[1] = "0" + dat[1];
//        }
//        if (Integer.parseInt(dat[2]) < 10) {
//            dat[2] = "0" + dat[2];
//        }
//        date.setText(dat[0] + "/" + dat[1] + "/" + dat[2]);
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {

    }
}
