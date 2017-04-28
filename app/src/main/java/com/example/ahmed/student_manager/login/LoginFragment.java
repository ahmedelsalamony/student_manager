package com.example.ahmed.student_manager.login;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmed.student_manager.R;
import com.example.ahmed.student_manager.web.WebServices;
import com.labo.kaji.fragmentanimations.CubeAnimation;

import static com.daimajia.androidanimations.library.BaseViewAnimator.DURATION;

/**
 * Created by ahmed on 3/21/2017.
 */

public class LoginFragment extends Fragment {


    private EditText mPasswordView;
    private AutoCompleteTextView mUserName;
    private Button signUp;

    private WebServices web;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.login_fragment_login, parent, false);

        mPasswordView = (EditText) v.findViewById(R.id.password);
        mUserName = (AutoCompleteTextView)v.findViewById(R.id.email);



        web= new WebServices();

        signUp = (Button) v.findViewById(R.id.signUp);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // replace
                FragmentManager fm=getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.activity_main, new RegisterFragment());
                ft.addToBackStack(null);
                ft.commit();

            }
        });



        // Set up the login form.


        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
//                if (id == R.id.login ) {
//                    attemptLogin();
//                    return true;
//                }
                return false;
            }
        });


        Button mEmailSignInButton = (Button) v.findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                attemptLogin();
            }
        });


        return v;
    }






    //TODO Login Action
    private void attemptLogin()
    {
    web.user_login(getActivity(),mUserName.getText().toString().trim(),mPasswordView.getText().toString().trim());
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return CubeAnimation.create(CubeAnimation.UP, enter, DURATION);
    }
}
