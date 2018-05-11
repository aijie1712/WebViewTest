package cmeplaza.com.webviewtest.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import cmeplaza.com.webviewtest.bean.GpBean;
import cmeplaza.com.webviewtest.db.GpDbHelper;

/**
 * Created by klx on 2018/3/1.
 * Gp数据库工具类
 */

public class GpDbUtils {
    private static final String DB_NAME = "my_db";
    private static final String TABLE_NAME = "my_gp";

    private static GpDbUtils dbUtils;

    private GpDbHelper gpDbHelper;

    private GpDbUtils(Context context) {
        gpDbHelper = new GpDbHelper(context.getApplicationContext(), DB_NAME);
    }

    public static GpDbUtils getInstance(Context context) {
        if (dbUtils == null) {
            synchronized (GpDbUtils.class) {
                if (dbUtils == null) {
                    dbUtils = new GpDbUtils(context);
                }
            }
        }
        return dbUtils;
    }

    public List<GpBean> getAllSaveGp() {
        SQLiteDatabase db = gpDbHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(TABLE_NAME, new String[]{"code", "name", "number", "price"},
                    "", new String[]{}, "", "", "");
            List<GpBean> result = new ArrayList<>();
            while (cursor.moveToNext()) {
                String code = cursor.getString(cursor.getColumnIndex("code"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int number = cursor.getInt(cursor.getColumnIndex("number"));
                float price = cursor.getFloat(cursor.getColumnIndex("price"));
                GpBean gpBean = new GpBean(code, name, number, price);
                result.add(gpBean);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }

        }
        return null;
    }

    public void saveGpInfo(GpBean gpBean) {
        SQLiteDatabase db = gpDbHelper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{"code"}, "code = ?", new String[]{gpBean.getCode()}, "", "", "");
        ContentValues contentValues = new ContentValues();
        contentValues.put("code", gpBean.getCode());
        contentValues.put("name", gpBean.getName());
        contentValues.put("number", gpBean.getNumber());
        contentValues.put("price", gpBean.getPrice());
        if (cursor.moveToNext()) {
            db.update(TABLE_NAME, contentValues, "code = ?", new String[]{gpBean.getCode()});
        } else {
            db.insert(TABLE_NAME, null, contentValues);
        }
        cursor.close();
        db.close();
    }

    public int delGpInfo(GpBean gpBean) {
        SQLiteDatabase db = gpDbHelper.getReadableDatabase();
        int result = db.delete(TABLE_NAME, "code = ?", new String[]{gpBean.getCode()});
        db.close();
        return result;
    }
}
