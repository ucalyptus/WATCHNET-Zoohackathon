package com.example.aashu.watchnet;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import static com.example.aashu.watchnet.AnalyseArticleActivity.mydb;


public class ResultFragment extends Fragment {
    ListView resultListView;
    EditText editText;
    Button button;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_result, container, false);
        resultListView = rootView.findViewById(R.id.resultListView);
        editText = rootView.findViewById(R.id.searchEditText);
        final String searchText = editText.getText().toString();
        button = rootView.findViewById(R.id.Search);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Cursor c = mydb.rawQuery("SELECT * FROM analyseTable where title LIKE %'"+searchText+"'", null);
                    int title = c.getColumnIndex("title");
                    int who = c.getColumnIndex("who");

                    c.moveToFirst();
                    do{
                        Toast.makeText(getContext(), "No results found", Toast.LENGTH_SHORT).show();


                    }while(c.moveToNext());

                }catch(Exception e){
                    e.printStackTrace();
                }


            }
        });
        return rootView;

    }
}