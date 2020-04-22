package com.lenovo.sharedperferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.icu.util.Freezable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences = this.getSharedPreferences("com.lenovo.sharedperferences", Context.MODE_PRIVATE);
        //sharedPreferences.edit().putString("username","ashlesh").apply();
        //String username= sharedPreferences.getString("username","");

        ArrayList<String> friends= new ArrayList<String>();
        friends.add("Monica");
        friends.add("Chandler");
        friends.add("Rachel");
        friends.add("Ross");
        friends.add("Joey");
        friends.add("Pheobe");
        try {
            sharedPreferences.edit().putString("friends",ObjectSerializer.serialize(friends)).apply();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Log.i("Username",username);
        ArrayList<String> newfriends = new ArrayList<>();
        try {
            newfriends=(ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("friends",ObjectSerializer.serialize(new ArrayList<String>())));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.i("firends",newfriends.toString());
    }
}
