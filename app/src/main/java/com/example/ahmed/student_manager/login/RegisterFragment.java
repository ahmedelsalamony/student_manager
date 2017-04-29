package com.example.ahmed.student_manager.login;

import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmed.student_manager.R;
import com.example.ahmed.student_manager.web.WebServices;
import com.labo.kaji.fragmentanimations.CubeAnimation;


import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static com.daimajia.androidanimations.library.BaseViewAnimator.DURATION;

/**
 *
 */

public class RegisterFragment extends Fragment {

    EditText edtUserName,edtPassword,edtConfirmPassword,edtEmail,edtPhone,edGrade,edName,edCurrentDegree , edUniqueNum,edDepartment;
    RadioButton rdStudent,rdInstructor;
    String strUserName, strPassword, strConfirm, strEmail, strDepartmet,strPhone, strGrade, strName,strCurrentDegree,strUniqueNum ,strType;


    private WebServices web;

    TextInputLayout  Grade, UniqueNumber ,CurrentDegree;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.login_register_fragmant, parent, false);
        MainActivity.sFlag=2;


        web=new WebServices();

        edtUserName=(EditText)v.findViewById(R.id.xUserName);
        edtPassword=(EditText)v.findViewById(R.id.xPassword);
        edtConfirmPassword=(EditText)v.findViewById(R.id.xConfirmPassword);
        edtEmail=(EditText)v.findViewById(R.id.xEmail);
        edtPhone=(EditText)v.findViewById(R.id.xPhone);
        edDepartment=(EditText)v.findViewById(R.id.xDepartment);
        edUniqueNum=(EditText)v.findViewById(R.id.xNum);
        rdStudent=(RadioButton)v.findViewById(R.id.xStudent);
        rdInstructor=(RadioButton)v.findViewById(R.id.xInstructor);

        edName=(EditText)v.findViewById(R.id.xName);
        edCurrentDegree=(EditText)v.findViewById(R.id.xCurrentDegree);
        edGrade=(EditText)v.findViewById(R.id.xGrade);

        Grade= (TextInputLayout) v.findViewById(R.id.input_layout_Grade);
        CurrentDegree= (TextInputLayout) v.findViewById(R.id.input_layout_CurrentDegree);
        UniqueNumber= (TextInputLayout) v.findViewById(R.id.input_layout_Num);

        Grade.setVisibility(View.INVISIBLE);
        CurrentDegree.setVisibility(View.INVISIBLE);
        UniqueNumber.setVisibility(View.INVISIBLE);








        Button btn=(Button)v.findViewById(R.id.xbtnsave);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                strUserName = edtUserName.getText().toString();
                strPassword = edtPassword.getText().toString();
                strConfirm = edtConfirmPassword.getText().toString();
                strEmail = edtEmail.getText().toString();
                strPhone = edtPhone.getText().toString();
                strCurrentDegree = edCurrentDegree.getText().toString();
                strGrade = edGrade.getText().toString();
                strDepartmet = edDepartment.getText().toString();
                strUniqueNum = edUniqueNum.getText().toString();
                strName = edName.getText().toString();

                if (rdStudent.isChecked()) {
                    strType = rdStudent.getText().toString();


                } else {
                    strType = rdInstructor.getText().toString();
                }

                String typeName = edName.getText().toString();


                if (edtUserName.getText().toString().trim().equals("")){
                    edtUserName.setError("enter valid username");
                }else if (edtPassword.getText().toString().trim().equals("")){
                    edtPassword.setError("enter valid password");
                }else if (!edtConfirmPassword.getText().toString().trim().equals(edtPassword.getText().toString().trim())) {
                    edtConfirmPassword.setError("password not match");
                }else if (edtConfirmPassword.getText().toString().trim().equals("")){
                    edtConfirmPassword.setError("error ");
                }else if (edtPhone.getText().toString().trim().toString().equals("")){
                    edtPhone.setError("enter valid phone");
                }else if (edUniqueNum.getText().toString().trim().equals("")){
                    edUniqueNum.setError("enter valid address");
                }else if(edName.getText().toString().trim().equals("")){
                    edName.setError("enter valid charity name");
                }else if (!isValidEmailAddress(edtEmail.getText().toString().trim())){
                    edtEmail.setError("enter valid email");
                }else{

                    // TODO add_user

                    web.addUser(getActivity(),typeName,strUniqueNum,strDepartmet, strGrade,strCurrentDegree,strPhone,strEmail,strUserName,strPassword,strType);
                }

            }
        });

        rdStudent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                CurrentDegree.setVisibility(View.VISIBLE);
                Grade.setVisibility(View.VISIBLE);
                UniqueNumber.setVisibility(View.VISIBLE);
                edUniqueNum.setHint("enter college Number");


                return false;
            }
        });

        rdInstructor.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                UniqueNumber.setVisibility(View.VISIBLE);
                CurrentDegree.setVisibility(View.INVISIBLE);
                Grade.setVisibility(View.INVISIBLE);
                edUniqueNum.setHint("enter Job Number");

                return false;
            }
        });



        return v;
    }
    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }






    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return CubeAnimation.create(CubeAnimation.UP, enter, 400);
    }



}
