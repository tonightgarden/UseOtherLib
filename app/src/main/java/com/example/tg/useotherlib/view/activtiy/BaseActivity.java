package com.example.tg.useotherlib.view.activtiy;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.tg.useotherlib.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tg on 2017/6/15.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public static final String TITLE = "TITLE";

    @BindView(R.id.toolbar)Toolbar mToolBar;
    @BindView(R.id.myContentView)FrameLayout frameLayout;

    protected abstract View getMyContentView();
    protected abstract void initView();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_layout);
        ButterKnife.bind(this);
        intMyContentLayout();
        initActionBar();
        getSupportActionBar().setTitle(getIntent()!=null?getIntent().getStringExtra(TITLE):"PlayGo");
        initView();
    }

    void intMyContentLayout()
    {
        frameLayout.addView(getMyContentView());
    }

    private void initActionBar( ) {
        if (mToolBar != null) {
            setSupportActionBar(mToolBar);
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
