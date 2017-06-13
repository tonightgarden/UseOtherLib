package com.example.tg.useotherlib;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.orhanobut.logger.Logger;

import hugo.weaving.DebugLog;
import hugo.weaving.internal.Hugo;
import timber.log.Timber;

@DebugLog
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testHugo("hello");
        testHugo2(100);
        testHugo3(savedInstanceState);
        Timber.d("Activity Created");
        Timber.tag("LifeCycles");
        Timber.d("Activity Created");
        Timber.d("Activity Created2");

        Logger.d("hello");
        Logger.d("debug");
        Logger.e("error");
        Logger.w("warning");
        Logger.v("verbose");
        Logger.i("information");
        Logger.wtf("wtf!!!!");

    }

    @DebugLog
    String testHugo(String str)
    {
        Timber.d(str);
        SystemClock.sleep(15);
        return str;
    }

    @DebugLog
    int testHugo2(int num)
    {
        return num+1;
    }

    @DebugLog
    void testHugo3(Bundle savedInstanceState)
    {

    }



}
