package cmeplaza.com.webviewtest;

import android.text.TextUtils;
import android.util.Log;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by klx on 2017/12/6.
 */

public class NetUtils {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String baseUrl = "https://hq.sinajs.cn/list=";

    public static void getDzInfo(String code, final StringResult stringResult) {
        if (TextUtils.isEmpty(code)) {
            return;
        }
        StringBuilder url = new StringBuilder(baseUrl);
        if (code.startsWith("0")) {
            url.append("sz");
        } else {
            url.append("sh");
        }
        url.append(code);
        OkHttpUtils.get()
                .url(url.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        e.printStackTrace();
                        Log.i(TAG, "出错了：" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (!TextUtils.isEmpty(response) && response.contains("=")) {
                            response = response.substring(response.indexOf("=") + 1);
                            if (stringResult != null) {
                                stringResult.stringResult(response);
                            }
                        }
                    }
                });
    }

    public interface StringResult {
        void stringResult(String result);
    }
}
