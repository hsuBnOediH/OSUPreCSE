package com.demo.colin.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class PreCSEActivity extends AppCompatActivity {

    /**
     *  Create an ApListAdapter adapter
     */
    private ApListAdapter apListAdapter;
    /**
     * Create an arrayList to contain the options of available courses hints
     */
    private ArrayList<String> contextList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_cse);
        /* Declare the listView by finding the listView in layout */
        ListView listView = findViewById(R.id.ap_checkbox_list);
        /* Initialize the ArrayList contextList */
        contextList = new ArrayList<>();
        initData();
        /* Declare an map for recording the selections of the user */
        HashMap<Integer, Boolean> satisfyClass = new HashMap<>();
        /* Instantiate the adapter for apListAdapter */
        apListAdapter = new ApListAdapter(contextList, this, satisfyClass);
        /* Set up listView using apListAdapter */
        listView.setAdapter(apListAdapter);
        /* Set listener for items in listView */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ApListAdapter.ViewHolder holder = (ApListAdapter.ViewHolder) view.getTag();
                holder.cb.toggle();
                ApListAdapter.getIsSelected().put(position, holder.cb.isChecked());
                dataChanged();
            }
        });
        /* Declare button to next page */
        Button nextStep = findViewById(R.id.ap_confirm_button);
        nextStep.setOnClickListener(nextStepListener);
    }

    /**
     * @requires
     * contextList != null
     * @ensures <pre>
     *     contextList contains and only contain the string listed below
     * </pre>
     */
    private void initData() {
        contextList.add("My AP-Computer Science A ≥ 3");
        contextList.add("My Calculus AB ≥ 3");
        contextList.add("My Calculus BC ≥ 3");
        contextList.add("My English Literature and Composition ≥ 3");
        contextList.add("My Physics C: Mechanics ≥ 3");
        contextList.add("My Physics C: Electricity and Magnetism ≥ 3");
        contextList.add("I am an International Student with Native Language is NOT English");
    }


    /**
     * @requires
     * this.apListAdapter != null
     * @ensures <pre>
     *     this.apListAdapter =<update items in this.context>
     * </pre>
     */
    private void dataChanged() {
        apListAdapter.notifyDataSetChanged();
    }

   /* Declare button listener for button to next step */
    Button.OnClickListener nextStepListener = new Button.OnClickListener() {
        /*Response method of listener*/
        public void onClick(View v) {
            /*Build path to next activity*/
            Intent jumpPre = new Intent(com.demo.colin.demo.PreCSEActivity.this, EnglishActivity.class);
            /*Pass the set named Set to next activity */
            jumpPre.putExtra("Set", getSatisfySet(apListAdapter.getSelectedMap()));
            /*Jump to next activity*/
            startActivity(jumpPre);
        }
    };

    /**
     * Translate the selected map into set for passing between activity
     *
     * @param map   Selected map form the AP list adapter
     * @return  Set     A translated selected choice form map
     */
    private HashSet<String> getSatisfySet(HashMap<Integer, Boolean> map) {
        HashSet<String> set = new HashSet<>();
        if (map.get(0)) set.add("CSE1223");

        if (map.get(1)) set.add("MATH1151");

        if (map.get(2)) set.add("MATH1152");

        if (map.get(3)) set.add("ENG1110");

        if (map.get(4)) set.add("PHY1250");

        if (map.get(5)) set.add("PHY1251");

        if (map.get(6)) set.add("INTER");

        return set;
    }


}
