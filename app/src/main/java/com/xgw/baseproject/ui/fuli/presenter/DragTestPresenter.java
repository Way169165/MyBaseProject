package com.xgw.baseproject.ui.fuli.presenter;

import com.xgw.baseproject.ui.fuli.contract.DragTestContract;

/**
 * Created by lenovo on 2016/12/29.
 */

public class DragTestPresenter implements DragTestContract.Presenter {
    private DragTestContract.View mView;
    private DragTestContract.Modle mModel;

    public DragTestPresenter(DragTestContract.View mView, DragTestContract.Modle mModel) {
        this.mView = mView;
        this.mModel = mModel;
    }

    @Override
    public void start() {

    }

    @Override
    public void destroy() {

    }
}
