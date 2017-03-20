package com.xgw.baseproject.api;

import com.xgw.baseproject.bean.BaseResult;
import com.xgw.baseproject.bean.ItemFuliBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by lenovo on 2016/11/30.
 */

public interface ApiService {
    @GET("http://gank.io/api/data/福利/{pagesize}/{pageno}")
    Observable<BaseResult<ItemFuliBean>> getFuliList(@Path("pagesize") String pagesize,@Path("pageno") String pageno);
}
