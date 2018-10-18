package com.demo.colin.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button preMajor;
    private Button major;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preMajor = findViewById(R.id.main_pre_major);
        preMajor.setOnClickListener(preMajorListener);
        major = findViewById(R.id.main_in_major);
        major.setOnClickListener(majorListener);


    }
    Button.OnClickListener preMajorListener = new Button.OnClickListener() {
        public void onClick(View v) {
            Intent jumpPre = new Intent(com.demo.colin.demo.MainActivity.this, PreCSEActivity.class);
            startActivity(jumpPre);
            MainActivity.this.finish();
        }
    };

    Button.OnClickListener majorListener = new Button.OnClickListener(){
      public void onClick(View view){
          Intent jumpMajor = new Intent(com.demo.colin.demo.MainActivity.this,MajorActivity.class);
          startActivity(jumpMajor);
      }
    };
}
