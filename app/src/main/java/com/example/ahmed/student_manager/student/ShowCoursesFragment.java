package com.example.ahmed.student_manager.student;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

public class ShowCoursesFragment extends Fragment {


    private WebServices mWebServices;
    private  View mView;
    private ListView mListCoursesNames,  mListAttend;
    private ArrayAdapter adapter;
    private  CardViewAdapterAttend adapterAttend;
    private  StringBuffer mCoursesNamesBuffer   , mCoursesIdsBuffer ,mCoursesAppointsBuffer , mAttendsBuffer ;
    private String mCoursesNamesArray[]   , mCoursesIdsArray[] ,mCoursesAppointsArray[], mAttendArray[];


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fstudent_ragment_show_courses, container, false);

       mWebServices=new WebServices();
       mWebServices.sharedPreferences=getActivity().getSharedPreferences("manager",0);
       mCoursesNamesBuffer=new StringBuffer();
       mCoursesIdsBuffer=new StringBuffer();
        mCoursesAppointsBuffer=new StringBuffer();
        mAttendsBuffer=new StringBuffer();


       mListCoursesNames = (ListView) mView.findViewById(R.id.LisView_CoursesRegistered);
        mListAttend = (ListView) mView.findViewById(R.id.LisView_Attend);

        mListAttend.setVisibility(View.INVISIBLE);
        mWebServices.getCourses(getActivity(), mWebServices.sharedPreferences.getInt("id", 2017), new request_interface() {
           @Override
           public void onResponse(String response) {
               try {

                   JSONObject jsonResponse = new JSONObject(response);
                   JSONArray jsonArray = jsonResponse.getJSONArray("courses");


                   for (int i = 0; i < jsonArray.length(); i++) {
                       JSONObject search_object = jsonArray.getJSONObject(i);

                       mCoursesNamesBuffer.append(search_object.getString("course_name") + ":");
                       mCoursesIdsBuffer.append(search_object.getString("id") + ":");
                       mCoursesAppointsBuffer.append(search_object.getString("course_appointment") + ":");
                       mAttendsBuffer.append(search_object.getString("attend") + ":");

                   }



                   mCoursesNamesArray=mCoursesNamesBuffer.toString().split(":");
                   mCoursesIdsArray=mCoursesIdsBuffer.toString().split(":");
                   mCoursesAppointsArray=mCoursesAppointsBuffer.toString().split(":");
                   mAttendArray=mAttendsBuffer.toString().split(":");


                   adapter = new ArrayAdapter(getActivity() , android.R.layout.simple_list_item_1 , mCoursesNamesArray);
                   mListCoursesNames.setAdapter(adapter);


               } catch (JSONException e) {
                   Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
               }

           }

           @Override
           public void onError() {

           }
       });


     mListCoursesNames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        // TODO get course Appointment
      //  Toast.makeText(getActivity(), ""+mCoursesNamesArray[i]+"\n"+mCoursesAppointsArray[0]+"\n"+mAttendArray[0], Toast.LENGTH_SHORT).show();

        mListAttend.setVisibility(View.VISIBLE);

        String   DateAttend[];
        DateAttend=mCoursesAppointsArray[i].split("#");

        String   AttendStatus[];
        AttendStatus=mAttendArray[i].split("#");


        for(int index =0 ; index< AttendStatus.length ;index++){

            if (AttendStatus[index].equals("0")){
                AttendStatus[index]="--";

                adapterAttend = new CardViewAdapterAttend(getActivity(), DateAttend, AttendStatus);
                mListAttend.setAdapter(adapterAttend);
            }

            else   if (AttendStatus[index].equals("false")) {
                AttendStatus[index]="not attended";

                adapterAttend = new CardViewAdapterAttend(getActivity(), DateAttend, AttendStatus);
                mListAttend.setAdapter(adapterAttend);
            }

            else  if (AttendStatus[index].equals("true")) {

                 AttendStatus[index]="attended";

                 adapterAttend = new CardViewAdapterAttend(getActivity(), DateAttend, AttendStatus);
                 mListAttend.setAdapter(adapterAttend);
            }
        }





       }

       });


        return  mView;
    }

}
