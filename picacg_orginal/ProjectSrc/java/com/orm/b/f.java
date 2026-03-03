package com.orm.b;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
/* loaded from: picacg_1.jar:com/orm/b/f.class */
public class f {
    public static String c(Class<?> cls) {
        return (cls.equals(Boolean.class) || cls.equals(Boolean.TYPE) || cls.equals(Integer.class) || cls.equals(Integer.TYPE) || cls.equals(Long.class) || cls.equals(Long.TYPE)) ? "INTEGER" : (cls.isPrimitive() || !com.orm.d.class.isAssignableFrom(cls)) ? (cls.equals(Date.class) || cls.equals(java.sql.Date.class) || cls.equals(Calendar.class)) ? "INTEGER NULL" : cls.getName().equals("[B") ? "BLOB" : (cls.equals(Double.class) || cls.equals(Double.TYPE) || cls.equals(Float.class) || cls.equals(Float.TYPE)) ? "FLOAT" : (cls.equals(String.class) || cls.equals(Character.TYPE) || cls.equals(BigDecimal.class)) ? "TEXT" : "" : "INTEGER";
    }

    public static String i(int i) {
        if (i >= 1) {
            StringBuilder sb = new StringBuilder((i * 2) - 1);
            sb.append("?");
            for (int i2 = 1; i2 < i; i2++) {
                sb.append(",?");
            }
            return sb.toString();
        }
        throw new RuntimeException("The number of arguments must be greater than or equal to 1.");
    }
}
