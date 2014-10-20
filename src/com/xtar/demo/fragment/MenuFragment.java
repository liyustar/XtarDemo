package com.xtar.demo.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.xtar.demo.R;
import com.xtar.demo.TabHostActivity;
import com.xtar.demo.XMLParserActivity;

public class MenuFragment extends Fragment implements OnItemClickListener {

    private ListView menulist;

    private ArrayAdapter<String> adapter;

    private String[] menuItems = { "Sound", "Display" };

    private boolean isTwoPanes;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        adapter = new ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, menuItems);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_fragment, container, false);
        menulist = (ListView) view.findViewById(R.id.menu_list);
        menulist.setAdapter(adapter);
        menulist.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.detail_layout) != null) {
            isTwoPanes = true;
        } else {
            isTwoPanes = false;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View view, int index, long arg3) {
        if (isTwoPanes) {
            Fragment frag = null;
            if (index == 0) {
                frag = new TextViewFragment1();
            } else if (index == 1) {
                frag = new TextViewFragment2();
            }
            getFragmentManager().beginTransaction().replace(R.id.detail_layout, frag).commit();
        } else {
            Intent intent = null;
            if (index == 0) {
                intent = new Intent(getActivity(), TabHostActivity.class);
            } else if (index == 1) {
                intent = new Intent(getActivity(), XMLParserActivity.class);
            }
            startActivity(intent);
        }
    }

}
