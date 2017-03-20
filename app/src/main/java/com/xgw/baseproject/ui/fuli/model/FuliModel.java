package com.xgw.baseproject.ui.fuli.model;

import com.xgw.baseproject.api.Api;
import com.xgw.baseproject.bean.BaseResult;
import com.xgw.baseproject.bean.ItemFuliBean;
import com.xgw.baseproject.ui.fuli.contract.FuliContract;

import java.util.List;

import rx.Observable;

/**
 * Created by lenovo on 2016/11/30.
 */

public class FuliModel implements FuliContract.Model {
    @Override
    public Observable<BaseResult<ItemFuliBean>> requestFuliList(String pagesize,String pageno) {
        return Api.getInstance().getApiService().getFuliList(pagesize,pageno);
    }
}
