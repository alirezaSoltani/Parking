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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.alireza.parking.Adapter.VorudKhorujListViewAdapter;
import com.example.alireza.parking.DataBase.DataBaseHandler;
import com.example.alireza.parking.Model.VorudKhoruj;
import com.example.alireza.parking.R;

import java.util.ArrayList;

public class ManageVorudKhorujActivity extends AppCompatActivity {
    ArrayList<VorudKhoruj> list;
    DataBaseHandler handler;
    VorudKhorujListViewAdapter adapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_vorud_khoruj);
        init();
    }

    private void init() {
        list = new ArrayList<>();
        handler = new DataBaseHandler(ManageVorudKhorujActivity.this);
        list.addAll(handler.getAllVorudKhoruj());
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
                        list.addAll(handler.getAllVorudKhoruj());
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

    @Override
    protected void onResume() {
        list.clear();
        list.addAll(handler.getAllVorudKhoruj());
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
            Intent intent = new Intent(ManageVorudKhorujActivity.this,VorudKhorujAddBayganiActivity.class);
            startActivity(intent);
        }
        else if (id==R.id.vorud_khoruj_show_baygani_menu){
            Intent intent = new Intent(ManageVorudKhorujActivity.this,VorudKhorujAddBayganiActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
