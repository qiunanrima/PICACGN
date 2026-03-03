package com.orm.b;

import java.util.Comparator;
/* loaded from: picacg_1.jar:com/orm/b/e.class */
public class e implements Comparator<Object> {
    private static char charAt(String str, int i) {
        if (i >= str.length()) {
            return (char) 0;
        }
        return str.charAt(i);
    }

    private int g(String str, String str2) {
        int i;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            int i5 = i4;
            char charAt = charAt(str, i2);
            char charAt2 = charAt(str2, i3);
            if (!Character.isDigit(charAt) && !Character.isDigit(charAt2)) {
                return i5;
            }
            if (!Character.isDigit(charAt)) {
                return -1;
            }
            if (!Character.isDigit(charAt2)) {
                return 1;
            }
            if (charAt < charAt2) {
                i = i5;
                if (i5 == 0) {
                    i = -1;
                }
            } else if (charAt > charAt2) {
                i = i5;
                if (i5 == 0) {
                    i = 1;
                }
            } else {
                i = i5;
                if (charAt == 0) {
                    i = i5;
                    if (charAt2 == 0) {
                        return i5;
                    }
                } else {
                    continue;
                }
            }
            i2++;
            i3++;
            i4 = i;
        }
    }

    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        int g;
        String obj3 = obj.toString();
        String obj4 = obj2.toString();
        int i = 0;
        int i2 = 0;
        while (true) {
            int i3 = i2;
            char charAt = charAt(obj3, i);
            char charAt2 = charAt(obj4, i3);
            int i4 = i;
            char c = charAt;
            int i5 = 0;
            while (true) {
                if (!Character.isSpaceChar(c) && c != '0') {
                    break;
                }
                i5 = c == '0' ? i5 + 1 : 0;
                i4++;
                c = charAt(obj3, i4);
            }
            int i6 = i3;
            char c2 = charAt2;
            int i7 = 0;
            while (true) {
                if (!Character.isSpaceChar(c2) && c2 != '0') {
                    break;
                }
                i7 = c2 == '0' ? i7 + 1 : 0;
                i6++;
                c2 = charAt(obj4, i6);
            }
            if (Character.isDigit(c) && Character.isDigit(c2) && (g = g(obj3.substring(i4), obj4.substring(i6))) != 0) {
                return g;
            }
            if (c == 0 && c2 == 0) {
                return i5 - i7;
            }
            if (c < c2) {
                return -1;
            }
            if (c > c2) {
                return 1;
            }
            i = i4 + 1;
            i2 = i6 + 1;
        }
    }
}
