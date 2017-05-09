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

import com.example.alireza.parking.Adapter.DaramadRuzaneListViewAdapter;
import com.example.alireza.parking.DataBase.DataBaseHandler;
import com.example.alireza.parking.Model.DaramadRuzane;
import com.example.alireza.parking.R;
import com.example.alireza.parking.SetAppFont;

import java.util.ArrayList;

public class ManageDaramadActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<DaramadRuzane> list;
    DaramadRuzaneListViewAdapter adapter;
    DataBaseHandler handler;
    RadioGroup group;
    RadioButton r_sob,r_asr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_daramad);
        init();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ManageDaramadActivity.this,DaramadRuzaneDetailsActivity.class);
                intent.putExtra("id",list.get(position).getId());
                startActivity(intent);
            }
        });
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                list.clear();
                list.addAll(handler.getAllDaramadRuzaneNBaygani());
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
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ManageDaramadActivity.this);
                builder.setMessage("آیا میخواهید این مورد را حذف کنید؟");
                builder.setPositiveButton("بله", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        handler.deleteDaramadRuzaneByID(list.get(position).getId());
                        Toast.makeText(ManageDaramadActivity.this, "مورد با موفقیت حذف شد", Toast.LENGTH_SHORT).show();
                        list.clear();
                        list.addAll(handler.getAllDaramadRuzaneNBaygani());
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
    }


    private void init() {
        listView = (ListView)findViewById(R.id.manage_daramad_list_view);
        list = new ArrayList<>();
        handler = new DataBaseHandler(ManageDaramadActivity.this);
        list.addAll(handler.getAllDaramadRuzaneNBaygani());
        r_sob = (RadioButton)findViewById(R.id.daramad_list_sob);
        r_asr = (RadioButton)findViewById(R.id.daramad_list_asr);
        for(int i=list.size()-1;i>=0;i--){
            if (r_sob.isChecked()==true&&list.get(i).getShift().equals("بعد از ظهر")){
                list.remove(i);
            }else if(r_asr.isChecked()==true&&list.get(i).getShift().equals("صبح")) {
                list.remove(i);
            }
        }
        adapter = new DaramadRuzaneListViewAdapter(ManageDaramadActivity.this,list);
        listView.setAdapter(adapter);
        group = (RadioGroup)findViewById(R.id.radioGroup_daramad);
        r_sob = (RadioButton)findViewById(R.id.daramad_list_sob);
        r_asr = (RadioButton)findViewById(R.id.daramad_list_asr);
        final ViewGroup mContainer = (ViewGroup) findViewById(
                android.R.id.content).getRootView();
        new SetAppFont(this,mContainer);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater findMenuItems = getMenuInflater();
        findMenuItems.inflate(R.menu.daramad_ruzane_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.daramad_ruzane_add_menu) {

            Intent intent = new Intent(ManageDaramadActivity.this,NewDaramadActivity.class);
            startActivity(intent);
            return true;
        }
        else if (id==R.id.daramad_ruzane_set_baygani_menu){
            if(list.size()==0){
                Toast.makeText(this, "درامدی برای بایگانی کردن ثبت نشده است", Toast.LENGTH_SHORT).show();
            }else{
                Intent intent = new Intent(ManageDaramadActivity.this,DaramadAddBayganiActivity.class);
                startActivity(intent);
            }

        }
        else if (id==R.id.daramad_ruzane_show_baygani_menu){
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.addAll(handler.getAllDaramaddStatusBaygani());
            if(arrayList.size()==0){
                Toast.makeText(this, "درآمد بایگانی شده ای وجود ندارد", Toast.LENGTH_SHORT).show();
            }else{
                Intent intent = new Intent(ManageDaramadActivity.this,DaramadBayganiStatusActivity.class);
                startActivity(intent);
            }

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        list.clear();
        list.addAll(handler.getAllDaramadRuzaneNBaygani());
        for(int i=list.size()-1;i>=0;i--){
            if (r_sob.isChecked()==true&&list.get(i).getShift().equals("بعد از ظهر")){
                list.remove(i);
            }else if(r_asr.isChecked()==true&&list.get(i).getShift().equals("صبح")) {
                list.remove(i);
            }
        }
        adapter.notifyDataSetChanged();
    }
}
