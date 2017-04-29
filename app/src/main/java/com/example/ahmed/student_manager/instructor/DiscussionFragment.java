package com.example.ahmed.student_manager.instructor;


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

private CardViewAdapterAskReply adapterAskReply;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

         mView =  inflater.inflate(R.layout.student_fragment_discussion_int, container, false);
         mWebServices = new WebServices();
         mInstNameBuffer =new StringBuffer();
         mWebServices.sharedPreferences=getActivity().getSharedPreferences("manager",0);
           mAskBuffer=new StringBuffer();
            mReplyBuffer=new StringBuffer();


        mAskBuffer.append("ask1:ask2:ask3:");
        mReplyBuffer.append("reply1:reply2:reply3:");
         mFirst = (Button) mView.findViewById(R.id.Button_firstInst);
         mSecond = (Button) mView.findViewById(R.id.Button_secondtInst);
         mThird = (Button) mView.findViewById(R.id.Button_thirdInst);
         mListAsk = (ListView) mView.findViewById(R.id.LisView_Asks);
         AskBtn= (Button) mView.findViewById(R.id.Button_ask);
         edAsk= (EditText) mView.findViewById(R.id.Edit_ask);



        mAskArray=mAskBuffer.toString().split(":");
        mReplyArray=mReplyBuffer.toString().split(":");

        adapterAskReply=new CardViewAdapterAskReply(getActivity(),mAskArray,mReplyArray);
        mListAsk.setAdapter(adapterAskReply);


        AskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });






           return  mView;


    }

}
