package com.example.aashu.watchnet;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class ReviewFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View rootView =  inflater.inflate(R.layout.fragment_review, container, false);
       ListView lv = (ListView)rootView.findViewById(R.id.reviewArticleListView);
        ArrayList<String> reviewArticleTitles = new ArrayList<String>();
        reviewArticleTitles.add("Title 1");
        reviewArticleTitles.add("Title 2");
        reviewArticleTitles.add("Title 3");
        reviewArticleTitles.add("Title 4");
        reviewArticleTitles.add("Title 5");
        reviewArticleTitles.add("Title 6");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, reviewArticleTitles);
        lv.setAdapter(arrayAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), AnalyseArticleActivity.class);
                startActivity(intent);

            }
        });
        return rootView;
    }
    }
