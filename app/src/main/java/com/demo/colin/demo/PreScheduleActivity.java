package com.demo.colin.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;


public class PreScheduleActivity extends Activity {
    private ArrayList<String> availableClass = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_schedule);
        Intent intent = getIntent();
        HashSet<String> set = (HashSet<String>) getIntent().getSerializableExtra("Set");

        TextView textView  = findViewById(R.id.item_class);


        CourseTree courseTree = new CourseTree();
       courseTree.firstMarkAndAddAll(set);
        textView.setText(courseTree.printAvail());
    }

}
