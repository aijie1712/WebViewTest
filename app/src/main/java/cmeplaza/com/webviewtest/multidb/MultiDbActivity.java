package cmeplaza.com.webviewtest.multidb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.litepal.LitePal;
import org.litepal.LitePalDB;
import org.litepal.crud.DataSupport;

import java.util.List;

import cmeplaza.com.webviewtest.R;
import cmeplaza.com.webviewtest.dbbean.TestBean;

public class MultiDbActivity extends AppCompatActivity {
    private EditText et_input;
    private TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_db);
        et_input = findViewById(R.id.et_input);
        tv_result = findViewById(R.id.tv_result);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_create1:
                LitePalDB litePalDB = LitePalDB.fromDefault("create1");
                LitePal.use(litePalDB);
                break;
            case R.id.btn_create2:
                litePalDB = LitePalDB.fromDefault("create2");
                LitePal.use(litePalDB);
                break;
            case R.id.btn_use1:
                LitePal.use(LitePalDB.fromDefault("create1"));
                break;
            case R.id.btn_use2:
                LitePal.use(LitePalDB.fromDefault("create2"));
                break;
            case R.id.btn_add_data:
                String name = et_input.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    return;
                }
                et_input.setText("");
                TestBean testBean = new TestBean("111", name);
                testBean.save();
                break;
            case R.id.btn_delete:
                DataSupport.deleteAll(TestBean.class);
                break;
            case R.id.btn_select:
                List<TestBean> testBeanList = DataSupport.findAll(TestBean.class);
                tv_result.setText("");
                for (TestBean bean : testBeanList) {
                    tv_result.append(bean.getMyId() + " : " + bean.getMyName());
                }
                break;
        }
    }
}
