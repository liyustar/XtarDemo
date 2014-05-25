package com.xtar.demo;

import android.app.Activity;
import android.os.Bundle;

/**
 * 通过别名来引用合适布局<br>
 * 别名在values/layouts.xml和values-large/layouts.xml中定义
 * 
 * @author lyx
 * 
 */
public class FragmentModel1Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.fragment_activity);
        setContentView(R.layout.menu_activity);
    }

}
