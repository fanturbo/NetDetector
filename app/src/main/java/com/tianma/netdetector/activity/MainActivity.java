package com.tianma.netdetector.activity;

import android.app.Activity;
import android.app.Service;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.TextView;

import com.tianma.netdetector.R;
import com.tianma.netdetector.lib.NetworkType;

public class MainActivity extends BaseActivity {

    private TextView networkStateTv;
    private View netErrorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        networkStateTv = (TextView) findViewById(R.id.network_state_text);
        netErrorView = findViewById(R.id.net_error_view);
    }

    @Override
    protected boolean needRegisterNetworkChangeObserver() {
        return true;
    }

    @Override
    public void onNetConnected(NetworkType networkType) {
        networkStateTv.setVisibility(View.VISIBLE);
        networkStateTv.setText(networkType.toString());
        netErrorView.setVisibility(View.GONE);
        vibrate(this, 500);
    }

    @Override
    public void onNetDisconnected() {
        networkStateTv.setVisibility(View.INVISIBLE);
        netErrorView.setVisibility(View.VISIBLE);
        vibrate(this, 500);
    }

    //震动milliseconds毫秒
    public static void vibrate(final Activity activity, long milliseconds) {
        Vibrator vib = (Vibrator) activity.getSystemService(Service.VIBRATOR_SERVICE);
        vib.vibrate(milliseconds);
    }

    //以pattern[]方式震动
    public static void vibrate(final Activity activity, long[] pattern, int repeat) {
        Vibrator vib = (Vibrator) activity.getSystemService(Service.VIBRATOR_SERVICE);
        vib.vibrate(pattern, repeat);
    }

    //取消震动
    public static void virateCancle(final Activity activity) {
        Vibrator vib = (Vibrator) activity.getSystemService(Service.VIBRATOR_SERVICE);
        vib.cancel();
    }
}
