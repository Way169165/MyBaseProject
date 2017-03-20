package com.xgw.baseproject.ui.fuli.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xgw.baseproject.R;
import com.xgw.baseproject.base.BaseAdapter;
import com.xgw.baseproject.bean.ItemFuliBean;

import java.util.List;

/**
 * Created by lenovo on 2016/11/30.
 */

public class FuliAdapter extends BaseAdapter<ItemFuliBean> {

    public FuliAdapter(Context context,RecyclerView recyclerView,int layoutResId, List<ItemFuliBean> data) {
        super(context,recyclerView,layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder baseViewHolder, ItemFuliBean itemFuliBean) {
        Glide.with(mContext)
                .load(itemFuliBean.getUrl())
                .crossFade()
                .into((ImageView) baseViewHolder.getView(R.id.item_fuli));
    }
}
