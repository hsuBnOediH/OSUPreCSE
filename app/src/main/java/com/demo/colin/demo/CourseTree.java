package com.demo.colin.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class CourseTree {

    private HashMap<String, Course> courses;
    private HashMap<String, Integer> flagTree;
    private HashSet<String> finishedCourses;
    private HashSet<String> availCourses;
    private HashSet<String> basicCourses;

    public CourseTree() {
        this.courses = preTreeBuild();
        this.flagTree = flagTreeBuild();
        this.basicCourses = basicBuild();
        this.finishedCourses = new HashSet<String>();
        this.availCourses = new HashSet<String>();
    }

    public void printAvail() {
        System.out.println("Student can take: " + this.availCourses.toString());
    }

    private static void arrayClear(ArrayList<String> pre,
                                   ArrayList<String> sub) {
        pre.clear();
        sub.clear();

    }

    private static HashMap<String, Integer> flagTreeBuild() {
        HashMap<String, Integer> flagTree = new HashMap<>();
        Set<String> courseSet = new HashSet<>();
        courseSet.addAll(Arrays.asList(new String[] { "ENG1181", "ENG1182",
                "ENG1901", "ENG1902", "ENG1110", "ENG2367", "MATH1151",
                "MATH1152", "MATH2153", "PHY1250", "PHY1251", "MATH3345",
                "STAT3470", "SUV1110", "CSE1223", "CSE2221", "CSE2231",
                "CSE2321", "CSE3241" }));
        for (String course : courseSet) {
            flagTree.put(course, 0);
        }
        return flagTree;
    }

    private static HashSet<String> basicBuild() {
        HashSet<String> courseSet = new HashSet<>();
        courseSet.addAll(Arrays.asList(new String[] { "ENG1181", "ENG1901",
                "MATH1151", "PHY1250", "SUV1110", "CSE1223" }));
        return courseSet;
    }

    private static HashMap<String, Course> preTreeBuild() {
        HashMap<String, Course> res = new HashMap<>();
        ArrayList<String> pre = new ArrayList<>();
        ArrayList<String> sub = new ArrayList<>();
        sub.add("ENG1182");
        res.put("ENG1181", new Course("ENG1181", pre, sub));

        arrayClear(pre, sub);
        pre.add("ENG1181");
        res.put("ENG1182", new Course("ENG1182", pre, sub));

        arrayClear(pre, sub);
        sub.add("ENG1902");
        res.put("ENG1901", new Course("ENG1901", pre, sub));

        arrayClear(pre, sub);
        pre.add("ENG1901");
        sub.add("ENG1110");
        res.put("ENG1902", new Course("ENG1902", pre, sub));

        arrayClear(pre, sub);
        pre.add("ENG1902");
        sub.add("ENG2367");
        res.put("ENG1110", new Course("ENG1110", pre, sub));

        arrayClear(pre, sub);
        pre.add("ENG1110");
        sub.add("CSE2501");
        sub.add("CSE3901");
        sub.add("CSE3902");
        sub.add("CSE3903");
        res.put("ENG2367", new Course("ENG2367", pre, sub));

        arrayClear(pre, sub);
        sub.add("PHY1251");
        res.put("PHY1250", new Course("PHY1250", pre, sub));

        arrayClear(pre, sub);
        pre.add("PHY1250");
        pre.add("MATH1151");
        pre.add("PHY1250");
        res.put("PHY1251", new Course("PHY1251", pre, sub));

        arrayClear(pre, sub);
        sub.add("CSE2221");
        res.put("CSE1223", new Course("CSE1223", pre, sub));

        arrayClear(pre, sub);
        pre.add("CSE1223");
        sub.add("CSE2231");
        sub.add("CSE2321");
        res.put("CSE2221", new Course("CSE2221", pre, sub));

        arrayClear(pre, sub);
        pre.add("CSE2221");
        sub.add("CSE2331");
        sub.add("CSE2421");
        sub.add("CSE2501");
        res.put("CSE2231", new Course("CSE2231", pre, sub));

        arrayClear(pre, sub);
        pre.add("CSE2221");
        sub.add("CSE2331");
        sub.add("CSE2421");
        sub.add("CSE2501");
        res.put("CSE2321", new Course("CSE2321", pre, sub));

        arrayClear(pre, sub);
        pre.add("CSE2231");
        pre.add("CSE2321");
        res.put("CSE3241", new Course("CSE3241", pre, sub));

        arrayClear(pre, sub);
        sub.add("MATH1152");
        res.put("MATH1151", new Course("MATH1151", pre, sub));

        arrayClear(pre, sub);
        pre.add("MATH1151");
        sub.add("STAT3470");
        sub.add("MATH2153");
        res.put("MATH1152", new Course("MATH1152", pre, sub));

        arrayClear(pre, sub);
        pre.add("MATH1152");
        sub.add("MATH3345");
        res.put("MATH2153", new Course("MATH2153", pre, sub));

        arrayClear(pre, sub);
        pre.add("MATH2153");
        res.put("MATH3345", new Course("MATH3345", pre, sub));

        arrayClear(pre, sub);
        pre.add("MATH1152");
        res.put("STAT3470", new Course("STAT3470", pre, sub));

        arrayClear(pre, sub);
        res.put("SUV1110", new Course("SUV1110", pre, sub));

        return res;
    }

    private void markAllPre(String botCourse) {
        if (this.flagTree.get(botCourse) == 0) {
            // This course should be marked now;
            this.flagTree.put(botCourse, 1);
            // Get the child course list for this specific course node
            ArrayList<String> pre = this.courses.get(botCourse).getPre();
            // make all its prerequisite courses marked
            for (String preName : pre) {
                this.markAllPre(preName);
                // this.courses.get(preName).deleteAllPre();
            }
        }
    }

    public void firstMarkAndAddAll(HashSet<String> set) {
        // Keep the records of the finished courses of the students
        this.finishedCourses.addAll(set);
        for (String name : set) {
            this.markAllPre(name);
            // Clear all the prerequisite courses of this finished course
            // this.courses.get(name).deleteAllPre();
        }
        for (String current : set) {
            ArrayList<String> curretnSub = this.courses.get(current).getSub();
            for (int i = 0; i < curretnSub.size(); i++) {
                String perSub = curretnSub.get(i);
                // Determine if this subCourse has been marked
                if (this.flagTree.get(perSub) == 0) {
                    ArrayList<String> subPreList = this.courses.get(perSub)
                            .getPre();
                    ArrayList<String> tempMeetList = new ArrayList<String>();
                    // Find all the meet prerequisite courses
                    for (int j = 0; j < subPreList.size(); j++) {
                        if (this.flagTree.get(subPreList.get(j)) == 1) {
                            tempMeetList.add(subPreList.get(j));
                        }
                    }
                    // Delete them from this sub course
                    this.courses.get(perSub).getPre().removeAll(tempMeetList);
                    if (this.courses.get(perSub).getPreSize() == 0) {
                        this.availCourses.add(perSub);
                        this.flagTree.put(perSub, 2);
                    }
                }
            }
        }
        for (String basic : this.basicCourses) {
            if (this.flagTree.get(basic) == 0) {
                this.availCourses.add(basic);
                this.flagTree.put(basic, 2);
            }
        }
    }

    public void addCourse(String selectCourse) {
        // First remove the course from availCourses and add that to finishedCourses
        this.availCourses.remove(selectCourse);
        this.finishedCourses.add(selectCourse);
        this.flagTree.put(selectCourse, 1);
        // Now it is necessary to check all the sub course of the new updated course
        ArrayList<String> newcourSub = this.courses.get(selectCourse).getSub();
        System.out.println("All the subCourses of " + selectCourse + " are "
                + newcourSub.toString());
        for (int i = 0; i < newcourSub.size(); i++) {
            String perSub = newcourSub.get(i);
            // Determine if this subCourse has been marked
            if (this.flagTree.get(perSub) == 0) {
                ArrayList<String> subPreList = this.courses.get(perSub)
                        .getPre();
                ArrayList<String> tempMeetList = new ArrayList<String>();
                // Find all the meet prerequisite courses
                for (int j = 0; j < subPreList.size(); j++) {
                    if (this.flagTree.get(subPreList.get(j)) == 1) {
                        tempMeetList.add(subPreList.get(j));
                    }
                }
                // Delete them from this sub course
                this.courses.get(perSub).getPre().removeAll(tempMeetList);
                if (this.courses.get(perSub).getPreSize() == 0) {
                    this.availCourses.add(perSub);
                    this.flagTree.put(perSub, 2);
                }
            }
        }
        // Show the updated finished courses
        this.printAvail();
    }
}

//// Get the pre course list for this specific course node
//ArrayList<String> pre = this.courses.get(i).getPre();
//// Delete all the prerequisite of all the next courses
//for (String subName : pre) {
//  int j = 0;
//  // Get one course node from the next course lists
//  while (!this.courses.get(j).getName().equals(subName)) {
//      j++;
//  }
//  // Update nextNode.pre by deleting the course node set.name
//  this.courses.get(j).deleteSub(name);
//  if (this.courses.get(j).getSub().size() == 0) {
//      this.courses.remove(j);
//  }
//}
//this.courses.remove(i);
