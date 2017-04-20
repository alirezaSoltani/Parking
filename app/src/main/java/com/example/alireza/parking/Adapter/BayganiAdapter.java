package com.example.alireza.parking.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.alireza.parking.R;

import java.util.ArrayList;

/**
 * Created by AliReza on 4/15/2017.
 */

public class BayganiAdapter extends BaseAdapter {
    ArrayList<String> list;
    Context context;
    public BayganiAdapter (ArrayList<String> list , Context context){
        this.list = list;
        this.context = context;
    }
    static class ViewHolder{
        TextView Status;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public String getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView=convertView;
        if (rowView == null){
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.baygani_list_view_item,parent , false);
            BayganiAdapter.ViewHolder viewHolder=new BayganiAdapter.ViewHolder();
            viewHolder.Status=(TextView)rowView.findViewById(R.id.baygani_list_view_item_txt);
            rowView.setTag(viewHolder);
        }
        BayganiAdapter.ViewHolder viewHolder=(BayganiAdapter.ViewHolder)rowView.getTag();
        viewHolder.Status.setText(list.get(position));
        return rowView;
    }
}
