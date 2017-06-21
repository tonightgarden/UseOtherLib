package com.example.tg.useotherlib.view.fragment;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tg.useotherlib.R;
import com.example.tg.useotherlib.bean.GanHuo;
import  com.example.tg.useotherlib.bean.VideoRes;
import com.example.tg.useotherlib.view.activtiy.AboutActivity;
import com.example.tg.useotherlib.view.activtiy.BaseActivity;
import com.example.tg.useotherlib.view.activtiy.TypeVideosActivity;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.orhanobut.logger.Logger;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tg on 2017/6/20.
 */

public class VideoPageFragment extends BaseArticleFragment <VideoRes> {

    String HOST = "http://api.svipmovie.com/front/";
    String GET = "homePageApi/homePage.do";


    @Override
    protected int getViewLayoutID() {
        return R.layout.fragment_photo;
    }

    @Override
    protected void initViews() {
        isCanLoadMore = false;
        super.initViews();
    }

    @Override
    protected void myconvert(ViewHolder holder, VideoRes item, int position) {

        TextView text = (TextView)holder.getView(R.id.tv_title);
        text.setText(item.getTitle());

        DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
        int width = dm.widthPixels / 2;//宽度为屏幕宽度一半
//        int height = data.getHeight()*width/data.getWidth();//计算View的高度
        ImageView image = holder.getView(R.id.img_video);
        ViewGroup.LayoutParams params = image.getLayoutParams();
        params.height = (int) (width / 1.8);
//        image.setLayoutParams(params);
        if( item.getChildList().size()>=1)
        {
            Glide.with(VideoPageFragment.this)
                    .load(item.getChildList().get(0).getPic())
                    .placeholder(R.drawable.default_320)
                    .into(image);
        }
    }

    @Override
    protected int getLayoutId() {
         return R.layout.item_video;
    }

    @Override
    protected List<VideoRes> secondResloveData(String data) {
        List<VideoRes> tempList = new ArrayList<VideoRes>() ;
        try {
            JSONObject jsonObject=  new JSONObject(data);
            String msg = jsonObject.getString("msg");
            if(!TextUtils.isEmpty(msg)&&msg.equals("成功"))
            {
                Gson gson = new Gson();
               JSONObject retObj =  jsonObject.getJSONObject("ret");
                JSONArray resultJsonArray = retObj.getJSONArray("list");
                JsonArray array = new JsonParser().parse(resultJsonArray.toString()).getAsJsonArray();
                for(final JsonElement elem : array){
                    VideoRes video = gson.fromJson(elem, VideoRes.class);
                    if(video.getShowType().equals("IN"))
                    tempList.add(video);
                }
            }
            Logger.d(tempList.size());
            Logger.d(tempList);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tempList;
    }

    @Override
    protected void onMyItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        VideoRes videoRes = (VideoRes) dataList.get(position);
        startActivity(new Intent(getActivity(), TypeVideosActivity.class).putExtra(BaseActivity.TITLE,videoRes.getTitle())
        .putExtra(TypeVideosActivity.TYPE_ID,TypeVideosActivity.getCatalogId(videoRes.getMoreURL()))
        );
        Logger.i(TypeVideosActivity.getCatalogId(videoRes.getMoreURL()));

    }

    @Override
    protected RecyclerView.LayoutManager getLayouManager() {
        return new GridLayoutManager(getActivity(), 2);
    }

    @Override
    protected String getLoadURL() {
        return HOST+GET;
    }
}
