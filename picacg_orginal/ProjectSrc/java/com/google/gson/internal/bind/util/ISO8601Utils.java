package com.google.gson.internal.bind.util;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
/* loaded from: picacg_1.jar:com/google/gson/internal/bind/util/ISO8601Utils.class */
public class ISO8601Utils {
    private static final String UTC_ID = "UTC";
    private static final TimeZone TIMEZONE_UTC = TimeZone.getTimeZone(UTC_ID);

    private static boolean checkOffset(String str, int i, char c) {
        return i < str.length() && str.charAt(i) == c;
    }

    public static String format(Date date) {
        return format(date, false, TIMEZONE_UTC);
    }

    public static String format(Date date, boolean z) {
        return format(date, z, TIMEZONE_UTC);
    }

    public static String format(Date date, boolean z, TimeZone timeZone) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(timeZone, Locale.US);
        gregorianCalendar.setTime(date);
        StringBuilder sb = new StringBuilder("yyyy-MM-ddThh:mm:ss".length() + (z ? ".sss".length() : 0) + (timeZone.getRawOffset() == 0 ? "Z" : "+hh:mm").length());
        padInt(sb, gregorianCalendar.get(1), "yyyy".length());
        sb.append('-');
        padInt(sb, gregorianCalendar.get(2) + 1, "MM".length());
        sb.append('-');
        padInt(sb, gregorianCalendar.get(5), "dd".length());
        sb.append('T');
        padInt(sb, gregorianCalendar.get(11), "hh".length());
        sb.append(':');
        padInt(sb, gregorianCalendar.get(12), "mm".length());
        sb.append(':');
        padInt(sb, gregorianCalendar.get(13), "ss".length());
        if (z) {
            sb.append('.');
            padInt(sb, gregorianCalendar.get(14), "sss".length());
        }
        int offset = timeZone.getOffset(gregorianCalendar.getTimeInMillis());
        if (offset != 0) {
            int i = offset / 60000;
            int abs = Math.abs(i / 60);
            int abs2 = Math.abs(i % 60);
            sb.append(offset < 0 ? '-' : '+');
            padInt(sb, abs, "hh".length());
            sb.append(':');
            padInt(sb, abs2, "mm".length());
        } else {
            sb.append('Z');
        }
        return sb.toString();
    }

    private static int indexOfNonDigit(String str, int i) {
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (charAt < '0' || charAt > '9') {
                return i;
            }
            i++;
        }
        return str.length();
    }

    private static void padInt(StringBuilder sb, int i, int i2) {
        String num = Integer.toString(i);
        for (int length = i2 - num.length(); length > 0; length--) {
            sb.append('0');
        }
        sb.append(num);
    }

    /* JADX WARN: Code restructure failed: missing block: B:123:0x03cc, code lost:
        if (r0.isEmpty() != false) goto L120;
     */
    /* JADX WARN: Removed duplicated region for block: B:108:0x036d A[Catch: IllegalArgumentException -> 0x037c, IllegalArgumentException -> 0x037c, NumberFormatException -> 0x0381, NumberFormatException -> 0x0381, IndexOutOfBoundsException | NumberFormatException | IllegalArgumentException -> 0x0386, IndexOutOfBoundsException | NumberFormatException | IllegalArgumentException -> 0x0386, TRY_ENTER, TRY_LEAVE, TryCatch #2 {IndexOutOfBoundsException | NumberFormatException | IllegalArgumentException -> 0x0386, blocks: (B:2:0x0000, B:4:0x0009, B:6:0x0013, B:11:0x0025, B:13:0x002f, B:18:0x0042, B:20:0x005a, B:22:0x0063, B:27:0x008f, B:29:0x0099, B:34:0x00ab, B:36:0x00b6, B:41:0x00cf, B:43:0x00d8, B:51:0x0118, B:57:0x0136, B:61:0x0145, B:71:0x01c0, B:73:0x01c9, B:76:0x01d8, B:105:0x0313, B:105:0x0313, B:106:0x0316, B:83:0x01f6, B:84:0x0229, B:86:0x022b, B:91:0x0261, B:93:0x0272, B:96:0x027f, B:98:0x02b8, B:101:0x02cc, B:102:0x030a, B:104:0x030e, B:89:0x023e, B:108:0x036d, B:108:0x036d, B:109:0x0370, B:110:0x037b), top: B:127:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x01c9 A[Catch: IllegalArgumentException -> 0x037c, NumberFormatException -> 0x0381, IndexOutOfBoundsException | NumberFormatException | IllegalArgumentException -> 0x0386, TRY_LEAVE, TryCatch #2 {IndexOutOfBoundsException | NumberFormatException | IllegalArgumentException -> 0x0386, blocks: (B:2:0x0000, B:4:0x0009, B:6:0x0013, B:11:0x0025, B:13:0x002f, B:18:0x0042, B:20:0x005a, B:22:0x0063, B:27:0x008f, B:29:0x0099, B:34:0x00ab, B:36:0x00b6, B:41:0x00cf, B:43:0x00d8, B:51:0x0118, B:57:0x0136, B:61:0x0145, B:71:0x01c0, B:73:0x01c9, B:76:0x01d8, B:105:0x0313, B:105:0x0313, B:106:0x0316, B:83:0x01f6, B:84:0x0229, B:86:0x022b, B:91:0x0261, B:93:0x0272, B:96:0x027f, B:98:0x02b8, B:101:0x02cc, B:102:0x030a, B:104:0x030e, B:89:0x023e, B:108:0x036d, B:108:0x036d, B:109:0x0370, B:110:0x037b), top: B:127:0x0000 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.Date parse(java.lang.String r5, java.text.ParsePosition r6) throws java.text.ParseException {
        /*
            Method dump skipped, instructions count: 1087
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.bind.util.ISO8601Utils.parse(java.lang.String, java.text.ParsePosition):java.util.Date");
    }

    private static int parseInt(String str, int i, int i2) throws NumberFormatException {
        int i3;
        int i4;
        if (i < 0 || i2 > str.length() || i > i2) {
            throw new NumberFormatException(str);
        }
        if (i < i2) {
            i3 = i + 1;
            int digit = Character.digit(str.charAt(i), 10);
            if (digit < 0) {
                throw new NumberFormatException("Invalid number: " + str.substring(i, i2));
            }
            i4 = -digit;
        } else {
            i3 = i;
            i4 = 0;
        }
        while (i3 < i2) {
            int digit2 = Character.digit(str.charAt(i3), 10);
            if (digit2 < 0) {
                throw new NumberFormatException("Invalid number: " + str.substring(i, i2));
            }
            i4 = (i4 * 10) - digit2;
            i3++;
        }
        return -i4;
    }
}
