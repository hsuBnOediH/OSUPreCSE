package com.demo.colin.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class PreCSEActivity extends AppCompatActivity {


    private ListView listView;
    private CheckBoxListAdapter checkBoxListAdapter;
    private ArrayList<String> contextList;
    private Button bt_selectedall;
    private Button bt_cancel;
    private Button bt_deseletedall;
    private int checkNum;
    private TextView tv_showNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_cse);
        listView = (ListView) findViewById(R.id.lv);
        bt_selectedall = findViewById(R.id.);
    }
}
