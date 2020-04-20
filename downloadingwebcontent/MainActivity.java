package com.lenovo.downloadingwebcontent;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    public class Downloadtask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... params) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try
            {
                url=new URL(params[0]);
                urlConnection= (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader=new InputStreamReader(in);

                int data = reader.read();
                while(data != -1)
                {
                    char current = (char )data;
                    result += current;
                    data=reader.read();
                }
                return result;
            }catch (Exception e)
            {
                e.printStackTrace();
                return "FAILED";
            }

            //Log.i("url:",params[0]);

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Downloadtask task = new Downloadtask();
        String result= null;
        try {
            result = task.execute("https://www.ecohosting.co.uk/").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Log.i("CONTENTS OF URL",result);
    }
}
