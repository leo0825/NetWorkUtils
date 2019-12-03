package com.leo.networkutils;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.leo.lib_network_utils.NetStateReciver;
import com.leo.lib_network_utils.NetWorkType;

public class MainActivity extends AppCompatActivity {

    private NetStateReciver netStateReciver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView tvText = findViewById(R.id.tv_text);

        //动态注册广播，适配7.0
        IntentFilter filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        netStateReciver = new NetStateReciver(new NetStateReciver.OnNetStateCallBackListener() {
            @Override
            public void OnNetStateCallBack(boolean isConnected, NetWorkType netWorkType) {
                tvText.setText("当前网络是否连接："+isConnected +"\n"+"当前网络连接类型："+netWorkType.name());
            }
        });

        registerReceiver(netStateReciver,filter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(netStateReciver);
    }
}
