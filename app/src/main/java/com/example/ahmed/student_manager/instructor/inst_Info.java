package com.example.ahmed.student_manager.instructor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.EditText;

import com.example.ahmed.student_manager.R;
import com.example.ahmed.student_manager.web.WebServices;
import com.labo.kaji.fragmentanimations.CubeAnimation;

import static com.daimajia.androidanimations.library.BaseViewAnimator.DURATION;

/**
 * Created by ahmed on 4/27/2017.
 */

public class inst_Info extends Fragment {

    View mView;
    WebServices webServices;
    EditText edtUserName,edtPassword,edtEmail,edtPhoneNumber,edtDepartment,edtEmployeeNum;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.inst_info, container, false);

        inst_Home_Instructor_activity.sInstHomeInstructorActivity =1;

        edtUserName=(EditText)mView.findViewById(R.id.inst_info_userName);
        edtPassword=(EditText)mView.findViewById(R.id.inst_info_password);
        edtEmail=(EditText)mView.findViewById(R.id.inst_info_email);
        edtPhoneNumber=(EditText)mView.findViewById(R.id.inst_info_phone);
        edtDepartment=(EditText)mView.findViewById(R.id.inst_info_department);
        edtEmployeeNum=(EditText)mView.findViewById(R.id.inst_info_employeeNum);


        webServices=new WebServices();
        webServices.sharedPreferences=getActivity().getSharedPreferences("manager",0);

        edtUserName.setText(webServices.sharedPreferences.getString("user_name",""));
        edtPassword.setText(webServices.sharedPreferences.getString("password",""));
        edtEmail.setText(webServices.sharedPreferences.getString("email",""));
        edtPhoneNumber.setText(webServices.sharedPreferences.getString("phone",""));
        edtDepartment.setText(webServices.sharedPreferences.getString("department",""));
        edtEmployeeNum.setText(webServices.sharedPreferences.getString("unique_num",""));

       return mView;
    }
    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return CubeAnimation.create(CubeAnimation.UP, enter, 500);
    }

    }
