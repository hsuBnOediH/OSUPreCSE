package com.demo.colin.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class PreCSEActivity extends AppCompatActivity {

    // 创建adapter
    private CheckBoxListAdapter checkBoxListAdapter;
    private ArrayList<String> contextList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_cse);
        ListView listView = findViewById(R.id.lv);
        contextList = new ArrayList<>();
        initData();
        HashMap<Integer, Boolean> satisfyClass = new HashMap<>();
        checkBoxListAdapter = new CheckBoxListAdapter(contextList,this, satisfyClass);
        listView.setAdapter(checkBoxListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckBoxListAdapter.ViewHolder holder = (CheckBoxListAdapter.ViewHolder) view.getTag();
                holder.cb.toggle();
                CheckBoxListAdapter.getIsSelected().put(position, holder.cb.isChecked());
                dataChanged();
            }
        });
        Button nextStep = findViewById(R.id.ap_confirm_button);
        nextStep.setOnClickListener(nextStepListener);
    }

    private void initData(){
        contextList.add("我的ap可以换掉CSE1223");
        contextList.add("我的ap可以换掉MATH1151");
        contextList.add("我的ap可以换掉MATH1152");
        contextList.add("我的ap可以换掉ENG1110");
        contextList.add("我的ap可以换掉PHY1250");
        contextList.add("我的ap可以换掉PHY1251");
    }

    private void dataChanged(){
        checkBoxListAdapter.notifyDataSetChanged();
    }

    Button.OnClickListener nextStepListener= new Button.OnClickListener() {
        public void onClick(View v) {
            Intent jumpPre = new Intent(com.demo.colin.demo.PreCSEActivity.this, MajorActivity.class);
            HashMap<Integer,Boolean> map = checkBoxListAdapter.getSelectedMap();
            HashSet<String> set = new HashSet<>();

            if(map.get(0)) set.add("CSE1223");

            if(map.get(1))set.add("MATH1151");

            if(map.get(2))set.add("MATH1152");

            if(map.get(3)) set.add("ENG1110");

            if(map.get(4)) set.add("PHY1250");

            if(map.get(5)) set.add("PHY1251");

            jumpPre.putExtra("Set",set);
            startActivity(jumpPre);
            PreCSEActivity.this.finish();
        }
    };
}
