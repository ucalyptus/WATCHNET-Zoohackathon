package com.example.aashu.watchnet;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.monkeylearn.ExtraParam;
import com.monkeylearn.MonkeyLearn;
import com.monkeylearn.MonkeyLearnException;
import com.monkeylearn.MonkeyLearnResponse;
import com.monkeylearn.Tuple;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<String> ListOfTopics = new ArrayList<String>();
    public static ArrayList<String> ListOfTopicsUrl = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
//        MonkeyLearn ml = new MonkeyLearn("04f05ea0b1531ae8c87465086bfc3dfdd616adbb");
//        String modelId = "cl_jypWecck";
//        String[] data = {"Hunters did a good job"};
//        try {
//            MonkeyLearnResponse res = ml.classifiers.classify(modelId, data, true);
//            Log.i("res",res.arrayResult.toJSONString());
//        } catch (MonkeyLearnException e) {
//            e.printStackTrace();
//        }


        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
        }
    }



    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.navigation_review:
                            selectedFragment = new ReviewFragment();
                            break;
                        case R.id.navigation_result:
                            selectedFragment = new ResultFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };
}