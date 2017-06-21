package com.example.tg.useotherlib.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tg.useotherlib.R;
import com.example.tg.useotherlib.bean.VideoInfoTotal;
import com.example.tg.useotherlib.bean.VideoTypeInfo;
import com.example.tg.useotherlib.utils.StringUtils;
import com.example.tg.useotherlib.view.activtiy.BaseActivity;
import com.example.tg.useotherlib.view.activtiy.VideoInfoActivity;
import com.example.tg.useotherlib.widget.TextViewExpandableAnimation;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.orhanobut.logger.Logger;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by tg on 2017/6/21.
 */

public class AbstractFragment extends BaseArticleFragment <VideoTypeInfo>{

    String HOST = "http://api.svipmovie.com/front/";
    String GET = "videoDetailApi/videoDetail.do?mediaId=";

    public static final String MEDIA_ID = "MEDIA_ID";

    String mediaId ;
    VideoInfoTotal infoTotal;
    View headerView;
    TextViewExpandableAnimation tvExpand;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            mediaId = getArguments().getString(MEDIA_ID);
    }

    @Override
    protected void initViews() {
        super.initViews();
        isCanLoadMore = false;
        headerView = LayoutInflater.from(getContext()).inflate(R.layout.head_view, null);
        tvExpand = ButterKnife.findById(headerView, R.id.tv_expand);
        mHeaderAndFooterWrapper.addHeaderView(headerView);
    }

    @Override
    protected int getViewLayoutID() {
        return R.layout.fragment_abstract;
    }

    public static AbstractFragment newInstance(@NonNull String media_id) {
        Bundle args = new Bundle();
        args.putString(MEDIA_ID, media_id);
        AbstractFragment fragment = new AbstractFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void myconvert(ViewHolder holder, VideoTypeInfo item, int position) {
        TextView text = (TextView) holder.getView(R.id.tv_title);
        text.setText(item.getTitle());
        ImageView image = holder.getView(R.id.img_video);
        Glide.with(AbstractFragment.this)
                .load(item.getPic())
                .placeholder(R.drawable.default_320)
                .into(image);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_videoinfo;
    }

    @Override
    protected List<VideoTypeInfo> secondResloveData(String data) {
        Logger.i(data);
        List<VideoTypeInfo> tempList = new ArrayList<VideoTypeInfo>();

        try {
            JSONObject jsonObject = new JSONObject(data);
            String msg = jsonObject.getString("msg");
            if (!TextUtils.isEmpty(msg) && msg.equals("成功")) {
                Gson gson = new Gson();
                JSONObject retObj = jsonObject.getJSONObject("ret");

                 infoTotal = gson.fromJson(retObj.toString(), VideoInfoTotal.class);
                if(infoTotal!=null)
                {
                    if(infoTotal.list.size()>0)
                    {
                        tempList.addAll(infoTotal.list.get(0).getChildList());
                    }
                }
            }
            Logger.d(tempList.size());
            Logger.d(tempList);
            if(tempList.size()>0)
            {
                mSwipeRefreshLayout.setEnabled(false);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tempList;
    }

    @Override
    protected void loadComplete() {
        super.loadComplete();
        if(infoTotal!=null)
        {
            EventBus.getDefault().post(infoTotal.getVideoUrl());
            String dir = "导演：" + StringUtils.removeOtherCode(infoTotal.director);
            String act = "主演：" + StringUtils.removeOtherCode(infoTotal.actors);
            String des = dir + "\n" + act + "\n" + "简介：" + StringUtils.removeOtherCode(infoTotal.description);
            tvExpand.setText(des);
        }

    }

    @Override
    protected void onMyItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        Intent intent = new Intent(getActivity(), VideoInfoActivity.class);
        intent.putExtra("videoInfo", dataList.get(position));
        intent.putExtra(BaseActivity.TITLE,dataList.get(position).getTitle());
        startActivity(intent);
        getActivity().finish();

    }

    @Override
    protected RecyclerView.LayoutManager getLayouManager() {
        return new GridLayoutManager(getActivity(), 3);
    }

    @Override
    protected String getLoadURL() {
        return HOST+GET+mediaId;
    }
}
