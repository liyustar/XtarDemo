package com.xtar.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

public class ListViewActivity extends Activity implements View.OnClickListener {

    private Button btn_listview1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_activity);

        btn_listview1 = (Button) findViewById(R.id.btn_listview1);
        btn_listview1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_listview1:
                break;
            default:
                break;
        }
    }

}
