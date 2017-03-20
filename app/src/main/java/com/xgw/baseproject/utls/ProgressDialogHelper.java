package com.xgw.baseproject.utls;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import com.xgw.baseproject.R;

/**
 * Created by lenovo on 2016/12/2.
 * todo 加载进度条帮助类
 * <p>
 *     description:此处通过android旋转动画向progressbar添加类似ios的菊花动画加载效果，
 *     宽高已写死（均为屏幕宽度的三分之一），可设置是否可取消（cancelable）、
 *     点击窗体外部是否可取消（cancelbleTouchOutside）。
 * </p>
 *
 */

public class ProgressDialogHelper {
    private Activity mActivity;
    private AlertDialog mAlertDialog;

    public ProgressDialogHelper(Activity activity) {
        this.mActivity = activity;
    }

    /**
     * 进度条显示的方法
     * @param cancelable 是否可取消：true，可取消，false：不可取消
     * @param cancelbleTouchOutside 点击进度条外部是否可取消显示，true：可取消，false：不可取消
     */
    public void showProgressDialog(boolean cancelable,boolean cancelbleTouchOutside){
        mAlertDialog = new AlertDialog.Builder(mActivity, R.style.LoadingStyle).create();
        View convertView = View.inflate(mActivity, R.layout.loading_layout, null);
        mAlertDialog.show();
        mAlertDialog.setContentView(convertView);
        WindowManager m = mActivity.getWindowManager();
        //此处宽高不设置为固定值，是为了更好的适配不同屏幕分辨率的手机
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = mAlertDialog.getWindow().getAttributes();
        p.height = (int) (d.getWidth() * 0.3); // 高度设置为屏幕宽度的三分之一
        p.width = (int) (d.getWidth() * 0.3); // 宽度设置为屏幕宽度的三分之一
        mAlertDialog.getWindow().setAttributes(p);
        mAlertDialog.setCanceledOnTouchOutside(cancelbleTouchOutside);
        mAlertDialog.setCancelable(cancelable);
    }

    /**
     * 隐藏进度条对话框的方法
     */
    public void dismissProgressDialog(){
        if (mAlertDialog != null) {
            mAlertDialog.dismiss();
        }
    }
}
