package com.example.alireza.parking.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.alireza.parking.Model.Gharardad;
import com.example.alireza.parking.R;

import java.util.ArrayList;

/**
 * Created by AliReza on 4/5/2017.
 */

public class GharardadListViewAdapter extends BaseAdapter {
    Context context;
    ArrayList<Gharardad> list;
    public GharardadListViewAdapter(Context contex , ArrayList<Gharardad> list){
        this.context = contex;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }
    static class ViewHolder{
        TextView Name;
        TextView  Pelak;
        TextView Mablagh;
    }
    @Override
    public Gharardad getItem(int position) {
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
            rowView = inflater.inflate(R.layout.gharardad_list_view_item,parent , false);
            ViewHolder viewHolder=new ViewHolder();
            viewHolder.Name=(TextView)rowView.findViewById(R.id.gharardad_list_view_item_name);
            viewHolder.Pelak=(TextView)rowView.findViewById(R.id.gharardad_list_view_item_pelak);
            viewHolder.Mablagh=(TextView)rowView.findViewById(R.id.gharardad_list_view_item_mablagh);
            rowView.setTag(viewHolder);
        }
        ViewHolder viewHolder=(ViewHolder)rowView.getTag();
        System.out.println("id:"+list.get(position).getId());
        viewHolder.Name.setText(list.get(position).getName());
        viewHolder.Pelak.setText(list.get(position).getCar_pelak());
        viewHolder.Mablagh.setText(list.get(position).getMablagh());
        return rowView;
    }
}
