package com.example.myclock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TextView clockTextView ;
    private static Handler mHandler ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Calendar cal = Calendar.getInstance();

                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                String strTime = sdf.format(cal.getTime());

                clockTextView = findViewById(R.id.clock);
                clockTextView.setText(strTime);
            }
        };

        class newRunnable implements Runnable {

            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace() ;
                    }
                    mHandler.sendEmptyMessage(0) ;
                }
            }
        }

        newRunnable nu = new newRunnable();
        Thread t = new Thread(nu) ;
        t.start() ;


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}