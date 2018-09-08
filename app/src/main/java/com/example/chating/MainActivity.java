package com.example.chating;

        import android.content.Context;
        import android.content.IntentFilter;
        import android.support.v4.app.FragmentTabHost;
        import android.support.v4.content.LocalBroadcastManager;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private FragmentTabHost mTabHost;

    private LocalBroadcastReceiverOne receiverOne = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTabHost = findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

//        mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator("send"),
//                SendingFragment.class, null);
//        mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator("Recive"),
//                ReciveFragment.class, null);

        mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator(createTabView(mTabHost, "Send")),
                SendingFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator(createTabView(mTabHost, "Receive")),
                ReciveFragment.class, null);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(LocalBroadcastReceiverOne.LOCAL_BROADCAST_ACTION);
        receiverOne=new LocalBroadcastReceiverOne();
        LocalBroadcastManager.getInstance(this).registerReceiver(receiverOne, intentFilter);
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiverOne);
        super.onDestroy();
    }

    private static View createTabView(ViewGroup parent, String label) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tabview, null, false);
        TextView tvLabel = ((TextView) view.findViewById(R.id.tvLabel));
        tvLabel.setText(label);
        return view;
    }
}
