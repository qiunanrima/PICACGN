package com.orm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.orm.b.j;
/* loaded from: picacg_1.jar:com/orm/c.class */
public class c extends SQLiteOpenHelper {
    private final a gR;
    private SQLiteDatabase gS;
    private int gT;

    public c(Context context) {
        super(context, com.orm.b.a.m(context), new j(com.orm.b.a.n(context)), com.orm.b.a.k(context));
        this.gT = 0;
        this.gR = new a(context);
    }

    public SQLiteDatabase bo() {
        SQLiteDatabase sQLiteDatabase;
        synchronized (this) {
            if (this.gS == null) {
                this.gS = getWritableDatabase();
            }
            sQLiteDatabase = this.gS;
        }
        return sQLiteDatabase;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper, java.lang.AutoCloseable
    public void close() {
        synchronized (this) {
            Log.d("SUGAR", "getReadableDatabase");
            this.gT--;
            if (this.gT == 0) {
                Log.d("SUGAR", "closing");
                super.close();
            }
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public SQLiteDatabase getReadableDatabase() {
        SQLiteDatabase readableDatabase;
        synchronized (this) {
            Log.d("SUGAR", "getReadableDatabase");
            this.gT++;
            readableDatabase = super.getReadableDatabase();
        }
        return readableDatabase;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        this.gR.a(sQLiteDatabase);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        this.gR.a(sQLiteDatabase, i, i2);
    }
}
