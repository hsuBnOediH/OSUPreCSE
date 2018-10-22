package com.demo.colin.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 主页的两个按钮声明
        Button preMajor = findViewById(R.id.main_pre_major);
        preMajor.setOnClickListener(preMajorListener);
        Button major = findViewById(R.id.main_in_major);
        major.setOnClickListener(majorListener);


    }
    //preMajor按钮 跳转preCSE页面
    Button.OnClickListener preMajorListener = new Button.OnClickListener() {
        public void onClick(View v) {
            Intent jumpPre = new Intent(com.demo.colin.demo.MainActivity.this, PreCSEActivity.class);
            startActivity(jumpPre);
        }
    };
    //major按钮 跳转major页面
    Button.OnClickListener majorListener = new Button.OnClickListener(){
      public void onClick(View view){
          Intent jumpMajor = new Intent(com.demo.colin.demo.MainActivity.this,MajorActivity.class);
          startActivity(jumpMajor);
      }
    };
}
