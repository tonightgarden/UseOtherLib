package com.example.tg.useotherlib.view.activtiy;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.tg.useotherlib.R;
import com.example.tg.useotherlib.bean.VideoTypeInfo;
import com.example.tg.useotherlib.view.fragment.AbstractFragment;
import com.example.tg.useotherlib.view.fragment.CommentFragment;
import com.example.tg.useotherlib.view.fragment.GanHuoPageFragment;
import com.example.tg.useotherlib.widget.MyTabLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

import static com.example.tg.useotherlib.R.id.videoplayer;

public class VideoInfoActivity extends BaseActivity {

    ViewPager mViewPager;
    MyTabLayout mMyTabLayout;
    JCVideoPlayerStandard videoPlayer;

    FragmentPagerAdapter mAdpter;
    Fragment currentFragment;
    private  int mCurrentPosition;

    VideoTypeInfo videoInfo;

    @Override
    protected View getMyContentView() {
        View view = getLayoutInflater().inflate(R.layout.activity_video_info,null);
        mViewPager = ButterKnife.findById(view,R.id.view_pager);
        mMyTabLayout = ButterKnife.findById(view,R.id.tab_layout);
        videoPlayer = ButterKnife.findById(view, videoplayer);
        return view;
    }

    @Override
    protected void initView() {

        videoInfo = (VideoTypeInfo) getIntent().getSerializableExtra("videoInfo");

        mAdpter =new FragmentPagerAdapter(getSupportFragmentManager()) {
            String[] types = {"简介", "评论"};

            @Override
            public Fragment getItem(int position) {
                switch (position)
                {
                    case 0:
                        return AbstractFragment.newInstance(videoInfo.getDataId());
                    case 1:
                        return CommentFragment.newInstance(videoInfo.getDataId());
                }
                return null;
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
                currentFragment = (Fragment) object;
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

        mMyTabLayout.setupWithViewPager(mViewPager);

        videoPlayer.thumbImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        videoPlayer.backButton.setVisibility(View.GONE);
        videoPlayer.titleTextView.setVisibility(View.GONE);
        videoPlayer.tinyBackImageView.setVisibility(View.GONE);


        if (!TextUtils.isEmpty(videoInfo.getPic()))
            Glide.with(this)
                    .load(videoInfo.getPic())
                    .into(videoPlayer.thumbImageView);

    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onvideoUrlEvent(String videoUrl)
    {

        if (!TextUtils.isEmpty(videoUrl)) {
            videoPlayer.setUp(videoUrl
                    , JCVideoPlayerStandard.SCREEN_LAYOUT_LIST, title);
            videoPlayer.onClick(videoPlayer.thumbImageView);
        }
        /* Do something */
    };

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }


}
