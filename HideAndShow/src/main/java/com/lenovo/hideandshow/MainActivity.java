package com.lenovo.hideandshow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    public void show(View view)
    {
        textView.setVisibility(View.VISIBLE);

    }
    public void hide(View view)

    {
        textView.setVisibility(View.INVISIBLE);
    }
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.textView);

    }
}
