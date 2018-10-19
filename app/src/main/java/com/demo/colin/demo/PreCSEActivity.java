package com.demo.colin.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class PreCSEActivity extends AppCompatActivity {


    private ListView listView;
    private CheckBoxListAdapter checkBoxListAdapter;
    private ArrayList<String> contextList;
    private static class ViewHolder {
        TextView tv;
        CheckBox cb;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_cse);
        listView = findViewById(R.id.lv);
        contextList = new ArrayList<>();
        initData();
        checkBoxListAdapter = new CheckBoxListAdapter(contextList,this);
        listView.setAdapter(checkBoxListAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ViewHolder holder = (ViewHolder) view.getTag();
                holder.cb.toggle();

                checkBoxListAdapter.getIsSelected().put(position, holder.cb.isChecked());
                dataChanged();
            }
        });
    }

    private void initData(){
        contextList.add("option 1");
        contextList.add("option 2");
        contextList.add("option 3");
        contextList.add("option 4");
        contextList.add("option 5");
    }

    private void dataChanged(){
        checkBoxListAdapter.notifyDataSetChanged();
    }
}
