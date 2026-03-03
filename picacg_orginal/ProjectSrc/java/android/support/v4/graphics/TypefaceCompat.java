package android.support.v4.graphics;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.annotation.GuardedBy;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.content.res.FontResourcesParserCompat;
import android.support.v4.graphics.fonts.FontResult;
import android.support.v4.provider.FontsContractCompat;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: picacg_1.jar:android/support/v4/graphics/TypefaceCompat.class */
public class TypefaceCompat {
    private static final Object sLock = new Object();
    @GuardedBy("sLock")
    private static TypefaceCompatImpl sTypefaceCompatImpl;

    /* loaded from: picacg_1.jar:android/support/v4/graphics/TypefaceCompat$FontRequestCallback.class */
    public static abstract class FontRequestCallback {
        public static final int FAIL_REASON_FONT_LOAD_ERROR = -3;
        public static final int FAIL_REASON_FONT_NOT_FOUND = 1;
        public static final int FAIL_REASON_FONT_UNAVAILABLE = 2;
        public static final int FAIL_REASON_MALFORMED_QUERY = 3;
        public static final int FAIL_REASON_PROVIDER_NOT_FOUND = -1;
        public static final int FAIL_REASON_WRONG_CERTIFICATES = -2;

        public abstract void onTypefaceRequestFailed(int i);

        public abstract void onTypefaceRetrieved(Typeface typeface);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: picacg_1.jar:android/support/v4/graphics/TypefaceCompat$TypefaceCompatImpl.class */
    public interface TypefaceCompatImpl {
        TypefaceHolder createFromResourcesFamilyXml(FontResourcesParserCompat.FamilyResourceEntry familyResourceEntry, Resources resources, int i, int i2);

        TypefaceHolder createFromResourcesFontFile(Resources resources, int i, int i2);

        TypefaceHolder createTypeface(@NonNull List<FontResult> list);

        TypefaceHolder createTypeface(@NonNull FontsContractCompat.FontInfo[] fontInfoArr, Map<Uri, ByteBuffer> map);

        TypefaceHolder findFromCache(Resources resources, int i, int i2);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: picacg_1.jar:android/support/v4/graphics/TypefaceCompat$TypefaceHolder.class */
    public static class TypefaceHolder {
        private final boolean mItalic;
        private final Typeface mTypeface;
        private final int mWeight;

        public TypefaceHolder(Typeface typeface, int i, boolean z) {
            this.mTypeface = typeface;
            this.mWeight = i;
            this.mItalic = z;
        }

        public Typeface getTypeface() {
            return this.mTypeface;
        }

        public int getWeight() {
            return this.mWeight;
        }

        public boolean isItalic() {
            return this.mItalic;
        }
    }

    private TypefaceCompat() {
    }

    public static TypefaceHolder createFromResourcesFamilyXml(Context context, FontResourcesParserCompat.FamilyResourceEntry familyResourceEntry, Resources resources, int i, int i2) {
        maybeInitImpl(context);
        return sTypefaceCompatImpl.createFromResourcesFamilyXml(familyResourceEntry, resources, i, i2);
    }

    @Nullable
    public static TypefaceHolder createFromResourcesFontFile(Context context, Resources resources, int i, int i2) {
        maybeInitImpl(context);
        return sTypefaceCompatImpl.createFromResourcesFontFile(resources, i, i2);
    }

    public static TypefaceHolder createTypeface(Context context, @NonNull List<FontResult> list) {
        maybeInitImpl(context);
        return sTypefaceCompatImpl.createTypeface(list);
    }

    public static TypefaceHolder createTypeface(Context context, @NonNull FontsContractCompat.FontInfo[] fontInfoArr, Map<Uri, ByteBuffer> map) {
        maybeInitImpl(context);
        return sTypefaceCompatImpl.createTypeface(fontInfoArr, map);
    }

    public static TypefaceHolder findFromCache(Resources resources, int i, int i2) {
        synchronized (sLock) {
            if (sTypefaceCompatImpl == null) {
                return null;
            }
            return sTypefaceCompatImpl.findFromCache(resources, i, i2);
        }
    }

    @TargetApi(26)
    private static void maybeInitImpl(Context context) {
        if (sTypefaceCompatImpl == null) {
            synchronized (sLock) {
                if (sTypefaceCompatImpl == null) {
                    sTypefaceCompatImpl = new TypefaceCompatBaseImpl(context);
                }
            }
        }
    }
}
