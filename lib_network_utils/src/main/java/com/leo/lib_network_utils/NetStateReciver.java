package com.leo.lib_network_utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

/**
 * Created by : Leo
 * Date : 2019/12/3
 * Describe : 网络状态监听广播
 */
public class NetStateReciver extends BroadcastReceiver {
    private OnNetStateCallBackListener onNetStateCallBackListener;

    public NetStateReciver(@NonNull OnNetStateCallBackListener onNetStateCallBackListener) {
        this.onNetStateCallBackListener = onNetStateCallBackListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        onNetStateCallBackListener.OnNetStateCallBack(NetWorkUtils.isConnected(context), NetWorkUtils.getNetWorkType(context));

    }


    public interface OnNetStateCallBackListener {
        void OnNetStateCallBack(boolean isConnected, NetWorkType netWorkType);
    }
}
