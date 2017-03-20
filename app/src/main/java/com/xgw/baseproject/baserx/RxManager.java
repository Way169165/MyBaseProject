package com.xgw.baseproject.baserx;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by lenovo on 2016/11/30.
 * rxJava管理类
 */

public class RxManager {
    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    /**
     * 添加rxJava subscription对象
     */
    public void add(Subscription subscription){
        compositeSubscription.add(subscription);
    }

    /**
     * 解除所有观察
     */
    public void clear(){
        compositeSubscription.unsubscribe();
    }

}
