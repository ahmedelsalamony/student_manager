package com.example.ahmed.student_manager.instructor;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ahmed.student_manager.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AttendanceFragment extends Fragment {


  ListView attendanceList;
  CardViewAdapterAttendance adapter;
    String arrayDates[]={"5/2/2017" , "8/5/2017" ,"11/5/2017" ,"14/5/2017"};
    String arrayNames[]={"ahmed " , "Mohamed" ,"Mahmoud" ,"ALi"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v =inflater.inflate(R.layout.inst_fragment_attendance, container, false);

         attendanceList = (ListView) v.findViewById(R.id.LisView_ATTendance);

          adapter=new CardViewAdapterAttendance(getActivity(),arrayDates,arrayNames);
   attendanceList.setAdapter(adapter);
        return  v;

    }

}
