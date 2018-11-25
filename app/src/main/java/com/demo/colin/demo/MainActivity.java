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

        /* Declare two buttons object in the MainActivity
        *  PRE-CSE MAJOR button
        *  ALREADY IN MAJOR button.
        */
        Button preMajor = findViewById(R.id.main_pre_major);
        preMajor.setOnClickListener(preMajorListener);
        Button major = findViewById(R.id.main_in_major);
        major.setOnClickListener(majorListener);


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }


    /* Set listener for PRE-CSE MAJOR button for jumping to the PreCSEActivity */
    Button.OnClickListener preMajorListener = new Button.OnClickListener() {
        public void onClick(View v) {
            Intent jumpPre = new Intent(com.demo.colin.demo.MainActivity.this, PreCSEActivity.class);
            startActivity(jumpPre);
        }
    };
    /* Set listener for ALREADY IN MAJOR button for jumping to the MajorActivity */
    Button.OnClickListener majorListener = new Button.OnClickListener(){
      public void onClick(View view){
          Intent jumpMajor = new Intent(com.demo.colin.demo.MainActivity.this,MajorActivity.class);
          startActivity(jumpMajor);
      }
    };
}
