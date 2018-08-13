package com.hfq.qiandroid.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

//存储工具类
public class QiStorage {

    private static final String PREF_NAME = "QiStorage";
    private Context mContext;
    public static QiStorage mInstance = null;

    private QiStorage(Context context) {
        mContext = context.getApplicationContext();
    }

    public static QiStorage getInstance(Context context) {
        if (mInstance != null)
            return mInstance;
        synchronized (QiStorage.class) {
            if (mInstance == null) {
                mInstance = new QiStorage(context);
            }
        }
        return mInstance;
    }

    private String getPrefKey(String frefix, String uid) {
        return String.format("%s_%s", frefix, uid);
    }

    private String getPrefValueString(String key) {
        SharedPreferences sp = mContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sp.getString(key, null);
    }

    private void updatePref(String prefKey, String value) {
        SharedPreferences sp = mContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        if (TextUtils.isEmpty(value)) {
            ed.remove(prefKey);
        } else {
            ed.putString(prefKey, value);
        }
        ed.apply();
    }

}
