package com.lenovo.parseserverstarterproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        // Add your initialization code here
        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("aa84cd91fd1c6e4f42d0e8cb27bd479dc9f13c1d")
                .clientKey("0a162ef04c19311d5a900f9ea20b8082b7974274")
                .server("http://18.224.23.225:80/parse/")
                .build()
        );
        /*
        ParseObject object = new ParseObject("ExampleObject");
        object.put("myNumber", "123");
        object.put("myString", "rob");
        ParseObject score = new ParseObject("Score");
        score.put("username","rob");
        score.put("score",86);
        score.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e ==null){
                    Log.i("SAveIN BACKGROUND","Successfull");
                }else {
                    Log.i("SAveIN BACKGROUND","failed"+e.toString());

                }
            }
        });
*/
        /*
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");
        query.getInBackground("lbiFLOlC7s", new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e==null&& object !=null)
                {
                    object.put("score",200);
                    object.saveInBackground();
                    Log.i("Object value",object.getString("username"));
                    Log.i("score",Integer.toString(object.getInt("score")));
                }
            }

        });
        */

        ParseObject tweet = new ParseObject("tweet");
        tweet.put("username","Ashlesh");
        tweet.put("tweet","Hey there!!");

        tweet.saveEventually(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e ==null){
                    Log.i("SAVEIN BACKGROUND","Successfull");
                }else {
                    Log.i("SAveIN BACKGROUND","failed"+e.toString());

                }
            }
        });
        ParseQuery<ParseObject> query = ParseQuery.getQuery("tweet");
        query.getInBackground("9CEMOHTJrn", new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e==null&& object !=null)
                {
                    object.put("tweet","chamak chaaloo");
                    object.saveInBackground();
                    Log.i("Object value",object.getString("username"));
                    Log.i("score",object.getString("tweet"));
                }
            }

        });


        /*ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");
        query.whereEqualTo("username","ashlesh");
        query.setLimit(1);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null)
                {
                    Log.i("findInbackground",objects.size()+" objects");
                    if(objects.size()>0)
                    {
                        for(ParseObject object : objects)
                        {
                            Log.i("findinback result",object.getString("username"));
                        }
                    }
                }
            }
        });
*/
        /*      SIGN IN USER
        ParseUser user = new ParseUser();
        user.setUsername("ashlesh");
        user.setPassword("password");

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e==null)
                {
                    Log.i("Sign UP","Successful");
                }
                else
                {
                    Log.i("Sign UP","failed");

                }
            }
        });
*/
        // LOGIN USER
        /*ParseUser.logInInBackground("Ashlesh", "Passwor", new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(user !=null)
                {
                    Log.i("LOGIN","Succesful");

                }
                else

                {
                    Log.i("LOGIN","failed "+e.toString());
                }
            }
        });
        */
/*
        ParseUser.logOut();
        if(ParseUser.getCurrentUser() !=null)
        {
            Log.i("Current user","USer Logged in "+ ParseUser.getCurrentUser().getUsername());
        }
        else
        {
            Log.i("Current user ","user not logged in");
        }*/
        ParseAnalytics.trackAppOpenedInBackground(getIntent());

    }
}
