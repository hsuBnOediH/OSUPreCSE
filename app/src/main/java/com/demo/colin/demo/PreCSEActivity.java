package com.demo.colin.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class PreCSEActivity extends AppCompatActivity {


    private ListView listView;
    private CheckBoxListAdapter checkBoxListAdapter;
    private ArrayList<String> contextList;
    private Button nextStep;

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
        contextList.add("option 1");
        contextList.add("option 2");
        contextList.add("option 3");
        contextList.add("option 4");
        contextList.add("option 5");
    }

    private void dataChanged(){

        checkBoxListAdapter.notifyDataSetChanged();
    }

    Button.OnClickListener nextStepListener= new Button.OnClickListener() {
        public void onClick(View v) {
            Intent jumpPre = new Intent(com.demo.colin.demo.PreCSEActivity.this, MajorActivity.class);
            startActivity(jumpPre);
            PreCSEActivity.this.finish();
        }
    };
}
