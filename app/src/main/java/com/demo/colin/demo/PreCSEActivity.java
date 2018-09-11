package com.demo.colin.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class PreCSEActivity extends AppCompatActivity {


    ListView ap_list;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_cse);

        ap_list = findViewById(R.id.ap_list);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.addAll(Arrays.asList(getResources().getStringArray(R.array.ap_list)));

        adapter = new ArrayAdapter<>(
                PreCSEActivity.this,
                android.R.layout.simple_list_item_1,
                arrayList
        );

        ap_list.setAdapter(adapter);

    }
}
