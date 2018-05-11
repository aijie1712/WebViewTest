package cmeplaza.com.webviewtest;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import cmeplaza.com.amodule.AMessageActivity;
import cmeplaza.com.amodule.AUIEvent;
import cmeplaza.com.amodule.UIEvent;
import cmeplaza.com.webviewtest.gif.GifTestActivity;
import cmeplaza.com.webviewtest.multidb.MultiDbActivity;
import cmeplaza.com.webviewtest.testchat.HomeActivity;
import cmeplaza.com.webviewtest.utils.FileUtils;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_CHOOSE = 1001;

    TextView tv_first;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_first = findViewById(R.id.tv_first);
        EventBus.getDefault().register(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                EventBus.getDefault().post(new AUIEvent());
            }
        }, 5000);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_first:
                startActivity(new Intent(MainActivity.this, GifTestActivity.class));
                break;
            case R.id.button1:
                startActivity(new Intent(MainActivity.this, GPInfoActivity.class));
                break;
            case R.id.btn_test_event:
                startActivity(new Intent(MainActivity.this, AMessageActivity.class));
                break;
            case R.id.btn_test_multi_db:
                startActivity(new Intent(MainActivity.this, MultiDbActivity.class));
                break;
        }
    }


    private void chooseImages() {
        Matisse.from(MainActivity.this)
                .choose(MimeType.allOf())
                .countable(true)
                .maxSelectable(9)
//                        .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
//                        .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f)
                .imageEngine(new GlideEngine())
                .forResult(REQUEST_CODE_CHOOSE);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUIEvent(UIEvent uiEvent) {
        tv_first.setText("收到消息了");
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
