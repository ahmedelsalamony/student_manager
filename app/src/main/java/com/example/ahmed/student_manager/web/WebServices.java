package com.example.ahmed.student_manager.web;

/**
 * Created by ahmed on 4/23/2017.
 */

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ahmed.student_manager.admin.adm_Home;
import com.example.ahmed.student_manager.instructor.inst_Home_Instructor_activity;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class WebServices {

    AlertDialog.Builder b;
    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;

    // TODO     "  users  "
    public static String ID = "id";
    public static String USERNAME = "user_name";
    public static String PASSWORD = "password";
    public static String EMAIL = "email";
    public static String PHONE = "phone";
    public static String USERTYPE = "user_type";
    public static String NAME = "name";
    public static String UNIQUENUM = "unique_num";
    public static String GRADE = "grade";
    public static String DEPARTMENT = "department";
    public static String CURRENTDEGREE = "current_degree";



    //  TODO    "  courses "
    public static String ID_COURSES = "id";
    public static String COURSENAME = "course_name";
    public static String COURSECODE = "course_code";
    public static String MAXSTUDENTNUM = "max_student_num";
    public static String COURSEHOURS = "course_hours";
    public static String COURSEINSTRUCTORUID = "course_instructor_uid";
    public static String COURSEAPPOINTENT = "course_appointment";
    public static String COURSESTATUS = "course_status";
    public static String COURSESTUDENTUID = "course_student_uid";
    public static String STUDENTASK = "student_ask";
    public static String STUDENTREPLY = "student_reply";



    // TODO    "  courses_registered "
    public static String ID_ReGISTERED = "id";
    public static String STUDENTUID = "student_uid";
    public static String COURSEUID = "course_uid";



    // TODO Tag that use it to know type of WebService   >> you can here put the TAG  of your method Name ------//
    public static String TAG = "tag";

    public static String ADD_USER_TAG = "add_user";
    public static String USER_LOGIN = "login_user";
    public static String ADD_COURSE_BY_ADMIN = "add_course_by_admin";
    public static String UPDATE_COURSE_BY_INTSRUCTOR = "update_course_by_instructor";
    public static String GET_OFF_COURSES_TO_INSTRUCTOR="get_off_courses_to_instructor";
    public static String GET_COURSES_TO_STUDENT="get_courses_to_student";
    public static String ADD_COURSE_BY_STUDENT="add_course_by_student";
    public static String UPDATE_COURSE_BY_STUDENT="update_course_by_student";
    public static String UPDATE_COURSE_REPLY="update_course_reply";





    private RequestQueue queue;
    private String url = "http://register-student.000webhostapp.com/uploads/re_tags.php";

    // TODO Login Method ----------------------------------//
    public void user_login(final Activity activity, final String user_name, final String password)
    {
        sharedPreferences=activity.getSharedPreferences("manager",0);
        editor = sharedPreferences.edit();
        queue = Volley.newRequestQueue(activity);
        final StringRequest request = new StringRequest(com.android.volley.Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    String login_response = jsonObject.getString("login_response");

                    if (login_response.equals("done")) {

                        int id = jsonObject.getInt("id");
                        String user_name = jsonObject.getString("user_name");
                        String password = jsonObject.getString("password");
                        String email = jsonObject.getString("email");
                        String phone = jsonObject.getString("phone");
                        String user_type = jsonObject.getString("user_type");
                        String name = jsonObject.getString("name");
                        String unique_num = jsonObject.getString("unique_num");
                        String department = jsonObject.getString("department");
                        String  grade = jsonObject.getString("grade");
                        String  current_degree  = jsonObject.getString("current_degree");

                        editor.putInt("id",  id);
                        editor.putString("user_name",user_name);
                        editor.putString("password",password);
                        editor.putString("email",email);
                        editor.putString("phone",phone);
                        editor.putString("user_type",user_type);
                        editor.putString("name",name);
                        editor.putString("unique_num",unique_num);
                        editor.putString("grade", grade);
                        editor.putString("current_degree",current_degree);
                        editor.putString("department", department);


                        editor.commit();
                        if (user_type.equals("admin")) {
                            Intent i=new Intent(activity,adm_Home.class);
                            activity.startActivity(i);
                        }
                        else if (user_type.equals("student")) {



                        } else if (user_type.equals("instructor")) {
                           Intent i=new Intent(activity,inst_Home_Instructor_activity.class);
                           activity.startActivity(i);

                        }

                    } else if (login_response.equals("incorrect password")) {
                        Toast.makeText(activity, "من فضلك اعد ادخال كلمة المرور الصحيحة", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(activity, " هذا المستخدم غير موجود بالنظام", Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {

                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            protected java.util.Map<String, String> getParams() throws AuthFailureError {
                java.util.Map<String, String> params = new HashMap<String, String>();
                params.put(USERNAME, user_name);
                params.put(PASSWORD, password);
                params.put(TAG, USER_LOGIN);
                return params;
            }

        };
        queue.add(request);

    }




    // TODO Register Method --------------------//

    public void addUser(final Activity activity, final String name, final String unique_num, final String department,
                        final double grade ,  final String current_degree,
                        final String phone,  final String email, final String user_name,
                        final String password,  final String user_type) {





        queue = Volley.newRequestQueue(activity);
        StringRequest request = new StringRequest(com.android.volley.Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                b.setCancelable(true);
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        })

        {
            @Override
            protected java.util.Map<String, String> getParams() throws AuthFailureError {
                java.util.Map<String, String> params = new HashMap<String, String>();


                params.put(NAME, name);
                params.put(UNIQUENUM, unique_num);
                params.put(DEPARTMENT, department);
                params.put(GRADE, ""+grade);
                params.put(CURRENTDEGREE, current_degree);
                params.put(PHONE, phone);
                params.put(EMAIL, email);
                params.put(USERNAME, user_name);
                params.put(PASSWORD, password);
                params.put(USERTYPE, user_type);

                params.put(TAG, ADD_USER_TAG);
                return params;
            }
        };
        queue.add(request);
    }



    //-------------------------------------------------------------------------------------------------//
    public void addCourseByAdmin(final Activity activity, final String course_name, final String course_code, final int max_student_num,
                        final String course_hours , final int  course_instructor_uid, final String course_appointment
                                , final String course_status, final String course_student_uid, final String student_ask,final String student_reply ) {

        queue = Volley.newRequestQueue(activity);
        StringRequest request = new StringRequest(com.android.volley.Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(activity, "added successfully.", Toast.LENGTH_SHORT).show();
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        })

        {
            @Override
            protected java.util.Map<String, String> getParams() throws AuthFailureError {
                java.util.Map<String, String> params = new HashMap<String, String>();


                params.put(COURSENAME, course_name);
                params.put(COURSECODE, course_code);
                params.put(MAXSTUDENTNUM, ""+max_student_num);
                params.put(COURSEHOURS, ""+course_hours);
                params.put(COURSEINSTRUCTORUID,""+ course_instructor_uid);
                params.put(COURSEAPPOINTENT, course_appointment);
                params.put(COURSESTATUS, course_status);
                params.put(COURSESTUDENTUID, course_student_uid);
                params.put(STUDENTASK, student_ask);
                params.put(STUDENTREPLY, student_reply);

                params.put(TAG, ADD_COURSE_BY_ADMIN);
                return params;
            }
        };
        queue.add(request);
    }


//----------------------------------------------------------------------------------------------------------------------//

    //-------------------------------------------------------------------------------------------------//
    public void getOffCoursesToInstructor(final Activity activity,final request_interface request_interface ) {

        queue = Volley.newRequestQueue(activity);
        StringRequest request = new StringRequest(com.android.volley.Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                request_interface.onResponse(response);
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                request_interface.onError();
            }
        })

        {
            @Override
            protected java.util.Map<String, String> getParams() throws AuthFailureError {
                java.util.Map<String, String> params = new HashMap<String, String>();

                params.put(TAG, GET_OFF_COURSES_TO_INSTRUCTOR);
                return params;
            }
        };
        queue.add(request);
    }






}