package com.xgw.baseproject.ui.fuli.presenter;

import com.xgw.baseproject.baserx.RxManager;
import com.xgw.baseproject.bean.BaseResult;
import com.xgw.baseproject.bean.ItemFuliBean;
import com.xgw.baseproject.ui.fuli.contract.FuliContract;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lenovo on 2016/11/30.
 */

public class FuliPresenter implements FuliContract.Presenter {
    private FuliContract.Model mModel;
    private FuliContract.View mView;
    private RxManager mRxManager;

    public FuliPresenter(FuliContract.Model mModel, FuliContract.View mView) {
        this.mModel = mModel;
        this.mView = mView;
        mRxManager = new RxManager();
    }

    @Override
    public void getFuliListData(String pagesize,String pageno) {
        mRxManager.add(mModel.requestFuliList(pagesize,pageno).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseResult<ItemFuliBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.stopLoading();
                        mView.onError(e.getMessage());
                    }

                    @Override
                    public void onNext(BaseResult<ItemFuliBean> itemFuliBeanBaseResult) {
                        mView.stopLoading();
                        mView.onSuccess(itemFuliBeanBaseResult.getResults());
                    }
                }));
    }

    @Override
    public void start() {
        mView.showLoading();
    }

    @Override
    public void destroy() {
        mView.stopLoading();
        mRxManager.clear();
    }
}
