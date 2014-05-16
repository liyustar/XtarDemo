package com.xtar.demo;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.xtar.demo.app.MainApplication;
import com.xtar.demo.model.MFile;
import com.xtar.demo.resolver.MFileDomParser;
import com.xtar.demo.resolver.MFilePullParser;
import com.xtar.demo.resolver.MFileSaxParser;

public class XMLParserActivity extends Activity implements View.OnClickListener {
    private static final String TAG = XMLParserActivity.class.getSimpleName();

    private Button btnSaxParser = null;
    private Button btnDomParser = null;
    private Button btnPullParser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.xmlparser_activity);

        btnSaxParser = (Button) findViewById(R.id.btn_sax);
        btnSaxParser.setOnClickListener(this);
        btnDomParser = (Button) findViewById(R.id.btn_dom);
        btnDomParser.setOnClickListener(this);
        btnPullParser = (Button) findViewById(R.id.btn_pull);
        btnPullParser.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sax:
                doSaxParse();
                break;
            case R.id.btn_dom:
                doDomParse();
                break;
            case R.id.btn_pull:
                doPullParse();
                break;
            default:
                break;
        }
    }

    private void doSaxParse() {
        InputStream in = this.getResources().openRawResource(R.raw.person_data);
        MFileSaxParser mfsp = new MFileSaxParser();
        List<MFile> mfiles = mfsp.doParse(in);
        for (int i = 0; i < mfiles.size(); i++) {
            Log.d(TAG, mfiles.get(i).toString());
        }

        // close inputstream
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doDomParse() {
        InputStream in = this.getResources().openRawResource(R.raw.person_data);
        MFileDomParser mfsp = new MFileDomParser();
        List<MFile> mfiles = mfsp.doParse(in);
        for (int i = 0; i < mfiles.size(); i++) {
            Log.d(TAG, mfiles.get(i).toString());
        }

        // close inputstream
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doPullParse() {
        InputStream in = this.getResources().openRawResource(R.raw.person_data);
        MFilePullParser mfsp = new MFilePullParser();
        List<MFile> mfiles = mfsp.doParse(in);
        for (int i = 0; i < mfiles.size(); i++) {
            Log.d(TAG, mfiles.get(i).toString());
        }

        // close inputstream
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<MFile> getMFileData() {
        InputStream in = MainApplication.getContext().getResources()
                .openRawResource(R.raw.person_data);
        MFileSaxParser mfsp = new MFileSaxParser();
        List<MFile> mfiles = mfsp.doParse(in);

        // close inputstream
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mfiles;
    }
}