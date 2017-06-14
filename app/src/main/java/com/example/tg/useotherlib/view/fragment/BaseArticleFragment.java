package com.example.tg.useotherlib.view.fragment;

import com.orhanobut.logger.Logger;

/**
 * Created by tg on 2017/6/14.
 */

public abstract class BaseArticleFragment extends BaseFragment{

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser)
        {
//            Logger.i("VisibleToUser : "+getClass());
        }
    }
}
