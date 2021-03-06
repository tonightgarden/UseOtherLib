/*
 * Copyright 2017 GcsSloop
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Last modified 2017-04-09 14:15:03
 *
 * GitHub:  https://github.com/GcsSloop
 * Website: http://www.gcssloop.com
 * Weibo:   http://weibo.com/GcsSloop
 */

package com.example.tg.useotherlib.test;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.tg.useotherlib.R;
import com.example.tg.useotherlib.bean.Data;
import com.example.tg.useotherlib.bean.GanHuo;
import com.example.tg.useotherlib.view.fragment.BaseArticleFragment;
import com.example.tg.useotherlib.view.fragment.BaseFragment;
import com.orhanobut.logger.Logger;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import butterknife.BindView;


public class TextFragment extends BaseArticleFragment {
    private static final String TYPE = "type";
    @BindView(R.id.text)TextView textView;
    String text;


    public static TextFragment newInstance(@NonNull String type) {
        Bundle args = new Bundle();
        args.putString(TYPE, type);
        TextFragment fragment = new TextFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getViewLayoutID() {
        Logger.d("name  : "+text+"   creaeView");
        return R.layout.fragment_text;

    }


    @Override
    protected void myconvert(ViewHolder holder, Data item, int position) {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected List secondResloveData(String data) {
        return null;
    }

    @Override
    protected void onMyItemClick(View view, RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    protected RecyclerView.LayoutManager getLayouManager() {
        return null;
    }

    @Override
    protected String getLoadURL() {
        return null;
    }

    @Override
    protected void initViews() {
         text = getArguments().getString(TYPE);
        textView.setText(text);
        Logger.d("name  : "+text+"   initViews");
    }

    @Override
    public void quickToTop() {

    }



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser)
        {
            Logger.i("VisibleToUser : "+getClass());
        }
        Logger.i("name  : "+text+ "  "+isVisibleToUser);
    }
}
