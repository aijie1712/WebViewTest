package cmeplaza.com.webviewtest.dbbean;

import org.litepal.crud.DataSupport;

/**
 * Created by klx on 2018/3/29.
 * 测试多数据库
 */

public class TestBean extends DataSupport {
    private String myId;
    private String myName;

    public TestBean(String myId, String myName) {
        this.myId = myId;
        this.myName = myName;
    }

    public String getMyId() {
        return myId;
    }

    public void setMyId(String myId) {
        this.myId = myId;
    }

    public String getMyName() {
        return myName;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }
}
