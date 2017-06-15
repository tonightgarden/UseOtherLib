package com.example.tg.useotherlib.view.activtiy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.example.tg.useotherlib.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingActivity extends BaseActivity {

    TextView textView;

    @Override
    protected View getMyContentView() {
        View view = getLayoutInflater().inflate(R.layout.activity_setting,null);
        textView =ButterKnife.findById(view,R.id.versionText);
        return view;
    }

    @Override
    protected void initView() {
        textView.setText("软件版本："+ AppUtils.getAppVersionName());
    }

}
