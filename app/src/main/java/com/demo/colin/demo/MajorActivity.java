package com.demo.colin.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.HashMap;

public class MajorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_major);

        Intent intent = getIntent();
        String data = intent.getStringExtra("ConditionMap");
        TextView textView = findViewById(R.id.textaaa);
        textView.setText(data);
        Log.d("data pased", data);
    }
}
