package com.lenovo.animations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public void fade(View view)
    {
        ImageView cat = (ImageView) findViewById(R.id.cat);
        //ImageView cat2 = (ImageView)findViewById(R.id.cat2);
        //cat.animate().alpha(0f).setDuration(2000);
        //cat2.animate().alpha(1f).setDuration(2000);
        //cat.animate().translationXBy(1000f).setDuration(2000);
        cat.animate().rotation(180f).setDuration(2000);
        cat.animate().scaleX(0.5f).scaleY(0.5f).setDuration(2000);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    /*
        ImageView cat = (ImageView) findViewById(R.id.cat);

        cat.setTranslationX(-1000f);*/
    }
}
