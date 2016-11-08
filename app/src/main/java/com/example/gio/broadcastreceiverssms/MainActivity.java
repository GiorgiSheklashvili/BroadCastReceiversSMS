package com.example.gio.broadcastreceiverssms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText editText,from;
    public final String SMS_RECEIVED="android.provider.Telephony.SMS_RECEIVED";
    public static final String LOCAL_SMS="com.example.gio.broadcastreceiverssms";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=(EditText)findViewById(R.id.editTextForFill);
        from=(EditText)findViewById(R.id.from);
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver,new IntentFilter(LOCAL_SMS));
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }

    BroadcastReceiver receiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle =intent.getExtras();
            editText.setText(bundle.getString("message"));
            from.setText(bundle.getString("from"));
        }
    };
}
