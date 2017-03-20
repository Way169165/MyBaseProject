package com.xgw.baseproject.ui.fuli.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.jakewharton.rxbinding.view.RxView;
import com.xgw.baseproject.R;
import com.xgw.baseproject.base.BaseActivity;
import com.xgw.baseproject.bean.ItemFuliBean;
import com.xgw.baseproject.ui.fuli.adapter.FuliAdapter;
import com.xgw.baseproject.ui.fuli.contract.FuliContract;
import com.xgw.baseproject.ui.fuli.model.FuliModel;
import com.xgw.baseproject.ui.fuli.presenter.FuliPresenter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class FuliActivity extends BaseActivity<FuliPresenter> implements FuliContract.View, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private FuliAdapter adapter;

    private int pageno = 1;
    private int pageSize = 20;
    private List<ItemFuliBean> listItems = new ArrayList<>();

    @Override
    protected void initView() {
        super.initView();
        setTitle_back(false,"美女");
        adapter = new FuliAdapter(this,recyclerView,R.layout.item_fuli,null);
        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                swipeRefreshLayout.setEnabled(false);
                pageno++;
                mPresenter.getFuliListData(pageSize + "",pageno + "");
            }
        });

        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Intent intent = new Intent(FuliActivity.this,PhotoViewPagerActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("index",i);
                bundle.putSerializable("list", (Serializable) listItems);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        onRefresh();
    }

    @Override
    protected FuliPresenter getPresenter() {
        return new FuliPresenter(new FuliModel(),this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onSuccess(List<ItemFuliBean> itemFuliBeanList) {
        if(pageno == 1) {
            if(itemFuliBeanList == null || itemFuliBeanList.size() == 0) {
                adapter.setNoDataView();
                return;
            }
            adapter.setNewData(itemFuliBeanList);
        }else {
            adapter.addData(itemFuliBeanList);
        }
        listItems = (ArrayList<ItemFuliBean>) adapter.getData();
        adapter.loadMoreComplete();
        adapter.setEnableLoadMore(true);
        swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout.setEnabled(true);
        if(itemFuliBeanList.size() < pageSize) {
            adapter.loadMoreEnd();
        }
    }

    @Override
    public void onError(String error) {
        showToast(error);
        swipeRefreshLayout.setRefreshing(false);
        adapter.loadMoreFail();
        swipeRefreshLayout.setEnabled(true);
        if(pageno == 1) {
            adapter.setErrorView();
        }
    }

    @Override
    public void showLoading() {
        dialog.showProgressDialog(true,false);
    }

    @Override
    public void stopLoading() {
//        dialog.dismissProgressDialog();
    }

    @Override
    public void onRefresh() {
        adapter.setEnableLoadMore(false);
        pageno = 1;
        mPresenter.getFuliListData(pageSize + "",pageno + "");
    }

    @OnClick({R.id.go})
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.go:
                Intent intent = new Intent(FuliActivity.this,DragTestActivity.class);
                startActivity(intent);
                break;
        }
    }
}
