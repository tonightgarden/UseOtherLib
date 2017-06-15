package com.example.tg.useotherlib.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tg.useotherlib.R;
import com.example.tg.useotherlib.bean.GanHuo;
import com.example.tg.useotherlib.test.TextFragment;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * Created by tg on 2017/6/15.
 */

public class GanHuoPageFragment extends BaseArticleFragment {

    private static  final  String TYPE = "DATA_TYPE";

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
    protected void myconvert(ViewHolder holder, GanHuo ganHuo, int position) {

        TextView text = (TextView)holder.getView(R.id.ganhuoText);
        text.setText(Html.fromHtml("<a href=\""
                + ganHuo.getUrl() + "\">"
                + ganHuo.getDesc() + "</a>"
                + "[" + ganHuo.getWho() + "]"));
        ImageView image = (ImageView)holder.getView(R.id.ganhuoPhoto);
        if(ganHuo.getImages()!=null&&ganHuo.getImages().size()>=1)
        {
            image.setVisibility(View.VISIBLE);
            Glide.with(GanHuoPageFragment.this)
                    .load(ganHuo.getImages().get(0))
                    .placeholder(R.drawable.emptyimage)
                    .into(image);
        }
        else
        {
            image.setVisibility(View.GONE);
        }

        String time[] = ganHuo.getPublishedAt().split("T");

        holder.setText(R.id.ganhuoTime,time.length>=1?time[0]:"未知");

    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_ganhuo;
    }
}
