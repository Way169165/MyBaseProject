package com.xgw.baseproject.ui.fuli.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.xgw.baseproject.R;
import com.xgw.baseproject.bean.ItemFuliBean;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by lenovo on 2016/12/5.
 */

public class MyPagerAdapter extends PagerAdapter {
    private List<ItemFuliBean> urls = new ArrayList<>();
    private Context context;

    public MyPagerAdapter(List<ItemFuliBean> urls,Context context) {
        this.urls = urls;
        this.context = context;
    }

    @Override
    public int getCount() {
        return urls.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Log.d("adapter---","执行了instantiateItem" + position);
//        View parent = View.inflate(context, R.layout.activity_photo_viewpager,null);
//        TextView textView = (TextView) parent.findViewById(R.id.index_tv);
//        textView.setText((position + 1) + "/" + urls.size());
        PhotoView view = new PhotoView(context);
        view.enable();
        view.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        EventBus.getDefault().post("start");
        Glide.with(context).load(urls.get(position).getUrl()).into(new GlideDrawableImageViewTarget(view){
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                super.onResourceReady(resource, animation);
                EventBus.getDefault().post("complete");
                
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
