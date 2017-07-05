package com.example.tg.useotherlib.net;


import android.provider.SyncStateContract;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.example.tg.useotherlib.BuildConfig;
import com.example.tg.useotherlib.app.MyApplication;
import com.example.tg.useotherlib.net.api.GankApis;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Description: RetrofitHelper1
 * Creator: yxc
 * date: 2016/9/21 10:03
 */
public class RetrofitHelper {

    public static final String PATH_DATA = MyApplication.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";
    public static final String PATH_CACHE = PATH_DATA + File.separator + "NetCache";

    private static OkHttpClient okHttpClient = null;
    private static GankApis gankApis;

    public static GankApis getGankApis() {
        initOkHttp();
        if (gankApis == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(GankApis.HOST)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            gankApis = retrofit.create(GankApis.class);
        }
        return gankApis;
    }
    private static void initOkHttp() {
        if (okHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
                builder.addInterceptor(loggingInterceptor);
            }
            File cacheFile = new File(PATH_CACHE);
            Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
            Interceptor cacheInterceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    if (NetworkUtils.isConnected()) {
                        request = request.newBuilder()
                                .cacheControl(CacheControl.FORCE_CACHE)
                                .build();
                    }
                    int tryCount = 0;
                    Response response = chain.proceed(request);
                    while (!response.isSuccessful() && tryCount < 3) {
                        Logger.d("interceptRequest is not successful - :{}"+response.code());

                        tryCount++;

                        // retry the request
                        response = chain.proceed(request);
                    }


                    if (NetworkUtils.isConnected()) {
                        int maxAge = 0;
                        // 有网络时, 不缓存, 最大保存时长为0
                        response.newBuilder()
                                .header("Cache-Control", "public, max-age=" + maxAge)
                                .removeHeader("Pragma")
                                .build();
                    } else {
                        // 无网络时，设置超时为4周
                        int maxStale = 60 * 60 * 24 * 28;
                        response.newBuilder()
                                .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                                .removeHeader("Pragma")
                                .build();
                    }
                    return response;
                }
            };
            //设置缓存
//            builder.addNetworkInterceptor(cacheInterceptor);
//            builder.addInterceptor(cacheInterceptor);
//            builder.cache(cache);
            //设置超时
            builder.connectTimeout(10, TimeUnit.SECONDS);
            builder.readTimeout(20, TimeUnit.SECONDS);
            builder.writeTimeout(20, TimeUnit.SECONDS);
            //错误重连
            builder.retryOnConnectionFailure(true);
            okHttpClient = builder.build();
        }
    }
}
