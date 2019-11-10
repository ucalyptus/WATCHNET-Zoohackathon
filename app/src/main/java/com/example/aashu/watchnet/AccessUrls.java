package com.example.aashu.watchnet;

import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class DownloadTask extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(String... urls) {

        String result = "";
        URL url;
        HttpURLConnection urlConnection = null;
        try {
            int count = urls.length;
            for (int i = 0; i < count; i++) {
                url = new URL(urls[i]);
                Log.i("url:", urls[i]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();
                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }

            }

                return result;
            }catch(Exception e){
                e.getMessage();
                return null;
            }
        }
    }


public class AccessUrls {
    public static ArrayList<String> URLS = new ArrayList<String>();
    static String url;
    static DownloadTask task = new DownloadTask();
    static String allArticles;
    static Calendar c = Calendar.getInstance();
    static int day = c.get(Calendar.DAY_OF_MONTH);
    static int month = c.get(Calendar.MONTH);
    static int year = c.get(Calendar.YEAR);
    static String startMonth = Integer.toString(month);
    static String startYear = Integer.toString(year);
    static String startDay = Integer.toString(day);
    public static void monthlyReview(boolean week){


        //For https://www.customs.gov.hk/

        url = "https://www.customs.gov.hk/en/publication_press/press/index_year_"+startYear+"-month_"+startMonth+".html";
        try {
            allArticles = task.execute(url).get();
            String[] splitResult = allArticles.split("<!-- CONTENT END -->");
            String processedText = splitResult[0];
            splitResult = processedText.split("<!-- CONTENT START -->");
            Pattern p = Pattern.compile("<td valign=\"top\"><a href=\"(.*?)\" title=\"");
            Matcher m = p.matcher(splitResult[1]);
            while(m.find()){
                MainActivity.ListOfTopicsUrl.add("https://www.customs.gov.hk"+m.group(1));
//                Log.i("message","https://www.customs.gov.hk"+m.group(1));
            }
            p = Pattern.compile("title=\"Click here to(.*?)\">");
            m = p.matcher(splitResult[1]);
            while(m.find()){
                MainActivity.ListOfTopics.add(m.group(1));
//                Log.i("message",m.group(1));
            }

            //For next website

            /************************/
            // ML CODE To identify the titles GOES HERE //
            /**********************/

        } catch (InterruptedException e) {
            Log.i("memem","askldfj");
            e.printStackTrace();
        } catch (ExecutionException e) {
            Log.i("asldf","memem");
            e.printStackTrace();
        }
    }

    public static void yearlyReview(){
        String newStartMonth;
        //For https://www.customs.gov.hk/
        for(int i=month; i>=1; i--) {
            newStartMonth = Integer.toString(i);
            Log.i("MONTH PRESENTLY",newStartMonth);
            DownloadTask task = new DownloadTask();

            url = "https://www.customs.gov.hk/en/publication_press/press/index_year_" + startYear + "-month_" + newStartMonth + ".html";
            try {
                allArticles = task.execute(url).get();
                String[] splitResult = allArticles.split("<!-- CONTENT END -->");
                String processedText = splitResult[0];
                splitResult = processedText.split("<!-- CONTENT START -->");
                Pattern p = Pattern.compile("<td valign=\"top\"><a href=\"(.*?)\" title=\"");
                Matcher m = p.matcher(splitResult[1]);
                while (m.find()) {
                    MainActivity.ListOfTopicsUrl.add("https://www.customs.gov.hk" + m.group(1));
//                Log.i("message","https://www.customs.gov.hk"+m.group(1));
                }
                p = Pattern.compile("title=\"Click here to(.*?)\">");
                m = p.matcher(splitResult[1]);
                while (m.find()) {
                    MainActivity.ListOfTopics.add(m.group(1));
//                Log.i("message",m.group(1));
                }

                //For next website

                /************************/
                // ML CODE To identify the titles GOES HERE //
                /**********************/

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

    }
}
