package com.lenovo.braintrainerapp;

import android.content.Intent;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startbutton,playagainbutton;
    Button button0,button1,button2,button3;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    TextView sumTextView;
    TextView resulttextView,finalresultTextview;
    TextView pointsTextview;
    TextView timeTextView;
    RelativeLayout gamerelativeLayout,playagainlayout;
    int score=0;
    int numberofquestion=0;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    public void start(View view) {
        startbutton.setVisibility(View.INVISIBLE);
        gamerelativeLayout.setVisibility(View.VISIBLE);
        playagainlayout.setVisibility(View.INVISIBLE);
        playagain(findViewById(R.id.playagainbutton));
    }
    public void playagain(View view)
    {
        score=0;
        numberofquestion=0;

        timeTextView.setText("30s");
        pointsTextview.setText("0/0");
        resulttextView.setText("");
        playagainbutton.setVisibility(View.INVISIBLE);
        gamerelativeLayout.setVisibility(View.VISIBLE);
        playagainlayout.setVisibility(View.INVISIBLE);
        generateQuestion();
        new CountDownTimer(30100,1000)
        {

            @Override
            public void onTick(long millisUntilFinished) {
                timeTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                playagainbutton.setVisibility(View.VISIBLE);
                timeTextView.setText("0s");
                gamerelativeLayout.setVisibility(View.INVISIBLE);
                playagainlayout.setVisibility(View.VISIBLE);
                finalresultTextview.setText("Your Score :"+Integer.toString(score)+"/"+Integer.toString(numberofquestion));
            }
        }.start();
    }
    public void generateQuestion()
    {
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);
        answers.clear();
        int incorrectAnswer;
        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(a + b);

            } else {
                incorrectAnswer = rand.nextInt(41);
                while (incorrectAnswer == a + b) {
                    incorrectAnswer = rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));


    }

    public void chooseAnswer(View view )
    {
        Log.i("tag",(String) view.getTag());
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer)))
        {
            Log.i("Correct answer","Selected");
            score++;
            resulttextView.setText("Correct");
        }
        else
        {
            resulttextView.setText("Wrong");
        }
        numberofquestion++;
        pointsTextview.setText(Integer.toString(score)+"/"+Integer.toString(numberofquestion));
        generateQuestion();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startbutton = (Button) findViewById(R.id.startbutton);
        sumTextView = (TextView) findViewById(R.id.sumTextView);
        button0=(Button)findViewById(R.id.button0);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
        resulttextView = (TextView)findViewById(R.id.resulttextView);
        pointsTextview = (TextView)findViewById(R.id.pointsTextView);
        timeTextView = (TextView)findViewById(R.id.timerTextView);
        playagainbutton=(Button)findViewById(R.id.playagainbutton);
        gamerelativeLayout =(RelativeLayout)findViewById(R.id.gameRelativeLayout);
        playagainlayout=(RelativeLayout)findViewById(R.id.playagainLayout);
        finalresultTextview=(TextView)findViewById(R.id.finalresulttextView);
        //generateQuestion();


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
