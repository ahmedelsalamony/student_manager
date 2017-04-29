package com.example.ahmed.student_manager.student;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ahmed.student_manager.R;
import com.example.ahmed.student_manager.web.WebServices;
import com.example.ahmed.student_manager.web.request_interface;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscussionFragment extends Fragment {

     private View mView;
     private Button mFirst , mSecond , mThird ;
     private WebServices mWebServices;
     private  StringBuffer mInstNameBuffer , mAskBuffer , mReplyBuffer;
     private  String       mInstArray[] , mAskArray[] , mReplyArray[];
     private ListView mListAsk;
    private EditText edAsk;
    private  Button AskBtn;

private  CardViewAdapterAskReply adapterAskReply;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

         mView =  inflater.inflate(R.layout.student_fragment_discussion, container, false);
         mWebServices = new WebServices();
         mInstNameBuffer =new StringBuffer();
         mWebServices.sharedPreferences=getActivity().getSharedPreferences("manager",0);
 mAskBuffer=new StringBuffer();
 mReplyBuffer=new StringBuffer();


         mFirst = (Button) mView.findViewById(R.id.Button_firstInst);
         mSecond = (Button) mView.findViewById(R.id.Button_secondtInst);
         mThird = (Button) mView.findViewById(R.id.Button_thirdInst);
         mListAsk = (ListView) mView.findViewById(R.id.LisView_Asks);
         AskBtn= (Button) mView.findViewById(R.id.Button_ask);
         edAsk= (EditText) mView.findViewById(R.id.Edit_ask);




        mWebServices.getInstructors(getActivity(), mWebServices.sharedPreferences.getInt("id", 2017), new request_interface() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray jsonArray = jsonResponse.getJSONArray("instructors");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject search_object = jsonArray.getJSONObject(i);
                        mInstNameBuffer.append(search_object.getString("name") + ":");


                    }

                        mInstArray = mInstNameBuffer.toString().split(":");
                        if(mInstArray.length == 1){

                            mFirst.setVisibility(View.VISIBLE);
                            mFirst.setText(mInstArray[0]);

                        }

                        else if(mInstArray.length == 2){

                        mSecond.setVisibility(View.VISIBLE);
                            mSecond.setText(mInstArray[1]);
                        }

                        else if(mInstArray.length == 3){

                        mThird.setVisibility(View.VISIBLE);
                            mThird.setText(mInstArray[2]);
                    }

                }
                catch (Exception e){

                    Toast.makeText(getActivity(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError() {


            }

             });



        mWebServices.getAskReply(getActivity(), mWebServices.sharedPreferences.getInt("id", 2017), new request_interface() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray jsonArray = jsonResponse.getJSONArray("asks");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject search_object = jsonArray.getJSONObject(i);
                        mAskBuffer.append(search_object.getString("student_ask") + ":");
                        mReplyBuffer.append(search_object.getString("student_reply") + ":");

                    }

                    mAskArray = mAskBuffer.toString().split(":");
                    mReplyArray =mReplyBuffer.toString().split(":");


   if(mAskArray.length==0 || mReplyArray.length==0){
       Toast.makeText(getActivity(), "no asks yet.!", Toast.LENGTH_SHORT).show();


   }

   else {

       adapterAskReply = new CardViewAdapterAskReply(getActivity(), mAskArray, mReplyArray);
       mListAsk.setAdapter(adapterAskReply);
   }
                }
                catch (Exception e){

                    Toast.makeText(getActivity(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {

            }
        });


        AskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mWebServices.updateCourseAsk(getActivity(), mWebServices.sharedPreferences.getInt("id", 2017), edAsk.getText().toString(), new request_interface() {
                    @Override
                    public void onResponse(String response) {

                        // TODO refresh page

                    }

                    @Override
                    public void onError() {


                    }
                });

            }
        });






           return  mView;


    }

}
