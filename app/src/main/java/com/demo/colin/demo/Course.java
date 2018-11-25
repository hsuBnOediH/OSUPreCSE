package com.demo.colin.demo;

import java.util.ArrayList;

public class Course {
    private ArrayList<String> pre = new ArrayList<>();
    private ArrayList<String> sub = new ArrayList<>();
    private String courseName;

    public Course(String courseName, ArrayList<String> pre,
                  ArrayList<String> sub) {
        this.courseName = courseName;
        this.pre.addAll(pre);
        this.sub.addAll(sub);
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
        return this.pre.size();
    }

    public int getSubSize() {
        return this.sub.size();
    }

    public void deletePre(String name) {
        this.pre.remove(name);
    }

    public void deleteAllPre() {
        this.pre.clear();
    }

    public void deleteSub(String name) {
        this.sub.remove(name);
    }
}
