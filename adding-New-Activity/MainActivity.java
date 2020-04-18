package com.lenovo.addingnewactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    public void toSecondActivity(View view)
//    {
//        Intent intent = new Intent(getApplicationContext(),secondActivity.class);
//        intent.putExtra("username","Ashlesh");
//        startActivity(intent);
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listview = (ListView)findViewById(R.id.listview);
        final ArrayList<String> friends = new ArrayList<String>();
        friends.add("Ashleah");
        friends.add("sham ");
        friends.add("purva");
        friends.add("shayanayah");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,friends);
        listview.setAdapter(arrayAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),secondActivity.class);
                intent.putExtra("name",friends.get(position));
                startActivity(intent);
            }
        });
    }
}
