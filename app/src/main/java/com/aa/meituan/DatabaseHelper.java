package com.aa.meituan;

import android.content.Context;
import android.database.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "encrypted.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "credentials";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String PASSPHRASE = "your-secure-passphrase";

    //构造方法
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase.loadLibs(context);
    }

    //创建数据库
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USERNAME + " TEXT, "
                + COLUMN_PASSWORD + " TEXT)");
    }

    //升级数据库,删除旧的表并重新创建新的表
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //插入用户
    public boolean insertUser(String username, String password) {
        if (isUserExists(username)) {
            return false; // 用户存在就返回false
        }

        SQLiteDatabase db = getWritableDatabase(PASSPHRASE);
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COLUMN_USERNAME + ", " + COLUMN_PASSWORD + ") VALUES (?, ?)",
                new Object[]{username, password});
        db.close();
        return true;
    }
    //检测用户的账号和密码
    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = getReadableDatabase(PASSPHRASE);
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + " = ?",
                new String[]{username, password});
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        db.close();
        return exists;
    }

    //检测用户是否存在
    public boolean isUserExists(String username) {
        SQLiteDatabase db = getReadableDatabase(PASSPHRASE);
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_USERNAME + " = ?",
                new String[]{username});
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        db.close();
        return exists;
    }
}
