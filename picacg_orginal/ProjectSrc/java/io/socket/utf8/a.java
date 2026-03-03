package io.socket.utf8;

import java.util.ArrayList;
import java.util.List;
/* loaded from: picacg_1.jar:io/socket/utf8/a.class */
public final class a {
    private static int[] Ae;
    private static int Af;
    private static int Ag;

    private static String a(int[] iArr) {
        StringBuilder sb = new StringBuilder();
        for (int i : iArr) {
            sb.appendCodePoint(i);
        }
        return sb.toString();
    }

    private static int[] aT(String str) {
        int length = str.length();
        int i = 0;
        int[] iArr = new int[str.codePointCount(0, length)];
        int i2 = 0;
        while (i < length) {
            int codePointAt = str.codePointAt(i);
            iArr[i2] = codePointAt;
            i += Character.charCount(codePointAt);
            i2++;
        }
        return iArr;
    }

    private static String aj(int i) throws UTF8Exception {
        StringBuilder sb = new StringBuilder();
        if ((i & (-128)) == 0) {
            sb.append(Character.toChars(i));
            return sb.toString();
        }
        if ((i & (-2048)) == 0) {
            sb.append(Character.toChars(((i >> 6) & 31) | 192));
        } else if (((-65536) & i) == 0) {
            ak(i);
            sb.append(Character.toChars(((i >> 12) & 15) | 224));
            sb.append(l(i, 6));
        } else if (((-2097152) & i) == 0) {
            sb.append(Character.toChars(((i >> 18) & 7) | 240));
            sb.append(l(i, 12));
            sb.append(l(i, 6));
        }
        sb.append(Character.toChars((i & 63) | 128));
        return sb.toString();
    }

    private static void ak(int i) throws UTF8Exception {
        if (i < 55296 || i > 57343) {
            return;
        }
        throw new UTF8Exception("Lone surrogate U+" + Integer.toHexString(i).toUpperCase() + " is not a scalar value");
    }

    private static int[] c(List<Integer> list) {
        int size = list.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = list.get(i).intValue();
        }
        return iArr;
    }

    public static String decode(String str) throws UTF8Exception {
        Ae = aT(str);
        Af = Ae.length;
        Ag = 0;
        ArrayList arrayList = new ArrayList();
        while (true) {
            int fu = fu();
            if (fu == -1) {
                return a(c(arrayList));
            }
            arrayList.add(Integer.valueOf(fu));
        }
    }

    public static String encode(String str) throws UTF8Exception {
        int[] aT = aT(str);
        int length = aT.length;
        StringBuilder sb = new StringBuilder();
        int i = -1;
        while (true) {
            i++;
            if (i >= length) {
                return sb.toString();
            }
            sb.append(aj(aT[i]));
        }
    }

    private static int fu() throws UTF8Exception {
        int fv;
        if (Ag <= Af) {
            if (Ag == Af) {
                return -1;
            }
            int i = Ae[Ag] & 255;
            Ag++;
            if ((i & 128) == 0) {
                return i;
            }
            if ((i & 224) == 192) {
                int fv2 = ((i & 31) << 6) | fv();
                if (fv2 >= 128) {
                    return fv2;
                }
                throw new UTF8Exception("Invalid continuation byte");
            } else if ((i & 240) == 224) {
                int fv3 = ((i & 15) << 12) | (fv() << 6) | fv();
                if (fv3 >= 2048) {
                    ak(fv3);
                    return fv3;
                }
                throw new UTF8Exception("Invalid continuation byte");
            } else if ((i & 248) != 240 || (fv = ((i & 15) << 18) | (fv() << 12) | (fv() << 6) | fv()) < 65536 || fv > 1114111) {
                throw new UTF8Exception("Invalid continuation byte");
            } else {
                return fv;
            }
        }
        throw new UTF8Exception("Invalid byte index");
    }

    private static int fv() throws UTF8Exception {
        if (Ag < Af) {
            int i = Ae[Ag] & 255;
            Ag++;
            if ((i & 192) == 128) {
                return i & 63;
            }
            throw new UTF8Exception("Invalid continuation byte");
        }
        throw new UTF8Exception("Invalid byte index");
    }

    private static char[] l(int i, int i2) {
        return Character.toChars(((i >> i2) & 63) | 128);
    }
}
