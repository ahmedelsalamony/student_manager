package com.example.ahmed.student_manager.instructor;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.ahmed.student_manager.R;
import com.example.ahmed.student_manager.web.WebServices;
import com.example.ahmed.student_manager.web.request_interface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ahmed on 4/25/2017.
 */

public class CustomAdapterInstructor extends ArrayAdapter<DataModelInstructortList> implements View.OnClickListener {


    private ArrayList<DataModelInstructortList> dataSet;
    Context mContext;
    AppCompatActivity appActivity;
    TextView txtCoursName, txtCourseCode, txtCourseHours, txtMaxStudentNum;
    LinearLayout linearContainer;
    WebServices mWebServices;
    StringBuffer mIdsBuffer;
    String mIdsArray[];
    static  String  sCourse_Code;



    public CustomAdapterInstructor(ArrayList<DataModelInstructortList> data, Context context, AppCompatActivity appActivity) {
        super(context, R.layout.inst_single_row, data);
        this.dataSet = data;
        this.mContext = context;
        this.appActivity = appActivity;
    }

    @Override
    public void onClick(View v) {

        int position = (Integer) v.getTag();
        Object object = getItem(position);
        final DataModelInstructortList dataModel = (DataModelInstructortList) object;

        switch (v.getId()) {
            case R.id.container_listView:
                  mWebServices =new WebServices();
                  mWebServices.sharedPreferences=mContext.getSharedPreferences("manager",0);

                AlertDialog.Builder builder=new AlertDialog.Builder(mContext);
                builder.setIcon(R.drawable.logo);
                builder.setMessage("register to this Course ");
                builder.setTitle(""+dataModel.getCourse_code());
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mIdsBuffer=new StringBuffer();

                        mWebServices.getInstUid( appActivity,mWebServices.sharedPreferences.getInt("id",2017) ,new request_interface() {
                            @Override
                            public void onResponse(String response) {

                                try {

                                    JSONObject jsonResponse = new JSONObject(response);
                                    JSONArray jsonArray = jsonResponse.getJSONArray("instructor_uid");


                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject search_object = jsonArray.getJSONObject(i);
                                        mIdsBuffer.append(search_object.getInt("id") + ":");

                                    }

                                    mIdsArray=mIdsBuffer.toString().split(":");

                                    if(mIdsArray.length >3){

                                        Toast.makeText(mContext, "u can`t register \nlimit 3 courses", Toast.LENGTH_SHORT).show();

                                    }else {
                                        // TODO    add  +  updateAppointment

                 mWebServices.updateCoursesByInstuctor(appActivity, dataModel.getCourse_code(), mWebServices.sharedPreferences.getInt("id", 2017), "on", new request_interface() {
                     @Override
                     public void onResponse(String response) {
                         Toast.makeText(appActivity, "Registered.", Toast.LENGTH_SHORT).show();

                     }

                     @Override
                     public void onError() {

                     }
                 });

                                        mWebServices.updateInstructorUid(appActivity, dataModel.getId(), mWebServices.sharedPreferences.getInt("id", 2017), new request_interface() {
                                            @Override
                                            public void onResponse(String response) {
                                                Toast.makeText(appActivity, "please  Determine appointments. ", Toast.LENGTH_SHORT).show();


                                                sCourse_Code=dataModel.getCourse_code();

                                                inst_Assign_Course_Appointment inst_assign_course_appointment = new inst_Assign_Course_Appointment();
                                                FragmentManager fm=appActivity.getSupportFragmentManager();
                                                FragmentTransaction ft= fm.beginTransaction();
                                                ft.replace(R.id.content_inst__home__instructor_activity,inst_assign_course_appointment);
                                                ft.commit();

                                            }

                                            @Override
                                            public void onError() {

                                            }
                                        });




                                    }

                                } catch (JSONException e) {
                                    Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onError() {

                                Toast.makeText(mContext, "error server.!", Toast.LENGTH_SHORT).show();
                            }

                        });
                    }
                });

                builder.show();


                break;
        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        DataModelInstructortList dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
       // ViewHolder viewHolder = null; // view lookup cache stored in tag

        final View result;

        //viewHolder = new ViewHolder();
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.inst_single_row, parent, false);
        txtCoursName = (TextView) convertView.findViewById(R.id.inst_single_row_courseName);
        txtCourseHours = (TextView) convertView.findViewById(R.id.inst_single_row_courseHours);
        txtCourseCode = (TextView) convertView.findViewById(R.id.inst_single_row_courseCode);
        txtMaxStudentNum = (TextView) convertView.findViewById(R.id.inst_single_row_maxStudentNum);
        linearContainer=(LinearLayout)convertView.findViewById(R.id.container_listView);
        result = convertView;


        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

       /* viewHolder.txtName.setText(dataModel.getUser_name());
        viewHolder.txtPhone.setText(dataModel.getPhone());*/
        txtCoursName.setText(dataModel.getCourse_name());
        txtCourseHours.setText(""+dataModel.getCourse_hours());
        txtCourseCode.setText(dataModel.getCourse_code());
        txtMaxStudentNum.setText(""+dataModel.getMax_student_num());
        linearContainer.setOnClickListener(this);
        linearContainer.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}
