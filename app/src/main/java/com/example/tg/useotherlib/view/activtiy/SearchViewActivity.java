package com.example.tg.useotherlib.view.activtiy;

import android.app.SearchManager;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.tg.useotherlib.R;
import com.example.tg.useotherlib.view.fragment.TypesVideoFragment;
import com.orhanobut.logger.Logger;

public class SearchViewActivity  extends BaseActivity {

    public static final String TYPE_ID = "TYPE_ID";

    SearchView searchView;
    TypesVideoFragment mTypesVideoFragment;

    public static String getCatalogId(String url) {
        String catalogId = "";
        if (!TextUtils.isEmpty(url) && url.contains("="))
            catalogId = url.substring(url.lastIndexOf("=") + 1);
        return catalogId;
    }

    @Override
    protected View getMyContentView() {
        View view = getLayoutInflater().inflate(R.layout.activity_search_view,null);
        return view;
    }

    @Override
    protected void initView() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.viewContent, mTypesVideoFragment =TypesVideoFragment.newInstance(getIntent()!=null?getIntent().getStringExtra(TYPE_ID):""));
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.ab_search);//在菜单中找到对应控件的item
        searchView= (SearchView) MenuItemCompat.getActionView(menuItem);//加载searchview
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Logger.d("onQueryTextSubmit");
                if(mTypesVideoFragment!=null)
                {
                    mTypesVideoFragment.setSearchID(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Logger.d("onQueryTextChange");
                return false;
            }
        });//为搜索框设置监听事件
//        searchView.setSubmitButtonEnabled(true);//设置是否显示搜索按钮
        searchView.setQueryHint("输入影片名");//设置提示信息
        searchView.setIconifiedByDefault(true);//设置搜索默认为图标
        searchView.onActionViewExpanded();
        searchView.setSubmitButtonEnabled(true);
        return true;
    }
}
