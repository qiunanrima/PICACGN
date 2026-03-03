package com.orm;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteStatement;
import android.text.TextUtils;
import android.util.Log;
import com.orm.a.e;
import com.orm.b.f;
import com.orm.b.g;
import com.orm.b.i;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
/* loaded from: picacg_1.jar:com/orm/d.class */
public class d {
    public static final String SUGAR = "Sugar";
    private Long id = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: picacg_1.jar:com/orm/d$a.class */
    public static class a<E> implements Iterator<E> {
        Class<E> gU;
        Cursor gV;

        public a(Class<E> cls, Cursor cursor) {
            this.gU = cls;
            this.gV = cursor;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return (this.gV == null || this.gV.isClosed() || this.gV.isAfterLast()) ? false : true;
        }

        @Override // java.util.Iterator
        public E next() {
            E e;
            E e2;
            if (this.gV == null || this.gV.isAfterLast()) {
                throw new NoSuchElementException();
            }
            if (this.gV.isBeforeFirst()) {
                this.gV.moveToFirst();
            }
            try {
                try {
                    e = this.gU.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                    try {
                        d.inflate(this.gV, e, b.bj().bn());
                        e2 = e;
                    } catch (Exception e3) {
                        e = e3;
                        e.printStackTrace();
                        this.gV.moveToNext();
                        e2 = e;
                        if (this.gV.isAfterLast()) {
                            this.gV.close();
                            e2 = e;
                        }
                        return e2;
                    }
                } finally {
                    this.gV.moveToNext();
                    if (this.gV.isAfterLast()) {
                        this.gV.close();
                    }
                }
            } catch (Exception e4) {
                e = e4;
                e = null;
            }
            return e2;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static <T> long count(Class<?> cls) {
        return count(cls, null, null, null, null, null);
    }

    public static <T> long count(Class<?> cls, String str, String[] strArr) {
        return count(cls, str, strArr, null, null, null);
    }

    public static <T> long count(Class<?> cls, String str, String[] strArr, String str2, String str3, String str4) {
        try {
            SQLiteStatement compileStatement = getSugarDataBase().compileStatement("SELECT count(*) FROM " + com.orm.b.d.b(cls) + (TextUtils.isEmpty(str) ? "" : " where " + str));
            if (strArr != null) {
                for (int length = strArr.length; length != 0; length--) {
                    compileStatement.bindString(length, strArr[length - 1]);
                }
            }
            try {
                return compileStatement.simpleQueryForLong();
            } finally {
                compileStatement.close();
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
            return -1L;
        }
    }

    public static boolean delete(Object obj) {
        Class<?> cls = obj.getClass();
        if (!cls.isAnnotationPresent(e.class)) {
            if (d.class.isAssignableFrom(cls)) {
                return ((d) obj).delete();
            }
            Log.i(SUGAR, "Cannot delete object: " + obj.getClass().getSimpleName() + " - not persisted");
            return false;
        }
        try {
            Field declaredField = cls.getDeclaredField("id");
            boolean z = true;
            declaredField.setAccessible(true);
            Long l = (Long) declaredField.get(obj);
            if (l == null || l.longValue() <= 0) {
                Log.i(SUGAR, "Cannot delete object: " + obj.getClass().getSimpleName() + " - object has not been saved");
                return false;
            }
            if (getSugarDataBase().delete(com.orm.b.d.b(cls), "Id=?", new String[]{l.toString()}) != 1) {
                z = false;
            }
            Log.i(SUGAR, cls.getSimpleName() + " deleted : " + l);
            return z;
        } catch (IllegalAccessException e) {
            Log.i(SUGAR, "Cannot delete object: " + obj.getClass().getSimpleName() + " - can't access id");
            return false;
        } catch (NoSuchFieldException e2) {
            Log.i(SUGAR, "Cannot delete object: " + obj.getClass().getSimpleName() + " - annotated object has no id");
            return false;
        }
    }

    public static <T> int deleteAll(Class<T> cls) {
        return deleteAll(cls, null, new String[0]);
    }

    public static <T> int deleteAll(Class<T> cls, String str, String... strArr) {
        return getSugarDataBase().delete(com.orm.b.d.b(cls), str, strArr);
    }

    public static <T> int deleteInTx(Collection<T> collection) {
        int i;
        SQLiteDatabase sugarDataBase = getSugarDataBase();
        try {
            try {
                sugarDataBase.beginTransaction();
                sugarDataBase.setLockingEnabled(false);
                i = 0;
                for (T t : collection) {
                    if (delete(t)) {
                        i++;
                    }
                }
                sugarDataBase.setTransactionSuccessful();
            } catch (Exception e) {
                Log.i(SUGAR, "Error in deleting in transaction " + e.getMessage());
                sugarDataBase.endTransaction();
                sugarDataBase.setLockingEnabled(true);
                i = 0;
            }
            return i;
        } finally {
            sugarDataBase.endTransaction();
            sugarDataBase.setLockingEnabled(true);
        }
    }

    public static <T> int deleteInTx(T... tArr) {
        return deleteInTx(Arrays.asList(tArr));
    }

    public static void executeQuery(String str, String... strArr) {
        getSugarDataBase().execSQL(str, strArr);
    }

    public static <T> List<T> find(Class<T> cls, String str, String... strArr) {
        return find(cls, str, strArr, null, null, null);
    }

    public static <T> List<T> find(Class<T> cls, String str, String[] strArr, String str2, String str3, String str4) {
        return getEntitiesFromCursor(getSugarDataBase().query(com.orm.b.d.b(cls), null, str, strArr, str2, null, str3, str4), cls);
    }

    public static <T> Iterator<T> findAll(Class<T> cls) {
        return findAsIterator(cls, null, null, null, null, null);
    }

    public static <T> Iterator<T> findAsIterator(Class<T> cls, String str, String... strArr) {
        return findAsIterator(cls, str, strArr, null, null, null);
    }

    public static <T> Iterator<T> findAsIterator(Class<T> cls, String str, String[] strArr, String str2, String str3, String str4) {
        return new a(cls, getSugarDataBase().query(com.orm.b.d.b(cls), null, str, strArr, str2, null, str3, str4));
    }

    public static <T> T findById(Class<T> cls, Integer num) {
        return (T) findById(cls, Long.valueOf(num.intValue()));
    }

    public static <T> T findById(Class<T> cls, Long l) {
        List find = find(cls, "id=?", new String[]{String.valueOf(l)}, null, null, "1");
        if (find.isEmpty()) {
            return null;
        }
        return (T) find.get(0);
    }

    public static <T> List<T> findById(Class<T> cls, String[] strArr) {
        return find(cls, "id IN (" + f.i(strArr.length) + ")", strArr);
    }

    public static <T> List<T> findWithQuery(Class<T> cls, String str, String... strArr) {
        return getEntitiesFromCursor(getSugarDataBase().rawQuery(str, strArr), cls);
    }

    public static <T> Iterator<T> findWithQueryAsIterator(Class<T> cls, String str, String... strArr) {
        return new a(cls, getSugarDataBase().rawQuery(str, strArr));
    }

    public static <T> T first(Class<T> cls) {
        List findWithQuery = findWithQuery(cls, "SELECT * FROM " + com.orm.b.d.b(cls) + " ORDER BY ID ASC LIMIT 1", new String[0]);
        if (findWithQuery.isEmpty()) {
            return null;
        }
        return (T) findWithQuery.get(0);
    }

    public static <T> Cursor getCursor(Class<T> cls, String str, String[] strArr, String str2, String str3, String str4) {
        return new i(getSugarDataBase().query(com.orm.b.d.b(cls), null, str, strArr, str2, null, str3, str4));
    }

    public static <T> List<T> getEntitiesFromCursor(Cursor cursor, Class<T> cls) {
        ArrayList arrayList = new ArrayList();
        while (cursor.moveToNext()) {
            try {
                try {
                    T newInstance = cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                    inflate(cursor, newInstance, b.bj().bn());
                    arrayList.add(newInstance);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } finally {
                cursor.close();
            }
        }
        return arrayList;
    }

    private static SQLiteDatabase getSugarDataBase() {
        return b.bj().bm().bo();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void inflate(Cursor cursor, Object obj, Map<Object, Long> map) {
        List<Field> d = g.d(obj.getClass());
        if (!map.containsKey(obj)) {
            map.put(obj, Long.valueOf(cursor.getLong(cursor.getColumnIndex("ID"))));
        }
        for (Field field : d) {
            field.setAccessible(true);
            Class<?> type = field.getType();
            if (isSugarEntity(type)) {
                try {
                    long j = cursor.getLong(cursor.getColumnIndex(com.orm.b.d.a(field)));
                    field.set(obj, j > 0 ? findById(type, Long.valueOf(j)) : null);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else {
                g.a(cursor, field, obj);
            }
        }
    }

    public static boolean isSugarEntity(Class<?> cls) {
        return cls.isAnnotationPresent(e.class) || d.class.isAssignableFrom(cls);
    }

    public static <T> T last(Class<T> cls) {
        List findWithQuery = findWithQuery(cls, "SELECT * FROM " + com.orm.b.d.b(cls) + " ORDER BY ID DESC LIMIT 1", new String[0]);
        if (findWithQuery.isEmpty()) {
            return null;
        }
        return (T) findWithQuery.get(0);
    }

    public static <T> List<T> listAll(Class<T> cls) {
        return find(cls, null, null, null, null, null);
    }

    public static <T> List<T> listAll(Class<T> cls, String str) {
        return find(cls, null, null, null, str, null);
    }

    static long save(SQLiteDatabase sQLiteDatabase, Object obj) {
        Map<Object, Long> bn = b.bj().bn();
        List<Field> d = g.d(obj.getClass());
        ContentValues contentValues = new ContentValues(d.size());
        Field field = null;
        for (Field field2 : d) {
            g.a(contentValues, field2, obj, bn);
            if (field2.getName().equals("id")) {
                field = field2;
            }
        }
        if (isSugarEntity(obj.getClass()) && bn.containsKey(obj)) {
            contentValues.put("id", bn.get(obj));
        }
        long insertWithOnConflict = sQLiteDatabase.insertWithOnConflict(com.orm.b.d.b(obj.getClass()), null, contentValues, 5);
        if (obj.getClass().isAnnotationPresent(e.class)) {
            if (field != null) {
                field.setAccessible(true);
                try {
                    field.set(obj, new Long(insertWithOnConflict));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else {
                bn.put(obj, Long.valueOf(insertWithOnConflict));
            }
        } else if (d.class.isAssignableFrom(obj.getClass())) {
            ((d) obj).setId(Long.valueOf(insertWithOnConflict));
        }
        Log.i(SUGAR, obj.getClass().getSimpleName() + " saved : " + insertWithOnConflict);
        return insertWithOnConflict;
    }

    public static long save(Object obj) {
        return save(getSugarDataBase(), obj);
    }

    public static <T> void saveInTx(Collection<T> collection) {
        SQLiteDatabase sugarDataBase = getSugarDataBase();
        try {
            try {
                sugarDataBase.beginTransaction();
                sugarDataBase.setLockingEnabled(false);
                for (T t : collection) {
                    save(t);
                }
                sugarDataBase.setTransactionSuccessful();
            } catch (Exception e) {
                Log.i(SUGAR, "Error in saving in transaction " + e.getMessage());
            }
        } finally {
            sugarDataBase.endTransaction();
            sugarDataBase.setLockingEnabled(true);
        }
    }

    public static <T> void saveInTx(T... tArr) {
        saveInTx(Arrays.asList(tArr));
    }

    static long update(SQLiteDatabase sQLiteDatabase, Object obj) {
        Map<Object, Long> bn = b.bj().bn();
        List<Field> d = g.d(obj.getClass());
        ContentValues contentValues = new ContentValues(d.size());
        StringBuilder sb = new StringBuilder();
        ArrayList arrayList = new ArrayList();
        for (Field field : d) {
            if (field.isAnnotationPresent(com.orm.a.f.class)) {
                try {
                    field.setAccessible(true);
                    String a2 = com.orm.b.d.a(field);
                    Object obj2 = field.get(obj);
                    sb.append(a2);
                    sb.append(" = ?");
                    arrayList.add(String.valueOf(obj2));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else if (!field.getName().equals("id")) {
                g.a(contentValues, field, obj, bn);
            }
        }
        long update = sQLiteDatabase.update(com.orm.b.d.b(obj.getClass()), contentValues, sb.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]));
        return update == 0 ? save(sQLiteDatabase, obj) : update;
    }

    public static long update(Object obj) {
        return update(getSugarDataBase(), obj);
    }

    public static <T> void updateInTx(Collection<T> collection) {
        SQLiteDatabase sugarDataBase = getSugarDataBase();
        try {
            try {
                sugarDataBase.beginTransaction();
                sugarDataBase.setLockingEnabled(false);
                for (T t : collection) {
                    update(t);
                }
                sugarDataBase.setTransactionSuccessful();
            } catch (Exception e) {
                Log.i(SUGAR, "Error in saving in transaction " + e.getMessage());
            }
        } finally {
            sugarDataBase.endTransaction();
            sugarDataBase.setLockingEnabled(true);
        }
    }

    public static <T> void updateInTx(T... tArr) {
        updateInTx(Arrays.asList(tArr));
    }

    public boolean delete() {
        Long id = getId();
        Class<?> cls = getClass();
        boolean z = false;
        if (id == null || id.longValue() <= 0) {
            Log.i(SUGAR, "Cannot delete object: " + cls.getSimpleName() + " - object has not been saved");
            return false;
        }
        Log.i(SUGAR, cls.getSimpleName() + " deleted : " + id);
        if (getSugarDataBase().delete(com.orm.b.d.b(cls), "Id=?", new String[]{id.toString()}) == 1) {
            z = true;
        }
        return z;
    }

    public Long getId() {
        return this.id;
    }

    void inflate(Cursor cursor) {
        inflate(cursor, this, b.bj().bn());
    }

    public long save() {
        return save(getSugarDataBase(), this);
    }

    public void setId(Long l) {
        this.id = l;
    }

    public long update() {
        return update(getSugarDataBase(), this);
    }
}
