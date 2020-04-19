package com.lenovo.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public void dropIn(View view)
    {
        ImageView Counter = (ImageView) view;
        Counter.setTranslationY(-1000f);
        Counter.setImageResource();
        Counter.animate().translationYBy(1000f).rotation(3600f).setDuration(300);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
