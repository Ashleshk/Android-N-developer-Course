package com.lenovo.instagram_clone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {

    Boolean signUpModeActive = true;
    int i;
    TextView changeSignupModeTextView;
    EditText passwordEditText;

    public void showUserList() {

        Intent intent = new Intent(getApplicationContext(), UserListActivity.class);
        startActivity(intent);

    }
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

        if(i==KeyEvent.KEYCODE_ENTER && event.getAction()==KeyEvent.ACTION_DOWN)
        {
            Signup(v);
        }
        return false;
    }
    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.changeSignupModeTextView) {

            Button signupButton = (Button) findViewById(R.id.signupButton);

            if (signUpModeActive) {

                signUpModeActive = false;
                signupButton.setText("Login");
                changeSignupModeTextView.setText("Or, Signup");

            } else {

                signUpModeActive = true;
                signupButton.setText("Signup");
                changeSignupModeTextView.setText("Or, Login");

            }

        }else if(view.getId() ==R.id.backgroundRelativeLayout || view.getId() ==R.id.LogoImageView )
        {
            InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }

    }
    public void Signup(View view) {

        EditText usernameEditText = (EditText) findViewById(R.id.UsernameeditText);



        if (usernameEditText.getText().toString().matches("") || passwordEditText.getText().toString().matches("")) {

            Toast.makeText(this, "A username and password are required.", Toast.LENGTH_SHORT).show();

        } else {

            if (signUpModeActive) {

                ParseUser user = new ParseUser();

                user.setUsername(usernameEditText.getText().toString());
                user.setPassword(passwordEditText.getText().toString());

                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {

                            Log.i("Signup", "Successful");
                            showUserList();

                        } else {

                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            } else {

                ParseUser.logInInBackground(usernameEditText.getText().toString(), passwordEditText.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {

                        if (user != null) {

                            Log.i("Signup", "Login successful");
                            showUserList();
                        } else {

                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                        }


                    }
                });


            }
        }


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        changeSignupModeTextView = (TextView) findViewById(R.id.changeSignupModeTextView);
        changeSignupModeTextView.setOnClickListener(this);
        RelativeLayout backgroundRelativeLayout=(RelativeLayout)findViewById(R.id.backgroundRelativeLayout);
        ImageView logoImageView =(ImageView)findViewById(R.id.LogoImageView);
        backgroundRelativeLayout.setOnClickListener(this);
        logoImageView.setOnClickListener(this);
        passwordEditText = (EditText) findViewById(R.id.PasswordeditText);
        passwordEditText.setOnKeyListener(this);

        Parse.enableLocalDatastore(this);

        // Add your initialization code here
        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("aa84cd91fd1c6e4f42d0e8cb27bd479dc9f13c1d")
                .clientKey("0a162ef04c19311d5a900f9ea20b8082b7974274")
                .server("http://18.224.23.225:80/parse/")
                .build()
        );

        if (ParseUser.getCurrentUser() != null) {

            showUserList();

        }

        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }


}
