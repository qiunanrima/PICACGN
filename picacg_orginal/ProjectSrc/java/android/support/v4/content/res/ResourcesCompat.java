package android.support.v4.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.FontRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.content.res.FontResourcesParserCompat;
import android.support.v4.graphics.TypefaceCompat;
import android.support.v4.os.BuildCompat;
import android.util.Log;
import android.util.TypedValue;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: picacg_1.jar:android/support/v4/content/res/ResourcesCompat.class */
public final class ResourcesCompat {
    private static final String TAG = "ResourcesCompat";

    private ResourcesCompat() {
    }

    @ColorInt
    public static int getColor(@NonNull Resources resources, @ColorRes int i, @Nullable Resources.Theme theme) throws Resources.NotFoundException {
        return Build.VERSION.SDK_INT >= 23 ? resources.getColor(i, theme) : resources.getColor(i);
    }

    @Nullable
    public static ColorStateList getColorStateList(@NonNull Resources resources, @ColorRes int i, @Nullable Resources.Theme theme) throws Resources.NotFoundException {
        return Build.VERSION.SDK_INT >= 23 ? resources.getColorStateList(i, theme) : resources.getColorStateList(i);
    }

    @Nullable
    public static Drawable getDrawable(@NonNull Resources resources, @DrawableRes int i, @Nullable Resources.Theme theme) throws Resources.NotFoundException {
        return Build.VERSION.SDK_INT >= 21 ? resources.getDrawable(i, theme) : resources.getDrawable(i);
    }

    @Nullable
    public static Drawable getDrawableForDensity(@NonNull Resources resources, @DrawableRes int i, int i2, @Nullable Resources.Theme theme) throws Resources.NotFoundException {
        return Build.VERSION.SDK_INT >= 21 ? resources.getDrawableForDensity(i, i2, theme) : Build.VERSION.SDK_INT >= 15 ? resources.getDrawableForDensity(i, i2) : resources.getDrawable(i);
    }

    @Nullable
    public static Typeface getFont(@NonNull Context context, @FontRes int i) throws Resources.NotFoundException {
        if (context.isRestricted()) {
            return null;
        }
        return BuildCompat.isAtLeastO() ? context.getResources().getFont(i) : loadFont(context, i, 0).getTypeface();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static TypefaceCompat.TypefaceHolder getFont(@NonNull Context context, @FontRes int i, int i2) throws Resources.NotFoundException {
        if (context.isRestricted()) {
            return null;
        }
        if (BuildCompat.isAtLeastO()) {
            return new TypefaceCompat.TypefaceHolder(Typeface.create(context.getResources().getFont(i), i2), (i2 & 1) == 0 ? 400 : 700, (i2 & 2) != 0);
        }
        return loadFont(context, i, i2);
    }

    private static TypefaceCompat.TypefaceHolder loadFont(@NonNull Context context, int i, int i2) {
        TypedValue typedValue = new TypedValue();
        Resources resources = context.getResources();
        resources.getValue(i, typedValue, true);
        TypefaceCompat.TypefaceHolder loadFont = loadFont(context, resources, typedValue, i, i2);
        if (loadFont != null) {
            return loadFont;
        }
        throw new Resources.NotFoundException("Font resource ID #0x" + Integer.toHexString(i));
    }

    private static TypefaceCompat.TypefaceHolder loadFont(@NonNull Context context, Resources resources, TypedValue typedValue, int i, int i2) {
        if (typedValue.string == null) {
            throw new Resources.NotFoundException("Resource \"" + resources.getResourceName(i) + "\" (" + Integer.toHexString(i) + ") is not a Font: " + typedValue);
        }
        TypefaceCompat.TypefaceHolder findFromCache = TypefaceCompat.findFromCache(resources, i, i2);
        if (findFromCache != null) {
            return findFromCache;
        }
        String charSequence = typedValue.string.toString();
        try {
            if (charSequence.toLowerCase().endsWith(".xml")) {
                FontResourcesParserCompat.FamilyResourceEntry parse = FontResourcesParserCompat.parse(resources.getXml(i), resources);
                if (parse == null) {
                    Log.e(TAG, "Failed to find font-family tag");
                    return null;
                }
                return TypefaceCompat.createFromResourcesFamilyXml(context, parse, resources, i, i2);
            }
            return TypefaceCompat.createFromResourcesFontFile(context, resources, i, i2);
        } catch (IOException e) {
            Log.e(TAG, "Failed to read xml resource " + charSequence, e);
            return null;
        } catch (XmlPullParserException e2) {
            Log.e(TAG, "Failed to parse xml resource " + charSequence, e2);
            return null;
        }
    }
}
