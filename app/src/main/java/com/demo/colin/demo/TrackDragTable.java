package com.demo.colin.demo;

import java.util.ArrayList;
import java.util.HashMap;

class TrackDragTable {
    class CourseRow {
        String courseName;
        int linerLayoutID;
        int miniLayoutID;
        int maxLayoutID;
    }

    private HashMap<String, CourseRow> table;
    private CourseTree courseTree;

    TrackDragTable(CourseTree courseTree) {
        this.table = new HashMap<>();
        this.courseTree = courseTree;
        initialAllCourseInTable();
    }

    private void initialAllCourseInTable() {
        for (String course : this.courseTree.getAllCourse().keySet()) {
            initialCourse(course);
        }
    }

    private void initialCourse(String courseName) {
        CourseRow courseRow = new CourseRow();
        courseRow.courseName = courseName;
        courseRow.linerLayoutID = 0;
        courseRow.miniLayoutID = 0;
        courseRow.maxLayoutID = 5;
        table.put(courseName, courseRow);
    }

    void deleteCourseInTable(String selectedCourse, CourseTree courseTree) {
        this.courseTree = courseTree;
        ArrayList<String> preCourse = this.courseTree.getAllCourse().get(selectedCourse).getPre();
        ArrayList<String> subCourse = this.courseTree.getDeepSub(selectedCourse);

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

    private void updateMax(String course) {
        CourseRow courseRow = new CourseRow();
        courseRow.courseName = course;
        courseRow.linerLayoutID = table.get(course).linerLayoutID;
        courseRow.miniLayoutID = table.get(course).miniLayoutID;
        courseRow.maxLayoutID = getMaxLayoutID(course);
        table.put(course, courseRow);

    }

    void updateAllPreMax(ArrayList<String> preCourses) {
        for (String preCourse : preCourses) {
            updateMax(preCourse);
        }
    }

    void addCourse(String courseName, int curLayoutNum) {
        CourseRow addingCourse = new CourseRow();
        addingCourse.courseName = courseName;
        addingCourse.linerLayoutID = curLayoutNum;
        addingCourse.miniLayoutID = getMiniLayout(courseName);
        // 根据Course 计算最小值 这个函数最好放在courseTree里面
        addingCourse.maxLayoutID = getMaxLayoutID(courseName);
        this.table.put(courseName, addingCourse);
    }

    private int getMaxLayoutID(String courseName) {
        int max = 5;
        for (String sub : courseTree.getAllCourse().get(courseName).getSub()) {
            if (table.get(sub).linerLayoutID != 0) {
                if (table.get(sub).linerLayoutID < max) {
                    max = table.get(sub).linerLayoutID;
                }
            }
        }

        return max - 1;
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
