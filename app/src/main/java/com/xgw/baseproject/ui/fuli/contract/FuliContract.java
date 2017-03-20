package com.xgw.baseproject.ui.fuli.contract;

import com.xgw.baseproject.base.BaseModel;
import com.xgw.baseproject.base.BasePresenter;
import com.xgw.baseproject.base.BaseView;
import com.xgw.baseproject.bean.BaseResult;
import com.xgw.baseproject.bean.ItemFuliBean;

import java.util.List;

import rx.Observable;

/**
 * Created by lenovo on 2016/11/30.
 */

public interface FuliContract {
    interface Model extends BaseModel{
        Observable<BaseResult<ItemFuliBean>> requestFuliList(String pagesize,String pageno);
    }
    interface View extends BaseView{
        void onSuccess(List<ItemFuliBean> itemFuliBeanList);
        void onError(String error);
    }
    interface Presenter extends BasePresenter{
        void getFuliListData(String pagesize,String pageno);
    }
}
