package com.orm.b;

import java.lang.reflect.Field;
/* loaded from: picacg_1.jar:com/orm/b/d.class */
public class d {
    public static String a(Field field) {
        return field.isAnnotationPresent(com.orm.a.a.class) ? ((com.orm.a.a) field.getAnnotation(com.orm.a.a.class)).name() : z(field.getName());
    }

    public static String b(Class<?> cls) {
        if (cls.isAnnotationPresent(com.orm.a.e.class)) {
            com.orm.a.e eVar = (com.orm.a.e) cls.getAnnotation(com.orm.a.e.class);
            return "".equals(eVar.name()) ? z(cls.getSimpleName()) : eVar.name();
        }
        return z(cls.getSimpleName());
    }

    public static String z(String str) {
        if (str.equalsIgnoreCase("_id")) {
            return "_id";
        }
        StringBuilder sb = new StringBuilder();
        char[] charArray = str.toCharArray();
        int i = 0;
        while (i < charArray.length) {
            char c = i > 0 ? charArray[i - 1] : (char) 0;
            char c2 = charArray[i];
            boolean z = true;
            char c3 = i < charArray.length - 1 ? charArray[i + 1] : (char) 0;
            if (i != 0) {
                z = false;
            }
            if (z || Character.isLowerCase(c2) || Character.isDigit(c2)) {
                sb.append(Character.toUpperCase(c2));
            } else if (Character.isUpperCase(c2)) {
                if (!Character.isLetterOrDigit(c)) {
                    sb.append(c2);
                } else if (Character.isLowerCase(c)) {
                    sb.append('_');
                    sb.append(c2);
                } else if (c3 <= 0 || !Character.isLowerCase(c3)) {
                    sb.append(c2);
                } else {
                    sb.append('_');
                    sb.append(c2);
                }
            }
            i++;
        }
        return sb.toString();
    }
}
