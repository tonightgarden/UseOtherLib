package com.example.tg.useotherlib.view.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import com.example.tg.useotherlib.R;
import com.example.tg.useotherlib.test.TextFragment;
import com.example.tg.useotherlib.widget.MyTabLayout;
import com.orhanobut.logger.Logger;

import butterknife.BindView;

/**
 * Created by tg on 2017/6/14.
 */

public class ArticlePageFragment extends BaseFragment {

    @BindView(R.id.tab_layout)MyTabLayout mTablayout;
    @BindView(R.id.view_pager)ViewPager mViewPager;

    private  int mCurrentPosition;

    FragmentPagerAdapter mAdpter;

    GanHuoPageFragment currentFragment;


    @Override
    protected int getViewLayoutID() {
        return R.layout.fragment_article;
    }

    @Override
    protected void initViews() {

        mAdpter =new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            String[] types = {"all", "Android", "iOS","休息视频","前端","拓展资源","App","瞎推荐"};

            @Override
            public Fragment getItem(int position) {
                return GanHuoPageFragment.newInstance(types[position]);
            }

            @Override
            public int getCount() {
                return types.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return types[position];
            }

            @Override
            public void setPrimaryItem(ViewGroup container, int position, Object object) {
                currentFragment = (GanHuoPageFragment) object;
                super.setPrimaryItem(container, position, object);
            }
        };

        mViewPager.setAdapter(mAdpter);

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

        mCurrentPosition = 0;
        mViewPager.setCurrentItem(mCurrentPosition);

        mTablayout.setupWithViewPager(mViewPager);

    }

    @Override
    public void quickToTop() {
        Logger.d(" news quickToTop " );
        currentFragment.quickToTop();
    }
}
