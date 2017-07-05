package com.example.tg.useotherlib.app;

import android.app.Application;
import android.util.Log;

import com.blankj.utilcode.util.CrashUtils;
import com.blankj.utilcode.util.Utils;
import com.example.tg.useotherlib.BuildConfig;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.squareup.leakcanary.LeakCanary;
import com.zhy.http.okhttp.OkHttpUtils;


import java.util.concurrent.TimeUnit;

import hugo.weaving.internal.Hugo;
import okhttp3.OkHttpClient;
import timber.log.Timber;

/**
 * Created by tg on 2017/6/9.
 */

public class MyApplication extends Application {


    private  static   MyApplication app;

    @Override
    public void onCreate() {
        super.onCreate();
        app =this;
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...

        if (BuildConfig.DEBUG) {

            Timber.plant(new Timber.DebugTree());
            Timber.d(" DEBUG");
        } else {
            Timber.plant(new CrashReportingTree());
        }


        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(0)         // (Optional) How many method line to show. Default 2
                .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
             //   .logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
                .tag("My custom tag")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);

        Utils.init(this);
        initCrash();

    }

    public static MyApplication getInstance()
    {
        return app;
    }

    private void initCrash() {
        CrashUtils.init();
    }

    private static class CrashReportingTree extends Timber.Tree {
        @Override protected void log(int priority, String tag, String message, Throwable t) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return;
            }

            FakeCrashLibrary.log(priority, tag, message);

            if (t != null) {
                if (priority == Log.ERROR) {
                    FakeCrashLibrary.logError(t);
                } else if (priority == Log.WARN) {
                    FakeCrashLibrary.logWarning(t);
                }
            }
        }
    }
}
