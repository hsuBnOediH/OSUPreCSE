package com.demo.colin.demo;

import android.widget.LinearLayout;

import java.util.HashSet;
import java.util.Set;

public class TrackDragTable {
    class CourseRow{
        String courseName;
        int linerLayoutID;
        int miniLayoutID;
    }

    private Set table;

    public TrackDragTable() {
        this.table = new HashSet();
    }

    public void addCourse(String courseName, int curLayoutNum){
        CourseRow addingCourse = new CourseRow();
        addingCourse.courseName = courseName;
        addingCourse.linerLayoutID = curLayoutNum;
       addingCourse.miniLayoutID = 0; // 根据Course 计算最小值 这个函数最好放在courseTree里面
    }

}
