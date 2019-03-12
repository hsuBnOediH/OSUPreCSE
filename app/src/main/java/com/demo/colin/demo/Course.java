package com.demo.colin.demo;

import java.util.ArrayList;

public class Course {
    private ArrayList<String> pre = new ArrayList<>();
    private ArrayList<String> sub = new ArrayList<>();
    private int unfinishedPre;

    private String courseName;

    Course(String courseName, ArrayList<String> pre,
           ArrayList<String> sub) {
        this.courseName = courseName;
        this.pre.addAll(pre);
        this.sub.addAll(sub);
        this.unfinishedPre=pre.size();
    }

    public ArrayList<String> getPre() {
        return this.pre;
    }

    public ArrayList<String> getSub() {
        return this.sub;
    }

    public String getName() {
        return this.courseName;
    }

    public int getPreSize() {
        return this.unfinishedPre;
    }

    public void finishOnePreCourse() {
        this.unfinishedPre--;
    }

//    public int getSubSize() {
//        return this.sub.size();
//    }
//
//    public void deletePre(String name) {
//        this.pre.remove(name);
//    }
//
//    public void deleteAllPre() {
//        this.pre.clear();
//    }
//
//    public void deleteSub(String name) {
//        this.sub.remove(name);
//    }
}
