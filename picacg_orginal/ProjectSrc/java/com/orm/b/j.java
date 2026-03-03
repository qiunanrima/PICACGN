package com.orm.b;

import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.util.Log;
/* loaded from: picacg_1.jar:com/orm/b/j.class */
public class j implements SQLiteDatabase.CursorFactory {
    private boolean gY;

    public j() {
        this.gY = false;
    }

    public j(boolean z) {
        this.gY = z;
    }

    @Override // android.database.sqlite.SQLiteDatabase.CursorFactory
    public Cursor newCursor(SQLiteDatabase sQLiteDatabase, SQLiteCursorDriver sQLiteCursorDriver, String str, SQLiteQuery sQLiteQuery) {
        if (this.gY) {
            Log.d("SQL Log", sQLiteQuery.toString());
        }
        return new SQLiteCursor(sQLiteDatabase, sQLiteCursorDriver, str, sQLiteQuery);
    }
}
