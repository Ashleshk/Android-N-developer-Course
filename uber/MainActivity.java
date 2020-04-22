package com.lenovo.uber;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class MainActivity extends AppCompatActivity {
/* appId: "4dedb0f3872a4543e16f8624cf8ac5fb4789f925",
    masterKey: "948bbcfceec6438c259633bfdba7411d0746f3ef",*/

    public void redirectActivity() {

        if (ParseUser.getCurrentUser().getString("riderOrDriver").equals("rider")) {

            Intent intent = new Intent(getApplicationContext(), RiderActivity.class);
            startActivity(intent);

        } else {

            Intent intent = new Intent(getApplicationContext(), ViewRequestActivity.class);
            startActivity(intent);


        }
    }
    public void getStarted(View view) {

        Switch userTypeSwitch = (Switch) findViewById(R.id.userTypeSwitch);

        Log.i("Switch value", String.valueOf(userTypeSwitch.isChecked()));

        String userType = "rider";

        if (userTypeSwitch.isChecked()) {

            userType = "driver";

        }

        ParseUser.getCurrentUser().put("riderOrDriver", userType);
        Log.i("INFO","REdirecting as "+userType);
        ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {

                redirectActivity();

            }
        });




    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
       // Parse.enableLocalDatastore(this);

        // Add your initialization code here
        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("4dedb0f3872a4543e16f8624cf8ac5fb4789f925")
                .clientKey("948bbcfceec6438c259633bfdba7411d0746f3ef")
                .server("http://52.14.175.235:80/parse/")
                .build()
        );

        if (ParseUser.getCurrentUser() == null) {

            ParseAnonymousUtils.logIn(new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {

                    if (e == null) {

                        Log.i("Info", "Anonymous login successful");

                    } else {

                        Log.i("Info", "Anonymous login failed");

                    }


                }
            });

        } else {

            if (ParseUser.getCurrentUser().get("riderOrDriver") != null) {

                Log.i("Info", "Redirecting as " + ParseUser.getCurrentUser().get("riderOrDriver"));

                redirectActivity();

            }


        }
        ParseAnalytics.trackAppOpenedInBackground(getIntent());

    }
}
