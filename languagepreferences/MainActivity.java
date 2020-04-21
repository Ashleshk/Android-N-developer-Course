package com.lenovo.languagepreferences;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    TextView textView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

         super.onOptionsItemSelected(item);
        switch(item.getItemId())
        {
            case R.id.english :
               setLanguage("English");
                return  true;
            case R.id.spanish:
                setLanguage("Spanish");
                return true;
            default:
                return false;
        }

    }

    public void setLanguage(String language)
    {
        sharedPreferences.edit().putString("language",language).apply();

        textView.setText(language);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = this.getSharedPreferences("com.lenovo.languagepreferences", Context.MODE_PRIVATE);
        textView =(TextView)findViewById(R.id.textView);
        String language = sharedPreferences.getString("language","");
        if(language=="") {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_btn_speak_now)
                    .setTitle("Choose a language")
                    .setMessage("which language would you like!")
                    .setPositiveButton("English", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Toast.makeText(MainActivity.this,"It's Done",Toast.LENGTH_SHORT).show();
                            setLanguage("English");
                        }
                    })
                    .setNegativeButton("Spanish", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Toast.makeText(MainActivity.this,"It's Done",Toast.LENGTH_SHORT).show();
                            setLanguage("spanish");
                        }
                    })
                    .show();
        }else {
            textView.setText(language);
        }
    }
}
