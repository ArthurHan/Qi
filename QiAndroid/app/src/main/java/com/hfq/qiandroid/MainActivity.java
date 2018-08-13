package com.hfq.qiandroid;

import android.graphics.Bitmap;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

import com.hfq.qiandroid.network.ServerUrls;
import com.hfq.qiandroid.network.ServerUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class MainActivity extends AppCompatActivity {

    private TextView tv_content;
    private Button bt_get;

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);丼
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_content = findViewById(R.id.tv_content);
        bt_get = findViewById(R.id.bt_get);
        bt_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInfo();
            }
        });
    }

    public void getInfo() {
        ServerUtil.postRequest(ServerUtil.HTTP_GET, ServerUrls.HOME_PAGE, null,
                new ServerUtil.IServerOk() {
                    @Override
                    public void onSuccess(String response) {
                        tv_content.setText(response);
                    }
                }, new ServerUtil.IServerFailed() {
                    @Override
                    public void onFailed() {
                        Toast.makeText(MainActivity.this, "net error", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
