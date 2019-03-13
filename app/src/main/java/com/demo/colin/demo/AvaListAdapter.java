package com.demo.colin.demo;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;

class AvaListAdapter extends BaseAdapter {

    private HashSet<String> availCourse = new HashSet<>();
    public ArrayList<String> availCourseList = new ArrayList<>();
    private LayoutInflater inflater;
    private static final String TEXT_VIEW_TAG = "DRAG TEXT";


    public AvaListAdapter(HashSet<String> availCourse, Context context) {
        this.availCourse = availCourse;
        inflater = LayoutInflater.from(context);
        availCourseList.addAll(availCourse);

    }

    @Override
    public int getCount() {
        return availCourse.size();
    }

    @Override
    public Object getItem(int position) {
        return this.availCourseList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            //We must create a View:
            convertView = inflater.inflate(R.layout.item_sch_ava, parent, false);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.sch_ava_item_text);
        textView.setTextSize(20);
        textView.setTextColor(Color.BLACK);
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        textView.setText(this.availCourseList.get(position));
        textView.setTag(TEXT_VIEW_TAG);
        //Here we can do changes to the convertView, such as set a text on a TextView
        //or an image on an ImageView.
        convertView.setTag(TEXT_VIEW_TAG);
        return convertView;
    }


    public void remove(String course) {
        availCourseList.remove(course);
        availCourse.remove(course);
        notifyDataSetChanged();
    }

    public void add(String course) {
        availCourseList.add(course);
        availCourse.add(course);
        notifyDataSetChanged();
    }

    public void updateAvailable(HashSet<String> newAvailableCourse) {
        availCourseList.addAll(newAvailableCourse);
        availCourse.addAll(newAvailableCourse);
        notifyDataSetChanged();
    }
}
