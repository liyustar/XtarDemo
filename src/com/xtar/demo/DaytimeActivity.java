package com.xtar.demo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import com.xtar.demo.app.MainApplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DaytimeActivity extends Activity implements View.OnClickListener {
    static final String TAG = DaytimeActivity.class.getSimpleName();

    private Button btn_daytime = null;
    Handler mHandler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.daytime_activity);
        btn_daytime = (Button) findViewById(R.id.btn_daytime);

        mHandler = new Handler();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_daytime:
                Toast.makeText(this, "daytime", Toast.LENGTH_LONG).show();
                btn_daytime.setEnabled(false);
                doDaytime();
                break;
            default:
                break;
        }
    }

    public void doDaytime() {
        new Thread(new DaytimeRunnable()).start();
    }

    class DaytimeRunnable implements Runnable {
        @Override
        public void run() {

            DaytimeSocket sock = new DaytimeSocket();
            String str = null;
            long startTime, endTime;
            startTime = System.currentTimeMillis();
            try {
                sock.connect();
                str = sock.recvDaytime();
            } catch (Exception e) {
                str = "error";
                e.printStackTrace();
            }
            endTime = System.currentTimeMillis();

            final long timeInterval = endTime - startTime;
            final String content = str + "\ntime: " + timeInterval + "ms";
            Log.i(TAG, content);

            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainApplication.getContext(), content, Toast.LENGTH_LONG).show();
                    btn_daytime.setEnabled(true);
                }
            });
        }
    }

    class DaytimeSocket {
        Socket sock = null;

        public void connect() throws Exception {
            sock = new Socket("192.168.0.108", 13);
        }

        public String recvDaytime() throws Exception {
            InputStream is = sock.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String str = null;
            sock.setSoTimeout(30 * 1000);
            str = br.readLine();
            return str;
        }
    }

}
