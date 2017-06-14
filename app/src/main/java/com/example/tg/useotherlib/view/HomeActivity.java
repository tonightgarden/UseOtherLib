package com.example.tg.useotherlib.view;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
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
import com.example.tg.useotherlib.view.fragment.ArticlePageFragment;
import com.example.tg.useotherlib.view.fragment.PhotoPageFragment;
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
    private PhotoPageFragment mPhotoPageFragment;
    private ArticlePageFragment mArticlePageFragment;

    @OnClick(R.id.fab)
    public void floatButtonClick()
    {
        Logger.d("floatButtonClick");
        switch (mCurrentID) {
            case R.id.nav_photo:
                if (mPhotoPageFragment != null) {
                    mPhotoPageFragment.quickToTop();
                }
                break;
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
        initFragment(savedInstanceState);

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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        mCurrentID = id;

        if (id == R.id.nav_news) {
            // Handle the camera action
        } else if (id == R.id.nav_photo) {

        } else if (id == R.id.nav_video) {

        } else if (id == R.id.nav_funny) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        Logger.i("Title : "+item.getTitle());
        changeFragmentShowIndex(id);
        toolbar.setTitle(item.getTitle());
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    void changeFragmentShowIndex(int id) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        switch (id) {
            case R.id.nav_photo:
                if (mPhotoPageFragment == null) {
                    mPhotoPageFragment = new PhotoPageFragment();
                    transaction.add(R.id.fragment_content, mPhotoPageFragment,
                            PhotoPageFragment.class.getName());
                } else {
                    transaction.show(mPhotoPageFragment);
                }
                break;
            case R.id.nav_news:
                if (mArticlePageFragment == null) {
                    mArticlePageFragment = new ArticlePageFragment();
                    transaction.add(R.id.fragment_content, mArticlePageFragment,
                            ArticlePageFragment.class.getName());
                } else {
                    transaction.show(mArticlePageFragment);
                }
                break;
        }
        transaction.commit();
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (mPhotoPageFragment != null) {
            transaction.hide(mPhotoPageFragment);
        }
        if(mArticlePageFragment!=null)
        {
            transaction.hide(mArticlePageFragment);
        }
    }

    private void initFragment(Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            mPhotoPageFragment = (PhotoPageFragment) fragmentManager
                    .findFragmentByTag(PhotoPageFragment.class.getName());
            mArticlePageFragment = (ArticlePageFragment) fragmentManager
                    .findFragmentByTag(ArticlePageFragment.class.getName());
        } else {

        }
    }

    @Override
    protected void onDestroy() {
        CleanLeakUtils.fixInputMethodManagerLeak(this);
        super.onDestroy();
    }
}
