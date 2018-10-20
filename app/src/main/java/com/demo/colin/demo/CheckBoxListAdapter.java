package com.demo.colin.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class CheckBoxListAdapter extends BaseAdapter {
    private ArrayList<String > contextList;
    private static HashMap<Integer,Boolean> isSelected;
    private Context context;
    private LayoutInflater inflater = null;

    private static class ViewHolder {
        TextView tv;
        CheckBox cb;
    }


    public CheckBoxListAdapter(ArrayList<String> contextList, Context context){
        this.contextList = contextList;
        this.context = context;
        inflater = LayoutInflater.from(context);
        isSelected  = new HashMap<Integer, Boolean>();
        initDate();
    }

    private void initDate() {
        for (int i = 0; i < contextList.size(); i++) {
            getIsSelected().put(i,false);
        }
    }


    @Override
    public int getCount(){
        return contextList.size();
    }

    @Override
    public Object getItem(int position){
        return contextList.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            // 获得ViewHolder对象
            holder = new ViewHolder();
            // 导入布局并赋值给convertview
            convertView = inflater.inflate(R.layout.item_checkbox_list, null);
            holder.tv = (TextView) convertView.findViewById(R.id.item_tv);
            holder.cb = (CheckBox) convertView.findViewById(R.id.item_cb);
            // 为view设置标签
            convertView.setTag(holder);
        } else {
            // 取出holder
            holder = (ViewHolder) convertView.getTag();
        }


        // 设置list中TextView的显示
        holder.tv.setText(contextList.get(position));
        // 根据isSelected来设置checkbox的选中状况
        holder.cb.setChecked(getIsSelected().get(position));
        return convertView;
    }



    public static HashMap<Integer,Boolean> getIsSelected() {
        return isSelected;
    }




    public static void setIsSelected(HashMap<Integer,Boolean> isSelected) {
        CheckBoxListAdapter.isSelected = isSelected;
    }
}
