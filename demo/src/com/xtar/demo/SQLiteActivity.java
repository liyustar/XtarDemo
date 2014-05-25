package com.xtar.demo;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import com.xtar.demo.model.MFile;

public class SQLiteActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 打开或创建test.db数据库
        SQLiteDatabase db = openOrCreateDatabase("test.db", Context.MODE_PRIVATE, null);
        db.execSQL("DROP TABLE IF EXISTS mfiles");

        // 创建mfiles表
        db.execSQL("CREATE TABLE mfiles (_id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, len SMALLINT)");

        // 插入数据
        MFile mfile = new MFile();
        mfile.name = "john";
        mfile.len = 30;
        db.execSQL("INSERT INTO mfiles VALUES (NULL, ?, ?)", new Object[] { mfile.name, mfile.len });

        mfile.name = "david";
        mfile.len = 33;
        // ContentValues以键值对的形式存放数据
        ContentValues cv = new ContentValues();
        cv.put("name", mfile.name);
        cv.put("len", mfile.len);
        // 插入ContentValues中的数据
        db.insert("mfiles", null, cv);

        cv = new ContentValues();
        cv.put("len", 35);
        // 更新数据
        db.update("mfiles", cv, "name = ?", new String[] { "john" });

        Cursor c = db.rawQuery("SELECT * FROM mfiles WHERE len >= ?", new String[] { "33" });
        while (c.moveToNext()) {
            int _id = c.getInt(c.getColumnIndex("_id"));
            String name = c.getString(c.getColumnIndex("name"));
            int len = c.getInt(c.getColumnIndex("len"));
            Log.i("db", "_id=>" + _id + ", name=>" + name + ", len=>" + len);
        }
        c.close();

        // 删除数据
        db.delete("mfiles", "len < ?", new String[] { "35" });

        // 关闭当前数据库
        db.close();

        // 删除test.db数据
        deleteDatabase("test.db");
    }

}
