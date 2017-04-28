package com.example.ahmed.student_manager.instructor;

import android.content.Context;
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


import com.example.ahmed.student_manager.R;

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

 /*   // View lookup cache
    private static class ViewHolder {



    }*/

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
        DataModelInstructortList dataModel = (DataModelInstructortList) object;

        switch (v.getId()) {
            case R.id.container_listView:


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
