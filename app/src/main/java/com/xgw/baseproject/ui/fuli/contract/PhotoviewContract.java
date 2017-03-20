package com.xgw.baseproject.ui.fuli.contract;

import android.graphics.Bitmap;

import com.xgw.baseproject.base.BaseModel;
import com.xgw.baseproject.base.BasePresenter;
import com.xgw.baseproject.base.BaseView;
import com.xgw.baseproject.bean.ItemFuliBean;

import java.util.List;

import rx.Observable;

/**
 * Created by lenovo on 2016/12/5.
 */

public interface PhotoviewContract {
    interface Model extends BaseModel{
        Observable<String> requestData(List<ItemFuliBean> itemFuliBeanList);
    }

    interface View extends BaseView {
        void response(String url);
    }

    interface Presenter extends BasePresenter{
        void getData(List<ItemFuliBean> itemFuliBeanList);
        void startLoading();
        void endLoading();
    }
}
