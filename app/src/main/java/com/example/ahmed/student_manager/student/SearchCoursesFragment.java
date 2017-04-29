package com.example.ahmed.student_manager.student;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmed.student_manager.R;
import com.example.ahmed.student_manager.web.WebServices;
import com.example.ahmed.student_manager.web.request_interface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchCoursesFragment extends Fragment {


    private View mView;

    private ListView mListStudent;
    private WebServices mWebServices;
    private StringBuffer mCourseNameBuffer ,mCourseCodeBuffer , mCourseHourBuffer , mCourseMaxNumberBuffer , mCourseIdBuffer;
    private String mCourseNameArray[] ,mCourseCodeArray[],mHourNameArray[],mCourseMaxNumberArray[] ,mCourseIdArray[];
    private  String mStudentIdArray[];
    private StringBuffer mStudentIdBuffer;
    private CardViewAdapter adapter;
    private  int position=0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView= inflater.inflate(R.layout.student_fragment_search_courses, container, false);

        mWebServices =new WebServices();

        mCourseCodeBuffer=new StringBuffer();
        mCourseHourBuffer=new StringBuffer();
        mCourseMaxNumberBuffer=new StringBuffer();
        mCourseNameBuffer=new StringBuffer();
        mCourseIdBuffer =new StringBuffer();
        mStudentIdBuffer=new StringBuffer();

        mWebServices.sharedPreferences=getActivity().getSharedPreferences("manager",0);


        mListStudent= (ListView) mView.findViewById(R.id.LisView_Student_Courses);



                        mWebServices.getCoursesTOStudent(getActivity() , new request_interface() {
                            @Override
                            public void onResponse(String response) {

                                try {

                                    JSONObject jsonResponse = new JSONObject(response);
                                    JSONArray jsonArray = jsonResponse.getJSONArray("all_courses");


                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject search_object = jsonArray.getJSONObject(i);
                                        mCourseIdBuffer.append(search_object.getInt("id") + ":");
                                        mCourseNameBuffer.append(search_object.getString("course_name") + ":");
                                        mCourseCodeBuffer.append(search_object.getString("course_code") + ":");
                                        mCourseHourBuffer.append(search_object.getString("course_hours") + ":");
                                        mCourseMaxNumberBuffer.append(search_object.getString("max_student_num") + ":");

                                    }

                                    mCourseIdArray = mCourseIdBuffer.toString().split(":");
                                    mCourseNameArray = mCourseNameBuffer.toString().split(":");
                                    mCourseCodeArray = mCourseCodeBuffer.toString().split(":");
                                    mHourNameArray = mCourseHourBuffer.toString().split(":");
                                    mCourseMaxNumberArray=mCourseMaxNumberBuffer.toString().split(":");


                                    adapter = new CardViewAdapter(getActivity(),mCourseNameArray,mCourseCodeArray,mHourNameArray,mCourseMaxNumberArray);

                                    mListStudent.setAdapter(adapter);



                                } catch (JSONException e) {
                                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onError() {

                                Toast.makeText(getActivity(), "error server.!", Toast.LENGTH_SHORT).show();
                            }

                        });






        // TODO Get Student Uid

        mWebServices.getStudentUid(getActivity() ,mWebServices.sharedPreferences.getInt("id",2017) ,new request_interface() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray jsonArray = jsonResponse.getJSONArray("student_uid");


                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject search_object = jsonArray.getJSONObject(i);
                        mStudentIdBuffer.append(search_object.getString("id") + ":");


                    }



                    mStudentIdArray=mStudentIdBuffer.toString().split(":");


                } catch (JSONException e) {
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {

                Toast.makeText(getActivity(), "error server.!", Toast.LENGTH_SHORT).show();
            }

        });


//---------------------------------------------------------------------------------------------------------//
         mListStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                 position=i;

                 AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                 builder.setIcon(R.drawable.logo);
                 builder.setTitle(""+mCourseCodeArray[i]);
                 builder.setMessage("please Register to this Course");

                 builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialogInterface, int i) {
                     if(Integer.parseInt(mCourseMaxNumberArray[position]) <= 0 ){

                         Toast.makeText(getActivity(), "you can`t register at this Course\nAs it Completed.", Toast.LENGTH_LONG).show();
                     }
                     else if (mStudentIdArray.length > 3){

                         Toast.makeText(getActivity(), "you can`t register at this course\nAs the limit of courses is 3 ", Toast.LENGTH_LONG).show();

                     }

                     else {


                            // TODO update  courses

                         int max= Integer.parseInt( mCourseMaxNumberArray[position])-1;
                         mWebServices.updateCoursesByStudent(getActivity(), mCourseCodeArray[position], max,mWebServices.sharedPreferences.getInt("id" , 2017) , "ask", new request_interface() {
                            @Override
                            public void onResponse(String response) {

                                Toast.makeText(getActivity(), "u are successfully registered.", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError() {
                                Toast.makeText(getActivity(), "server error.", Toast.LENGTH_SHORT).show();
                            }
                        });


                            //TODO  add into  " courses registered "

                         mWebServices.addCourseByStudent(getActivity() , mWebServices.sharedPreferences.getInt("id",2017),Integer.parseInt(mCourseIdArray[position]));


                     }



                     }
                 });
                 builder.show();

             }
         });

        return  mView;


    }


}
