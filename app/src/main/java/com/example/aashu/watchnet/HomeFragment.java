package com.example.aashu.watchnet;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.monkeylearn.ExtraParam;
import com.monkeylearn.MonkeyLearn;
import com.monkeylearn.MonkeyLearnException;
import com.monkeylearn.MonkeyLearnResponse;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class HomeFragment extends Fragment {
    ConstraintLayout cons;

    public void listArticles( View view){
        cons.setVisibility(cons.INVISIBLE);
        Intent intent = new Intent(getContext(), ArticleViewActivity.class);
        if(view.getTag().toString().matches("0")){
            Toast.makeText(getContext(), "Feature to be added soon", Toast.LENGTH_SHORT).show();
        }
        else if(view.getTag().toString().matches("1")){
            Log.i("Button","Clicked here");
            AccessUrls.monthlyReview(false);
            try {
                startActivity(intent);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        else if(view.getTag().toString().matches("2")){
            Log.i("Button","Clicked here");
            AccessUrls.yearlyReview();
            try {
                startActivity(intent);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        else{
            Toast.makeText(getContext(), "Feature to be added soon", Toast.LENGTH_SHORT).show();
        }




    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_home, container, false);
        cons = rootView.findViewById(R.id.constraintLayout);
        Button button = rootView.findViewById(R.id.customAnalysis);
//        URL url = null;
//         HttpURLConnection client;
//
//
//
//        // Use the API key from your account
//        MonkeyLearn ml = new MonkeyLearn("04f05ea0b1531ae8c87465086bfc3dfdd616adbb");
//
//        // Use the keyword extractor
//        String[] textList = {"I love the movie", "I hate the movie"};
//        ExtraParam[] extraParams = {new ExtraParam("max_keywords", "30")};
//        try {
//            url = new URL("http://exampleurl.com/");
//            client = (HttpURLConnection) url.openConnection();
//            client.setRequestMethod("POST");
//            client.setRequestProperty("data",textList.toString());
//            client.setDoOutput(true);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        MonkeyLearnResponse res = null;
//        try {
//            res = ml.extractors.extract("cl_jypWecck", textList, extraParams);
//        } catch (MonkeyLearnException e) {
//            e.printStackTrace();
//        }
//
//        Log.i("message:", res.arrayResult.toJSONString() );
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cons.setVisibility(cons.VISIBLE);
            }
        });
        Button weeklyAnalysis = rootView.findViewById(R.id.weeklyAnalysis);
        Button monthlyAnalysis = rootView.findViewById(R.id.monthlyAnalysis);
        Button yearlyAnalysis = rootView.findViewById(R.id.yearlyAnalysis);
        Button startAnalysis = rootView.findViewById(R.id.startAnalysis);
        weeklyAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listArticles(v);
            }
        });
        monthlyAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listArticles(v);
            }
        });
        yearlyAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listArticles(v);
            }
        });
        startAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listArticles(v);
            }
        });

        return rootView;
    }

}