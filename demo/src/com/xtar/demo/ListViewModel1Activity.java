package com.xtar.demo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.xtar.demo.model.MFile;

public class ListViewModel1Activity extends Activity {

    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listview = new ListView(this);
        listview.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1, getData()));
        setContentView(listview);
    }

    private List<String> getData() {
        List<MFile> mfiles = XMLParserActivity.getMFileData();
        List<String> items = new ArrayList<String>();
        for (int i = 0, len = mfiles.size(); i < len; i++) {
            items.add(mfiles.get(i).name);
        }
        return items;
    }

}
