package android.support.v4.content.res;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.annotation.ArrayRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.compat.R;
import android.util.Base64;
import android.util.Xml;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: picacg_1.jar:android/support/v4/content/res/FontResourcesParserCompat.class */
public class FontResourcesParserCompat {
    private static final int ITALIC = 1;
    private static final int NORMAL_WEIGHT = 400;

    /* loaded from: picacg_1.jar:android/support/v4/content/res/FontResourcesParserCompat$FamilyResourceEntry.class */
    public interface FamilyResourceEntry {
    }

    /* loaded from: picacg_1.jar:android/support/v4/content/res/FontResourcesParserCompat$FontFamilyFilesResourceEntry.class */
    public static final class FontFamilyFilesResourceEntry implements FamilyResourceEntry {
        @NonNull
        private final FontFileResourceEntry[] mEntries;

        public FontFamilyFilesResourceEntry(@NonNull FontFileResourceEntry[] fontFileResourceEntryArr) {
            this.mEntries = fontFileResourceEntryArr;
        }

        @NonNull
        public FontFileResourceEntry[] getEntries() {
            return this.mEntries;
        }
    }

    /* loaded from: picacg_1.jar:android/support/v4/content/res/FontResourcesParserCompat$FontFileResourceEntry.class */
    public static final class FontFileResourceEntry {
        @NonNull
        private final String mFileName;
        private boolean mItalic;
        private int mResourceId;
        private int mWeight;

        public FontFileResourceEntry(@NonNull String str, int i, boolean z, int i2) {
            this.mFileName = str;
            this.mWeight = i;
            this.mItalic = z;
            this.mResourceId = i2;
        }

        @NonNull
        public String getFileName() {
            return this.mFileName;
        }

        public int getResourceId() {
            return this.mResourceId;
        }

        public int getWeight() {
            return this.mWeight;
        }

        public boolean isItalic() {
            return this.mItalic;
        }
    }

    /* loaded from: picacg_1.jar:android/support/v4/content/res/FontResourcesParserCompat$ProviderResourceEntry.class */
    public static final class ProviderResourceEntry implements FamilyResourceEntry {
        @Nullable
        private final List<List<byte[]>> mCerts;
        @NonNull
        private final String mProviderAuthority;
        @NonNull
        private final String mProviderPackage;
        @NonNull
        private final String mQuery;

        public ProviderResourceEntry(@NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable List<List<byte[]>> list) {
            this.mProviderAuthority = str;
            this.mProviderPackage = str2;
            this.mQuery = str3;
            this.mCerts = list;
        }

        @NonNull
        public String getAuthority() {
            return this.mProviderAuthority;
        }

        @Nullable
        public List<List<byte[]>> getCerts() {
            return this.mCerts;
        }

        @NonNull
        public String getPackage() {
            return this.mProviderPackage;
        }

        @NonNull
        public String getQuery() {
            return this.mQuery;
        }
    }

    @Nullable
    public static FamilyResourceEntry parse(XmlPullParser xmlPullParser, Resources resources) throws XmlPullParserException, IOException {
        int next;
        do {
            next = xmlPullParser.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next == 2) {
            return readFamilies(xmlPullParser, resources);
        }
        throw new XmlPullParserException("No start tag found");
    }

    public static List<List<byte[]>> readCerts(Resources resources, @ArrayRes int i) {
        ArrayList arrayList;
        if (i != 0) {
            TypedArray obtainTypedArray = resources.obtainTypedArray(i);
            if (obtainTypedArray.length() > 0) {
                ArrayList arrayList2 = new ArrayList();
                if (obtainTypedArray.getResourceId(0, 0) != 0) {
                    int i2 = 0;
                    while (true) {
                        arrayList = arrayList2;
                        if (i2 >= obtainTypedArray.length()) {
                            break;
                        }
                        arrayList2.add(toByteArrayList(resources.getStringArray(obtainTypedArray.getResourceId(i2, 0))));
                        i2++;
                    }
                } else {
                    arrayList2.add(toByteArrayList(resources.getStringArray(i)));
                    arrayList = arrayList2;
                }
                return arrayList;
            }
        }
        arrayList = null;
        return arrayList;
    }

    @Nullable
    private static FamilyResourceEntry readFamilies(XmlPullParser xmlPullParser, Resources resources) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, null, "font-family");
        if (xmlPullParser.getName().equals("font-family")) {
            return readFamily(xmlPullParser, resources);
        }
        skip(xmlPullParser);
        return null;
    }

    @Nullable
    private static FamilyResourceEntry readFamily(XmlPullParser xmlPullParser, Resources resources) throws XmlPullParserException, IOException {
        TypedArray obtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlPullParser), R.styleable.FontFamily);
        String string = obtainAttributes.getString(R.styleable.FontFamily_fontProviderAuthority);
        String string2 = obtainAttributes.getString(R.styleable.FontFamily_fontProviderPackage);
        String string3 = obtainAttributes.getString(R.styleable.FontFamily_fontProviderQuery);
        int resourceId = obtainAttributes.getResourceId(R.styleable.FontFamily_fontProviderCerts, 0);
        obtainAttributes.recycle();
        if (string != null && string2 != null && string3 != null) {
            while (xmlPullParser.next() != 3) {
                skip(xmlPullParser);
            }
            return new ProviderResourceEntry(string, string2, string3, readCerts(resources, resourceId));
        }
        ArrayList arrayList = new ArrayList();
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                if (xmlPullParser.getName().equals("font")) {
                    arrayList.add(readFont(xmlPullParser, resources));
                } else {
                    skip(xmlPullParser);
                }
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new FontFamilyFilesResourceEntry((FontFileResourceEntry[]) arrayList.toArray(new FontFileResourceEntry[arrayList.size()]));
    }

    private static FontFileResourceEntry readFont(XmlPullParser xmlPullParser, Resources resources) throws XmlPullParserException, IOException {
        TypedArray obtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlPullParser), R.styleable.FontFamilyFont);
        int i = obtainAttributes.getInt(R.styleable.FontFamilyFont_fontWeight, NORMAL_WEIGHT);
        boolean z = true;
        if (1 != obtainAttributes.getInt(R.styleable.FontFamilyFont_fontStyle, 0)) {
            z = false;
        }
        String string = obtainAttributes.getString(R.styleable.FontFamilyFont_font);
        int resourceId = obtainAttributes.getResourceId(R.styleable.FontFamilyFont_font, 0);
        obtainAttributes.recycle();
        while (xmlPullParser.next() != 3) {
            skip(xmlPullParser);
        }
        return new FontFileResourceEntry(string, i, z, resourceId);
    }

    private static void skip(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int i = 1;
        while (i > 0) {
            switch (xmlPullParser.next()) {
                case 2:
                    i++;
                    break;
                case 3:
                    i--;
                    break;
            }
        }
    }

    private static List<byte[]> toByteArrayList(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            arrayList.add(Base64.decode(str, 0));
        }
        return arrayList;
    }
}
