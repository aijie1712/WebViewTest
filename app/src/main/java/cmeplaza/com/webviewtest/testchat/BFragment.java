package cmeplaza.com.webviewtest.testchat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cmeplaza.com.webviewtest.R;

/**
 * Created by klx on 2018/3/16.
 */

public class BFragment extends Fragment {

    public static BFragment newInstance(String data) {
        BFragment bFragment = new BFragment();
        Bundle bundle = new Bundle();
        bundle.putString("data", data);
        bFragment.setArguments(bundle);
        return bFragment;
    }

    TextView tv_data;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b, null);
        tv_data = view.findViewById(R.id.tv_data);
        Log.i("aijie", "创建Fragment");
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            String data = bundle.getString("data");
            if (!TextUtils.isEmpty(data)) {
                tv_data.setText(data);
            }
        }
    }

    public void reSetData(String data) {
        Log.i("aijie", "重置数据");
        if (tv_data != null) {
            tv_data.setText(data);
        }
    }

    public void onBackPressed() {
        tv_data.setText("");
    }
}
