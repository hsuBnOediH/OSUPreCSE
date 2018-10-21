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

public class PreCSEActivity extends AppCompatActivity {


    private ListView listView;
    private CheckBoxListAdapter checkBoxListAdapter;
    private ArrayList<String> contextList;
    private Button nextStep;
    private HashMap<String,Boolean> staify;

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
                CheckBoxListAdapter.ViewHolder holder = (CheckBoxListAdapter.ViewHolder) view.getTag();
                holder.cb.toggle();
                checkBoxListAdapter.getIsSelected().put(position, holder.cb.isChecked());
                dataChanged();
            }
        });
        nextStep = findViewById(R.id.ap_confirm_button);
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
            Intent jumpPre = new Intent(com.demo.colin.demo.PreCSEActivity.this, MainActivity.class);

            startActivity(jumpPre);
            PreCSEActivity.this.finish();
        }
    };
}
