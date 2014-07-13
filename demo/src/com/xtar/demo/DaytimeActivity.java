package com.xtar.demo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DaytimeActivity extends Activity implements View.OnClickListener {

    private Context mContext = null;
    private Button btn_daytime = null;
    Handler mHandler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.daytime_activity);
        btn_daytime = (Button) findViewById(R.id.btn_daytime);
        
        mContext = this;
        mHandler = new Handler();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
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
            try {
                sock.connect();
                str = sock.recvDaytime();
            } catch (Exception e) {
                str = "error";
                e.printStackTrace();
            }

            final String content = str;

            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(mContext, content, Toast.LENGTH_LONG).show();
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
            str = br.readLine();
            return str;
        }
    }

}
