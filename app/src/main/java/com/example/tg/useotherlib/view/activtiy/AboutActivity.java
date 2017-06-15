package com.example.tg.useotherlib.view.activtiy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.tg.useotherlib.R;
import com.example.tg.useotherlib.view.fragment.BaseFragment;

public class AboutActivity extends BaseActivity {

    @Override
    protected View getMyContentView() {
        View view = getLayoutInflater().inflate(R.layout.activity_about,null);
        return view;
    }

    @Override
    protected void initView() {

    }
}
