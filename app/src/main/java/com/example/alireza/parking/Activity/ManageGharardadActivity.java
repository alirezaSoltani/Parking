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

import com.example.alireza.parking.Adapter.GharardadListViewAdapter;
import com.example.alireza.parking.DataBase.DataBaseHandler;
import com.example.alireza.parking.Model.Gharardad;
import com.example.alireza.parking.R;

import java.util.ArrayList;

public class ManageGharardadActivity extends AppCompatActivity {
    ListView listView;
    GharardadListViewAdapter adapter;
    DataBaseHandler dataBaseHandler;
    ArrayList<Gharardad> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_gharardad);
        init();
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ManageGharardadActivity.this);
                builder.setMessage("آیا میخواهید این مورد را حذف کنید؟");
                builder.setPositiveButton("بله", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dataBaseHandler.deleteGaharardadByID(list.get(position).getId());
                        Toast.makeText(ManageGharardadActivity.this, "قرار داد با موفقیت حذف شد", Toast.LENGTH_SHORT).show();
                        list.clear();
                        list.addAll(dataBaseHandler.getAllGharardadNBaygani());
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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ManageGharardadActivity.this,GharardadDetailsActivity.class);
                intent.putExtra("id",list.get(position).getId());
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater findMenuItems = getMenuInflater();
        findMenuItems.inflate(R.menu.gharardad_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.gharardad_baygani_menu) {

            Intent intent = new Intent(ManageGharardadActivity.this,GharadadAddBayganiActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.gharardad_show_baygani_menu){
            Intent intent = new Intent(ManageGharardadActivity.this,GharardadBayganiStatusActivity.class);
            startActivity(intent);
        }

            return super.onOptionsItemSelected(item);
    }
    private void init() {
        list = new ArrayList<>();
        listView = (ListView)findViewById(R.id.gharardad_list_view);
        dataBaseHandler = new DataBaseHandler(ManageGharardadActivity.this);
        list.addAll(dataBaseHandler.getAllGharardadNBaygani());
        adapter = new GharardadListViewAdapter(ManageGharardadActivity.this,list);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        list.clear();
        list.addAll(dataBaseHandler.getAllGharardadNBaygani());
        adapter.notifyDataSetChanged();
    }
}
