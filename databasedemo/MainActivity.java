package com.lenovo.databasedemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            SQLiteDatabase mydatabase= this.openOrCreateDatabase("Users",MODE_PRIVATE,null);

            mydatabase.execSQL("CREATE TABLE IF NOT EXISTS users(name VARCHAR,age INT(3))");
            mydatabase.execSQL("INSERT INTO users(name, age) VALUES ('Akshay',87)");
            mydatabase.execSQL("INSERT INTO users(name, age) VALUES ('saksham',2)");

            Cursor c = mydatabase.rawQuery("SELECT * FROM users",null);

            int nameIndex= c.getColumnIndex("name");
            int ageIndex = c.getColumnIndex("age");

            c.moveToFirst();
            while(c!=null){
                Log.i("name",c.getString(nameIndex));
                Log.i("age",Integer.toString(c.getInt(ageIndex)));
                c.moveToNext();
            }


        }catch (Exception e)
        {

        }
    }
}
