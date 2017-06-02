package com.tryndamere.lzm.kuaikanmanhua.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.nostra13.universalimageloader.utils.L;
import com.tryndamere.lzm.kuaikanmanhua.bean.WeekInfoBean;
import com.tryndamere.lzm.kuaikanmanhua.fragment.HomeFragments.HotFragments.WeekFragment;
import com.tryndamere.lzm.kuaikanmanhua.utils.DateUtil;
import com.tryndamere.lzm.kuaikanmanhua.utils.UrlUtils;

import java.util.ArrayList;
import java.util.List;


public class HotFragmentAdapter extends FragmentPagerAdapter {

    private List<WeekInfoBean> list;
    String[] WEEK = DateUtil.initWeek();

    public HotFragmentAdapter(FragmentManager fragmentManager, List<WeekInfoBean> list) {
        super(fragmentManager);
        this.list = list;
    }


    @Override
    public Fragment getItem(int position) {

        WeekFragment weekFragment = new WeekFragment();
        Bundle bundle = new Bundle();
        //position 是0 应该取到7的值
        Log.d("msg", list.size() + "======");
        bundle.putLong("week", list.get(position).getDaytime());
        weekFragment.setArguments(bundle);
        return weekFragment;
    }

    @Override
    public int getCount() {
        return WEEK.length;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return WEEK[position];
    }
}
