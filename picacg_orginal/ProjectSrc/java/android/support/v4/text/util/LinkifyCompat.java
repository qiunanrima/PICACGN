package android.support.v4.text.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.util.PatternsCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.webkit.WebView;
import android.widget.TextView;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: picacg_1.jar:android/support/v4/text/util/LinkifyCompat.class */
public final class LinkifyCompat {
    private static final String[] EMPTY_STRING = new String[0];
    private static final Comparator<LinkSpec> COMPARATOR = new Comparator<LinkSpec>() { // from class: android.support.v4.text.util.LinkifyCompat.1
        @Override // java.util.Comparator
        public final int compare(LinkSpec linkSpec, LinkSpec linkSpec2) {
            if (linkSpec.start < linkSpec2.start) {
                return -1;
            }
            if (linkSpec.start <= linkSpec2.start && linkSpec.end >= linkSpec2.end) {
                return linkSpec.end > linkSpec2.end ? -1 : 0;
            }
            return 1;
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: picacg_1.jar:android/support/v4/text/util/LinkifyCompat$LinkSpec.class */
    public static class LinkSpec {
        int end;
        URLSpan frameworkAddedSpan;
        int start;
        String url;

        LinkSpec() {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: picacg_1.jar:android/support/v4/text/util/LinkifyCompat$LinkifyMask.class */
    public @interface LinkifyMask {
    }

    private LinkifyCompat() {
    }

    private static void addLinkMovementMethod(@NonNull TextView textView) {
        MovementMethod movementMethod = textView.getMovementMethod();
        if ((movementMethod == null || !(movementMethod instanceof LinkMovementMethod)) && textView.getLinksClickable()) {
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    public static final void addLinks(@NonNull TextView textView, @NonNull Pattern pattern, @Nullable String str) {
        addLinks(textView, pattern, str, (String[]) null, (Linkify.MatchFilter) null, (Linkify.TransformFilter) null);
    }

    public static final void addLinks(@NonNull TextView textView, @NonNull Pattern pattern, @Nullable String str, @Nullable Linkify.MatchFilter matchFilter, @Nullable Linkify.TransformFilter transformFilter) {
        addLinks(textView, pattern, str, (String[]) null, matchFilter, transformFilter);
    }

    public static final void addLinks(@NonNull TextView textView, @NonNull Pattern pattern, @Nullable String str, @Nullable String[] strArr, @Nullable Linkify.MatchFilter matchFilter, @Nullable Linkify.TransformFilter transformFilter) {
        SpannableString valueOf = SpannableString.valueOf(textView.getText());
        if (addLinks(valueOf, pattern, str, strArr, matchFilter, transformFilter)) {
            textView.setText(valueOf);
            addLinkMovementMethod(textView);
        }
    }

    public static final boolean addLinks(@NonNull Spannable spannable, int i) {
        if (i == 0) {
            return false;
        }
        URLSpan[] uRLSpanArr = (URLSpan[]) spannable.getSpans(0, spannable.length(), URLSpan.class);
        for (int length = uRLSpanArr.length - 1; length >= 0; length--) {
            spannable.removeSpan(uRLSpanArr[length]);
        }
        if ((i & 4) != 0) {
            Linkify.addLinks(spannable, 4);
        }
        ArrayList arrayList = new ArrayList();
        if ((i & 1) != 0) {
            gatherLinks(arrayList, spannable, PatternsCompat.AUTOLINK_WEB_URL, new String[]{"http://", "https://", "rtsp://"}, Linkify.sUrlMatchFilter, null);
        }
        if ((i & 2) != 0) {
            gatherLinks(arrayList, spannable, PatternsCompat.AUTOLINK_EMAIL_ADDRESS, new String[]{"mailto:"}, null, null);
        }
        if ((i & 8) != 0) {
            gatherMapLinks(arrayList, spannable);
        }
        pruneOverlaps(arrayList, spannable);
        if (arrayList.size() == 0) {
            return false;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            LinkSpec linkSpec = (LinkSpec) it.next();
            if (linkSpec.frameworkAddedSpan == null) {
                applyLink(linkSpec.url, linkSpec.start, linkSpec.end, spannable);
            }
        }
        return true;
    }

    public static final boolean addLinks(@NonNull Spannable spannable, @NonNull Pattern pattern, @Nullable String str) {
        return addLinks(spannable, pattern, str, (String[]) null, (Linkify.MatchFilter) null, (Linkify.TransformFilter) null);
    }

    public static final boolean addLinks(@NonNull Spannable spannable, @NonNull Pattern pattern, @Nullable String str, @Nullable Linkify.MatchFilter matchFilter, @Nullable Linkify.TransformFilter transformFilter) {
        return addLinks(spannable, pattern, str, (String[]) null, matchFilter, transformFilter);
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0014, code lost:
        if (r8.length < 1) goto L37;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final boolean addLinks(@android.support.annotation.NonNull android.text.Spannable r5, @android.support.annotation.NonNull java.util.regex.Pattern r6, @android.support.annotation.Nullable java.lang.String r7, @android.support.annotation.Nullable java.lang.String[] r8, @android.support.annotation.Nullable android.text.util.Linkify.MatchFilter r9, @android.support.annotation.Nullable android.text.util.Linkify.TransformFilter r10) {
        /*
            r0 = r7
            r11 = r0
            r0 = r7
            if (r0 != 0) goto Lb
            java.lang.String r0 = ""
            r11 = r0
        Lb:
            r0 = r8
            if (r0 == 0) goto L17
            r0 = r8
            r7 = r0
            r0 = r8
            int r0 = r0.length
            r1 = 1
            if (r0 >= r1) goto L1b
        L17:
            java.lang.String[] r0 = android.support.v4.text.util.LinkifyCompat.EMPTY_STRING
            r7 = r0
        L1b:
            r0 = r7
            int r0 = r0.length
            r1 = 1
            int r0 = r0 + r1
            java.lang.String[] r0 = new java.lang.String[r0]
            r12 = r0
            r0 = r12
            r1 = 0
            r2 = r11
            java.util.Locale r3 = java.util.Locale.ROOT
            java.lang.String r2 = r2.toLowerCase(r3)
            r0[r1] = r2
            r0 = 0
            r13 = r0
        L33:
            r0 = r13
            r1 = r7
            int r1 = r1.length
            if (r0 >= r1) goto L5d
            r0 = r7
            r1 = r13
            r0 = r0[r1]
            r8 = r0
            int r13 = r13 + 1
            r0 = r8
            if (r0 != 0) goto L4c
            java.lang.String r0 = ""
            r8 = r0
            goto L54
        L4c:
            r0 = r8
            java.util.Locale r1 = java.util.Locale.ROOT
            java.lang.String r0 = r0.toLowerCase(r1)
            r8 = r0
        L54:
            r0 = r12
            r1 = r13
            r2 = r8
            r0[r1] = r2
            goto L33
        L5d:
            r0 = r6
            r1 = r5
            java.util.regex.Matcher r0 = r0.matcher(r1)
            r6 = r0
            r0 = 0
            r14 = r0
        L66:
            r0 = r6
            boolean r0 = r0.find()
            if (r0 == 0) goto Lb2
            r0 = r6
            int r0 = r0.start()
            r13 = r0
            r0 = r6
            int r0 = r0.end()
            r15 = r0
            r0 = r9
            if (r0 == 0) goto L8f
            r0 = r9
            r1 = r5
            r2 = r13
            r3 = r15
            boolean r0 = r0.acceptMatch(r1, r2, r3)
            r16 = r0
            goto L92
        L8f:
            r0 = 1
            r16 = r0
        L92:
            r0 = r16
            if (r0 == 0) goto L66
            r0 = r6
            r1 = 0
            java.lang.String r0 = r0.group(r1)
            r1 = r12
            r2 = r6
            r3 = r10
            java.lang.String r0 = makeUrl(r0, r1, r2, r3)
            r1 = r13
            r2 = r15
            r3 = r5
            applyLink(r0, r1, r2, r3)
            r0 = 1
            r14 = r0
            goto L66
        Lb2:
            r0 = r14
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.text.util.LinkifyCompat.addLinks(android.text.Spannable, java.util.regex.Pattern, java.lang.String, java.lang.String[], android.text.util.Linkify$MatchFilter, android.text.util.Linkify$TransformFilter):boolean");
    }

    public static final boolean addLinks(@NonNull TextView textView, int i) {
        if (i == 0) {
            return false;
        }
        CharSequence text = textView.getText();
        if (text instanceof Spannable) {
            if (addLinks((Spannable) text, i)) {
                addLinkMovementMethod(textView);
                return true;
            }
            return false;
        }
        SpannableString valueOf = SpannableString.valueOf(text);
        if (addLinks(valueOf, i)) {
            addLinkMovementMethod(textView);
            textView.setText(valueOf);
            return true;
        }
        return false;
    }

    private static void applyLink(String str, int i, int i2, Spannable spannable) {
        spannable.setSpan(new URLSpan(str), i, i2, 33);
    }

    private static void gatherLinks(ArrayList<LinkSpec> arrayList, Spannable spannable, Pattern pattern, String[] strArr, Linkify.MatchFilter matchFilter, Linkify.TransformFilter transformFilter) {
        Matcher matcher = pattern.matcher(spannable);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            if (matchFilter == null || matchFilter.acceptMatch(spannable, start, end)) {
                LinkSpec linkSpec = new LinkSpec();
                linkSpec.url = makeUrl(matcher.group(0), strArr, matcher, transformFilter);
                linkSpec.start = start;
                linkSpec.end = end;
                arrayList.add(linkSpec);
            }
        }
    }

    private static final void gatherMapLinks(ArrayList<LinkSpec> arrayList, Spannable spannable) {
        int indexOf;
        String obj = spannable.toString();
        int i = 0;
        while (true) {
            try {
                String findAddress = WebView.findAddress(obj);
                if (findAddress == null || (indexOf = obj.indexOf(findAddress)) < 0) {
                    return;
                }
                LinkSpec linkSpec = new LinkSpec();
                int length = findAddress.length() + indexOf;
                linkSpec.start = indexOf + i;
                i += length;
                linkSpec.end = i;
                obj = obj.substring(length);
                try {
                    linkSpec.url = "geo:0,0?q=" + URLEncoder.encode(findAddress, "UTF-8");
                    arrayList.add(linkSpec);
                } catch (UnsupportedEncodingException e) {
                }
            } catch (UnsupportedOperationException e2) {
                return;
            }
        }
    }

    private static String makeUrl(@NonNull String str, @NonNull String[] strArr, Matcher matcher, @Nullable Linkify.TransformFilter transformFilter) {
        boolean z;
        String str2;
        String str3 = str;
        if (transformFilter != null) {
            str3 = transformFilter.transformUrl(matcher, str);
        }
        int i = 0;
        while (true) {
            if (i >= strArr.length) {
                z = false;
                str2 = str3;
                break;
            } else if (str3.regionMatches(true, 0, strArr[i], 0, strArr[i].length())) {
                z = true;
                str2 = str3;
                if (!str3.regionMatches(false, 0, strArr[i], 0, strArr[i].length())) {
                    str2 = strArr[i] + str3.substring(strArr[i].length());
                    z = true;
                }
            } else {
                i++;
            }
        }
        String str4 = str2;
        if (!z) {
            str4 = str2;
            if (strArr.length > 0) {
                str4 = strArr[0] + str2;
            }
        }
        return str4;
    }

    private static final void pruneOverlaps(ArrayList<LinkSpec> arrayList, Spannable spannable) {
        Object[] objArr = (URLSpan[]) spannable.getSpans(0, spannable.length(), URLSpan.class);
        for (int i = 0; i < objArr.length; i++) {
            LinkSpec linkSpec = new LinkSpec();
            linkSpec.frameworkAddedSpan = objArr[i];
            linkSpec.start = spannable.getSpanStart(objArr[i]);
            linkSpec.end = spannable.getSpanEnd(objArr[i]);
            arrayList.add(linkSpec);
        }
        Collections.sort(arrayList, COMPARATOR);
        int size = arrayList.size();
        int i2 = 0;
        while (i2 < size - 1) {
            LinkSpec linkSpec2 = arrayList.get(i2);
            int i3 = i2 + 1;
            LinkSpec linkSpec3 = arrayList.get(i3);
            if (linkSpec2.start <= linkSpec3.start && linkSpec2.end > linkSpec3.start) {
                int i4 = (linkSpec3.end > linkSpec2.end && linkSpec2.end - linkSpec2.start <= linkSpec3.end - linkSpec3.start) ? linkSpec2.end - linkSpec2.start < linkSpec3.end - linkSpec3.start ? i2 : -1 : i3;
                if (i4 != -1) {
                    Object obj = arrayList.get(i4).frameworkAddedSpan;
                    if (obj != null) {
                        spannable.removeSpan(obj);
                    }
                    arrayList.remove(i4);
                    size--;
                }
            }
            i2 = i3;
        }
    }
}
