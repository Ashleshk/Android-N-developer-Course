package com.lenovo.toastdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void sayHi(View view )
    {
        EditText name= (EditText) findViewById(R.id.NameId);
        ImageView image = (ImageView) findViewById(R.id.image);

        Log.i("test","clicked");
        Toast.makeText(MainActivity.this,"Hit there, "+name.getText().toString(),Toast.LENGTH_SHORT).show();
        image.setImageResource(R.drawable.cat2);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
