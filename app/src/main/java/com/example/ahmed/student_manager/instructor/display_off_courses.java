package com.example.ahmed.student_manager.instructor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ahmed.student_manager.R;
import com.example.ahmed.student_manager.web.WebServices;
import com.example.ahmed.student_manager.web.request_interface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ahmed on 4/27/2017.
 */

public class display_off_courses extends Fragment {

    View mView;
    WebServices webServices;
    ArrayList<DataModelInstructortList> dataModels;
    ListView listView;
    private static CustomAdapterInstructor adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        mView = inflater.inflate(R.layout.display_off_courses, container, false);
        webServices=new WebServices();

        inst_Home_Instructor_activity.sInstHomeInstructorActivity = 2;
        listView = (ListView) mView.findViewById(R.id.display_off_courses_listview);
        dataModels = new ArrayList<>();
        webServices.getOffCoursesToInstructor(getActivity(), new request_interface() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("off_courses");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject sub_jsonObject = jsonArray.getJSONObject(i);
                        String course_name = sub_jsonObject.getString("course_name");
                        String course_code=sub_jsonObject.getString("course_code");
                        int max_student_num = sub_jsonObject.getInt("max_student_num");
                        int course_hours=sub_jsonObject.getInt("course_hours");
                        int id=sub_jsonObject.getInt("id");




                       /* DataModelInstructortList d=new DataModelInstructortList("a",1,"c",0);
                        DataModelInstructortList a=new DataModelInstructortList("a",2,"c",1);
                        DataModelInstructortList c=new DataModelInstructortList("a",3,"c",2);
                        DataModelInstructortList e=new DataModelInstructortList("a",4,"c",3);
                        dataModels.add(d);
                        dataModels.add(a);
                        dataModels.add(c);
                        dataModels.add(e);*/
                        dataModels.add(new DataModelInstructortList(id,course_name,course_hours,course_code,max_student_num));
                        adapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();}}
            @Override
            public void onError() {

            }
        });

        adapter = new CustomAdapterInstructor(dataModels, getActivity(), (AppCompatActivity) getActivity());
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


            }
        });


        return mView;
    }
}
