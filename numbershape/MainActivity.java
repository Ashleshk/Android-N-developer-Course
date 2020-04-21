package com.lenovo.numbershape;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    class Number{
        int number;
        public boolean isSquare()
        {
            double squareroot = Math.sqrt(number);
            if(squareroot == Math.floor(squareroot)){
                return true;
            }
            else
            {
                return false;
            }
        }
        public boolean isTriangular() {
            int x = 1;
            int triangular = 1;
            while (triangular < number) {
                x++;
                triangular = triangular + x;

            }
            if (triangular == number)
            {
                return  true;

            }else {
                return false;
            }
        }
    }
    public void testnumber(View view)
    {
        EditText usernumber = (EditText)findViewById(R.id.userNumber);
        String message ="";
        if(usernumber.getText().toString().isEmpty())
        {
            message = "Please enter number Hoss!!";
        }
        else {
            Log.i("Usernumber", usernumber.getText().toString());
            Number myNumber = new Number();
            myNumber.number = Integer.parseInt(usernumber.getText().toString());
            System.out.println(myNumber.isSquare());
            System.out.println(myNumber.isTriangular());

            if (myNumber.isSquare()) {
                if (myNumber.isTriangular()) {
                    message = myNumber.number + " is both triangular and square !!";
                } else {
                    message = myNumber.number + " is square number !!";
                }
            } else {
                if (myNumber.isTriangular()) {
                    message = myNumber.number + " is triangular and not  square !!";
                } else {
                    message = myNumber.number + " neither square and triangular !!";
                }
            }
        }
        Toast.makeText(MainActivity.this,message,Toast.LENGTH_SHORT).show();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
