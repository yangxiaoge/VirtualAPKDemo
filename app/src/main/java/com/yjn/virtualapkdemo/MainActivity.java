package com.yjn.virtualapkdemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.didi.virtualapk.PluginManager;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 加载插件
     *
     * @param view
     */
    public void loadPlugin(View view) {
        PluginManager pluginManager = PluginManager.getInstance(this);
        //此处是当查看插件apk是否存在,如果存在就去加载(比如修改线上的bug,把插件apk下载到sdcard的根目录下取名为plugin-release.apk)
        File apk = new File(Environment.getExternalStorageDirectory(), "plugin-release.apk");
        if (apk.exists()) {
            try {
                pluginManager.loadPlugin(apk);
                Toast.makeText(this, "插件加载成功", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "插件加载异常！", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 跳转插件apk的相应页面
     *
     * @param view
     */
    public void go2PluginActivity(View view) {
        try {
            Intent intent = new Intent();
            intent.setClassName("com.yjn.plugin", "com.yjn.plugin.ScrollingActivity");
            intent.putExtra("data","我是宿主app传来的消息");
            intent.putExtra("amsg","我是宿主app传来的消息");
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "请先加载插件", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
