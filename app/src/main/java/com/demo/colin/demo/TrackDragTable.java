package com.demo.colin.demo;

import java.util.ArrayList;
import java.util.HashMap;

public class TrackDragTable {
    class CourseRow {
        String courseName;
        int linerLayoutID;
        int miniLayoutID;
        int maxLayoutID;
    }

    private HashMap<String, CourseRow> table;
    private CourseTree courseTree;

    public TrackDragTable(CourseTree courseTree) {
        this.table = new HashMap<>();
        this.courseTree = courseTree;
        initialAllCourseInTable();
    }

    private void initialAllCourseInTable() {
        for (String course : this.courseTree.getAllCourse().keySet()) {
            CourseRow courseRow = new CourseRow();
            courseRow.courseName = course;
            courseRow.linerLayoutID = 0;
            courseRow.miniLayoutID = 0;
            courseRow.maxLayoutID = 5;
        }
    }

    private void initialCourse(String courseName) {
        CourseRow courseRow = new CourseRow();
        courseRow.courseName = courseName;
        courseRow.linerLayoutID = 0;
        courseRow.miniLayoutID = 0;
        courseRow.maxLayoutID = 5;
    }

    void deleteCourseInTable(String selectedCourse, CourseTree courseTree) {
        this.courseTree = courseTree;
        ArrayList<String> preCourse = this.courseTree.getAllCourse().get(selectedCourse).getPre();
        int firsrSubSize = this.courseTree.getAllCourse().get(selectedCourse).getSub().size();
        ArrayList<String> subCourse = getAllSub(selectedCourse, firsrSubSize);

        initialCourse(selectedCourse);

        for (String sub : subCourse) {
            initialCourse(sub);
        }
        for (String pre : preCourse) {
            CourseRow courseRow = new CourseRow();
            courseRow.courseName = pre;
            courseRow.miniLayoutID = getMiniLayout(pre);
            courseRow.maxLayoutID = getMaxLayoutID(pre);
            courseRow.linerLayoutID = this.table.get(pre).linerLayoutID;
            this.table.put(pre, courseRow);
        }


    }

    private ArrayList<String> getAllSub(String courseName, int size) {
        ArrayList<String> allSub = this.courseTree.getAllCourse().get(courseName).getSub();
        for (int i = 0; i < size; i++) {
            ArrayList<String> subs = this.courseTree.getAllCourse().get(allSub.get(i)).getSub();
            if (subs.size() > 0) {
                allSub.addAll(getAllSub(allSub.get(i), subs.size()));
            }
        }
        return allSub;
    }

    void addCourse(String courseName, int curLayoutNum) {
        CourseRow addingCourse = new CourseRow();
        addingCourse.courseName = courseName;
        addingCourse.linerLayoutID = curLayoutNum;
        addingCourse.miniLayoutID = getMiniLayout(courseName); // 根据Course 计算最小值 这个函数最好放在courseTree里面
        addingCourse.maxLayoutID = getMaxLayoutID(courseName);
        this.table.put(courseName, addingCourse);
    }

    void updateAllPreMax(ArrayList<String> preCourses) {
        for (String preCourse : preCourses) {
            ArrayList<String> subCourses = this.courseTree.getAllCourse().get(preCourse).getSub();
            int max = 5;
            for (String subCourse : subCourses) {
                if (this.table.containsKey(subCourse)) {
                    if (this.table.get(subCourse).linerLayoutID < max) {
                        max = this.table.get(subCourse).linerLayoutID;
                    }
                }
            }
            if (this.table.containsKey(preCourse)) {
                CourseRow preRow = this.table.get(preCourse);
                preRow.maxLayoutID = max - 1;
                this.table.put(preCourse, preRow);
            }
        }

    }

    public int getMaxLayoutID(String courseName) {
        if (!this.table.containsKey(courseName)) {
            return 5;
        }
        CourseRow curCourse = this.table.get(courseName);
        return curCourse.maxLayoutID;
    }

    private int getMiniLayout(String courseName) {
        //TODO 得到前一层的课
        ArrayList<String> perCoreneList = this.courseTree.getSingleCourse(courseName).getPre();
        int miniLayout = 0;
        for (String course : perCoreneList) {
            for (CourseRow courseRow : table.values()) {
                if (course.equals(courseRow.courseName)) {
                    if (courseRow.linerLayoutID > miniLayout) {
                        miniLayout = courseRow.linerLayoutID;
                    }
                }
            }
        }
        return miniLayout + 1;
    }

    boolean isDoableSem(String courseName, int curLayout) {
        int miniLayout = getMiniLayout(courseName);
        int maxLayout = getMaxLayoutID(courseName);
        return curLayout >= miniLayout && curLayout <= maxLayout;
    }

    boolean isSuitableSem(String courseName, int curLayout) {
        int miniLayout = getMiniLayout(courseName);
        return curLayout == miniLayout;
    }


}
