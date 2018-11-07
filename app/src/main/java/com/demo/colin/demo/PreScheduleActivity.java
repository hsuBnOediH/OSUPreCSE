package com.demo.colin.demo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class PreScheduleActivity extends Activity {
    private ArrayList<String> availableClass = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_schedule);
        Intent intent = getIntent();
        HashSet<String> set = (HashSet<String>) getIntent().getSerializableExtra("Set");

        TextView textView  = findViewById(R.id.item_class);

        CourseTree courseTree = new CourseTree("Pre");
        courseTree.deleteAll(set);
        initClass(set);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        /**
         * 创建一个linearlayoutmaneger对象，并将他设置到recyclerview当中。layoutmanager用于指定
         * recyclerview的布局方式，这里是线性布局的意思。可以实现和listview类似的效果。
         *
         * 接下来我们创建了Fruitadapter的实例，并将数据传进去
         */
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        AvlilableClassAdapter adapter = new  AvlilableClassAdapter(availableClass);
        recyclerView.setAdapter(adapter);
    }

    private void initClass(Set<String> set) {
        for(String s : set){
            this.availableClass.add(s);
        }

    }
}
