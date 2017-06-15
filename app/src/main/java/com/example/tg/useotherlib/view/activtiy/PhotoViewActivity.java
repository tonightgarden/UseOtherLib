package com.example.tg.useotherlib.view.activtiy;

import android.support.design.widget.AppBarLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.tg.useotherlib.R;
import com.example.tg.useotherlib.bean.PhotoBean;
import com.example.tg.useotherlib.view.fragment.PhotoPageFragment;
import com.example.tg.useotherlib.widget.HackyViewPager;
import com.github.chrisbanes.photoview.OnOutsidePhotoTapListener;
import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.PhotoView;
import com.orhanobut.logger.Logger;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoViewActivity extends AppCompatActivity {

    @BindView(R.id.appBarLayout)AppBarLayout mAppBarLayout;
    @BindView(R.id.toolbar)Toolbar mToolBar;
    @BindView(R.id.viewPager)HackyViewPager mViewPager;

    private  int mCurrentPage;

    private  int mTotalSize ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);
        ButterKnife.bind(this);
        initActionBar();

        if(getIntent()!=null)
        {
            List<PhotoBean> list= (List<PhotoBean>) getIntent().getSerializableExtra(PhotoPageFragment.KEY_PHOTO_DATA);
            if(list!=null)
            {
                mTotalSize = list.size();
                mViewPager.setAdapter(new SamplePagerAdapter(new SamplePagerAdapter.MyOnPhotoTapListener() {
                    @Override
                    public void onPhotoTap() {
                        mAppBarLayout.setVisibility(mAppBarLayout.getVisibility()==View.VISIBLE?View.GONE:View.VISIBLE);
                    }
                }).addData(list));


                mCurrentPage = getIntent().getIntExtra(PhotoPageFragment.KEY_PHOTO_INDEX,-1);
                Logger.d("mCurrentPage :"+mCurrentPage);
                if(mCurrentPage!=-1 && mCurrentPage <mTotalSize)
                {
                    mViewPager.setCurrentItem(mCurrentPage);
                    updateTitle();
                }

                mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        mCurrentPage = position;
                        updateTitle();
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });

            }

        }
    }

    private void updateTitle()
    {
        int number =mCurrentPage+1;
        getSupportActionBar().setTitle("("+number+"/"+mTotalSize+")");
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.photo_menu, menu);
        setIconsVisible(menu,true);
        return true;
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

    private void setIconsVisible(Menu menu, boolean flag) {
        //判断menu是否为空
        if(menu != null) {
            try {
                //如果不为空,就反射拿到menu的setOptionalIconsVisible方法
                Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                //暴力访问该方法
                method.setAccessible(true);
                //调用该方法显示icon
                method.invoke(menu, flag);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }




    static class SamplePagerAdapter extends PagerAdapter {

        private List<PhotoBean> dataList = new ArrayList<>();

        private MyOnPhotoTapListener mOnPhotoTapListener;

        @Override
        public int getCount() {
            return dataList.size();
        }

        public SamplePagerAdapter addData(List<PhotoBean> list)
        {
            dataList.clear();
            dataList.addAll(list);
            list.clear();
            return this;
        }

        public SamplePagerAdapter(MyOnPhotoTapListener listener) {

            // TODO Auto-generated constructor stub
            this.mOnPhotoTapListener = listener;
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(container.getContext());
           // photoView.setImageResource(sDrawables[position]);
            Glide.with(container.getContext())
                    .load(dataList.get(position).getUrl())
//                    .placeholder(R.drawable.photo)
                    .into(photoView);
            // Now just add PhotoView to ViewPager and return it
            photoView.setOnPhotoTapListener(new OnPhotoTapListener() {
                @Override
                public void onPhotoTap(ImageView view, float x, float y) {
                   Logger.i("onPhotoTap");
                    if(mOnPhotoTapListener!=null)
                    {
                        mOnPhotoTapListener.onPhotoTap();
                    }
                }
            });
            photoView.setOnOutsidePhotoTapListener(new OnOutsidePhotoTapListener() {
                @Override
                public void onOutsidePhotoTap(ImageView imageView) {
                    Logger.i("onOutsidePhotoTap");
                    if(mOnPhotoTapListener!=null)
                    {
                        mOnPhotoTapListener.onPhotoTap();
                    }
                }
            });
            container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        public interface MyOnPhotoTapListener{
            void onPhotoTap();
        }


    }

}
