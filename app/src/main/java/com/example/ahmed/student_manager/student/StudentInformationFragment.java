package com.example.ahmed.student_manager.student;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ahmed.student_manager.R;
import com.example.ahmed.student_manager.web.WebServices;


public class StudentInformationFragment extends Fragment {

      private TextView mUserName , mPhone ,mEmail ,mDepartment ,mGrade , mCurrentGrade ,mUnique , mName;
      private  View mView;
      private WebServices mWebServices;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView= inflater.inflate(R.layout.student_fragment_information, container, false);

        mWebServices=new WebServices();
        mWebServices.sharedPreferences=getActivity().getSharedPreferences("manager" , 0);

        mUserName= (TextView) mView.findViewById(R.id.textView_StudentName_Info);
        mEmail= (TextView) mView.findViewById(R.id.textView_StudentEmail_Info);
        mPhone= (TextView) mView.findViewById(R.id.textView_StudentPhone_Info);
        mGrade= (TextView) mView.findViewById(R.id.textView_StudentGrades_Info);
        mDepartment= (TextView) mView.findViewById(R.id.textView_StudentDept_Info);
        mUnique= (TextView) mView.findViewById(R.id.textView_Unique_Info);
        mCurrentGrade= (TextView) mView.findViewById(R.id.textView_StudentCurrent_Info);
        mName= (TextView) mView.findViewById(R.id.textView_StudentFName_Info);


        mUserName.setText(mWebServices.sharedPreferences.getString("user_name",""));
        mPhone.setText(mWebServices.sharedPreferences.getString("phone",""));
        mEmail.setText(mWebServices.sharedPreferences.getString("email",""));
        mUnique.setText(mWebServices.sharedPreferences.getString("unique_num",""));
        mCurrentGrade.setText(mWebServices.sharedPreferences.getString("current_degree",""));
        mGrade.setText(""+mWebServices.sharedPreferences.getFloat("grade",0));
        mDepartment.setText(mWebServices.sharedPreferences.getString("department",""));
        mName.setText(mWebServices.sharedPreferences.getString("name",""));







        return  mView;

    }

}
