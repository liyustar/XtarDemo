package com.xtar.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TabHost;

public class TabHostActivity extends Activity {

    private TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tabhost_activity);
        tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();

        TabHost.TabSpec tabSpec1 = tabHost.newTabSpec("tag1")
                .setIndicator("", getResources().getDrawable(android.R.drawable.ic_lock_idle_lock))
                .setContent(R.id.tv_content1);
        TabHost.TabSpec tabSpec2 = tabHost.newTabSpec("tag2").setIndicator("Tag 2")
                .setContent(R.id.tv_content2);
        TabHost.TabSpec tabSpec3 = tabHost.newTabSpec("tag3").setIndicator("Tag 3")
                .setContent(R.id.tv_content3);

        tabHost.addTab(tabSpec1);
        tabHost.addTab(tabSpec2);
        tabHost.addTab(tabSpec3);
    }
}
