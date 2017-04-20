package com.example.alireza.parking.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alireza.parking.DataBase.DataBaseHandler;
import com.example.alireza.parking.Model.VorudKhoruj;
import com.example.alireza.parking.R;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.time.RadialPickerLayout;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NewVorudKhorujActivity extends AppCompatActivity implements com.mohamadamin.persianmaterialdatetimepicker.time.TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {
    EditText shoamre_khodro , mablagh , des;
    RadioButton sob , asr;
    TextView saat_vorud , saat_khoruj ,date;
    int flag;
    Button save;
    DataBaseHandler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_vorud_khoruj);
        init();
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersianCalendar persianCalendar = new PersianCalendar();

                DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
                        NewVorudKhorujActivity.this,
                        persianCalendar.getPersianYear(),
                        persianCalendar.getPersianMonth(),
                        persianCalendar.getPersianDay()
                );
                datePickerDialog.show(getFragmentManager(), "Datepickerdialog");

            }
        });
        final Calendar calendar = Calendar.getInstance();
        saat_vorud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                com.mohamadamin.persianmaterialdatetimepicker.time.TimePickerDialog timePickerDialog = com.mohamadamin.persianmaterialdatetimepicker.time.TimePickerDialog.newInstance(
                        NewVorudKhorujActivity.this,
                        calendar.HOUR_OF_DAY,
                        calendar.MINUTE,
                        false
                );
                timePickerDialog.show(getFragmentManager(), "");
                flag=0;
            }
        });
        saat_khoruj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                com.mohamadamin.persianmaterialdatetimepicker.time.TimePickerDialog timePickerDialog = com.mohamadamin.persianmaterialdatetimepicker.time.TimePickerDialog.newInstance(
                        NewVorudKhorujActivity.this,
                        calendar.HOUR_OF_DAY,
                        calendar.MINUTE,
                        false
                );
                timePickerDialog.show(getFragmentManager(), "");
                flag=1;

            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shift;
                if (sob.isSelected()){
                    shift = "صبح";
                }else{
                    shift="بعد از ظهر";
                }
                VorudKhoruj vorudKhoruj = new VorudKhoruj(shoamre_khodro.getText().toString(),saat_vorud.getText().toString(),saat_khoruj.getText().toString(),date.getText().toString(),mablagh.getText().toString(),des.getText().toString(),shift,"false","");
                handler.insertVorudKhoruj(vorudKhoruj);
                Toast.makeText(NewVorudKhorujActivity.this, "اطلاعات با موفقیت ذخیره شد", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void init() {
        shoamre_khodro = (EditText)findViewById(R.id.new_vorud_khoruj_shoamre_khodro);
        mablagh = (EditText)findViewById(R.id.new_vorud_khoruj_mablagh);
        date = (TextView)findViewById(R.id.new_vorud_khoruj_date);
        des = (EditText)findViewById(R.id.new_vorud_khoruj_des);
        sob = (RadioButton)findViewById(R.id.new_vorud_khoruj_sob);
        asr = (RadioButton)findViewById(R.id.new_vorud_khoruj_asr);
        saat_khoruj = (TextView)findViewById(R.id.new_vorud_khoruj_saat_khoruj);
        saat_vorud = (TextView)findViewById(R.id.new_vorud_khoruj_saat_vorud);
        save = (Button)findViewById(R.id.new_vorud_khoruj_save);
        handler = new DataBaseHandler(NewVorudKhorujActivity.this);
        com.example.alireza.parking.PersianCalendar calendar2 = new com.example.alireza.parking.PersianCalendar();
        final String NEW_FORMAT = "yyyy/MM/dd";
        SimpleDateFormat sdm = new SimpleDateFormat(NEW_FORMAT);
        date.setText(calendar2.getIranianDate());
        Calendar c = Calendar.getInstance();
//        c.setTimeZone(TimeZone.getTimeZone("GMT+03:30"));
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        saat_vorud.setText(sdf.format(c.getTime()));
        saat_khoruj.setText(sdf.format(c.getTime()));
        String[] dat = date.getText().toString().split("/");
        if (Integer.parseInt(dat[1]) < 10) {
            dat[1] = "0" + dat[1];
        }
        if (Integer.parseInt(dat[2]) < 10) {
            dat[2] = "0" + dat[2];
        }

        date.setText(dat[0] + "/" + dat[1] + "/" + dat[2]);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        date.setText(year + "/" + monthOfYear + "/" + dayOfMonth);
        String[] dat = date.getText().toString().split("/");
        if (Integer.parseInt(dat[1]) < 10) {
            dat[1] = "0" + dat[1];
        }
        if (Integer.parseInt(dat[2]) < 10) {
            dat[2] = "0" + dat[2];
        }
        date.setText(dat[0] + "/" + dat[1] + "/" + dat[2]);
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {

        if (flag==0){
            saat_vorud.setText(hourOfDay + ":" + minute);
            String[] tim = saat_vorud.getText().toString().split(":");
            if (Integer.parseInt(tim[0]) < 10) {
                tim[0] = "0" + tim[0];
            }
            if (Integer.parseInt(tim[1]) < 10) {
                tim[1] = "0" + tim[1];
            }
            saat_vorud.setText(tim[0] + ":" + tim[1]);
        }else{
            saat_khoruj.setText(hourOfDay + ":" + minute);
            String[] tim = saat_khoruj.getText().toString().split(":");
            if (Integer.parseInt(tim[0]) < 10) {
                tim[0] = "0" + tim[0];
            }
            if (Integer.parseInt(tim[1]) < 10) {
                tim[1] = "0" + tim[1];
            }
            saat_khoruj.setText(tim[0] + ":" + tim[1]);
        }
    }
}
