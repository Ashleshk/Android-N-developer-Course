package com.lenovo.demoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    public void clickFunction(View view)
    {
        Toast.makeText(MainActivity.this,"hi there !",Toast.LENGTH_SHORT).show();

        EditText myTextField = (EditText) findViewById(R.id.UserName);
        EditText myPassword = (EditText) findViewById(R.id.Password);


        Toast.makeText(MainActivity.this,"Username : "+myTextField.getText().toString()+ " Password: "+myPassword.getText().toString() ,Toast.LENGTH_SHORT).show();
        //Log.i("Info","Button Pressed!");
        Log.i("Info","Username : "+myTextField.getText().toString()+ " Password: "+myPassword.getText().toString());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
