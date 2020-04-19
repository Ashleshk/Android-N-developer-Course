package com.lenovo.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    BluetoothAdapter BA;
    public void bluetoothOff(View view)
    {
        BA.disable();
        if (BA.isEnabled())
        {
            Toast.makeText(getApplicationContext(),"BLuetooth coul not be disabled",Toast.LENGTH_SHORT).show();

        }
        else
        {
            Toast.makeText(getApplicationContext(),"BLuetooth is off",Toast.LENGTH_SHORT).show();

        }
    }
    public void Finddiscoverabledevices(View view)
    {
        Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivity(i);
    }

    public  void ViewpairedDevies(View view)
    {
        Set<BluetoothDevice> paireddevices = BA.getBondedDevices();
        ListView paireddevicesListview = (ListView) findViewById(R.id.paireddevicesListview);
        ArrayList paireddevicesArraylist = new ArrayList();
        for(BluetoothDevice bluetoothDevice:paireddevices)
        {
            paireddevicesArraylist.add(bluetoothDevice.getName());

        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,paireddevicesArraylist);
        paireddevicesListview.setAdapter(arrayAdapter);
    }
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(BA.isEnabled())
        {
            Toast.makeText(getApplicationContext(),"Bluetooth is on",Toast.LENGTH_LONG).show();
        }
        else {
            Intent i =new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(i);
            if(BA.isEnabled())
            {
                Toast.makeText(getApplicationContext(),"Bluetooth is turned on",Toast.LENGTH_LONG).show();
            }

        }
    }

}
