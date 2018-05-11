package cmeplaza.com.webviewtest.testchat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import cmeplaza.com.webviewtest.R;

/**
 * Created by klx on 2018/3/16.
 */

public class AFragment extends Fragment implements View.OnClickListener {
    TextView tv_data;

    public static AFragment newInstance(String data) {
        AFragment aFragment = new AFragment();
        Bundle bundle = new Bundle();
        bundle.putString("data", data);
        aFragment.setArguments(bundle);
        return aFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, null);
        Button btn_click = view.findViewById(R.id.btn_click);
        tv_data = view.findViewById(R.id.tv_data);
        btn_click.setOnClickListener(this);
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

    public String getData(){
        return tv_data.getText().toString().trim();
    }

    @Override
    public void onClick(View view) {
        if (onButtonClickListener != null) {
            onButtonClickListener.onButtonClick();
        }
    }

    public void setOnButtonClickListener(OnButtonClickListener onButtonClickListener) {
        this.onButtonClickListener = onButtonClickListener;
    }

    private OnButtonClickListener onButtonClickListener;

    public interface OnButtonClickListener {
        void onButtonClick();
    }


}
