package com.example.tg.useotherlib.view.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.tg.useotherlib.R;
import com.example.tg.useotherlib.test.TextFragment;
import com.example.tg.useotherlib.widget.MyTabLayout;

import butterknife.BindView;

/**
 * Created by tg on 2017/6/14.
 */

public class ArticlePageFragment extends BaseFragment {

    @BindView(R.id.tab_layout)MyTabLayout mTablayout;
    @BindView(R.id.view_pager)ViewPager mViewPager;

    private  int mCurrentPosition;


    @Override
    protected int getViewLayoutID() {
        return R.layout.fragment_article;
    }

    @Override
    protected void initViews() {

        mViewPager.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            String[] types = {"Topics", "News", "Sites", "Test","Test1","Test2","Test3","Test4"};

            @Override
            public Fragment getItem(int position) {
                return TextFragment.newInstance(types[position]);
            }

            @Override
            public int getCount() {
                return types.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return types[position];
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mCurrentPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        mCurrentPosition = 1;
        mViewPager.setCurrentItem(mCurrentPosition);

        mTablayout.setupWithViewPager(mViewPager);

    }

    @Override
    public void quickToTop() {

    }
}
