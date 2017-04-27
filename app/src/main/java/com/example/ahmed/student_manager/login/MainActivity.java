package com.example.ahmed.student_manager.login;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.ahmed.student_manager.R;


public class MainActivity extends AppCompatActivity {

    private FragmentManager fm;
    FragmentTransaction ft;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.add(R.id.activity_main, new LoginFragment());
        ft.addToBackStack(null);
        ft.commit();


    }


    public void toSignUp(View v){

        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.activity_main, new RegisterFragment());
        ft.addToBackStack(null);
        ft.commit();
    }


    public void toForgetPassword(View v){

        ForgetPasswordFragment forgetPass=new ForgetPasswordFragment();
        FragmentManager fm =getSupportFragmentManager();
        FragmentTransaction  ft=fm.beginTransaction();
        ft.replace(R.id.activity_main,forgetPass);
        //
        ft.commit();

    }


}
