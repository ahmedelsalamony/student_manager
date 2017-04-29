package com.example.ahmed.student_manager.student;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ahmed.student_manager.R;

/**
 * Created by Mahmoud on 24/04/2017.
 */

public class CardViewAdapter extends BaseAdapter{
    private Context context;
    private String[] mCourseNameArray;
    private String[] mCourseCodeArray;
    private String[] mCourseHourArray;
    private String[] mCourseMaxArray;

    private TextView mCourseName ,mCourseCode,mCourseHour,mCourseMaxNumber;

    public CardViewAdapter(Context context, String[] courseName , String[] courseCode, String[] courseHour, String[] courseMax ) {
        this.context=context;
        this.mCourseNameArray=courseName;
        this.mCourseCodeArray=courseCode;
        this.mCourseHourArray=courseHour;
        this.mCourseMaxArray=courseMax;
    }
    @Override
    public int getCount() {
        /*return number of elements inside this array*/
        return mCourseNameArray.length;
    }
    @Override
    public Object getItem(int position) {
        /*return the item at posion -position-*/
        return null;
    }

    @Override
    public long getItemId(int position) {
        /*return the id of the row which in this case the index of the array*/
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.course_item,parent,false);
        View v;

        if(convertView == null) {
            v = new View(context);
            v = inflater.inflate(R.layout.course_item, null);


            mCourseName= (TextView) v.findViewById(R.id.textView_CourseName);
            mCourseHour= (TextView) v.findViewById(R.id.textView_CourseHours);
            mCourseCode= (TextView) v.findViewById(R.id.textView_CourseCode);
            mCourseMaxNumber= (TextView) v.findViewById(R.id.textView_CourseNumber);

            mCourseName.setText("course name           "+mCourseNameArray[position]);
            mCourseCode.setText("course code           "+mCourseCodeArray[position]);
            mCourseHour.setText("course hours          "+mCourseHourArray[position]);
            mCourseMaxNumber.setText("available number student      "+mCourseMaxArray[position]);




        }else {
            v = (View) convertView;
        }


        return v;
    }
}
