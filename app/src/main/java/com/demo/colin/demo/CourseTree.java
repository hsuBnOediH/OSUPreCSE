package com.demo.colin.demo;

import java.util.ArrayList;
import java.util.Set;

public class CourseTree {

    private ArrayList<Course> courses;


    public CourseTree(String major) {
        if (major.equals("Pre")) {
            this.courses = preTreeBuild();
        } else {
            this.courses = coreMajorTreeBuild();
            if (major.equals("AI")) {
                aITreeBuild(this.courses);
            } else if (major.equals("DataBase")) {
                DataTreeBuild(this.courses);
            } else if (major.equals("SWS")) {
                SoftTreeBuild(this.courses);
            } else if (major.equals("ComputerGGD")) {
                CGGDTreeBuild(this.courses );
            }
        }
    }


    private static ArrayList<Course> preTreeBuild() {
        ArrayList<Course> res = new ArrayList<>();
        ArrayList<String> pre = new ArrayList<>();
        ArrayList<String> sub = new ArrayList<>();
        sub.add("ENG1182");
        res.add(new Course("ENG1181", pre, sub));

        arrayClear(pre, sub);
        pre.add("ENG1181");
        res.add(new Course("ENG1182", pre, sub));

        arrayClear(pre, sub);
        sub.add("ENG1902");
        res.add(new Course("ENG1901", pre, sub));

        arrayClear(pre, sub);
        pre.add("ENG1901");
        sub.add("ENG1110");
        res.add(new Course("ENG1902", pre, sub));

        arrayClear(pre, sub);
        pre.add("ENG1902");
        res.add(new Course("ENG1110", pre, sub));

        arrayClear(pre, sub);
        res.add(new Course("MATH1151", pre, sub));

        arrayClear(pre, sub);
        sub.add("PHY1251");
        res.add(new Course("PHY1250", pre, sub));

        arrayClear(pre, sub);
        pre.add("PHY1250");
        res.add(new Course("PHY1251", pre, sub));


        arrayClear(pre, sub);
        sub.add("CSE2221");
        res.add(new Course("CSE1223", pre, sub));

        arrayClear(pre, sub);
        pre.add("CSE1223");
        res.add(new Course("CSE2221", pre, sub));

        return res;
    }
    private static ArrayList<Course> coreMajorTreeBuild() {
        ArrayList<Course> res= new ArrayList<>();
        ArrayList<String> pre = new ArrayList<>();
        ArrayList<String> sub = new ArrayList<>();
        pre.add("MATH1152 or Math 1172 or MATH2153");
        sub.add("CSE2331");
        res.add(new Course("STAT3470", pre, sub));

        arrayClear(pre, sub);
        sub.add("ECE2020");
        res.add(new Course("ECE2060", pre, sub));
        arrayClear(pre, sub);
        pre.add("ECE2060");
        res.add(new Course("ECE2020", pre, sub));

        arrayClear(pre, sub);
        pre.add("((MATH1152 or Math 1172) and CSE2321) or MATH2153");
        sub.add("CSE3321");
        res.add(new Course("MATH3345", pre, sub));

        arrayClear(pre, sub);
        pre.add("(MATH1152 and CSE2321) or MATH1172 or MATH2153");
        res.add(new Course("MATH2568", pre, sub));

        arrayClear(pre, sub);
        sub.add("CSE2501");
        sub.add("CSE3901");
        sub.add("CSE3902");
        sub.add("CSE3903");
        res.add(new Course("ENG2367", pre, sub));

        arrayClear(pre, sub);
        pre.add("CSE2221");
        sub.add("CSE2331");
        sub.add("CSE2421");
        res.add(new Course("CSE2231", pre, sub));

        arrayClear(pre, sub);
        pre.add("CSE2221");
        sub.add("CSE2331");
        sub.add("CSE2421");
        res.add(new Course("CSE2321", pre, sub));

        arrayClear(pre, sub);
        pre.add("CSE2231");
        pre.add("CSE2321");
        pre.add("STAT3470");
        sub.add("CSE3341");
        res.add(new Course("CSE2331", pre, sub));

        arrayClear(pre, sub);
        pre.add("CSE2231");
        pre.add("CSE2321");
        sub.add("CSE2431");
        res.add(new Course("CSE2421", pre, sub));

        arrayClear(pre, sub);
        pre.add("CSE2421");
        res.add(new Course("CSE2431", pre, sub));

        arrayClear(pre, sub);
        pre.add("CSE2231");
        pre.add("ENG2367");
        res.add(new Course("CSE2501", pre, sub));

        arrayClear(pre, sub);
        pre.add("CSE390X");
        sub.add("CSE5911");
        res.add(new Course("CSE3231", pre, sub));

        arrayClear(pre, sub);
        pre.add("CSE2231");
        pre.add("CSE2321");
        res.add(new Course("CSE3241", pre, sub));

        arrayClear(pre, sub);
        pre.add("CSE390X");
        pre.add("CSE2331");
        res.add(new Course("CSE3341", pre, sub));

        arrayClear(pre, sub);
        pre.add("CSE2421");
        res.add(new Course("CSE3421", pre, sub));

        arrayClear(pre, sub);
        pre.add("CSE2421");
        res.add(new Course("CSE3461", pre, sub));

        arrayClear(pre, sub);
        pre.add("CSE2331");
        pre.add("ENG2367");
        sub.add("CSE5914");
        res.add(new Course("CSE3521", pre, sub));

        arrayClear(pre, sub);
        pre.add("CSE3902");
        sub.add("CSE5912");
        res.add(new Course("CSE3541", pre, sub));

        arrayClear(pre, sub);
        pre.add("ENG2367");
        pre.add("ENG2421");
        res.add(new Course("CSE3901", pre, sub));

        arrayClear(pre, sub);
        pre.add("ENG2367");
        pre.add("ENG2421");
        sub.add("CSE3541");
        sub.add("CSE5912");
        res.add(new Course("CSE3902", pre, sub));

        arrayClear(pre, sub);
        pre.add("ENG2367");
        pre.add("ENG2421");
        res.add(new Course("CSE3903", pre, sub));

        arrayClear(pre, sub);
        pre.add("CSE2501");
        pre.add("CSE3231");
        pre.add("CSE390X");
        res.add(new Course("CSE5911", pre, sub));

        arrayClear(pre, sub);
        pre.add("CSE2501");
        pre.add("CSE3541");
        pre.add("CSE390X");
        res.add(new Course("CSE5912", pre, sub));

        arrayClear(pre, sub);
        pre.add("CSE2501");
        pre.add("CSE3541");
        pre.add("CSE390X");
        res.add(new Course("CSE5913", pre, sub));

        arrayClear(pre, sub);
        pre.add("CSE2501");
        pre.add("CSE3521");
        pre.add("CSE390X");
        res.add(new Course("CSE5914", pre, sub));

        arrayClear(pre, sub);
        pre.add("CSE2501");
        pre.add("CSE3241");
        pre.add("CSE390X");
        res.add(new Course("CSE5915", pre, sub));

        return res;
    }
    private static void aITreeBuild(ArrayList<Course> res) {
        ArrayList<String> pre = new ArrayList<>();
        ArrayList<String> sub = new ArrayList<>();
        for (Course course:res) {
            if (course.getName().equals("CSE2331")) {
                course.getSub().add("CSE5524");
            }
            if (course.getName().equals("CSE3521")) {
                course.getSub().add("CSE5522");
                course.getSub().add("CSE5523");
                course.getSub().add("CSE5525");
                course.getSub().add("CSE5526");
            }
        }
        arrayClear(pre, sub);
        pre.add("CSE3521");
        res.add(new Course("CSE5522", pre, sub));

        arrayClear(pre, sub);
        pre.add("CSE3521");
        res.add(new Course("CSE5523", pre, sub));

        arrayClear(pre, sub);
        pre.add("CSE3521");
        res.add(new Course("CSE5525", pre, sub));

        arrayClear(pre, sub);
        pre.add("CSE3521");
        res.add(new Course("CSE5526", pre, sub));
    }

    private static void DataTreeBuild(ArrayList<Course> res) {
        ArrayList<String> pre = new ArrayList<>();
        ArrayList<String> sub = new ArrayList<>();
        for (Course course:res) {
            if (course.getName().equals("CSE2331")) {
                course.getSub().add("CSE5243");
            }
            if (course.getName().equals("CSE3241")) {
                course.getSub().add("CSE5242");
            }
        }
        arrayClear(pre, sub);
        pre.add("CSE3241");
        res.add(new Course("CSE5242", pre, sub));

        arrayClear(pre, sub);
        pre.add("CSE3241");
        res.add(new Course("CSE5243", pre, sub));

        arrayClear(pre, sub);
        pre.add("CSE5243");
        res.add(new Course("CSE5523", pre, sub));
    }
    private static void SoftTreeBuild(ArrayList<Course> res) {
        ArrayList<String> pre = new ArrayList<>();
        ArrayList<String> sub = new ArrayList<>();
        for (Course course:res) {
            if (course.getName().equals("CSE390X")) {
                course.getSub().add("CSE3232");
                course.getSub().add("CSE5236");
            }
            if (course.getName().equals("CSE2431")) {
                course.getSub().add("CSE5234");
            }
        }
        arrayClear(pre, sub);
        pre.add("CSE390X");
        res.add(new Course("CSE3232", pre, sub));

        arrayClear(pre, sub);
        pre.add("CSE2431");
        res.add(new Course("CSE5234", pre, sub));

        arrayClear(pre, sub);
        pre.add("CSE5911");
        res.add(new Course("CSE5235", pre, sub));

        arrayClear(pre, sub);
        pre.add("CSE390X");
        res.add(new Course("CSE5236", pre, sub));
    }
    private static void CGGDTreeBuild(ArrayList<Course> res) {
        ArrayList<String> pre = new ArrayList<>();
        ArrayList<String> sub = new ArrayList<>();
        for (Course course:res) {
            if (course.getName().equals("MATH2568")) {
                course.getSub().add("CSE5542");
                course.getSub().add("CSE5243");
            }
            if (course.getName().equals("CSE3902")) {
                course.getSub().add("CSE5542");
            }
            if (course.getName().equals("CSE3541")) {
                course.getSub().add("CSE5544");
                course.getSub().add("CSE5545");
            }
        }
        arrayClear(pre, sub);
        pre.add("MATH2568");
        pre.add("CSE3902");
        res.add(new Course("CSE5542", pre, sub));

        arrayClear(pre, sub);
        pre.add("MATH2568");
        res.add(new Course("CSE5543", pre, sub));

        arrayClear(pre, sub);
        pre.add("CSE3541");
        res.add(new Course("CSE5544", pre, sub));

        arrayClear(pre, sub);
        pre.add("CSE3541");
        res.add(new Course("CSE5545", pre, sub));
    }

    private static void arrayClear(ArrayList<String> pre, ArrayList<String> sub) {
        pre.clear();
        sub.clear();

    }

    public ArrayList<String> getChoice() {
        ArrayList<String> res = new ArrayList<>();
        for (Course c : this.courses) {
            if (c.getPre().size() == 0) {
                res.add(c.getName());
            }
        }
        return res;
    }

    public void deletClass(String name) {
        int i = 0;
        while (!this.courses.get(i).getName().equals(name)) {
            i++;
        }

        ArrayList<String> sub = this.courses.get(i).getSub();

        for (String subName : sub) {
            int j = 0;
            while (!this.courses.get(j).getName().equals(subName)) {
                j++;
            }
            this.courses.get(j).deletePre(name);
        }

        this.courses.remove(i);
    }


    public void deleteAll(Set<String> set) {
        for (String name : set) {
            int i = 0;
            // Get the course node
            while (!this.courses.get(i).getName().equals(name)) {
                i++;
            }
            // Get the next course list for this specific course node
            ArrayList<String> sub = this.courses.get(i).getSub();
            // Delete all the prerequisite of all the next courses
            for (String subName : sub) {
                int j = 0;
                // Get one course node from the next course lists
                while (!this.courses.get(j).getName().equals(subName)) {
                    j++;
                }
                // Update nextNode.pre by deleting the course node set.name
                this.courses.get(j).deletePre(name);
            }

            // Get the pre course list for this specific course node
            ArrayList<String> pre = this.courses.get(i).getPre();
            // Delete all the prerequisite of all the next courses
            for (String subName : pre) {
                int j = 0;
                // Get one course node from the next course lists
                while (!this.courses.get(j).getName().equals(subName)) {
                    j++;
                }
                // Update nextNode.pre by deleting the course node set.name
                this.courses.get(j).deleteSub(name);
                if (this.courses.get(j).getSub().size()==0) {
                    this.courses.remove(j);
                }
            }
            this.courses.remove(i);
        }
    }

}