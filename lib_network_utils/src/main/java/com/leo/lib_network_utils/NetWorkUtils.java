package com.leo.lib_network_utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Build;

/**
 * Created by : Leo
 * Date : 2019/12/2
 * Describe : 获取网络状态工具类 兼容6.0以上
 */
public class NetWorkUtils {

    /**
     * 是否有网络连接
     *
     * @param context
     * @return
     */
    public static boolean isConnected(Context context) {
        boolean isConnected = false;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            isConnected = cm.getActiveNetwork() != null;
        } else {
            if (cm.getActiveNetworkInfo() == null || cm.getActiveNetworkInfo().isConnected() == false) {

                isConnected = false;
            } else {
                isConnected = true;
            }

        }
        return isConnected;
    }

    /**
     * 获取网络类型
     *
     * @param context
     * @return {@link NetWorkType}
     */
    public static NetWorkType getNetWorkType(Context context) {
        NetWorkType netWorkType = NetWorkType.NONE;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (isConnected(context)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                boolean isWifi = cm.getNetworkCapabilities(cm.getActiveNetwork()).hasTransport(NetworkCapabilities.TRANSPORT_WIFI);
                boolean isMobile = cm.getNetworkCapabilities(cm.getActiveNetwork()).hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR);

                if (isWifi) {
                    netWorkType = NetWorkType.WIFI;
                } else if (isMobile) {
                    netWorkType = NetWorkType.MOBILE;
                }
            } else {
                switch (cm.getActiveNetworkInfo().getType()) {
                    case ConnectivityManager.TYPE_WIFI:
                        netWorkType = NetWorkType.WIFI;
                        break;
                    case ConnectivityManager.TYPE_MOBILE:
                        netWorkType = NetWorkType.MOBILE;
                        break;
                    default:
                        netWorkType = NetWorkType.NONE;
                        break;
                }
            }
        }


        return netWorkType;
    }


}
