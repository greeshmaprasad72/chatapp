package com.example.chating;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;



/**
 * A simple {@link Fragment} subclass.
 */
public class ReciveFragment extends Fragment {


    public ReciveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View itemview= inflater.inflate(R.layout.fragment_recive, container, false);
        ArrayList<Mdel> list = new ArrayList<>();
        SharedPreferences prefs = this.getActivity().getSharedPreferences("myPrefs",
                Context.MODE_PRIVATE);
        String strJson = prefs.getString("jsonstring","");
        try {

            JSONArray jarray=new JSONArray(strJson);
            for (int i=0;i<=jarray.length();i++){
                JSONObject job=jarray.getJSONObject(i);
                String send_msg=job.getString("name");
                String time_stamp=job.getString("timestamp");
                Mdel model=new Mdel();
                model.setMessages(send_msg);
                model.setTime_stamp(time_stamp);
                list.add(model);
            }


//            Toast.makeText(getContext(), "responsee"+send_msg, Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        RecyclerView recyclerView = itemview.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        llm.setStackFromEnd(true);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                llm.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(llm);
        Adapter adapter=new Adapter(getContext(), list);
        recyclerView.setAdapter(adapter);



        return  itemview;
    }


}
