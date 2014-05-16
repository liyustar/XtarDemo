package com.xtar.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class XMLParserActivity extends Activity implements View.OnClickListener {

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
        // TODO Auto-generated method stub
    }

}
