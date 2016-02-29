package com.example.mukesh.usbaccessory;

import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbAccessory;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    private static final String ACTION_USB_PERMISSION =
            "com.android.example.USB_PERMISSION";
    String Message ;
    TextView mText;
    UsbManager mUsbManager ;
    UsbAccessory mAccessory;
    ParcelFileDescriptor mFileDescriptor;                      // for reading/writing
    FileInputStream mInputStream;
    FileOutputStream mOutputStream;
    String msg = "Debuging :";

    /*
    Runnable Readmessage = new Runnable() {
        @Override
        public void run() {                              // for reading message from Host..

            byte[] buffer = new byte[5];
            int ret;

            try {
                Message = ">>> ";

                ret = mInputStream.read(buffer);
                if (ret == 5) {
                    String msg = new String(buffer);
                    Message += msg;
                } else {
                    Message += "Read error";
                }

            } catch (IOException e) {
                e.printStackTrace();
                Message += "Read error";
            }

            Message += System.getProperty("line.separator");
            mText.append(Message);                               // showing data received from user  ..
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            new Thread(this).start();
        }
    };


     // making broadcast receiver ...
    private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d(msg, "doing " + action);
            if (ACTION_USB_PERMISSION.equals(action)) {

                synchronized (this) {
                    mAccessory = (UsbAccessory) intent.getParcelableExtra(UsbManager.EXTRA_ACCESSORY);
                    if (mAccessory != null) {

                         mText.append("Connected to Host!!");
                    }
                    else
                    {
                        mText.append("You opened directly");
                        return ;
                    }





                    try {
                        mFileDescriptor = (mUsbManager.openAccessory(mAccessory));
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                    if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, true)) {
                        if(mAccessory != null){
                            //call method to set up accessory communication

                            mFileDescriptor = mUsbManager.openAccessory(mAccessory);
                            if (mFileDescriptor != null) {
                                FileDescriptor fd = mFileDescriptor.getFileDescriptor();
                                mInputStream = new FileInputStream(fd);
                                mOutputStream = new FileOutputStream(fd);
                                new Thread(Readmessage).start();
                            }
                            else
                            {
                                Log.d(msg, "eror:mfilediscriptor");
                            }
                            mText.append("success");
                            Log.d(msg,"success");
                        }
                        else{
                            Log.d(msg,"failure");
                        }
                    }
                    else {
                        Log.d(msg, "permission denied for accessory " + mAccessory);
                    }

                }

            }
        }
    };
*/

    // private int accessory_filter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            mText = (TextView) findViewById(R.id.textView);
            mText.setMovementMethod(new ScrollingMovementMethod());
            Intent intent = getIntent();
            mUsbManager = (UsbManager) getSystemService(Context.USB_SERVICE);
            mAccessory = intent.getParcelableExtra(UsbManager.EXTRA_ACCESSORY);

            if (mAccessory != null) {
                mText.append("Device in accessory mode\n Connected to Host!");

            } else
                mText.append("Directly opened by u");
        /*
             PendingIntent mPermissionIntent = PendingIntent.getBroadcast(this, 0, new Intent(ACTION_USB_PERMISSION), 0);
             IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
             registerReceiver(mUsbReceiver, filter);
             mUsbManager.requestPermission(mAccessory, mPermissionIntent);
         */
        }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(msg, "The onStart() event");
    }
    @Override
    protected void onResume() {
        super.onResume();

        Log.d(msg, "The onResume() event");
    }


    @Override
    protected void onPause(){
        super.onPause();


        Log.d(msg, "The onPause() event");
    }
    /** Called when another activity is taking focus. */

    /** Called when the activity is no longer visible. */
    @Override
    protected void onStop() {

        super.onStop();

     /*
        try {
            mFileDescriptor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
     */
        Log.d(msg, "The onStop() event");
    }

    /** Called just before the activity is destroyed. */

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(msg, "The onDestroy() event");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
