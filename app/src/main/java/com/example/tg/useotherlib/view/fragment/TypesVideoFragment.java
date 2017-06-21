package com.example.tg.useotherlib.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.example.tg.useotherlib.bean.VideoRes;
import com.example.tg.useotherlib.bean.VideoTypeInfo;
import com.example.tg.useotherlib.view.activtiy.BaseActivity;
import com.example.tg.useotherlib.view.activtiy.VideoInfoActivity;
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
 * Created by tg on 2017/6/21.
 */

public class TypesVideoFragment extends BaseArticleFragment<VideoTypeInfo> {

    String HOST = "http://api.svipmovie.com/front/";
    String GET = "columns/getVideoList.do?catalogId=#&pnum=#";
    String typeId;

    public static final String TYPE_ID = "TYPE_ID";

    public static TypesVideoFragment newInstance(@NonNull String type) {
        Bundle args = new Bundle();
        args.putString(TypesVideoFragment.TYPE_ID, type);
        TypesVideoFragment fragment = new TypesVideoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (getArguments() != null)
            typeId = getArguments().getString(TYPE_ID);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getViewLayoutID() {
        return R.layout.fragment_photo;
    }

    @Override
    protected void initViews() {
        super.initViews();
    }

    @Override
    protected void myconvert(ViewHolder holder, VideoTypeInfo item, int position) {
        TextView text = (TextView) holder.getView(R.id.tv_title);
        text.setText(item.getTitle());
        ImageView image = holder.getView(R.id.img_video);
        Glide.with(TypesVideoFragment.this)
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
//        Logger.d(data);
        List<VideoTypeInfo> tempList = new ArrayList<VideoTypeInfo>();
        try {
            JSONObject jsonObject = new JSONObject(data);
            String msg = jsonObject.getString("msg");
            if (!TextUtils.isEmpty(msg) && msg.equals("成功")) {
                Gson gson = new Gson();
                JSONObject retObj = jsonObject.getJSONObject("ret");
                JSONArray resultJsonArray = retObj.getJSONArray("list");
                JsonArray array = new JsonParser().parse(resultJsonArray.toString()).getAsJsonArray();
                for (final JsonElement elem : array) {
                    VideoTypeInfo video = gson.fromJson(elem, VideoTypeInfo.class);
                  //  if (video.getLoadType().equals("video"))
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

        Intent intent = new Intent(getActivity(), VideoInfoActivity.class);
        intent.putExtra("videoInfo", dataList.get(position));
        intent.putExtra(BaseActivity.TITLE,dataList.get(position).getTitle());
        startActivity(intent);
    }

    @Override
    protected RecyclerView.LayoutManager getLayouManager() {
        return new GridLayoutManager(getActivity(), 3);
    }

    @Override
    protected String getLoadURL() {
        Logger.i(HOST + getCurrentGet());
        return HOST + getCurrentGet();
    }

    String getCurrentGet() {
        String get = GET.replaceFirst("#", typeId).replaceFirst("#", String.valueOf(currentPage + 1));
        return get;
    }


}
