package com.orm.b;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
/* loaded from: picacg_1.jar:com/orm/b/g.class */
public class g {
    private static Class a(String str, Context context) {
        String message;
        Class<?> cls;
        try {
            cls = Class.forName(str, true, context.getClass().getClassLoader());
        } catch (Throwable th) {
            if (th.getMessage() == null) {
                message = "getDomainClass " + str + " error";
            } else {
                message = th.getMessage();
            }
            Log.e(com.orm.d.SUGAR, message);
            cls = null;
        }
        if (cls != null) {
            if (((!com.orm.d.class.isAssignableFrom(cls) || com.orm.d.class.equals(cls)) && !cls.isAnnotationPresent(com.orm.a.e.class)) || Modifier.isAbstract(cls.getModifiers())) {
                return null;
            }
            Log.i(com.orm.d.SUGAR, "domain class : " + cls.getSimpleName());
            return cls;
        }
        return null;
    }

    private static List<Field> a(List<Field> list, Class<?> cls) {
        Collections.addAll(list, cls.getDeclaredFields());
        List<Field> list2 = list;
        if (cls.getSuperclass() != null) {
            list2 = a(list, cls.getSuperclass());
        }
        return list2;
    }

    public static void a(ContentValues contentValues, Field field, Object obj, Map<Object, Long> map) {
        field.setAccessible(true);
        Class<?> type = field.getType();
        try {
            String a = d.a(field);
            Object obj2 = field.get(obj);
            if (type.isAnnotationPresent(com.orm.a.e.class)) {
                try {
                    Field declaredField = type.getDeclaredField("id");
                    declaredField.setAccessible(true);
                    contentValues.put(a, declaredField != null ? String.valueOf(declaredField.get(obj2)) : "0");
                    return;
                } catch (NoSuchFieldException e) {
                    if (map.containsKey(obj2)) {
                        contentValues.put(a, map.get(obj2));
                        return;
                    }
                    return;
                }
            } else if (com.orm.d.class.isAssignableFrom(type)) {
                contentValues.put(a, obj2 != null ? String.valueOf(((com.orm.d) obj2).getId()) : "0");
                return;
            } else {
                if (!type.equals(Short.class) && !type.equals(Short.TYPE)) {
                    if (!type.equals(Integer.class) && !type.equals(Integer.TYPE)) {
                        if (!type.equals(Long.class) && !type.equals(Long.TYPE)) {
                            if (!type.equals(Float.class) && !type.equals(Float.TYPE)) {
                                if (!type.equals(Double.class) && !type.equals(Double.TYPE)) {
                                    if (!type.equals(Boolean.class) && !type.equals(Boolean.TYPE)) {
                                        if (type.equals(BigDecimal.class)) {
                                            try {
                                                contentValues.put(a, field.get(obj).toString());
                                                return;
                                            } catch (NullPointerException e2) {
                                                contentValues.putNull(a);
                                                return;
                                            }
                                        } else if (Timestamp.class.equals(type)) {
                                            try {
                                                contentValues.put(a, Long.valueOf(((Timestamp) field.get(obj)).getTime()));
                                                return;
                                            } catch (NullPointerException e3) {
                                                contentValues.put(a, (Long) null);
                                                return;
                                            }
                                        } else if (Date.class.equals(type)) {
                                            try {
                                                contentValues.put(a, Long.valueOf(((Date) field.get(obj)).getTime()));
                                                return;
                                            } catch (NullPointerException e4) {
                                                contentValues.put(a, (Long) null);
                                                return;
                                            }
                                        } else if (Calendar.class.equals(type)) {
                                            try {
                                                contentValues.put(a, Long.valueOf(((Calendar) field.get(obj)).getTimeInMillis()));
                                                return;
                                            } catch (NullPointerException e5) {
                                                contentValues.put(a, (Long) null);
                                                return;
                                            }
                                        } else if (type.equals(byte[].class)) {
                                            if (obj2 == null) {
                                                contentValues.put(a, "".getBytes());
                                                return;
                                            } else {
                                                contentValues.put(a, (byte[]) obj2);
                                                return;
                                            }
                                        } else if (obj2 == null) {
                                            contentValues.putNull(a);
                                            return;
                                        } else if (type.isEnum()) {
                                            contentValues.put(a, ((Enum) obj2).name());
                                            return;
                                        } else {
                                            contentValues.put(a, String.valueOf(obj2));
                                            return;
                                        }
                                    }
                                    contentValues.put(a, (Boolean) obj2);
                                    return;
                                }
                                contentValues.put(a, (Double) obj2);
                                return;
                            }
                            contentValues.put(a, (Float) obj2);
                            return;
                        }
                        contentValues.put(a, (Long) obj2);
                        return;
                    }
                    contentValues.put(a, (Integer) obj2);
                    return;
                }
                contentValues.put(a, (Short) obj2);
                return;
            }
        } catch (IllegalAccessException e6) {
            Log.e(com.orm.d.SUGAR, e6.getMessage());
        }
        Log.e(com.orm.d.SUGAR, e6.getMessage());
    }

    public static void a(Cursor cursor, Field field, Object obj) {
        field.setAccessible(true);
        try {
            Class<?> type = field.getType();
            String a = d.a(field);
            int columnIndex = cursor.getColumnIndex(a);
            if (columnIndex < 0) {
                Log.e("SUGAR", "Invalid colName, you should upgrade database");
            } else if (cursor.isNull(columnIndex)) {
            } else {
                if (a.equalsIgnoreCase("id")) {
                    field.set(obj, Long.valueOf(cursor.getLong(columnIndex)));
                    return;
                }
                if (!type.equals(Long.TYPE) && !type.equals(Long.class)) {
                    if (type.equals(String.class)) {
                        String string = cursor.getString(columnIndex);
                        Object obj2 = string;
                        if (string != null) {
                            obj2 = string;
                            if (string.equals("null")) {
                                obj2 = null;
                            }
                        }
                        field.set(obj, obj2);
                        return;
                    }
                    if (!type.equals(Double.TYPE) && !type.equals(Double.class)) {
                        if (!type.equals(Boolean.TYPE) && !type.equals(Boolean.class)) {
                            if (!type.equals(Integer.TYPE) && !type.equals(Integer.class)) {
                                if (!type.equals(Float.TYPE) && !type.equals(Float.class)) {
                                    if (!type.equals(Short.TYPE) && !type.equals(Short.class)) {
                                        if (type.equals(BigDecimal.class)) {
                                            String string2 = cursor.getString(columnIndex);
                                            field.set(obj, (string2 == null || !string2.equals("null")) ? new BigDecimal(string2) : null);
                                            return;
                                        } else if (type.equals(Timestamp.class)) {
                                            field.set(obj, new Timestamp(cursor.getLong(columnIndex)));
                                            return;
                                        } else if (type.equals(Date.class)) {
                                            field.set(obj, new Date(cursor.getLong(columnIndex)));
                                            return;
                                        } else if (type.equals(Calendar.class)) {
                                            long j = cursor.getLong(columnIndex);
                                            Calendar calendar = Calendar.getInstance();
                                            calendar.setTimeInMillis(j);
                                            field.set(obj, calendar);
                                            return;
                                        } else if (type.equals(byte[].class)) {
                                            if (cursor.getBlob(columnIndex) == null) {
                                                field.set(obj, "".getBytes());
                                                return;
                                            } else {
                                                field.set(obj, cursor.getBlob(columnIndex));
                                                return;
                                            }
                                        } else if (Enum.class.isAssignableFrom(type)) {
                                            try {
                                                field.set(obj, field.getType().getMethod("valueOf", String.class).invoke(field.getType(), cursor.getString(columnIndex)));
                                                return;
                                            } catch (Exception e) {
                                                Log.e(com.orm.d.SUGAR, "Enum cannot be read from Sqlite3 database. Please check the type of field " + field.getName());
                                                return;
                                            }
                                        } else {
                                            Log.e(com.orm.d.SUGAR, "Class cannot be read from Sqlite3 database. Please check the type of field " + field.getName() + "(" + field.getType().getName() + ")");
                                            return;
                                        }
                                    }
                                    field.set(obj, Short.valueOf(cursor.getShort(columnIndex)));
                                    return;
                                }
                                field.set(obj, Float.valueOf(cursor.getFloat(columnIndex)));
                                return;
                            }
                            field.set(obj, Integer.valueOf(cursor.getInt(columnIndex)));
                            return;
                        }
                        field.set(obj, Boolean.valueOf(cursor.getString(columnIndex).equals("1")));
                        return;
                    }
                    field.set(obj, Double.valueOf(cursor.getDouble(columnIndex)));
                    return;
                }
                field.set(obj, Long.valueOf(cursor.getLong(columnIndex)));
            }
        } catch (IllegalAccessException e2) {
            Log.e("field set error", e2.getMessage());
        } catch (IllegalArgumentException e3) {
            Log.e("field set error", e3.getMessage());
        }
    }

    private static void a(File file, List<String> list, String str) {
        File[] listFiles;
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                if ("".equals(str)) {
                    a(file2, list, file.getName());
                } else {
                    a(file2, list, str + "." + file.getName());
                }
            }
            return;
        }
        String name = file.getName();
        String str2 = name;
        if (name.endsWith(".class")) {
            str2 = name.substring(0, name.length() - ".class".length());
        }
        if ("".equals(str)) {
            list.add(str2);
            return;
        }
        list.add(str + "." + str2);
    }

    public static List<Field> d(Class cls) {
        List<Field> e = h.e(cls);
        if (e != null) {
            return e;
        }
        Log.d(com.orm.d.SUGAR, "Fetching properties");
        ArrayList<Field> arrayList = new ArrayList();
        a(arrayList, cls);
        ArrayList arrayList2 = new ArrayList();
        for (Field field : arrayList) {
            if (!field.isAnnotationPresent(com.orm.a.b.class) && !Modifier.isStatic(field.getModifiers()) && !Modifier.isTransient(field.getModifiers())) {
                arrayList2.add(field);
            }
        }
        h.a(cls, arrayList2);
        return arrayList2;
    }

    private static List<String> q(Context context) throws PackageManager.NameNotFoundException, IOException {
        String l = a.l(context);
        ArrayList arrayList = new ArrayList();
        try {
            for (String str : c.q(context)) {
                if (str.startsWith(l)) {
                    arrayList.add(str);
                }
            }
        } catch (NullPointerException e) {
            Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources("");
            while (resources.hasMoreElements()) {
                ArrayList<String> arrayList2 = new ArrayList();
                String file = resources.nextElement().getFile();
                if (file.contains("bin") || file.contains("classes") || file.contains("retrolambda")) {
                    for (File file2 : new File(file).listFiles()) {
                        a(file2, arrayList2, "");
                    }
                    for (String str2 : arrayList2) {
                        if (str2.startsWith(l)) {
                            arrayList.add(str2);
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    public static List<Class> r(Context context) {
        ArrayList arrayList = new ArrayList();
        try {
            for (String str : q(context)) {
                Class a = a(str, context);
                if (a != null) {
                    arrayList.add(a);
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(com.orm.d.SUGAR, e.getMessage());
        } catch (IOException e2) {
            Log.e(com.orm.d.SUGAR, e2.getMessage());
        }
        return arrayList;
    }
}
