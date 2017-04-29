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

public class CardViewAdapterAskReply extends BaseAdapter{
    private Context context;
    private String[] mAskArray;
    private String[] mreplyArray;



    private TextView mAskView ,mReplyView;

    public CardViewAdapterAskReply(Context context, String[] askArray , String[] replyArray ) {
        this.context=context;
        this.mAskArray=askArray;
        this.mreplyArray=replyArray;


    }
    @Override
    public int getCount() {
        /*return number of elements inside this array*/
        return mAskArray.length;
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
        inflater.inflate(R.layout.ask_item,parent,false);
        View v;

        if(convertView == null) {
            v = new View(context);
            v = inflater.inflate(R.layout.ask_item, null);


            mAskView= (TextView) v.findViewById(R.id.textView_Ask);
            mReplyView= (TextView) v.findViewById(R.id.textView_Reply);


            mAskView.setText(""+mAskArray[position]);
            mReplyView.setText(""+mreplyArray[position]);






        }else {
            v = (View) convertView;
        }


        return v;
    }
}
