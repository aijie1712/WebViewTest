package cmeplaza.com.amodule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class AMessageActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amessage);
        textView = findViewById(R.id.textView);
        EventBus.getDefault().register(this);
        EventBus.getDefault().post(new UIEvent());
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onAUIEvent(AUIEvent uiEvent) {
        textView.setText("AModule收到消息了");
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
