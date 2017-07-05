package com.example.tg.useotherlib.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.tg.useotherlib.R;
import com.example.tg.useotherlib.utils.CleanLeakUtils;
import com.example.tg.useotherlib.view.activtiy.AboutActivity;
import com.example.tg.useotherlib.view.activtiy.BaseActivity;
import com.example.tg.useotherlib.view.activtiy.SearchViewActivity;
import com.example.tg.useotherlib.view.activtiy.SettingActivity;
import com.example.tg.useotherlib.view.fragment.ArticlePageFragment;
import com.example.tg.useotherlib.view.fragment.BaseFragment;
import com.example.tg.useotherlib.view.fragment.PhotoPageFragment;
import com.example.tg.useotherlib.view.fragment.PhotoPageFragmentWithRx;
import com.example.tg.useotherlib.view.fragment.VideoPageFragment;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    @BindView(R.id.toolbar)Toolbar toolbar;
    @BindView(R.id.drawer_layout)DrawerLayout drawer;
    @BindView(R.id.nav_view) NavigationView navigationView;
    @BindView(R.id.fab)FloatingActionButton mfab;


    private FragmentManager fragmentManager;

    private String currentFragmentTag;

    @OnClick(R.id.fab)
    public void floatButtonClick()
    {
        Logger.d("floatButtonClick");
        Fragment currentFragment = fragmentManager.findFragmentByTag(currentFragmentTag);
                if (currentFragment != null) {
                    ((BaseFragment)currentFragment).quickToTop();
        }
    }

    private  int mCurrentID =-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(this);
        fragmentManager = getSupportFragmentManager();

        MenuItem item = navigationView.getMenu().getItem(0);
        item.setChecked(true);
        onNavigationItemSelected(item);

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);
        Logger.i("onCreateOptionsMenu");
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Logger.i("onPrepareOptionsMenu");

        if(mCurrentID == R.id.nav_video)
        {
            menu.findItem(R.id.action_search).setVisible(true);
        }
        else
        {
            menu.findItem(R.id.action_search).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Logger.i("onOptionsItemSelected");
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingActivity.class).putExtra(BaseActivity.TITLE,item.getTitle()));
            return true;
        }
        if(id == R.id.action_search)
        {
            startActivity(new Intent(this, SearchViewActivity.class).putExtra(BaseActivity.TITLE,"搜索一下"));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        Logger.i("onMenuOpened");
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        Logger.i("onOptionsMenuClosed");
        super.onOptionsMenuClosed(menu);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        mCurrentID = id;

        Logger.i("Title : "+item.getTitle());

        if (id == R.id.nav_about) {
            startActivity(new Intent(this, AboutActivity.class).putExtra(BaseActivity.TITLE,item.getTitle()));

        } else if (id == R.id.nav_setting) {
            startActivity(new Intent(this, SettingActivity.class).putExtra(BaseActivity.TITLE,item.getTitle()));

        }else
        {
            changeFragmentShowIndex(id,item.getTitle().toString());
            getSupportActionBar().setTitle(item.getTitle());
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    void changeFragmentShowIndex(int id,String title) {

        if (currentFragmentTag != null && currentFragmentTag.equals(title))
            return;

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment currentFragment = fragmentManager.findFragmentByTag(currentFragmentTag);
        if (currentFragment != null) {
            transaction.hide(currentFragment);
        }

        Fragment foundFragment = fragmentManager.findFragmentByTag(title);

        if (foundFragment == null) {
            switch (id) {
                case R.id.nav_photo:
                    foundFragment = new PhotoPageFragmentWithRx();
                    break;
                case R.id.nav_news:
                    foundFragment = new ArticlePageFragment();
                    break;
                case R.id.nav_video:
                    foundFragment = new VideoPageFragment();

            }
        }

        if (foundFragment == null) {

        } else if (foundFragment.isAdded()) {
            transaction.show(foundFragment);
        } else {
            transaction.add(R.id.fragment_content, foundFragment, title);
        }
        transaction.commit();
        currentFragmentTag = title;
        supportInvalidateOptionsMenu();

    }


    @Override
    protected void onDestroy() {
        CleanLeakUtils.fixInputMethodManagerLeak(this);
        super.onDestroy();
    }
}
