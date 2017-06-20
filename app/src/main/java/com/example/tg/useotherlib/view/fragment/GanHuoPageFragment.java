package com.example.tg.useotherlib.view.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tg.useotherlib.R;
import com.example.tg.useotherlib.bean.Data;
import com.example.tg.useotherlib.bean.GanHuo;
import com.example.tg.useotherlib.test.TextFragment;
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
 * Created by tg on 2017/6/15.
 */

public class GanHuoPageFragment extends BaseArticleFragment<GanHuo> {

    private static  final  String TYPE = "DATA_TYPE";

    private  static final String URL = "http://gank.io/api/data/";
    private  static final String URL_NUMBER = "/10/";

    public static GanHuoPageFragment newInstance(@NonNull String type) {
        Bundle args = new Bundle();
        args.putString(TYPE, type);
        GanHuoPageFragment fragment = new GanHuoPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            type = getArguments().getString(TYPE);
    }

    @Override
    protected int getViewLayoutID() {
        return R.layout.fragment_photo;
    }

    @Override
    protected void myconvert(ViewHolder holder, GanHuo item, int position) {
        TextView text = (TextView)holder.getView(R.id.ganhuoText);
        text.setText(Html.fromHtml("<a href=\""
                + item.getUrl() + "\">"
                + item.getDesc() + "</a>"
                + "[" + item.getWho() + "]"));
        ImageView image = (ImageView)holder.getView(R.id.ganhuoPhoto);
        if(item.getImages()!=null&&item.getImages().size()>=1)
        {
            image.setVisibility(View.VISIBLE);
            Glide.with(GanHuoPageFragment.this)
                    .load(item.getImages().get(0))
                    .placeholder(R.drawable.emptyimage)
                    .into(image);
        }
        else
        {
            image.setVisibility(View.GONE);
        }

        String time[] = item.getPublishedAt().split("T");

        holder.setText(R.id.ganhuoTime,time.length>=1?time[0]:"未知");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_ganhuo;
    }

    @Override
    protected List secondResloveData(String data) {
        List<com.example.tg.useotherlib.bean.GanHuo> tempList = new ArrayList<com.example.tg.useotherlib.bean.GanHuo>() ;
        try {
            JSONObject jsonObject=  new JSONObject(data);
            boolean error = jsonObject.getBoolean("error");
            if(!error)
            {
                Gson gson = new Gson();
                JSONArray resultJsonArray = jsonObject.getJSONArray("results");
                JsonArray array = new JsonParser().parse(resultJsonArray.toString()).getAsJsonArray();
                for(final JsonElement elem : array){
                    tempList.add(gson.fromJson(elem, com.example.tg.useotherlib.bean.GanHuo.class));
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
        GanHuo ganhuo = (GanHuo) dataList.get(position);
        Logger.d("click : "+ganhuo);
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(ganhuo.getUrl());
        intent.setData(content_url);
        startActivity(intent);
    }

    @Override
    protected RecyclerView.LayoutManager getLayouManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    protected String getLoadURL() {
        return URL+type+URL_NUMBER+(currentPage+1);
    }

}
