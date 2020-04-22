package com.lenovo.timer;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new CountDownTimer(10000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                Log.i("Seconds left",String.valueOf(millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {
                Log.i("done","Countdowntimer finished");
            }


        }.start();
        /*final Handler handler = new Handler();
        Runnable run = new Runnable() {
            @Override
            public void run() {
                Log.i("Runnable has run!!","A sec passed");
                handler.postDelayed(this,1000);
            }
        };

        handler.post(run);*/
    }
}
