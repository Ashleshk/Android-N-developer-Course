package com.lenovo.basicphrases;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonTapped(View view)
    {
        int id = view.getId();
        String ourid="";

        ourid= view.getResources().getResourceEntryName(id);

        int resourceId = getResources().getIdentifier(ourid,"raw","com.lenovo.basicphrases");

        MediaPlayer mplayer = MediaPlayer.create(this,resourceId);
        mplayer.start();
        Log.i("button tapped", ourid);
    }
}
