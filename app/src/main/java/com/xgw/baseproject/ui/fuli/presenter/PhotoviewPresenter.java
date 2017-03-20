package com.xgw.baseproject.ui.fuli.presenter;

import android.util.Log;

import com.elvishew.xlog.XLog;
import com.xgw.baseproject.bean.ItemFuliBean;
import com.xgw.baseproject.ui.fuli.contract.PhotoviewContract;

import java.util.List;

import rx.Subscriber;

/**
 * Created by lenovo on 2016/12/5.
 */

public class PhotoviewPresenter implements PhotoviewContract.Presenter{
    private PhotoviewContract.View mView;
    private PhotoviewContract.Model mModel;

    public PhotoviewPresenter(PhotoviewContract.View mView, PhotoviewContract.Model mModel) {
        this.mView = mView;
        this.mModel = mModel;
    }

    @Override
    public void getData(List<ItemFuliBean> itemFuliBeanList) {
        mModel.requestData(itemFuliBeanList).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("eee","error--------" + e.getMessage());
            }

            @Override
            public void onNext(String s) {
                Log.e("ddd",s);
                mView.response(s);
            }
        });
    }

    @Override
    public void startLoading() {
        mView.showLoading();
    }

    @Override
    public void endLoading() {
        mView.stopLoading();
    }

    @Override
    public void start() {
        mView.showLoading();
    }

    @Override
    public void destroy() {

    }
}
