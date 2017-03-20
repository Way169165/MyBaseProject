package com.xgw.baseproject.ui.fuli.model;

import android.graphics.Bitmap;

import com.xgw.baseproject.bean.ItemFuliBean;
import com.xgw.baseproject.ui.fuli.contract.PhotoviewContract;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by lenovo on 2016/12/5.
 */

public class PhotoviewModel implements PhotoviewContract.Model {

    @Override
    public Observable<String> requestData(List<ItemFuliBean> itemFuliBeanList) {
        return Observable.from(itemFuliBeanList).map(new Func1<ItemFuliBean, String>() {
            @Override
            public String call(ItemFuliBean itemFuliBean) {
                return itemFuliBean.getUrl();
            }
        });
    }
}
