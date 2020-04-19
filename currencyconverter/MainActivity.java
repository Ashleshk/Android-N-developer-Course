package com.lenovo.currencyconverter;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void Convert(View view)
    {

        EditText dollarAmount = (EditText)findViewById(R.id.dollarAmountEditText);
        Log.i("Info:","dollar amount is : "+dollarAmount.getText().toString());
        Double dollar = Double.parseDouble(dollarAmount.getText().toString());
        Double poundAmount = dollar*0.75;
        Double rupeeAmount = dollar*69.54;
        Toast.makeText(MainActivity.this, "£ "+String.format("%.2f",poundAmount), Toast.LENGTH_SHORT).show();
        Toast.makeText(MainActivity.this, "₹ "+String.format("%.2f",rupeeAmount), Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
