package com.example.gio.broadcastreceiverssms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.support.v4.content.LocalBroadcastManager;
import android.telephony.SmsMessage;


public class SmsReceiver extends BroadcastReceiver {
    public final String SMS_RECEIVED="android.provider.Telephony.SMS_RECEIVED";
    public final String LOCAL_SMS="com.example.gio.broadcastreceiverssms";
    String code="";
    String from="";
    public SmsReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction()==SMS_RECEIVED){
            SmsMessage [] msgs= Telephony.Sms.Intents.getMessagesFromIntent(intent);
            if(msgs.length > 0){
                code=msgs[0].getMessageBody();
                from=msgs[0].getOriginatingAddress();
            }
            Intent i = new Intent(MainActivity.LOCAL_SMS);
            i.putExtra("message",code);
            i.putExtra("from",from);
            LocalBroadcastManager.getInstance(context).sendBroadcast(i);
        }
    }
}
