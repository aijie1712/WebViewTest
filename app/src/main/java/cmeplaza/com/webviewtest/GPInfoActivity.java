package cmeplaza.com.webviewtest;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import cmeplaza.com.webviewtest.adapter.GpListAdapter;
import cmeplaza.com.webviewtest.bean.GpBean;
import cmeplaza.com.webviewtest.utils.GpDbUtils;

public class GPInfoActivity extends AppCompatActivity implements View.OnClickListener {
    DecimalFormat format = new DecimalFormat("###,###,###.##");
    private EditText et_input, et_input_c, et_input_s;
    private TextView tv_result;
    private List<GpBean> mDatas;
    private GpListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpinfo);

        et_input = findViewById(R.id.et_input);
        et_input_c = findViewById(R.id.et_input_c);
        et_input_s = findViewById(R.id.et_input_s);
        tv_result = findViewById(R.id.tv_result);
        ListView listView = findViewById(R.id.listView);
        findViewById(R.id.btn_get).setOnClickListener(this);

        mDatas = new ArrayList<>();
        adapter = new GpListAdapter(this, mDatas);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GpBean bean = mDatas.get(position);
                et_input.setText(bean.getCode());
                et_input_c.setText(format.format(bean.getPrice()));
                et_input_s.setText(String.valueOf(bean.getNumber()));
                getInfo(position);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(GPInfoActivity.this)
                        .setCancelable(true)
                        .setMessage("确定删除？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                GpDbUtils.getInstance(GPInfoActivity.this).delGpInfo(mDatas.get(position));
                                mDatas.remove(position);
                                adapter.notifyDataSetChanged();
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
                return true;
            }
        });

        initData();
    }

    private void getInfo(final int position) {
        NetUtils.getDzInfo(mDatas.get(position).getCode(), new NetUtils.StringResult() {
            @Override
            public void stringResult(String result) {
                String[] splitResult = result.split(",");
                if (splitResult.length > 4) {
                    String name = splitResult[0];
                    String now = splitResult[3];
                    String maxPrice = splitResult[4];
                    String minPrice = splitResult[5];
                    GpBean gpBean = mDatas.get(position);
                    gpBean.setName(name);
                    gpBean.setMinPrice(Float.parseFloat(minPrice));
                    gpBean.setMaxPrice(Float.parseFloat(maxPrice));
                    gpBean.setNowPrice(Float.parseFloat(now));
                    GpDbUtils.getInstance(GPInfoActivity.this).saveGpInfo(mDatas.get(position));
                    float totalResult = 0.0f;
                    gpBean.setResult(gpBean.getNumber() * (gpBean.getNowPrice() - gpBean.getPrice()));
                    for (GpBean mData : mDatas) {
                        totalResult += mData.getResult();
                    }
                    tv_result.setText(getString(R.string.result, format.format(totalResult)));
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void initData() {
        getAllSaveGpList();
    }

    private void getAllSaveGpList() {
        List<GpBean> allSaveGpList = GpDbUtils.getInstance(this).getAllSaveGp();
        if (allSaveGpList != null && allSaveGpList.size() > 0) {
            mDatas.clear();
            mDatas.addAll(allSaveGpList);
            adapter.notifyDataSetChanged();
        }
        getAllGpResult();
    }

    private void getAllGpResult() {
        for (int i = 0; i < mDatas.size(); i++) {
            getInfo(i);
        }
    }

    @Override
    public void onClick(View v) {
        String code = et_input.getText().toString().trim();
        String price = et_input_c.getText().toString().trim();
        String number = et_input_s.getText().toString().trim();
        if (TextUtils.isEmpty(code) && TextUtils.isEmpty(price) && TextUtils.isEmpty(number)) {
            getAllGpResult();
            return;
        }

        if (TextUtils.isEmpty(code) || TextUtils.isEmpty(price) || TextUtils.isEmpty(number)) {
            Toast.makeText(this, "数据错误", Toast.LENGTH_SHORT).show();
            return;
        }

        et_input.setText("");
        et_input_c.setText("");
        et_input_s.setText("");

        GpBean gpBean = new GpBean(code, "", Integer.parseInt(number), Float.parseFloat(price));
        GpDbUtils.getInstance(this).saveGpInfo(gpBean);
        initData();
    }
}
