package com.xgw.baseproject.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.elvishew.xlog.XLog;
import com.xgw.baseproject.baseapp.AppConfig;
import com.xgw.baseproject.R;

import java.util.List;
public abstract class BaseAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {
    private Context context;
    private RecyclerView recyclerView;

    private LayoutInflater inflater;

    public BaseAdapter(Context context,RecyclerView recyclerView,int layoutResId, List<T> data) {
        super(layoutResId, data);
        this.context = context;
        this.recyclerView = recyclerView;
        inflater = LayoutInflater.from(context);
        openLoadAnimation(SLIDEIN_LEFT);
        setLoadMoreView(new CustomLoadMoreView());
    }


    public void setNoDataView() {
        View notDataView = inflater.inflate(R.layout.no_data_view, (ViewGroup) recyclerView.getParent(), false);
        setEmptyView(notDataView);
    }

    public void setErrorView(){
        View errorView = inflater.inflate(R.layout.no_data_view,(ViewGroup) recyclerView.getParent(), false);
        setEmptyView(errorView);
    }


}
