package okhttp3.internal.publicsuffix;

import java.net.IDN;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import okhttp3.internal.Util;
/* loaded from: picacg_1.jar:okhttp3/internal/publicsuffix/PublicSuffixDatabase.class */
public final class PublicSuffixDatabase {
    private static final byte EXCEPTION_MARKER = 33;
    public static final String PUBLIC_SUFFIX_RESOURCE = "publicsuffixes.gz";
    private byte[] publicSuffixExceptionListBytes;
    private byte[] publicSuffixListBytes;
    private static final byte[] WILDCARD_LABEL = {42};
    private static final String[] EMPTY_RULE = new String[0];
    private static final String[] PREVAILING_RULE = {"*"};
    private static final PublicSuffixDatabase instance = new PublicSuffixDatabase();
    private final AtomicBoolean listRead = new AtomicBoolean(false);
    private final CountDownLatch readCompleteLatch = new CountDownLatch(1);

    private static String binarySearchBytes(byte[] bArr, byte[][] bArr2, int i) {
        String str;
        int i2;
        int i3;
        int i4;
        int i5;
        int length = bArr.length;
        int i6 = 0;
        while (true) {
            if (i6 >= length) {
                str = null;
                break;
            }
            int i7 = (i6 + length) / 2;
            while (i7 > -1 && bArr[i7] != 10) {
                i7--;
            }
            int i8 = i7 + 1;
            int i9 = 1;
            while (true) {
                i2 = i8 + i9;
                if (bArr[i2] == 10) {
                    break;
                }
                i9++;
            }
            int i10 = i2 - i8;
            int i11 = i;
            boolean z = false;
            int i12 = 0;
            int i13 = 0;
            while (true) {
                if (z) {
                    z = false;
                    i3 = 46;
                } else {
                    i3 = bArr2[i11][i12] & 255;
                }
                i4 = i3 - (bArr[i8 + i13] & 255);
                if (i4 != 0) {
                    break;
                }
                int i14 = i13 + 1;
                int i15 = i12 + 1;
                if (i14 == i10) {
                    i12 = i15;
                    i13 = i14;
                    break;
                }
                i12 = i15;
                i13 = i14;
                if (bArr2[i11].length == i15) {
                    if (i11 == bArr2.length - 1) {
                        i13 = i14;
                        i12 = i15;
                        break;
                    }
                    i11++;
                    z = true;
                    i12 = -1;
                    i13 = i14;
                }
            }
            if (i4 >= 0) {
                if (i4 <= 0) {
                    int i16 = i10 - i13;
                    int length2 = bArr2[i11].length - i12;
                    while (true) {
                        i5 = length2;
                        i11++;
                        if (i11 >= bArr2.length) {
                            break;
                        }
                        length2 = i5 + bArr2[i11].length;
                    }
                    if (i5 >= i16) {
                        if (i5 <= i16) {
                            str = new String(bArr, i8, i10, Util.UTF_8);
                            break;
                        }
                    }
                }
                i6 = i2 + 1;
            }
            length = i8 - 1;
        }
        return str;
    }

    /* JADX WARN: Type inference failed for: r0v18, types: [byte[], byte[][], java.lang.Object] */
    private String[] findMatchingRule(String[] strArr) {
        String str;
        String str2;
        String str3;
        if (this.listRead.get() || !this.listRead.compareAndSet(false, true)) {
            try {
                this.readCompleteLatch.await();
            } catch (InterruptedException e) {
            }
        } else {
            readTheList();
        }
        synchronized (this) {
            if (this.publicSuffixListBytes == null) {
                throw new IllegalStateException("Unable to load publicsuffixes.gz resource from the classpath.");
            }
        }
        ?? r0 = new byte[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            r0[i] = strArr[i].getBytes(Util.UTF_8);
        }
        int i2 = 0;
        while (true) {
            if (i2 >= r0.length) {
                str = null;
                break;
            }
            str = binarySearchBytes(this.publicSuffixListBytes, r0, i2);
            if (str != null) {
                break;
            }
            i2++;
        }
        if (r0.length > 1) {
            byte[][] bArr = (byte[][]) r0.clone();
            for (int i3 = 0; i3 < bArr.length - 1; i3++) {
                bArr[i3] = WILDCARD_LABEL;
                str2 = binarySearchBytes(this.publicSuffixListBytes, bArr, i3);
                if (str2 != null) {
                    break;
                }
            }
        }
        str2 = null;
        if (str2 != null) {
            for (int i4 = 0; i4 < r0.length - 1; i4++) {
                str3 = binarySearchBytes(this.publicSuffixExceptionListBytes, r0, i4);
                if (str3 != null) {
                    break;
                }
            }
        }
        str3 = null;
        if (str3 != null) {
            return ("!" + str3).split("\\.");
        } else if (str == null && str2 == null) {
            return PREVAILING_RULE;
        } else {
            String[] split = str != null ? str.split("\\.") : EMPTY_RULE;
            String[] split2 = str2 != null ? str2.split("\\.") : EMPTY_RULE;
            if (split.length <= split2.length) {
                split = split2;
            }
            return split;
        }
    }

    public static PublicSuffixDatabase get() {
        return instance;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x006c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void readTheList() {
        /*
            r5 = this;
            java.lang.Class<okhttp3.internal.publicsuffix.PublicSuffixDatabase> r0 = okhttp3.internal.publicsuffix.PublicSuffixDatabase.class
            java.lang.ClassLoader r0 = r0.getClassLoader()
            java.lang.String r1 = "publicsuffixes.gz"
            java.io.InputStream r0 = r0.getResourceAsStream(r1)
            r6 = r0
            r0 = 0
            r7 = r0
            r0 = r6
            if (r0 == 0) goto L68
            okio.i r0 = new okio.i
            r1 = r0
            r2 = r6
            okio.q r2 = okio.k.a(r2)
            r1.<init>(r2)
            okio.e r0 = okio.k.b(r0)
            r8 = r0
            r0 = r8
            int r0 = r0.readInt()     // Catch: java.lang.Throwable -> L4c java.io.IOException -> L50
            byte[] r0 = new byte[r0]     // Catch: java.lang.Throwable -> L4c java.io.IOException -> L50
            r9 = r0
            r0 = r8
            r1 = r9
            r0.readFully(r1)     // Catch: java.lang.Throwable -> L4c java.io.IOException -> L50
            r0 = r8
            int r0 = r0.readInt()     // Catch: java.lang.Throwable -> L4c java.io.IOException -> L50
            byte[] r0 = new byte[r0]     // Catch: java.lang.Throwable -> L4c java.io.IOException -> L50
            r6 = r0
            r0 = r8
            r1 = r6
            r0.readFully(r1)     // Catch: java.lang.Throwable -> L4c java.io.IOException -> L50
            r0 = r8
            okhttp3.internal.Util.closeQuietly(r0)
            r0 = r9
            r7 = r0
            goto L6a
        L4c:
            r6 = move-exception
            goto L62
        L50:
            r6 = move-exception
            okhttp3.internal.platform.Platform r0 = okhttp3.internal.platform.Platform.get()     // Catch: java.lang.Throwable -> L4c
            r1 = 5
            java.lang.String r2 = "Failed to read public suffix list"
            r3 = r6
            r0.log(r1, r2, r3)     // Catch: java.lang.Throwable -> L4c
            r0 = r8
            okhttp3.internal.Util.closeQuietly(r0)
            goto L68
        L62:
            r0 = r8
            okhttp3.internal.Util.closeQuietly(r0)
            r0 = r6
            throw r0
        L68:
            r0 = 0
            r6 = r0
        L6a:
            r0 = r5
            monitor-enter(r0)
            r0 = r5
            r1 = r7
            r0.publicSuffixListBytes = r1     // Catch: java.lang.Throwable -> L80
            r0 = r5
            r1 = r6
            r0.publicSuffixExceptionListBytes = r1     // Catch: java.lang.Throwable -> L80
            r0 = r5
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L80
            r0 = r5
            java.util.concurrent.CountDownLatch r0 = r0.readCompleteLatch
            r0.countDown()
            return
        L80:
            r6 = move-exception
            r0 = r5
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L80
            r0 = r6
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.publicsuffix.PublicSuffixDatabase.readTheList():void");
    }

    public String getEffectiveTldPlusOne(String str) {
        if (str != null) {
            String[] split = IDN.toUnicode(str).split("\\.");
            String[] findMatchingRule = findMatchingRule(split);
            if (split.length != findMatchingRule.length || findMatchingRule[0].charAt(0) == '!') {
                StringBuilder sb = new StringBuilder();
                String[] split2 = str.split("\\.");
                for (int length = findMatchingRule[0].charAt(0) == '!' ? split.length - findMatchingRule.length : split.length - (findMatchingRule.length + 1); length < split2.length; length++) {
                    sb.append(split2[length]);
                    sb.append('.');
                }
                sb.deleteCharAt(sb.length() - 1);
                return sb.toString();
            }
            return null;
        }
        throw new NullPointerException("domain == null");
    }

    void setListBytes(byte[] bArr, byte[] bArr2) {
        this.publicSuffixListBytes = bArr;
        this.publicSuffixExceptionListBytes = bArr2;
        this.listRead.set(true);
        this.readCompleteLatch.countDown();
    }
}
