package com.example.chating;


import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;



/**
 * A simple {@link Fragment} subclass.
 */
public class SendingFragment extends Fragment {



    public SendingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sending, container, false);

        final EditText send_messge = v.findViewById(R.id.editText);
        FloatingActionButton send = v.findViewById(R.id.floatingActionButton);



        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String et_messgae = send_messge.getText().toString();
                Intent intent = new Intent(LocalBroadcastReceiverOne.LOCAL_BROADCAST_ACTION);
                intent.putExtra(LocalBroadcastReceiverOne.LOCAL_BROADCAST_SOURCE, et_messgae);
                LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);

                send_messge.setText(" ");


            }
        });
        return v;
    }


}
