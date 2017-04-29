package com.example.ahmed.student_manager.login;

import android.app.AlertDialog;
import android.support.v4.app.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ahmed.student_manager.R;
import com.example.ahmed.student_manager.web.WebServices;
import com.example.ahmed.student_manager.web.request_interface;
import com.labo.kaji.fragmentanimations.CubeAnimation;

import org.json.JSONException;
import org.json.JSONObject;

import static com.daimajia.androidanimations.library.BaseViewAnimator.DURATION;


/**
 *
 */

public class ForgetPasswordFragment extends Fragment {

    private EditText edRestorePassword;
    private Button   buRestorePassword;
    private WebServices web;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.login_forgrt_password_fragment, parent, false);
        MainActivity.sFlag=2;

        web =new WebServices();
        edRestorePassword= (EditText) v.findViewById(R.id.xRestorePasswordEditText);
        buRestorePassword= (Button) v.findViewById(R.id.xRestorePasswordButton);

        buRestorePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(edRestorePassword.getText().toString().trim().equals(null))
                {
                    edRestorePassword.setError("enter username");
                }

                else
                {

                    String userName= edRestorePassword.getText().toString().trim();
                    // TODO  >> Call forget web Service

                    Toast.makeText(getActivity(), ""+userName, Toast.LENGTH_SHORT).show();
                    web.forgetPassword(getActivity(), userName, new request_interface() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject password_json=new JSONObject(response);
                                String password_response= password_json.getString("response");
                                if(password_response.equals("found")){

                                    final AlertDialog.Builder dialog =new AlertDialog.Builder(getActivity());
                                    dialog.setCancelable(true);
                                    dialog.setTitle("Student Registeration System");
                                    dialog.setIcon(R.drawable.logo);
                                    dialog.setMessage("Your Correct password Sent to your E-mail");
                                    dialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int inst_Info) {

                                            dialogInterface .dismiss();

                                            // TODO >> then Call Login fragment to Login again
                                            LoginFragment loginFragment =new LoginFragment();
                                            android.support.v4.app.FragmentManager fm=getFragmentManager();
                                            android.support.v4.app.FragmentTransaction ft=fm.beginTransaction();
                                            ft.addToBackStack(null);
                                            ft.replace(R.id.activity_main,loginFragment);
                                            ft.commit();


                                        }
                                    });

                                    dialog.show();
                                }
                                else if(password_response.equals("notFound"))
                                {
                                    Toast.makeText(getActivity(), " not found account with user name", Toast.LENGTH_LONG).show();

                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError() {

                        }
                    });


                }


            }
        });

        return v;
    }
    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return CubeAnimation.create(CubeAnimation.UP, enter, 500);
    }

}
