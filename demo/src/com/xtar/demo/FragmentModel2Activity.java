package com.xtar.demo;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;

import com.xtar.demo.fragment.TextViewFragment1;
import com.xtar.demo.fragment.TextViewFragment2;

/**
 * 根据屏幕宽度来决定布局<br>
 * 当宽大于高时，用TextViewFragment1<br>
 * 当宽小于高时，用TextViewFragment2
 * 
 * @author lyx
 * 
 */
public class FragmentModel2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.empty_activity);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        if (size.x > size.y) {
            TextViewFragment1 frag1 = new TextViewFragment1();
            getFragmentManager().beginTransaction().replace(R.id.empty_activity, frag1).commit();
        } else {
            TextViewFragment2 frag2 = new TextViewFragment2();
            getFragmentManager().beginTransaction().replace(R.id.empty_activity, frag2).commit();
        }

    }

}
