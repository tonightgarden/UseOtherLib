package com.example.tg.useotherlib.view.activtiy;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.example.tg.useotherlib.R;
import com.example.tg.useotherlib.view.fragment.TypesVideoFragment;

import butterknife.ButterKnife;

public class TypeVideosActivity extends BaseActivity {

    public static final String TYPE_ID = "TYPE_ID";

    public static String getCatalogId(String url) {
        String catalogId = "";
        if (!TextUtils.isEmpty(url) && url.contains("="))
            catalogId = url.substring(url.lastIndexOf("=") + 1);
        return catalogId;
    }

    @Override
    protected View getMyContentView() {
        View view = getLayoutInflater().inflate(R.layout.activity_type_videos,null);
        return view;
    }

    @Override
    protected void initView() {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.viewContent, TypesVideoFragment.newInstance(getIntent()!=null?getIntent().getStringExtra(TYPE_ID):"null"));
        transaction.commit();
    }

}
