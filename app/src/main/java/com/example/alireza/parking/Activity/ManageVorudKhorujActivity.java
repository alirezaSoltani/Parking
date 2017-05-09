package com.example.alireza.parking.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.alireza.parking.Adapter.VorudKhorujListViewAdapter;
import com.example.alireza.parking.DataBase.DataBaseHandler;
import com.example.alireza.parking.Model.VorudKhoruj;
import com.example.alireza.parking.R;
import com.example.alireza.parking.SetAppFont;

import java.util.ArrayList;

public class ManageVorudKhorujActivity extends AppCompatActivity {
    ArrayList<VorudKhoruj> list;
    DataBaseHandler handler;
    VorudKhorujListViewAdapter adapter;
    ListView listView;
    RadioGroup group;
    RadioButton r_sob,r_asr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_vorud_khoruj);
        init();
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                list.clear();
                list.addAll(handler.getAllVorudKhorujNBaygani());
                for(int i=list.size()-1;i>=0;i--){
                    if (r_sob.isChecked()==true&&list.get(i).getShift().equals("بعد از ظهر")){
                        list.remove(i);
                    }else if(r_asr.isChecked()==true&&list.get(i).getShift().equals("صبح")) {
                        list.remove(i);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void init() {
        list = new ArrayList<>();
        handler = new DataBaseHandler(ManageVorudKhorujActivity.this);
        list.addAll(handler.getAllVorudKhorujNBaygani());
        r_sob = (RadioButton)findViewById(R.id.vorud_khoruj_list_sob);
        r_asr = (RadioButton)findViewById(R.id.vorud_khoruj_list_asr);
        group = (RadioGroup)findViewById(R.id.radioGroup_vorud_khoruj);
        for(int i=list.size()-1;i>=0;i--){
            if (r_sob.isChecked()==true&&list.get(i).getShift().equals("بعد از ظهر")){
                list.remove(i);
            }else if(r_asr.isChecked()==true&&list.get(i).getShift().equals("صبح")) {
                list.remove(i);
            }
        }
        listView = (ListView)findViewById(R.id.vorud_khoruj_list_view);
        adapter = new VorudKhorujListViewAdapter(ManageVorudKhorujActivity.this,list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ManageVorudKhorujActivity.this,VorudKhorujDetailsActivity.class);
                intent.putExtra("id",list.get(position).getId());
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ManageVorudKhorujActivity.this);
                builder.setMessage("آیا میخواهید این مورد را حذف کنید؟");
                builder.setPositiveButton("بله", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        handler.deleteVorudKhorujByID(list.get(position).getId());
                        Toast.makeText(ManageVorudKhorujActivity.this, "قرار داد با موفقیت حذف شد", Toast.LENGTH_SHORT).show();
                        list.clear();
                        list.addAll(handler.getAllVorudKhorujNBaygani());
                        for(int i=list.size()-1;i>=0;i--){
                            if (r_sob.isChecked()==true&&list.get(i).getShift().equals("بعد از ظهر")){
                                list.remove(i);
                            }else if(r_asr.isChecked()==true&&list.get(i).getShift().equals("صبح")) {
                                list.remove(i);
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("خیر", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
                return false;
            }
        });
        final ViewGroup mContainer = (ViewGroup) findViewById(
                android.R.id.content).getRootView();
        new SetAppFont(this,mContainer);

    }

    @Override
    protected void onResume() {
        list.clear();
        list.addAll(handler.getAllVorudKhorujNBaygani());
        for(int i=list.size()-1;i>=0;i--){
            if (r_sob.isChecked()==true&&list.get(i).getShift().equals("بعد از ظهر")){
                list.remove(i);
            }else if(r_asr.isChecked()==true&&list.get(i).getShift().equals("صبح")) {
                list.remove(i);
            }
        }
        adapter.notifyDataSetChanged();
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater findMenuItems = getMenuInflater();
        findMenuItems.inflate(R.menu.vorud_khoruj_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.vorud_khoruj_add_menu) {
            Intent intent = new Intent(ManageVorudKhorujActivity.this,NewVorudKhorujActivity.class);
            startActivity(intent);
            return true;
        }
        else if (id==R.id.vorud_khoruj_set_baygani_menu){

            if (list.size()==0){
                Toast.makeText(this, "ورود و خروجی برای بایگانی کردن ثبت نشده است", Toast.LENGTH_SHORT).show();
            }else {
                Intent intent = new Intent(ManageVorudKhorujActivity.this,VorudKhorujAddBayganiActivity.class);
                startActivity(intent);
            }

        }
        else if (id==R.id.vorud_khoruj_show_baygani_menu){
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.addAll(handler.getAllVorudKhorujStatusBaygani());
            if(arrayList.size()==0){
                Toast.makeText(this, "ورود و خروج بایگانی شده ای وجود ندارد", Toast.LENGTH_SHORT).show();
            }else{
                Intent intent = new Intent(ManageVorudKhorujActivity.this,VorudKhorujStatusActivity.class);
                startActivity(intent);
            }

        }

        return super.onOptionsItemSelected(item);
    }
}
