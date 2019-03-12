package com.demo.colin.demo;

import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class TrackDragTable {
    class CourseRow {
        String courseName;
        int linerLayoutID;
        int miniLayoutID;
    }

    private Set<CourseRow> table;
    private CourseTree courseTree;

    public TrackDragTable(CourseTree courseTree) {
        this.table = new HashSet();
        this.courseTree=courseTree;
    }

    void addCourse(String courseName, int curLayoutNum) {
        CourseRow addingCourse = new CourseRow();
        addingCourse.courseName = courseName;
        addingCourse.linerLayoutID = curLayoutNum;
        addingCourse.miniLayoutID = getMiniLayout(courseName); // 根据Course 计算最小值 这个函数最好放在courseTree里面
        this.table.add(addingCourse);
    }

    private int getMiniLayout(String courseName) {
        //TODO 得到前一层的课
        ArrayList<String> perCoreneList = this.courseTree.getSingleCourse(courseName).getPre();
        int miniLayout = 0;
        for (String course : perCoreneList) {
            for (CourseRow courseRow : table) {
                if (course.equals(courseRow.courseName)) {
                    if (courseRow.linerLayoutID > miniLayout) {
                        miniLayout = courseRow.linerLayoutID;
                    }
                }
            }
        }
        return miniLayout + 1;
    }

    boolean isDoableSem(String courseName,int curLayout) {
        int miniLayout = getMiniLayout(courseName);
        return curLayout >= miniLayout;
    }

    boolean isSuitableSem(String courseName,int curLayout){
        int miniLayout = getMiniLayout(courseName);
        return  curLayout == miniLayout;
    }


}
