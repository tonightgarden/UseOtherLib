package com.example.tg.useotherlib.view.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.tg.useotherlib.R;
import com.example.tg.useotherlib.bean.GanHuo;
import com.example.tg.useotherlib.bean.PhotoBean;
import com.example.tg.useotherlib.view.activtiy.PhotoViewActivity;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.orhanobut.logger.Logger;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.EmptyWrapper;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by tg on 2017/6/14.
 */

public abstract class BaseArticleFragment extends BaseFragment{


    private  static final String URL = "http://gank.io/api/data/";
    private  static final String URL_NUMBER = "/10/";

    protected String type="";

    private boolean isViewPrepared; // 标识fragment视图已经初始化完毕
    private boolean hasFetchData; // 标识已经触发过懒加载数据

    @BindView(R.id.refresh_layout)SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recycler_view)RecyclerView mRecyclerView;

    OkHttpClient client = new OkHttpClient();

    private int currentPage =0;

    private CommonAdapter<GanHuo> mAdapter;
    private LoadMoreWrapper mLoadMoreWrapper;
    private EmptyWrapper mEmptyWrapper;
    List<GanHuo> dataList = new ArrayList<GanHuo>() ;

    protected  abstract  void myconvert(ViewHolder holder, GanHuo ganHuo, int position);
    protected  abstract  int getLayoutId();





    @Override
    protected void initViews() {
        {
           Logger.i("initViews   "+type);
            mSwipeRefreshLayout.setProgressViewOffset(false, -20, 80);
            mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
            mSwipeRefreshLayout.setEnabled(true);
//            mSwipeRefreshLayout.setRefreshing(true);
//            refresh();
            mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    currentPage = 0;
                    dataList.clear();
                    mLoadMoreWrapper.setLoadMoreView(0);
                    mLoadMoreWrapper.notifyDataSetChanged();
                    lazyFetchData();
                }
            });


            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//            mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
//        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

            mAdapter = new CommonAdapter<GanHuo>(getActivity(),getLayoutId(),dataList) {
                @Override
                protected void convert(ViewHolder holder, GanHuo ganHuo, int position) {

//                holder.setText(R.id.photoName,photoBean.getWho());
//                    ImageView image = holder.getView(R.id.imagePhoto);
//
//                    Glide.with(PhotoPageFragment.this)
//                            .load(photoBean.getUrl())
//                            .placeholder(R.drawable.photo)
//                            .into(image);
                    myconvert( holder,  ganHuo,  position);
                }
            };

            mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

                    GanHuo ganhuo = dataList.get(position);
                    Logger.d("click : "+ganhuo);
                        Intent intent = new Intent();
                        intent.setAction("android.intent.action.VIEW");
                        Uri content_url = Uri.parse(ganhuo.getUrl());
                        intent.setData(content_url);
                        startActivity(intent);
                }

                @Override
                public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                    return false;
                }
            });

            initEmptyView();
            mLoadMoreWrapper = new LoadMoreWrapper(mEmptyWrapper);
            mLoadMoreWrapper.setLoadMoreView(R.layout.default_loading);
            mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener()
            {
                @Override
                public void onLoadMoreRequested()
                {
                    Logger.d("loadMore");
                    lazyFetchData();
                }
            });
            if(dataList.size()==0)
            {
                mLoadMoreWrapper.setLoadMoreView(0);
            }

            mRecyclerView.setAdapter(mLoadMoreWrapper);


        }

    }

    private void initEmptyView()
    {
        mEmptyWrapper = new EmptyWrapper(mAdapter);
        mEmptyWrapper.setEmptyView(LayoutInflater.from(getActivity()).inflate(R.layout.empty_view, mRecyclerView, false));
    }

    @Override
    public void quickToTop() {
        if(mRecyclerView !=null)
        {
            Logger.i("quickToTop   "+type);
            mRecyclerView.scrollToPosition(0);
        }

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            lazyFetchDataIfPrepared();
        }
    }

    private void lazyFetchDataIfPrepared() {
        if (getUserVisibleHint() && !hasFetchData && isViewPrepared) {
            hasFetchData = true;
            lazyFetchData();
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewPrepared = true;
        lazyFetchDataIfPrepared();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isViewPrepared = false;
    }

    void lazyFetchData()
    {
        new BaseArticleFragment.MyTask().execute(URL+type+URL_NUMBER+(currentPage+1));
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

        List<GanHuo> tempList = new ArrayList<GanHuo>() ;

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
                        tempList.add(gson.fromJson(elem, GanHuo.class));
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
        Logger.d(tempList);

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
