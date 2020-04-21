package com.lenovo.newsreader;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.net.HttpURLConnection;

public class MainActivity extends AppCompatActivity {
//https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty
    ArrayList<String> titles = new ArrayList<>();
    ArrayList <String> content = new ArrayList<>();
    ArrayAdapter arrayAdapter;

    SQLiteDatabase articlesDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView =(ListView)findViewById(R.id.listView);
        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,titles);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getApplicationContext(), ArticleActivity.class);
                intent.putExtra("content", content.get(i));

                startActivity(intent);
            }
        });

        articlesDb = this.openOrCreateDatabase("Articles", MODE_PRIVATE, null);

        articlesDb.execSQL("CREATE TABLE IF NOT EXISTS articles (id INTEGER PRIMARY KEY, articleId INTEGER, title VARCHAR, content VARCHAR)");

        updateListView();

        DownloadTask task = new DownloadTask();

        try{
            task.execute("https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty");

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void updateListView() {
        Cursor c = articlesDb.rawQuery("SELECT * FROM articles", null);

        int contentIndex = c.getColumnIndex("content");
        int titleIndex = c.getColumnIndex("title");

        if (c.moveToFirst()) {
            titles.clear();
            content.clear();

            do {
                titles.add(c.getString(titleIndex));
                content.add(c.getString(contentIndex));
            } while (c.moveToNext());

            arrayAdapter.notifyDataSetChanged();
        }
    }

    public class DownloadTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            String result = "";
            URL url,newURL ;
            HttpURLConnection urlConnection = null;
            try {
                url=new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(in));

                String data=reader.readLine();

                while(data!=null){

                    result+=data;
                    data=reader.readLine();
                }
                Log.i("URLCONTENT:",result);
                JSONArray jsonArray = new JSONArray(result);

                int numberOfItems =20;
//                if(jsonArray.length()<20)
//                {
//                    numberOfItems=jsonArray.length();
//                }

               articlesDb.execSQL("DELETE FROM articles");

                for(int i =0; i<numberOfItems;i++)
                {
                    //Log.i("JSon item:",jsonArray.getString(i));
                    //https://hacker-news.firebaseio.com/v0/item/8863.json?print=pretty

                    String articleId= jsonArray.getString(i);
                    url = new URL("https://hacker-news.firebaseio.com/v0/item/"+articleId+".json?print=pretty");

                    urlConnection = (HttpURLConnection)url.openConnection();
                    in=urlConnection.getInputStream();
                     reader = new BufferedReader(
                            new InputStreamReader(in));

                     data=reader.readLine();
                    String articleInfo = "";
                    while(data!=null){

                        articleInfo+=data;
                        data=reader.readLine();
                    }

                    Log.i("Article Info:",articleInfo);

                    JSONObject jsonObject = new JSONObject(articleInfo);
                    if(!jsonObject.isNull("title") && !jsonObject.isNull("url")) {
                        String articleTitle = jsonObject.getString("title");
                        String articleUrl=jsonObject.getString("url");
                        Log.i("info:",articleTitle+" "+articleUrl);
                        Log.i("url",articleUrl);
                        newURL = new URL(articleUrl);

                        urlConnection = (HttpURLConnection)newURL.openConnection();
                        in=urlConnection.getInputStream();
                         reader = new BufferedReader(
                                new InputStreamReader(in));

                        data=reader.readLine();
                        String articleContent = "";
                        while(data!=null){

                            articleContent+=data;
                            data=reader.readLine();
                        }

                        Log.i("articleContent",articleContent);
                        String sql = "INSERT INTO articles (articleId, title, content) VALUES (?, ?, ?)";

                        SQLiteStatement statement = articlesDb.compileStatement(sql);

                        statement.bindString(1, articleId);
                        statement.bindString(2, articleTitle);
                        statement.bindString(3, articleContent);

                        statement.execute();
                    }

                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            updateListView();
        }
    }
}
