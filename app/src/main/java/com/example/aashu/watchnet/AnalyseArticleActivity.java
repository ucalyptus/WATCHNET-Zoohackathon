package com.example.aashu.watchnet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AnalyseArticleActivity extends AppCompatActivity {
    public static TextView articleTitleTextView;
    public static EditText articleContentTextView;
    public static EditText contentSimplifiedTextView;
    public static String simplifiedText = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyse_article);
        //*****************DATABASE **********************/

        final SQLiteDatabase mydb = this.openOrCreateDatabase("Review Database", MODE_PRIVATE, null);
        try {
            mydb.execSQL("CREATE TABLE IF NOT EXISTS analyseTable (ID INTEGER PRIMARY KEY AUTOINCREMENT, title VARCHAR, who VARCHAR, what VARCHAR, how VARCHAR, place VARCHAR, vehiclesUsed VARCHAR, newspaperName VARCHAR, images VARCHAR, date VARCHAR)");
        }catch(Exception e){
            e.printStackTrace();
        }
//        mydb.execSQL("INSERT INTO users (name, age) VALUES ('Nick',28)");
//        mydb.execSQL("INSERT INTO users (name, age) VALUES ('Ravi',28)");
//        Cursor c = mydb.rawQuery("SELECT * FROM users", null);


        //**********************************************/
        Intent intent = getIntent();
        articleTitleTextView = findViewById(R.id.articleTitleTextView);
        articleContentTextView = findViewById(R.id.articleContentTextView);
        contentSimplifiedTextView = findViewById(R.id.contentSimplifiedTextView);
        Button saveInDatabaseButton = findViewById(R.id.saveInDatabaseButton);
        final String tit = intent.getStringExtra("title");
        final String content = intent.getStringExtra("content");
        final String who = intent.getStringExtra("who");
        final String what = intent.getStringExtra("what");
        final String how = intent.getStringExtra("how");
        final String place = intent.getStringExtra("where");
        final String vehiclesUsed = intent.getStringExtra("vehicles used");
        final String numberOfProducts = intent.getStringExtra("number of products");
        final String newspaperName = intent.getStringExtra("newspaper name");
        final String images = intent.getStringExtra("images");
        final String date = intent.getStringExtra("date");
        articleTitleTextView.setText(tit);
//        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.aashu.watchnet", Context.MODE_PRIVATE);
//        try{
//        }catch(Exception e){
//            e.printStackTrace();
//        }
        articleContentTextView.setText(content);

        simplifiedText += "Who:" + who +"\n";
        simplifiedText += "What:" + what +"\n";
        simplifiedText += "How:" + how +"\n";
        simplifiedText += "Where:" + place +"\n";
        simplifiedText += "When:" + date +"\n";
        simplifiedText += "Vehicles Used:" + vehiclesUsed +"\n";
        simplifiedText += "Number of Products:" + numberOfProducts +"\n";
        simplifiedText += "Newspaper Name:" + newspaperName +"\n";
        simplifiedText += "Images:" + images +"\n";
        contentSimplifiedTextView.setText(simplifiedText);

        /***************onclickListeners*****************/

        saveInDatabaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Cursor c = mydb.rawQuery("SELECT Count(*) FROM reviewTable WHERE  title = \"" + tit +"\"", null);
                    int i=0;
                    while (c.moveToNext()) {
                        i+=1;
                    }
                    if(i<=1){
                        //title VARCHAR, content VARCHAR, who VARCHAR, what VARCHAR, how VARCHAR, place VARCHAR, vehiclesUsed VARCHAR, newspaperName VARCHAR, images VARCHAR, date VARCHAR
                        mydb.execSQL("Insert into analyseTable(title, who, what, how, place, vehiclesUsed, newspaperName, images, date) values('"+tit+",'"+who+"', '"+what+"', '" +how+"', '" +place+"', '"+vehiclesUsed+"', '"+newspaperName+"', '"+images+"', '"+date+"')");

                        Toast.makeText(AnalyseArticleActivity.this, "Successfully Added", Toast.LENGTH_SHORT).show();
                    }
//
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });


        /**********************************************/
    }

}
