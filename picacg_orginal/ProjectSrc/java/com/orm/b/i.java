package com.orm.b;

import android.database.Cursor;
import android.database.CursorWrapper;
/* loaded from: picacg_1.jar:com/orm/b/i.class */
public class i extends CursorWrapper {
    public i(Cursor cursor) {
        super(cursor);
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public int getColumnIndex(String str) {
        String str2 = str;
        if (str.equals("_id")) {
            str2 = "ID";
        }
        return super.getColumnIndex(str2);
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public int getColumnIndexOrThrow(String str) throws IllegalArgumentException {
        try {
            return super.getColumnIndexOrThrow(str);
        } catch (IllegalArgumentException e) {
            if (str.equals("_id")) {
                return super.getColumnIndexOrThrow("ID");
            }
            throw e;
        }
    }
}
