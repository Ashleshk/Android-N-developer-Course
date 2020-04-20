package com.lenovo.higherorlower;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int no;
    public void maketoast(String string)
    {
        Toast.makeText(MainActivity.this,string,Toast.LENGTH_SHORT).show();
    }

    public void Guess(View view)
    {
        EditText guessEditTet = (EditText)findViewById(R.id.GuesseditText);
        int guess=Integer.parseInt(guessEditTet.getText().toString());
        if(guess>no)
        {
            maketoast("Lower");
        }
        else if(guess<no)
        {
            maketoast("Higher");
        }
        else
        {
            maketoast("That's right !! hoss");
            maketoast("Play again");
            Random rand = new Random();
            no =rand.nextInt(20)+1;
        }
        Toast.makeText(MainActivity.this, "input :"+guessEditTet.getText().toString(), Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Random rand = new Random();
         no =rand.nextInt(20)+1;
    }
}
