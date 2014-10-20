package com.xtar.demo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.xtar.demo.R;

public class TextViewFragment2 extends Fragment {

    private Button btn = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.textview_fragment_2, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btn = (Button) getActivity().findViewById(R.id.btn);
        btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                TextView tv = (TextView) getActivity().findViewById(R.id.tag1);
                Toast.makeText(getActivity(), tv.getText().toString(), Toast.LENGTH_LONG).show();
            }

        });
    }

}
