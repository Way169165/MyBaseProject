package com.xgw.baseproject.ui.fuli.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bm.library.PhotoView;
import com.elvishew.xlog.XLog;
import com.xgw.baseproject.R;
import com.xgw.baseproject.base.BaseActivity;
import com.xgw.baseproject.base.BasePresenter;
import com.xgw.baseproject.bean.ItemFuliBean;
import com.xgw.baseproject.ui.fuli.adapter.MyPagerAdapter;
import com.xgw.baseproject.ui.fuli.contract.PhotoviewContract;
import com.xgw.baseproject.ui.fuli.model.PhotoviewModel;
import com.xgw.baseproject.ui.fuli.presenter.PhotoviewPresenter;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by lenovo on 2016/12/5.
 */

public class PhotoViewPagerActivity extends BaseActivity<PhotoviewPresenter> implements PhotoviewContract.View, ViewPager.OnPageChangeListener {
    private List<ItemFuliBean> itemFuliBeanList = new ArrayList<>();
    @BindView(R.id.pager)
    ViewPager mPager;

    @BindView(R.id.index_tv)
    TextView indexTv;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    private MyPagerAdapter adapter;
    private int index = 1;
    @Override
    protected PhotoviewPresenter getPresenter() {
        return new PhotoviewPresenter(this,new PhotoviewModel());
    }

    @Override
    protected void initView() {
        super.initView();
        Bundle bundle = getIntent().getExtras();
        itemFuliBeanList = (List<ItemFuliBean>) bundle.getSerializable("list");
        index = bundle.getInt("index");
        mPresenter.getData(itemFuliBeanList);
        mPager.addOnPageChangeListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setPageMargin((int) (getResources().getDisplayMetrics().density * 15));
        adapter = new MyPagerAdapter(itemFuliBeanList,this);
        mPager.setAdapter(adapter);
        mPager.setCurrentItem(index);
        String who = itemFuliBeanList.get(index).getWho();
        String name = "";
        if(!TextUtils.isEmpty(who)){
            try {
                name = URLDecoder.decode(who,"utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if(TextUtils.isEmpty(name)) {
            setTitle_back(true,"美女");
        } else {
            setTitle_back(true,name);
        }
        indexTv.setText(index + 1 + "/" + itemFuliBeanList.size());

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_photo_viewpager;
    }

    @Override
    public void response(String url) {
        Log.e("---photoviewactivity---",url);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        String who = itemFuliBeanList.get(position).getWho();
        String name = "";
        if(!TextUtils.isEmpty(who)){
            try {
                name = URLDecoder.decode(who,"utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if(TextUtils.isEmpty(name)) {
            setTitle_back(true,"美女");
        } else {
            setTitle_back(true,name);
        }
        indexTv.setText(position + 1 + "/" + itemFuliBeanList.size());

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onEventMainThread(String what) {
        super.onEventMainThread(what);
        switch (what) {
            case "start":
                mPresenter.startLoading();
                break;
            case "complete" :
                mPresenter.endLoading();
                break;
            default:
                mPresenter.endLoading();
                break;
        }
    }
}
