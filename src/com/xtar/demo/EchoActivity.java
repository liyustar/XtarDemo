package com.xtar.demo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import com.xtar.demo.app.MainApplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class EchoActivity extends Activity implements View.OnClickListener {
    public static final String TAG = EchoActivity.class.getSimpleName();

    private static final String ECHO_STR = "hello world\n";
    private static final int ECHO_TIME = 1000;

    private Button btn_echo = null;
    Handler mHandler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.echo_activity);
        btn_echo = (Button) findViewById(R.id.btn_echo);

        mHandler = new Handler();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_echo:
                Toast.makeText(this, "echo", Toast.LENGTH_LONG).show();
                btn_echo.setEnabled(false);
                doEcho();
                break;
            default:
                break;
        }
    }

    private void doEcho() {
        new Thread(new EchoRunnable()).start();
    }

    class EchoRunnable implements Runnable {
        @Override
        public void run() {

            EchoSocket sock = new EchoSocket();
            String str = null;
            long startTime, endTime;
            startTime = System.currentTimeMillis();
            try {
                sock.connect();
                int t = ECHO_TIME;
                while (t-- != 0) {
                    sock.sendString();
                    str = sock.recvString();
                }
                sock.close();
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
                    btn_echo.setEnabled(true);
                }
            });
        }
    }

    class EchoSocket {
        Socket sock = null;
        OutputStreamWriter osw = null;
        BufferedReader br = null;

        public void connect() throws Exception {
            sock = new Socket("192.168.0.108", 7);

            OutputStream os = sock.getOutputStream();
            osw = new OutputStreamWriter(os);

            InputStream is = sock.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
        }

        public boolean sendString() throws Exception {
            osw.write(ECHO_STR);
            osw.flush();
            return true;
        }

        public String recvString() throws Exception {
            String str = null;
            sock.setSoTimeout(30 * 1000);
            str = br.readLine();
            return str;
        }

        public void close() throws Exception {
            if (sock != null) {
                sock.close();
            }
        }

    }

}
