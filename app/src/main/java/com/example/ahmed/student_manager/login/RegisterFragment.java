package com.example.ahmed.student_manager.login;

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

    TextView txtTitle;
    EditText edtUserName,edtPassword,edtConfirmPassword,edtEmail,edtPhone,edtAddress,edtCharity;
    RadioButton rdCharity,rdRestaurant;
    String strUserName, strPassword, strConfirm, strEmail, strPhone, strAddress, strCharity;

    boolean flag = false;
    Intent intent;




    private WebServices web;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.login_register_fragmant, parent, false);


        web=new WebServices();

        edtUserName=(EditText)v.findViewById(R.id.xUserName);
        edtPassword=(EditText)v.findViewById(R.id.xPassword);
        edtConfirmPassword=(EditText)v.findViewById(R.id.xConfirmPassword);
        edtEmail=(EditText)v.findViewById(R.id.xEmail);
        edtPhone=(EditText)v.findViewById(R.id.xPhone);
        edtAddress=(EditText)v.findViewById(R.id.xAddress);
        edtCharity=(EditText)v.findViewById(R.id.xCharityName);
        rdCharity=(RadioButton)v.findViewById(R.id.xrdCharity);
        rdRestaurant=(RadioButton)v.findViewById(R.id.xrdRestaurant);

        strUserName = edtUserName.getText().toString();
        strPassword = edtPassword.getText().toString();
        strConfirm = edtConfirmPassword.getText().toString();
        strEmail = edtEmail.getText().toString();
        strPhone = edtPhone.getText().toString();
        strAddress = edtAddress.getText().toString();
        strCharity = edtCharity.getText().toString();

        Button btn=(Button)v.findViewById(R.id.xbtnsave);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userName = edtUserName.getText().toString();
                String password = edtPassword.getText().toString();
                String email = edtEmail.getText().toString();
                String phone = edtPhone.getText().toString();
                String address = edtAddress.getText().toString();
                String type = null;

                if (rdRestaurant.isChecked()) {
                    type = rdRestaurant.getText().toString();
                } else {
                    type = rdCharity.getText().toString();
                }

                String typeName = edtCharity.getText().toString();

                if (edtUserName.getText().toString().trim().equals("")){
                    edtUserName.setError("enter valid username");
                }else if (edtPassword.getText().toString().trim().equals("")){
                    edtPassword.setError("enter valid password");
                }else if (!edtConfirmPassword.getText().toString().trim().equals(password)) {
                    edtConfirmPassword.setError("password not match");
                }else if (edtConfirmPassword.getText().toString().trim().equals("")){
                    edtConfirmPassword.setError("error ");
                }else if (edtPhone.getText().toString().trim().toString().equals("")){
                    edtPhone.setError("enter valid phone");
                }else if (edtAddress.getText().toString().trim().equals("")){
                    edtAddress.setError("enter valid address");
                }else if(edtCharity.getText().toString().trim().equals("")){
                    edtCharity.setError("enter valid charity name");
                }else if (!isValidEmailAddress(edtEmail.getText().toString().trim())){
                    edtEmail.setError("enter valid email");
                }else{

                    // TODO add_user

                }

            }
        });

        rdCharity.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {


                edtCharity.setVisibility(View.VISIBLE);
                edtCharity.setHint("Charity name");

                return false;
            }
        });

        rdRestaurant.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {


                edtCharity.setVisibility(View.VISIBLE);
                edtCharity.setHint("Restaurant name");
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

    public Boolean validate() {
        Boolean out = true;
        if (TextUtils.isEmpty(strUserName)) {
            edtUserName.setError("Enter user name");
            out = false;
        } else if (TextUtils.isEmpty(strPassword)) {
            edtPassword.setError("Enter password");
            out = false;
        } else if (TextUtils.isEmpty(strConfirm)) {
            edtConfirmPassword.setError("Password miss match ");
            out = false;
        } else if (TextUtils.isEmpty(strPhone)) {
            edtPhone.setError("enter your phone");
            out = false;
        } else if (TextUtils.isEmpty(strAddress)) {
            edtAddress.setError("enter your ");
            out = false;
        } else if (TextUtils.isEmpty(strCharity)) {
            edtCharity.setError("enter charity name");
            out = false;
        }


        return out;
    }




    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return CubeAnimation.create(CubeAnimation.UP, enter, DURATION);
    }



}
