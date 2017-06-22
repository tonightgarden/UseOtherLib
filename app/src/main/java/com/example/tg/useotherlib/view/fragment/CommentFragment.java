package com.example.tg.useotherlib.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.tg.useotherlib.R;
import com.example.tg.useotherlib.bean.CommentBean;
import com.example.tg.useotherlib.bean.CommentGetResult;
import com.example.tg.useotherlib.bean.VideoInfoTotal;
import com.example.tg.useotherlib.bean.VideoTypeInfo;
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

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tg on 2017/6/21.
 */

public class CommentFragment extends BaseArticleFragment<CommentBean> {

    String HOST = "http://api.svipmovie.com/front/";
    String GET = "Commentary/getCommentList.do?mediaId=#&pnum=#";
    @BindView(R.id.contentLayout)LinearLayout mContentLayout;

    public static final String MEDIA_ID = "MEDIA_ID";
    String mediaId;
    CommentGetResult mCommentGetResult ;
    int totalRecords ;
    int records ;

    public static CommentFragment newInstance(@NonNull String type) {
        Bundle args = new Bundle();
        args.putString(CommentFragment.MEDIA_ID, type);
        CommentFragment fragment = new CommentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            mediaId = getArguments().getString(MEDIA_ID);
    }

    @Override
    protected void initViews() {
        mContentLayout.setBackground(null);
        super.initViews();
    }

    @Override
    protected int getViewLayoutID() {
        return R.layout.fragment_photo;
    }

    @Override
    protected void myconvert(ViewHolder holder, CommentBean item, int position) {

        holder.setText(R.id.tv_nick,item.getPhoneNumber());
        holder.setText(R.id.tv_time,item.getTime());
        holder.setText(R.id.tv_like,""+item.getLikeNum());
        holder.setText(R.id.tv_comment,item.getMsg());
        if (!TextUtils.isEmpty(item.getUserPic()))
        {
            ImageView image = holder.getView(R.id.avatar);
            Glide.with(CommentFragment.this)
                    .load(item.getUserPic())
                    .placeholder(R.drawable.emptyimage)
                    .into(image);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_comment;
    }

    @Override
    protected void loadComplete() {
        if(totalRecords<records)
        {
            isCanLoadMore = false;
        }
        super.loadComplete();
    }

    @Override
    protected List<CommentBean> secondResloveData(String data) {

        Logger.d( data);
        List<CommentBean> tempList = new ArrayList<CommentBean>();
        try {
            JSONObject jsonObject = new JSONObject(data);
            String msg = jsonObject.getString("msg");
            if (!TextUtils.isEmpty(msg) && msg.equals("成功")) {

                Gson gson = new Gson();
                JSONObject retObj = jsonObject.getJSONObject("ret");

                mCommentGetResult = gson.fromJson(retObj.toString(), CommentGetResult.class);
                if(mCommentGetResult!=null)
                {
                    records = mCommentGetResult.getRecords();
                    if(mCommentGetResult.getList()!=null)
                    {
                        totalRecords = mCommentGetResult.getList().size();
                        if(totalRecords>0)
                        {
                            tempList.addAll(mCommentGetResult.getList());
                        }
                    }
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

    }

    @Override
    protected RecyclerView.LayoutManager getLayouManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    protected String getLoadURL() {
        String get = GET.replaceFirst("#", mediaId).replaceFirst("#", String.valueOf(currentPage + 1));
        return HOST+get;
    }
}
