package com.example.tg.useotherlib.net.api;


import com.example.tg.useotherlib.bean.PhotoBean;
import com.example.tg.useotherlib.net.entity.HttpResult;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by codeest on 16/8/19.
 */

public interface GankApis {

    String HOST = "http://gank.io/api/";

    /**
     * 福利列表
     */
    @GET("data/福利/{num}/{page}")
    Observable<HttpResult<List<PhotoBean>>> getGirlList(@Path("num") int num, @Path("page") int page);

}
