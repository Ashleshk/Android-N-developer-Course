package com.example.lenovo.radiobutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class radiobutton extends Activity {

    private RadioGroup radiogroup;
    private RadioButton radiobutton;
    private Button btnDisplay;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radiobutton);

        addListenerOnButton();

    }

    public void addListenerOnButton() {

        radiogroup = (RadioGroup) findViewById(R.id.radioquestion);
        btnDisplay = (Button) findViewById(R.id.btnDisplay);

        btnDisplay.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedId = radiogroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radiobutton = (RadioButton) findViewById(selectedId);

                Toast.makeText(radiobutton.this,
                        radiobutton.getText(), Toast.LENGTH_SHORT).show();

            }

        });

    }
}