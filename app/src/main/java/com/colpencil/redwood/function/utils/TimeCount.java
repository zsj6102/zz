package com.colpencil.redwood.function.utils;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/1/4.
 */
public class TimeCount extends CountDownTimer {

    private TextView textView;

    public TimeCount(long millisInFuture, long countDownInterval, TextView tv_get_yanzheng) {
        super(millisInFuture, countDownInterval);
        this.textView = tv_get_yanzheng;
    }

    @Override
    public void onFinish() {
        textView.setText("重新验证");
    }

    @Override
    public void onTick(long time) {
        // TODO Auto-generated method stub
        textView.setText(time / 1000 + "s后重发");
    }
}
