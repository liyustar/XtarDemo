package com.xtar.demo.fragment;

import com.xtar.demo.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ContentSetupFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View setupLayout = inflater.inflate(R.layout.tabcontent_setup_fragment, container, false);
        return setupLayout;
    }

}
