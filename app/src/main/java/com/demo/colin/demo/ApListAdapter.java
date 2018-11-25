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

public class ApListAdapter extends BaseAdapter {
    private ArrayList<String > contextList;
    private static HashMap<Integer,Boolean> isSelected;
    private LayoutInflater inflater ;
    private HashMap<Integer,Boolean> selectedMap;
    final class ViewHolder{
         TextView tv;
         CheckBox cb ;
    }


    // constructor 函数
    ApListAdapter(ArrayList<String> contextList, Context context, HashMap<Integer, Boolean> selectedMap){
        this.contextList = contextList;
        inflater = LayoutInflater.from(context);
        isSelected  = new HashMap<>();
        this.selectedMap = selectedMap;
        initDate();
    }

    // 初始化记录static map
    private void initDate() {
        for (int i = 0; i < contextList.size(); i++) {
            // 所有选项都在未选中状态
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
        ViewHolder holder ;
        if (convertView == null) {
            // 获得ViewHolder对象
            holder = new ViewHolder();
            // 导入布局并赋值给convert view
            convertView = inflater.inflate(R.layout.item_checkbox_list, null);
            holder.tv =  convertView.findViewById(R.id.item_tv);
            holder.cb = convertView.findViewById(R.id.item_cb);
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



    static HashMap<Integer,Boolean> getIsSelected() {
        return isSelected;
    }

    // 把非static 记录map 返回
    HashMap<Integer, Boolean> getSelectedMap() {
        for(int position = 0; position < this.getCount(); position++){
            selectedMap.put(position,getIsSelected().get(position));
        }
        return selectedMap;
    }
}
