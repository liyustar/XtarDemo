package com.xtar.demo;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.xtar.demo.module.MFile;
import com.xtar.demo.resolver.MFileSaxParser;

public class XMLParserActivity extends Activity implements View.OnClickListener {
    private static final String TAG = XMLParserActivity.class.getSimpleName();

    private Button btnSaxParser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.xmlparser_activity);

        btnSaxParser = (Button) findViewById(R.id.btn_sax);
        btnSaxParser.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sax:
                doSaxParse();
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
}