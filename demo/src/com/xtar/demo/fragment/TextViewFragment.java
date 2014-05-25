package com.xtar.demo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TextViewFragment extends Fragment {

    private int layoutId = -1;

    public static TextViewFragment newInstance(int layoutId) {
        TextViewFragment fragment = new TextViewFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("layoutId", layoutId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            layoutId = args.getInt("layoutId");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(layoutId, container, false);
    }

}
