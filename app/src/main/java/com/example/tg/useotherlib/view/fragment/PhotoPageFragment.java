package com.example.tg.useotherlib.view.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.tg.useotherlib.R;
import com.example.tg.useotherlib.bean.PhotoBean;
import com.example.tg.useotherlib.view.activtiy.PhotoViewActivity;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by tg on 2017/6/12.
 */

public class PhotoPageFragment extends BaseFragment {

    public static final String  KEY_PHOTO_DATA= "photo_data";
    public static final String  KEY_PHOTO_INDEX= "photo_index";

    private  static final String PHOTO_URL = "http://gank.io/api/data/福利/10/";


    @BindView(R.id.refresh_layout)SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recycler_view)RecyclerView mRecyclerView;

    OkHttpClient client = new OkHttpClient();

    private int currentPage =0;

    private CommonAdapter<PhotoBean> mAdapter;
    private LoadMoreWrapper mLoadMoreWrapper;
    List<PhotoBean> dataList = new ArrayList<PhotoBean>() ;


    @Override
    protected int getViewLayoutID() {
        return R.layout.fragment_photo;
    }

    @Override
    protected void initViews() {

        mSwipeRefreshLayout.setProgressViewOffset(false, -20, 80);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefreshLayout.setEnabled(true);
        mSwipeRefreshLayout.setRefreshing(true);
        refresh();
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentPage = 0;
                dataList.clear();
                mLoadMoreWrapper.setLoadMoreView(0);
                mLoadMoreWrapper.notifyDataSetChanged();
                refresh();
            }
        });

        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
//        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        mAdapter = new CommonAdapter<PhotoBean>(getActivity(),R.layout.item_photo,dataList) {
            @Override
            protected void convert(ViewHolder holder, PhotoBean photoBean, int position) {

//                holder.setText(R.id.photoName,photoBean.getWho());
               ImageView image = holder.getView(R.id.imagePhoto);

                Glide.with(PhotoPageFragment.this)
                        .load(photoBean.getUrl())
                        .placeholder(R.drawable.photo)
                        .into(image);
            }
        };

        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent mIntent = new Intent(getActivity(), PhotoViewActivity.class);
                Bundle bundle=new Bundle();
                bundle.putInt(KEY_PHOTO_INDEX,position);
                bundle.putSerializable(KEY_PHOTO_DATA,(Serializable)dataList);
                mIntent.putExtras(bundle);
                startActivity(mIntent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });


        mLoadMoreWrapper = new LoadMoreWrapper(mAdapter);
        mLoadMoreWrapper.setLoadMoreView(R.layout.default_loading);
        mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener()
        {
            @Override
            public void onLoadMoreRequested()
            {
                Logger.d("loadMore");
                refresh();
            }
        });
        mLoadMoreWrapper.setLoadMoreView(0);

        mRecyclerView.setAdapter(mLoadMoreWrapper);


    }

    @Override
    public void quickToTop() {
//        mRecyclerView.smoothScrollToPosition(0);
        mRecyclerView.scrollToPosition(0);
    }

    private void refresh() {
        new MyTask().execute(PHOTO_URL+(currentPage+1));
    }


    private class MyTask extends AsyncTask<String,Integer,String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
             final String result = null;
                Request request = new Request.Builder()
                        .url(params[0])
                        .build();

            Response response = null;
            try {
                response = client.newCall(request).execute();
                return  response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
                return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
//            Logger.json(s);
            resloveData(s);
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    private void resloveData(String data) {

        List<PhotoBean> tempList = new ArrayList<PhotoBean>() ;

        if(data!=null)
        {
            try {
                JSONObject jsonObject=  new JSONObject(data);
                boolean error = jsonObject.getBoolean("error");
                if(!error)
                {
                    Gson gson = new Gson();
                    JSONArray resultJsonArray = jsonObject.getJSONArray("results");
                    JsonArray array = new JsonParser().parse(resultJsonArray.toString()).getAsJsonArray();
                    for(final JsonElement elem : array){
                        tempList.add(gson.fromJson(elem, PhotoBean.class));
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else
        {
             Logger.d("data == null");
            mLoadMoreWrapper.setLoadMoreView(0);
            mLoadMoreWrapper.notifyDataSetChanged();
        }

        Logger.d(tempList.size());
//        Logger.d(tempList);

        if(tempList.size()!=0)
        {
            currentPage++;
            if(currentPage==1)
            {
                dataList.clear();
            }
            dataList.addAll(tempList);
            mLoadMoreWrapper.setLoadMoreView(R.layout.default_loading);
            mLoadMoreWrapper.notifyDataSetChanged();

        }
        tempList.clear();
    }
}
