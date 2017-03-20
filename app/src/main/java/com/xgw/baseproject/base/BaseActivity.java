package com.xgw.baseproject.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.xgw.baseproject.R;
import com.xgw.baseproject.utls.ProgressDialogHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import rx.functions.Action1;

/**
 * Created by lenovo on 2016/11/30.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {
    public T mPresenter;
    public ProgressDialogHelper dialog;
    public EventBus eventBus;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        AppManager.getAppManager().addActivity(this);
        eventBus = EventBus.getDefault();
        eventBus.register(this);
        ButterKnife.bind(this);
        mPresenter = getPresenter();
        initView();
        initData();
    }

    protected abstract T getPresenter();

    protected void initData() {

    }

    protected void initView() {
        dialog = new ProgressDialogHelper(this);
        mPresenter.start();
    }

    protected abstract int getLayoutId();

    public void showToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.destroy();
        eventBus.unregister(this);
        AppManager.getAppManager().finishActivity(this);
    }

    public void setTitle_back(boolean hasBack,String title){
        TextView titleTv = (TextView) findViewById(R.id.title);
        TextView back = (TextView) findViewById(R.id.back);
        titleTv.setText(title);
        if(hasBack) {
            back.setClickable(true);
            back.setFocusable(true);
            RxView.clicks(back).subscribe(new Action1<Void>() {
                @Override
                public void call(Void aVoid) {
                    finish();

                }
            });
        }else{
            back.setVisibility(View.GONE);
        }
    }

    public void onEventMainThread(String what){

    }
}
