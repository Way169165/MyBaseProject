package com.xgw.baseproject.ui.fuli.activity;

import android.widget.ImageView;

import com.xgw.baseproject.R;
import com.xgw.baseproject.base.BaseActivity;
import com.xgw.baseproject.ui.fuli.contract.DragTestContract;
import com.xgw.baseproject.ui.fuli.model.DragTestModel;
import com.xgw.baseproject.ui.fuli.presenter.DragTestPresenter;
import com.xgw.baseproject.utls.DragWidgetHelper;

import butterknife.BindView;

/**
 * Created by lenovo on 2016/12/29.
 */

public class DragTestActivity extends BaseActivity<DragTestPresenter> implements DragTestContract.View{
    @BindView(R.id.image)
    ImageView image;
    private DragWidgetHelper dragWidgetHelper;
    @Override
    protected DragTestPresenter getPresenter() {
        return new DragTestPresenter(this,new DragTestModel());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_drag_test;
    }

    @Override
    protected void initView() {
        super.initView();
        dragWidgetHelper = new DragWidgetHelper();
        dragWidgetHelper.registerDrag(image, this, new DragWidgetHelper.OnMoveEventListener() {
            @Override
            public void onMove(int lasty, int lastx) {

            }
        });
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void stopLoading() {

    }
}
