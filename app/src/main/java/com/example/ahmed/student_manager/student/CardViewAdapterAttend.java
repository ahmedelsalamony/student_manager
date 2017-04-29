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

public class CardViewAdapterAttend extends BaseAdapter{
    private Context context;
    private String[] mDateArray;
    private String[] mAttendArray;



    private TextView mDateView ,mAttendView;

    public CardViewAdapterAttend(Context context, String[] dateArray , String[] attendArray ) {
        this.context=context;
        this.mDateArray=dateArray;
        this.mAttendArray=attendArray;


    }
    @Override
    public int getCount() {
        /*return number of elements inside this array*/
        return mDateArray.length;
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
        inflater.inflate(R.layout.attend_item,parent,false);
        View v;

        if(convertView == null) {
            v = new View(context);
            v = inflater.inflate(R.layout.attend_item, null);


            mDateView= (TextView) v.findViewById(R.id.textView_Date);
            mAttendView= (TextView) v.findViewById(R.id.textView_Attend);


            mDateView.setText(""+mDateArray[position]);
            mAttendView.setText(""+mAttendArray[position]);






        }else {
            v = (View) convertView;
        }


        return v;
    }
}
