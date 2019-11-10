package com.example.aashu.watchnet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ArticleViewActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> articleTitles;
    static String ContentOfArticle;
    String resultFromUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_view);
        Intent intent = getIntent();
        listView = findViewById(R.id.articleListView);
        articleTitles = MainActivity.ListOfTopics;
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, articleTitles);
        arrayAdapter.notifyDataSetChanged();
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                    intent.putExtra("Topic",MainActivity.ListOfTopics.get(position));
//                    intent.putExtra("Url", MainActivity.ListOfTopicsUrl.get(position));
                    DownloadTask getContent = new DownloadTask();
                try {
                    resultFromUrl = getContent.execute(MainActivity.ListOfTopicsUrl.get(position)).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                String res = resultFromUrl;

                String[] splitResult = res.split("<!-- CONTENT END -->");
                String processedText = splitResult[0];
                splitResult = processedText.split("<!-- CONTENT START -->");
                res = splitResult[1];
                res = res.replaceAll("<[^>]*>", "");

                //************************************************//
                //Code for keywords extraction goes here ********//
                //**********************************************//

                Intent intentAnalyse = new Intent(getApplicationContext(), AnalyseArticleActivity.class);
                intentAnalyse.putExtra("title",MainActivity.ListOfTopics.get(position));
                intentAnalyse.putExtra("content",res);
                intentAnalyse.putExtra("who", "random");
                intentAnalyse.putExtra("what", "random");
                intentAnalyse.putExtra("where","random");
                intentAnalyse.putExtra("how","random");
                intentAnalyse.putExtra("vehicles used","random");
                intentAnalyse.putExtra("number of products","random");
                intentAnalyse.putExtra("newspaper name","random");
                intentAnalyse.putExtra("date","random");
                intentAnalyse.putExtra("images","random");
//                Log.i("final Result", MainActivity.ListOfTopics.get());



                startActivity(intentAnalyse);

            }
        });
    }
}
