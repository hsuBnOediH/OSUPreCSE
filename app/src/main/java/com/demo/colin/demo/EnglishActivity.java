package com.demo.colin.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.HashSet;

public class EnglishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english);


        Intent intent = getIntent();
        HashSet<String> set = (HashSet<String>) getIntent().getSerializableExtra("Set");

        TextView textView  = findViewById(R.id.textaaa);
        textView.setText(set.toString());
    }
}
