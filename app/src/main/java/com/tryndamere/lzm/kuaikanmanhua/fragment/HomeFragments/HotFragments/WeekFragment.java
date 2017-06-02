package com.tryndamere.lzm.kuaikanmanhua.fragment.HomeFragments.HotFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.tryndamere.lzm.kuaikanmanhua.R;
import com.tryndamere.lzm.kuaikanmanhua.adapter.WeekFragmentListViewAdapter;
import com.tryndamere.lzm.kuaikanmanhua.bean.WeekBean;
import com.tryndamere.lzm.kuaikanmanhua.http.IAsyncTask;
import com.tryndamere.lzm.kuaikanmanhua.utils.UrlUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/28 0028.
 */

public class WeekFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;
    private Long week;
    private WeekFragmentListViewAdapter adapter;
    private List<WeekBean.DataBean.ComicsBean> list = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weekfragment,container,false);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.week_swiperefershlayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        listView = (ListView) view.findViewById(R.id.week_listview);
        week = (Long) getArguments().get("week");
        adapter = new WeekFragmentListViewAdapter(getActivity(),list);
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new IAsyncTask(){
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                WeekBean weekBean = JSON.parseObject(result,WeekBean.class);
                list = weekBean.getData().getComics();
                adapter = new WeekFragmentListViewAdapter(getActivity(),list);
                listView.setAdapter(adapter);
            }
        }.execute(UrlUtils.getUrl(week));
    }

    @Override
    public void onRefresh() {
        new IAsyncTask(){
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                WeekBean weekBean = JSON.parseObject(result,WeekBean.class);
                list = null;
                list = weekBean.getData().getComics();
                adapter = new WeekFragmentListViewAdapter(getActivity(),list);
                listView.setAdapter(adapter);
                swipeRefreshLayout.setRefreshing(false);
            }
        }.execute(UrlUtils.getUrl(week));
    }
}
