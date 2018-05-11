package cmeplaza.com.webviewtest.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by klx on 2018/3/1.
 * Gp数据库工具类
 */

public class GpDbHelper extends SQLiteOpenHelper {
    // 数据库版本号
    private static final int Version = 1;

    public GpDbHelper(Context context, String name) {
        super(context, name, null, Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table my_gp(code varchar(10),name varchar(64) ,number integer ,price float)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
