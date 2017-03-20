package com.xgw.baseproject.utls;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.elvishew.xlog.XLog;

/**
 * Created by lenovo on 2016/12/29.
 * 拖拽控件工具类
 */

public class DragWidgetHelper implements View.OnTouchListener {
    private View mView;

    private int startx;
    private int starty;
    private Context context;
    private OnMoveEventListener onMoveEventListener;

    public void registerDrag(View view, Context context,OnMoveEventListener onMoveEventListener) {
        this.context = context;
        this.mView = view;
        this.onMoveEventListener = onMoveEventListener;
        if (view != null) {
            view.setOnTouchListener(this);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (v.getId() == mView.getId()) {
            // 如果手指放在imageView上拖动
            // event.getRawX(); //获取手指第一次接触屏幕在x方向的坐标
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:// 获取手指第一次接触屏幕
                    startx = (int) event.getRawX();
                    starty = (int) event.getRawY();
                    break;
                case MotionEvent.ACTION_MOVE:// 手指在屏幕上移动对应的事件
                    int x = (int) event.getRawX();
                    int y = (int) event.getRawY();
                    if (y < 400) {
                        // 设置TextView在窗体的下面
                        mView.layout(mView.getLeft(), 420,
                                mView.getRight(), 440);
                    } else {
                        mView.layout(mView.getLeft(), 60,
                                mView.getRight(), 80);
                    }
                    // 获取手指移动的距离
                    int dx = x - startx;
                    int dy = y - starty;
                    // 得到imageView最开始的各顶点的坐标
                    int l = mView.getLeft();
                    int r = mView.getRight();
                    int t = mView.getTop();
                    int b = mView.getBottom();
                    // 更改imageView在窗体的位置
                    mView.layout(l + dx, t + dy, r + dx, b + dy);
                    // 获取移动后的位置
                    startx = (int) event.getRawX();
                    starty = (int) event.getRawY();
                    break;
                case MotionEvent.ACTION_UP:// 手指离开屏幕对应事件
                    XLog.i("手指离开屏幕");
                    // 记录最后图片在窗体的位置
                    int lasty = mView.getTop();
                    int lastx = mView.getLeft();
                    onMoveEventListener.onMove(lasty,lastx);
                    break;
            }
        }
        return true;
    }
    public interface OnMoveEventListener{
        void onMove(int lasty,int lastx);
    }
}

