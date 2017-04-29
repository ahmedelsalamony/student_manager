package com.example.ahmed.student_manager.instructor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.ahmed.student_manager.R;

/**
 * Created by Mahmoud on 24/04/2017.
 */

public class CardViewAdapterAttendance extends BaseAdapter{
    private Context context;
    private String[] mDateAttendArray;
    private String[] mStudentsArray;

    private TextView mDateView ,mStudentView;
   private CheckBox mAttendCheck , mNotAttendCheck;
    public CardViewAdapterAttendance(Context context, String[] DateAttend , String[] NameAttend ) {
        this.context=context;
        this.mDateAttendArray=DateAttend;
        this.mStudentsArray=NameAttend;
    }
    @Override
    public int getCount() {
        /*return number of elements inside this array*/
        return mDateAttendArray.length;
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
        inflater.inflate(R.layout.attends_item_from_inst,parent,false);
        View v;

        if(convertView == null) {
            v = new View(context);
            v = inflater.inflate(R.layout.attends_item_from_inst, null);


            mDateView= (TextView) v.findViewById(R.id.textView_DateAttend);
            mStudentView= (TextView) v.findViewById(R.id.textView_NameStudentAttend);
            mDateView.setText(""+mDateAttendArray[position]);
            mStudentView.setText(""+mStudentsArray[position]);


        }else {
            v = (View) convertView;
        }


        return v;
    }
}
