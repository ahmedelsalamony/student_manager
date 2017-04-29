package com.example.ahmed.student_manager.instructor;


import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.ahmed.student_manager.R;
import com.example.ahmed.student_manager.web.WebServices;
import com.example.ahmed.student_manager.web.request_interface;
import com.squareup.timessquare.CalendarPickerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.R.interpolator.linear;

/**
 * Created by ahmed on 4/27/2017.
 */

public class inst_Assign_Course_Appointment extends Fragment {

View mView;
WebServices webServices;
EditText edtLecturesNum;
Button btn , saveBtn;
int lecturesNum;
    List<EditText> allEds;
    int j;
    String strings[];
    int i;


    int yr, month, day;
    int yrTo,monthTo,dayTo;


    EditText et[];

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.inst_assign_course_appointment, container, false);

        inst_Home_Instructor_activity.sInstHomeInstructorActivity=3;
        webServices = new WebServices();

webServices.sharedPreferences=getActivity().getSharedPreferences("manager",0);
        edtLecturesNum=(EditText)mView.findViewById(R.id.inst_assign_course_appointment_lecturesNum);
        btn=(Button)mView.findViewById(R.id.inst_assign_course_appointment_btndate);
        saveBtn=(Button)mView.findViewById(R.id.buttonSave);

        //lecturesNum= Integer.parseInt(edtLecturesNum.getText().toString());

saveBtn.setVisibility(View.INVISIBLE);
        Calendar today = Calendar.getInstance();
        yr = today.get(Calendar.YEAR);
        month = today.get(Calendar.MONTH);
        day = today.get(Calendar.DAY_OF_MONTH);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    /*for (int i = 0; i < 5; i++) {
                        Toast.makeText(getActivity(),i+ "  ", Toast.LENGTH_SHORT).show();
                        LinearLayout ll = (LinearLayout) mView.findViewById(R.id.inst_assign_course_appointment_container);
                        ViewGroup.LayoutParams lp =
                                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        Button myButton = new Button(getActivity());
                        btn.setId(i);
                        final int id_ = btn.getId();
                        myButton.setText("Add Me "+ i);
                        btn = ((Button) getActivity().findViewById(id_));

                        ll.addView(myButton, lp);
                    }*/

                      et=new EditText[Integer.parseInt(edtLecturesNum.getText().toString())];
                     allEds = new ArrayList<EditText>();
                    LinearLayout ll = (LinearLayout)mView.findViewById(R.id.inst_assign_course_appointment_container);
                    Display display = ((WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
                    int width = display.getWidth() /2;

                        LinearLayout l = new LinearLayout(getActivity());
                        l.setOrientation(LinearLayout.VERTICAL);

                        for( j=0;j<Integer.parseInt(edtLecturesNum.getText().toString());j++){
                           et[j]=new EditText(getActivity());
                            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);

                            et[j].setId(j);
                            allEds.add(et[j]);


                            l.addView(et[j],lp);


                        }



                        ll.addView(l);



                    //}
                }
            });





            saveBtn.setVisibility(View.VISIBLE);

            saveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    strings= new String[allEds.size()];

                for(int i=0; i < allEds.size(); i++){

                    strings[i] = allEds.get(i).getText().toString();
                }
                String content="";
                for (   int x =0 ; x<strings.length ;x++){
                    content+=strings[x]+"#";

                }

                  webServices.updateCourseByInstuctorAppointments(getActivity(), CustomAdapterInstructor.sCourse_Code, webServices.sharedPreferences.getInt("id", 0), content, new request_interface() {
                      @Override
                      public void onResponse(String response) {
                          Toast.makeText(getActivity(), "saved.", Toast.LENGTH_SHORT).show();
                      }

                      @Override
                      public void onError() {

                      }
                  });

                 }
            });

        Button b = (Button) mView.findViewById(R.id.buttonx);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//

                new DatePickerDialog(getActivity(), mDateSetListener, yr, month, day).show();


            }


        });



        return mView;

    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener()
    {
        public void onDateSet(
                DatePicker view, int year, int monthOfYear, int dayOfMonth)
        {
            yr = year;
            month = monthOfYear;
            day = dayOfMonth;

            et[i].setText(""+(month + 1) + "/" + day + "/" + yr);
            i++;


        }
    };


}
