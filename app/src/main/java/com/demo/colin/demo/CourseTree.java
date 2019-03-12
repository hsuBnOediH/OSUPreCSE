package com.demo.colin.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Enum to represent the state of course
 */
enum State {
    /**
     * Course in state finished
     */
    FINISHED,
    /**
     * Course in state available
     */
    AVAILABLE,
    /**
     * Course in state never reach
     */
    NONE
}

/**
 * Forest Courses List
 */
final public class CourseTree {
    /*Map contains all the Courses name the course objects*/
    private HashMap<String, Course> courses;
    /*Map contains all the Courses and State*/
    private HashMap<String, State> flagTree;
    /*Finished Course in adding order*/
    private Stack<String> finishedCourses;
    /*Set contains all the courses available for adding next step*/
    private HashSet<String> availCourses;
    /*jackass*/
    private HashSet<String> basicCourses;


    public CourseTree() {
        this.courses = preTreeBuild();
        this.flagTree = flagTreeBuild();
        this.basicCourses = basicBuild();
        this.finishedCourses = new Stack<>();
        this.availCourses = new HashSet<>();
    }

    // Getter: return the this.available course
    public HashSet<String> getAvailCourse() {
        return this.availCourses;
    }
    // Getter: return the this.finishedCourses course
    public Stack<String> getFinishedCourse() {
        return this.finishedCourses;
    }
    // Getter: return the this.courses course
    public HashMap<String, Course> getAllCourse() {
        return this.courses;
    }
    // Getter: return the this.flagTree course
    public  HashMap<String, State> getFlagCourse() {
        return this.flagTree;
    }

    public String printAvail() {
        return this.availCourses.toString();
    }

    private static void arrayClear(ArrayList<String> pre, ArrayList<String> sub) {
        pre.clear();
        sub.clear();
    }

    private static HashMap<String, State> flagTreeBuild() {
        HashMap<String, State> flagTree = new HashMap<>();
        Set<String> courseSet = new HashSet<>();
        courseSet.addAll(Arrays.asList(new String[]{"ENG1181", "ENG1182",
                "ENG1901", "ENG1902", "ENG1110", "ENG2367", "MATH1151",
                "MATH1152", "MATH2153", "PHY1250", "PHY1251", "MATH3345",
                "STAT3470", "SUV1110", "CSE1223", "CSE2221", "CSE2231",
                "CSE2321", "CSE3241"}));
        for (String course : courseSet) {
            flagTree.put(course, State.NONE);
        }
        return flagTree;
    }

    private static HashSet<String> basicBuild() {
        HashSet<String> courseSet = new HashSet<>();
        courseSet.addAll(Arrays.asList(new String[]{"ENG1181", "ENG1901",
                "MATH1151", "PHY1250", "SUV1110", "CSE1223"}));
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

    /*
     *  Mark one of the courses that the student has finished and also mark all the pre-course(s)
     *  for each finished course. This method using Recursion to finish the process.
     */
    private void markAllPre(String finished_course) {
        if (this.flagTree.get(finished_course) == State.NONE || this.flagTree.get(finished_course)==State.AVAILABLE) {
            // This course should be marked now;
            this.flagTree.put(finished_course, State.FINISHED);
            // Get the child course list for this specific course node
            ArrayList<String> pre_courses = this.courses.get(finished_course).getPre();
            // make all its prerequisite courses marked
            for (String pre_course : pre_courses) {
                this.markAllPre(pre_course);
            }
        }
    }

    /*
     *  Mark all the courses that the student has finished
     */
    public void firstMarkAndAddAll(HashSet<String> finished_courses) {
        // Keep the records of the finished courses of the students
        this.finishedCourses.addAll(finished_courses);
        // Mark all the prerequisite courses of the individual finished course and the individual
        // course itself to FINISHED STATE
        for (String course : finished_courses) {
            this.markAllPre(course);
        }
        // Mark all the sub courses of the individual finished course to AVAILABLE STATE
        for (String course : finished_courses) {
            ArrayList<String> sub_courses = this.courses.get(course).getSub();
            /*
             * Determine if all the sub_courses can be selected by the student one by one: if the
             * single subCourse has been marked before since it's possible that two finished course
             * both have the same sub_course(s) which may be marked in the one of the two courses
             * (may be in this course's sub_courses or the other finished courses' sub_courses).
             */
            for (int i = 0; i < sub_courses.size(); i++) {
                String sub_course = sub_courses.get(i);
                if (this.flagTree.get(sub_course) == State.NONE) {
                    ArrayList<String> subPreList = this.courses.get(sub_course).getPre();
                    // Find all the equivalent prerequisite courses like MATH 2153 has two kinds of
                    // prerequisite course-MATH 1152 OR MATH 1172 Remove the contemporary finished
                    // prerequisite courses from this individual sub_course
                    for (int j = 0; j < subPreList.size(); j++) {
                        if (this.flagTree.get(subPreList.get(j)) == State.FINISHED) {
                            this.courses.get(sub_course).finishOnePreCourse();
                        }
                    }
                    // Finally determine if this sub_course can be selected and
                    // then change its state to be AVAILABLE
                    if (this.courses.get(sub_course).getPreSize() == 0) {
                        this.availCourses.add(sub_course);
                        this.flagTree.put(sub_course, State.AVAILABLE);
                    }
                }
            }
        }
        // Finally,enable all the basic courses to be selected if the student didn't take them
        for (String basic : this.basicCourses) {
            if (this.flagTree.get(basic) == State.NONE) {
                this.availCourses.add(basic);
                this.flagTree.put(basic, State.AVAILABLE);
            }
        }
    }

    /*
     *  Mark the course that the student has finished and updated the available courses List
     */
    public HashSet<String> updateFinishedCourse (String finished_course) {
        HashSet<String> newAvailableCourse=new HashSet<>();
        // Keep the records of the finished course of the student Mark all the prerequisite
        // courses of the individual finished course and the individual course itself
        this.finishedCourses.add(finished_course);
        this.markAllPre(finished_course);
        // Mark all the sub courses of the individual finished course to AVAILABLE STATE
        ArrayList<String> sub_courses = this.courses.get(finished_course).getSub();
        /*
         * Determine if all the sub_courses can be selected by the student one by one: if the
         * single subCourse has been marked before since it's possible that two finished course
         * both have the same sub_course(s) which may be marked in the one of the two courses
         * (may be in this course's sub_courses or the other finished courses' sub_courses).
         */
        for (int i = 0; i < sub_courses.size(); i++) {
            String sub_course = sub_courses.get(i);
            if (this.flagTree.get(sub_course) == State.NONE) {
                ArrayList<String> subPreList = this.courses.get(sub_course).getPre();
                // Find all the equivalent prerequisite courses like MATH 2153 has two kinds of
                // prerequisite course-MATH 1152 OR MATH 1172 Remove the contemporary finished
                // prerequisite courses from this individual sub_course
                for (int j = 0; j < subPreList.size(); j++) {
                    if (this.flagTree.get(subPreList.get(j)) == State.FINISHED) {
                        this.courses.get(sub_course).finishOnePreCourse();
                    }
                }
                // Finally determine if this sub_course can be selected and
                // then change its state to be AVAILABLE
                if (this.courses.get(sub_course).getPreSize() == 0) {
                    this.availCourses.add(sub_course);
                    this.flagTree.put(sub_course, State.AVAILABLE);
                    newAvailableCourse.add(sub_course);
                }
            }
        }
        return newAvailableCourse;
    }

    // Getter for getting a single Course object
    public Course getSingleCourse(String course) {
        return this.courses.get(course);
    }

    public boolean undoable() {
        return !this.finishedCourses.isEmpty();
    }

    public void undo() {
        String undoCourse = this.finishedCourses.pop();
        this.flagTree.put(undoCourse, State.AVAILABLE);
        ArrayList<String> subList = this.courses.get(undoCourse).getSub();

        for (String sub : subList) {
            if (this.availCourses.contains(sub)) {
                this.availCourses.remove(sub);
                this.flagTree.put(sub, State.NONE);
            }
        }


    }

    public void addCourse(String selectCourse) {
        // First remove the course from availCourses and add that to finishedCourses
        this.availCourses.remove(selectCourse);
        this.finishedCourses.push(selectCourse);
        this.flagTree.put(selectCourse, State.FINISHED);
        // Now it is necessary to check all the sub course of the new updated course
        ArrayList<String> newCourseSub = this.courses.get(selectCourse).getSub();
        for (String subClass : newCourseSub) {
            // Determine if this subCourse has been marked
            ArrayList<String> subPreList = this.courses.get(subClass)
                    .getPre();
            if (couldBeAvailable(subPreList)) {
                this.flagTree.put(subClass, State.AVAILABLE);
            }
        }
    }

    private boolean couldBeAvailable(ArrayList<String> subPreList) {

        for (String course : subPreList) {
            if (this.flagTree.get(course) != State.FINISHED) {
                return false;
            }
        }
        return true;
    }

}