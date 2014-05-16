package com.xtar.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
                startActivity(new Intent(this, ListViewModel1Activity.class));
                break;
            default:
                break;
        }
    }

}
