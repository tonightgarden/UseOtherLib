package com.example.tg.useotherlib.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.example.tg.useotherlib.R;
import com.example.tg.useotherlib.bean.CommentBean;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tg on 2017/6/21.
 */

public class CommentFragment extends BaseArticleFragment<CommentBean> {

    String HOST = "http://api.svipmovie.com/front/";
    String GET = "columns/getVideoList.do?catalogId=#&pnum=#";
    @BindView(R.id.contentLayout)LinearLayout mContentLayout;


    public static CommentFragment newInstance(@NonNull String type) {
        Bundle args = new Bundle();
//        args.putString(CommentFragment.TYPE_ID, type);
        CommentFragment fragment = new CommentFragment();
        fragment.setArguments(args);
        return fragment;
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

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected List<CommentBean> secondResloveData(String data) {
        return null;
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
        return HOST;
    }
}
