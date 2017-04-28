package com.example.ahmed.student_manager.instructor;

/**
 * Created by ahmed on 4/25/2017.
 */

public class DataModelInstructortList {

    private int id;
    private String course_name;
    private String course_code;
    private int course_hours;
    private int max_student_num;



    public DataModelInstructortList(String course_name, int course_hours,String course_code,int max_student_num ){
        this.course_name=course_name;
        this.course_code=course_code;
        this.course_hours=course_hours;
        this.max_student_num=max_student_num;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public int getCourse_hours() {
        return course_hours;
    }

    public void setCourse_hours(int course_hours) {
        this.course_hours = course_hours;
    }

    public int getMax_student_num() {
        return max_student_num;
    }

    public void setMax_student_num(int max_student_num) {
        this.max_student_num = max_student_num;
    }
}
