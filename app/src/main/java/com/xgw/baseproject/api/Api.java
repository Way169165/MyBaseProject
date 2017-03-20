package com.xgw.baseproject.api;

import retrofit2.Retrofit;

/**
 * Created by lenovo on 2016/11/30.
 */

public class Api {
    private ApiService apiService;
    private static class SingletonHolder{
        private static final Api INSTANCE = new Api();
    }

    private Api(){
        Retrofit retrofit = RetrofitManager
                .config()
                .baseUrl(BaseAddress.Base_Url)
                .build();
        apiService = retrofit.create(ApiService.class);

    }

    public static Api getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public ApiService getApiService(){
        return apiService;
    }
}
