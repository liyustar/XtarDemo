package com.xtar.demo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SmsStateReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String MSG_TYPE = intent.getAction();

        if (MSG_TYPE.equals("android.provider.Telephony.SMS_RECEIVED")) {
            Toast toast = Toast.makeText(context, "SMS Received: " + MSG_TYPE, Toast.LENGTH_LONG);
            toast.show();

            Bundle bundle = intent.getExtras();
            Object messages[] = (Object[]) bundle.get("pdus");
            SmsMessage smsMessage[] = new SmsMessage[messages.length];
            for (int n = 0; n < messages.length; n++) {
                smsMessage[n] = SmsMessage.createFromPdu((byte[]) messages[n]);
            }

            // show first message
            Log.e("",
                    " smsMessage[0].getDisplayOriginatingAddress()=="
                            + smsMessage[0].getDisplayOriginatingAddress()
                            + "  smsMessage[0].getOriginatingAddress()=="
                            + smsMessage[0].getOriginatingAddress()
                            + "  smsMessage[0].getServiceCenterAddress()=="
                            + smsMessage[0].getServiceCenterAddress());

            Log.e("phone no==", "" + smsMessage[0].getOriginatingAddress());

            String msgBody = smsMessage[0].getMessageBody();
            if (msgBody.contains("star")) {
                Toast.makeText(context, "BLOCKED Received SMS: " + msgBody, Toast.LENGTH_LONG)
                        .show();
                abortBroadcast();
            }

        }
    }

}
