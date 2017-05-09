package com.example.alireza.parking.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.alireza.parking.Model.DaramadRuzane;
import com.example.alireza.parking.Model.Gharardad;
import com.example.alireza.parking.R;

import java.util.ArrayList;

/**
 * Created by AliReza on 4/7/2017.
 */

public class DaramadRuzaneListViewAdapter extends BaseAdapter {
    Context context;
    ArrayList<DaramadRuzane> list;
    Typeface font;
    public DaramadRuzaneListViewAdapter(Context contex , ArrayList<DaramadRuzane> list){
        this.context = contex;
        this.list = new ArrayList<>();
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
    public DaramadRuzane getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        font = Typeface.createFromAsset(context.getAssets(), "fonts/byekan+.ttf");
        View rowView=convertView;
        if (rowView == null){
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.gharardad_list_view_item,parent , false);
            DaramadRuzaneListViewAdapter.ViewHolder viewHolder=new DaramadRuzaneListViewAdapter.ViewHolder();
            viewHolder.Name=(TextView)rowView.findViewById(R.id.gharardad_list_view_item_name);
            viewHolder.Pelak=(TextView)rowView.findViewById(R.id.gharardad_list_view_item_pelak);
            viewHolder.Mablagh=(TextView)rowView.findViewById(R.id.gharardad_list_view_item_mablagh);
            rowView.setTag(viewHolder);
        }
        DaramadRuzaneListViewAdapter.ViewHolder viewHolder=(DaramadRuzaneListViewAdapter.ViewHolder)rowView.getTag();
        viewHolder.Name.setText(list.get(position).getDate());
        viewHolder.Name.setTypeface(font);
        viewHolder.Pelak.setText(list.get(position).getMablagh());
        viewHolder.Pelak.setTypeface(font);
        viewHolder.Mablagh.setText(list.get(position).getDay());
        viewHolder.Mablagh.setTypeface(font);
        return rowView;
    }
}
