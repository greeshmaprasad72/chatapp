package com.example.chating;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.Date;


class LocalBroadcastReceiverOne extends BroadcastReceiver {
    public static final String LOCAL_BROADCAST_ACTION = "com.dev2qa.example.broadcast.activity.LOCAL_BROADCAST";

    public static final String LOCAL_BROADCAST_SOURCE = "LOCAL_BROADCAST_SOURCE";
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (LOCAL_BROADCAST_ACTION.equals(action)) {
            String fromActivity = intent.getStringExtra(LOCAL_BROADCAST_SOURCE);
            SharedPreferences prefs = context.getSharedPreferences("myPrefs",
                    Context.MODE_PRIVATE);



            SimpleDateFormat s = new SimpleDateFormat("hh:mm aa");
            String format = s.format(new Date(System.currentTimeMillis()));

            JSONArray array = null;
            if (prefs.contains("jsonstring")) {

                String userData = prefs.getString("jsonstring", " ");
                try {
                    array = new JSONArray(userData);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {
                array = new JSONArray();

            }
            JSONObject jsonObj = new JSONObject();

            try {
                jsonObj.put("name", fromActivity);
                jsonObj.put("timestamp", format);

                array.put(jsonObj);

                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("jsonstring", array.toString());
                editor.commit();


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
}
