package android.support.v7.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.v7.appcompat.R;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.TextView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: picacg_1.jar:android/support/v7/widget/AppCompatTextViewAutoSizeHelper.class */
public class AppCompatTextViewAutoSizeHelper {
    private static final int DEFAULT_AUTO_SIZE_GRANULARITY_IN_PX = 1;
    private static final int DEFAULT_AUTO_SIZE_MAX_TEXT_SIZE_IN_SP = 112;
    private static final int DEFAULT_AUTO_SIZE_MIN_TEXT_SIZE_IN_SP = 12;
    private static final RectF TEMP_RECTF = new RectF();
    static final int UNSET_AUTO_SIZE_UNIFORM_CONFIGURATION_VALUE = -1;
    private static final int VERY_WIDE = 1048576;
    private final Context mContext;
    private TextPaint mTempTextPaint;
    private final TextView mTextView;
    private int mAutoSizeTextType = 0;
    private boolean mNeedsAutoSizeText = false;
    private int mAutoSizeStepGranularityInPx = -1;
    private int mAutoSizeMinTextSizeInPx = -1;
    private int mAutoSizeMaxTextSizeInPx = -1;
    private int[] mAutoSizeTextSizesInPx = new int[0];
    private boolean mHasPresetAutoSizeValues = false;
    private Hashtable<String, Method> mMethodByNameCache = new Hashtable<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    public AppCompatTextViewAutoSizeHelper(TextView textView) {
        this.mTextView = textView;
        this.mContext = this.mTextView.getContext();
    }

    private int[] cleanupAutoSizePresetSizes(int[] iArr) {
        int length = iArr.length;
        if (length == 0) {
            return iArr;
        }
        Arrays.sort(iArr);
        ArrayList arrayList = new ArrayList();
        for (int i : iArr) {
            if (i > 0 && Collections.binarySearch(arrayList, Integer.valueOf(i)) < 0) {
                arrayList.add(Integer.valueOf(i));
            }
        }
        if (length == arrayList.size()) {
            return iArr;
        }
        int size = arrayList.size();
        int[] iArr2 = new int[size];
        for (int i2 = 0; i2 < size; i2++) {
            iArr2[i2] = ((Integer) arrayList.get(i2)).intValue();
        }
        return iArr2;
    }

    private void clearAutoSizeConfiguration() {
        this.mAutoSizeTextType = 0;
        this.mAutoSizeMinTextSizeInPx = -1;
        this.mAutoSizeMaxTextSizeInPx = -1;
        this.mAutoSizeStepGranularityInPx = -1;
        this.mAutoSizeTextSizesInPx = new int[0];
        this.mNeedsAutoSizeText = false;
    }

    @TargetApi(23)
    private StaticLayout createStaticLayoutForMeasuring(CharSequence charSequence, Layout.Alignment alignment, int i, int i2) {
        TextDirectionHeuristic textDirectionHeuristic = (TextDirectionHeuristic) invokeAndReturnWithDefault(this.mTextView, "getTextDirectionHeuristic", TextDirectionHeuristics.FIRSTSTRONG_LTR);
        StaticLayout.Builder hyphenationFrequency = StaticLayout.Builder.obtain(charSequence, 0, charSequence.length(), this.mTempTextPaint, i).setAlignment(alignment).setLineSpacing(this.mTextView.getLineSpacingExtra(), this.mTextView.getLineSpacingMultiplier()).setIncludePad(this.mTextView.getIncludeFontPadding()).setBreakStrategy(this.mTextView.getBreakStrategy()).setHyphenationFrequency(this.mTextView.getHyphenationFrequency());
        int i3 = i2;
        if (i2 == -1) {
            i3 = Integer.MAX_VALUE;
        }
        return hyphenationFrequency.setMaxLines(i3).setTextDirection(textDirectionHeuristic).build();
    }

    @TargetApi(14)
    private StaticLayout createStaticLayoutForMeasuringPre23(CharSequence charSequence, Layout.Alignment alignment, int i) {
        float floatValue;
        float floatValue2;
        boolean booleanValue;
        if (Build.VERSION.SDK_INT >= 16) {
            floatValue = this.mTextView.getLineSpacingMultiplier();
            floatValue2 = this.mTextView.getLineSpacingExtra();
            booleanValue = this.mTextView.getIncludeFontPadding();
        } else {
            floatValue = ((Float) invokeAndReturnWithDefault(this.mTextView, "getLineSpacingMultiplier", Float.valueOf(1.0f))).floatValue();
            floatValue2 = ((Float) invokeAndReturnWithDefault(this.mTextView, "getLineSpacingExtra", Float.valueOf(0.0f))).floatValue();
            booleanValue = ((Boolean) invokeAndReturnWithDefault(this.mTextView, "getIncludeFontPadding", true)).booleanValue();
        }
        return new StaticLayout(charSequence, this.mTempTextPaint, i, alignment, floatValue, floatValue2, booleanValue);
    }

    private int findLargestTextSizeWhichFits(RectF rectF) {
        int length = this.mAutoSizeTextSizesInPx.length;
        if (length != 0) {
            int i = length - 1;
            int i2 = 1;
            int i3 = 0;
            while (i2 <= i) {
                int i4 = (i2 + i) / 2;
                if (suggestedSizeFitsInSpace(this.mAutoSizeTextSizesInPx[i4], rectF)) {
                    i3 = i2;
                    i2 = i4 + 1;
                } else {
                    i3 = i4 - 1;
                    i = i3;
                }
            }
            return this.mAutoSizeTextSizesInPx[i3];
        }
        throw new IllegalStateException("No available text sizes to choose from.");
    }

    private <T> T invokeAndReturnWithDefault(@NonNull Object obj, @NonNull String str, @NonNull T t) {
        T t2;
        try {
            Method method = this.mMethodByNameCache.get(str);
            Method method2 = method;
            if (method == null) {
                Method declaredMethod = TextView.class.getDeclaredMethod(str, new Class[0]);
                method2 = declaredMethod;
                if (declaredMethod != null) {
                    declaredMethod.setAccessible(true);
                    this.mMethodByNameCache.put(str, declaredMethod);
                    method2 = declaredMethod;
                }
            }
            t2 = method2.invoke(obj, new Object[0]);
        } catch (Exception e) {
            t2 = t;
        }
        return t2;
    }

    private void setRawTextSize(float f) {
        if (f != this.mTextView.getPaint().getTextSize()) {
            this.mTextView.getPaint().setTextSize(f);
            if (this.mTextView.getLayout() != null) {
                this.mNeedsAutoSizeText = false;
                try {
                    Method method = this.mMethodByNameCache.get("nullLayouts");
                    Method method2 = method;
                    if (method == null) {
                        Method declaredMethod = TextView.class.getDeclaredMethod("nullLayouts", new Class[0]);
                        method2 = declaredMethod;
                        if (declaredMethod != null) {
                            declaredMethod.setAccessible(true);
                            this.mMethodByNameCache.put("nullLayouts", declaredMethod);
                            method2 = declaredMethod;
                        }
                    }
                    if (method2 != null) {
                        method2.invoke(this.mTextView, new Object[0]);
                    }
                } catch (Exception e) {
                }
                this.mTextView.requestLayout();
                this.mTextView.invalidate();
            }
        }
    }

    private void setupAutoSizeText() {
        if (supportsAutoSizeText() && this.mAutoSizeTextType == 1) {
            if (!this.mHasPresetAutoSizeValues || this.mAutoSizeTextSizesInPx.length == 0) {
                int ceil = (int) Math.ceil((this.mAutoSizeMaxTextSizeInPx - this.mAutoSizeMinTextSizeInPx) / this.mAutoSizeStepGranularityInPx);
                int i = ceil;
                if ((this.mAutoSizeMaxTextSizeInPx - this.mAutoSizeMinTextSizeInPx) % this.mAutoSizeStepGranularityInPx == 0) {
                    i = ceil + 1;
                }
                this.mAutoSizeTextSizesInPx = new int[i];
                int i2 = this.mAutoSizeMinTextSizeInPx;
                for (int i3 = 0; i3 < i; i3++) {
                    this.mAutoSizeTextSizesInPx[i3] = i2;
                    i2 += this.mAutoSizeStepGranularityInPx;
                }
            }
            this.mNeedsAutoSizeText = true;
            autoSizeText();
        }
    }

    private void setupAutoSizeUniformPresetSizes(TypedArray typedArray) {
        int length = typedArray.length();
        int[] iArr = new int[length];
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                iArr[i] = typedArray.getDimensionPixelSize(i, -1);
            }
            this.mAutoSizeTextSizesInPx = cleanupAutoSizePresetSizes(iArr);
            setupAutoSizeUniformPresetSizesConfiguration();
        }
    }

    private boolean setupAutoSizeUniformPresetSizesConfiguration() {
        int length = this.mAutoSizeTextSizesInPx.length;
        this.mHasPresetAutoSizeValues = length > 0;
        if (this.mHasPresetAutoSizeValues) {
            this.mAutoSizeTextType = 1;
            this.mAutoSizeMinTextSizeInPx = this.mAutoSizeTextSizesInPx[0];
            this.mAutoSizeMaxTextSizeInPx = this.mAutoSizeTextSizesInPx[length - 1];
            this.mAutoSizeStepGranularityInPx = -1;
        }
        return this.mHasPresetAutoSizeValues;
    }

    private boolean suggestedSizeFitsInSpace(int i, RectF rectF) {
        CharSequence text = this.mTextView.getText();
        int maxLines = Build.VERSION.SDK_INT >= 16 ? this.mTextView.getMaxLines() : -1;
        int measuredWidth = ((Boolean) invokeAndReturnWithDefault(this.mTextView, "getHorizontallyScrolling", false)).booleanValue() ? 1048576 : (this.mTextView.getMeasuredWidth() - this.mTextView.getTotalPaddingLeft()) - this.mTextView.getTotalPaddingRight();
        if (this.mTempTextPaint == null) {
            this.mTempTextPaint = new TextPaint();
        } else {
            this.mTempTextPaint.reset();
        }
        this.mTempTextPaint.set(this.mTextView.getPaint());
        this.mTempTextPaint.setTextSize(i);
        Layout.Alignment alignment = (Layout.Alignment) invokeAndReturnWithDefault(this.mTextView, "getLayoutAlignment", Layout.Alignment.ALIGN_NORMAL);
        StaticLayout createStaticLayoutForMeasuring = Build.VERSION.SDK_INT >= 23 ? createStaticLayoutForMeasuring(text, alignment, measuredWidth, maxLines) : createStaticLayoutForMeasuringPre23(text, alignment, measuredWidth);
        return (maxLines == -1 || createStaticLayoutForMeasuring.getLineCount() <= maxLines) && ((float) createStaticLayoutForMeasuring.getHeight()) <= rectF.bottom;
    }

    private boolean supportsAutoSizeText() {
        return !(this.mTextView instanceof AppCompatEditText);
    }

    private void validateAndSetAutoSizeTextTypeUniformConfiguration(int i, int i2, int i3) throws IllegalArgumentException {
        if (i <= 0) {
            throw new IllegalArgumentException("Minimum auto-size text size (" + i + "px) is less or equal to (0px)");
        } else if (i2 <= i) {
            throw new IllegalArgumentException("Maximum auto-size text size (" + i2 + "px) is less or equal to minimum auto-size text size (" + i + "px)");
        } else if (i3 <= 0) {
            throw new IllegalArgumentException("The auto-size step granularity (" + i3 + "px) is less or equal to (0px)");
        } else {
            this.mAutoSizeTextType = 1;
            this.mAutoSizeMinTextSizeInPx = i;
            this.mAutoSizeMaxTextSizeInPx = i2;
            this.mAutoSizeStepGranularityInPx = i3;
            this.mHasPresetAutoSizeValues = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void autoSizeText() {
        int width = (this.mTextView.getWidth() - this.mTextView.getTotalPaddingLeft()) - this.mTextView.getTotalPaddingRight();
        int height = Build.VERSION.SDK_INT >= 21 ? (this.mTextView.getHeight() - this.mTextView.getExtendedPaddingBottom()) - this.mTextView.getExtendedPaddingBottom() : (this.mTextView.getHeight() - this.mTextView.getCompoundPaddingBottom()) - this.mTextView.getCompoundPaddingTop();
        if (width <= 0 || height <= 0) {
            return;
        }
        synchronized (TEMP_RECTF) {
            TEMP_RECTF.setEmpty();
            TEMP_RECTF.right = width;
            TEMP_RECTF.bottom = height;
            float findLargestTextSizeWhichFits = findLargestTextSizeWhichFits(TEMP_RECTF);
            if (findLargestTextSizeWhichFits != this.mTextView.getTextSize()) {
                setTextSizeInternal(0, findLargestTextSizeWhichFits);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getAutoSizeMaxTextSize() {
        return this.mAutoSizeMaxTextSizeInPx;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getAutoSizeMinTextSize() {
        return this.mAutoSizeMinTextSizeInPx;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getAutoSizeStepGranularity() {
        return this.mAutoSizeStepGranularityInPx;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int[] getAutoSizeTextAvailableSizes() {
        return this.mAutoSizeTextSizesInPx;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getAutoSizeTextType() {
        return this.mAutoSizeTextType;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean getNeedsAutoSizeText() {
        return this.mNeedsAutoSizeText;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean isAutoSizeEnabled() {
        return supportsAutoSizeText() && this.mAutoSizeTextType != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void loadFromAttributes(AttributeSet attributeSet, int i) {
        int resourceId;
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(attributeSet, R.styleable.AppCompatTextView, i, 0);
        if (obtainStyledAttributes.hasValue(R.styleable.AppCompatTextView_autoSizeTextType)) {
            this.mAutoSizeTextType = obtainStyledAttributes.getInt(R.styleable.AppCompatTextView_autoSizeTextType, 0);
        }
        int dimensionPixelSize = obtainStyledAttributes.hasValue(R.styleable.AppCompatTextView_autoSizeStepGranularity) ? obtainStyledAttributes.getDimensionPixelSize(R.styleable.AppCompatTextView_autoSizeStepGranularity, -1) : -1;
        int dimensionPixelSize2 = obtainStyledAttributes.hasValue(R.styleable.AppCompatTextView_autoSizeMinTextSize) ? obtainStyledAttributes.getDimensionPixelSize(R.styleable.AppCompatTextView_autoSizeMinTextSize, -1) : -1;
        int dimensionPixelSize3 = obtainStyledAttributes.hasValue(R.styleable.AppCompatTextView_autoSizeMaxTextSize) ? obtainStyledAttributes.getDimensionPixelSize(R.styleable.AppCompatTextView_autoSizeMaxTextSize, -1) : -1;
        if (obtainStyledAttributes.hasValue(R.styleable.AppCompatTextView_autoSizePresetSizes) && (resourceId = obtainStyledAttributes.getResourceId(R.styleable.AppCompatTextView_autoSizePresetSizes, 0)) > 0) {
            TypedArray obtainTypedArray = obtainStyledAttributes.getResources().obtainTypedArray(resourceId);
            setupAutoSizeUniformPresetSizes(obtainTypedArray);
            obtainTypedArray.recycle();
        }
        obtainStyledAttributes.recycle();
        if (!supportsAutoSizeText()) {
            this.mAutoSizeTextType = 0;
        } else if (this.mAutoSizeTextType == 1) {
            if (!this.mHasPresetAutoSizeValues) {
                DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
                int i2 = dimensionPixelSize2;
                if (dimensionPixelSize2 == -1) {
                    i2 = (int) TypedValue.applyDimension(2, 12.0f, displayMetrics);
                }
                int i3 = dimensionPixelSize3;
                if (dimensionPixelSize3 == -1) {
                    i3 = (int) TypedValue.applyDimension(2, 112.0f, displayMetrics);
                }
                int i4 = dimensionPixelSize;
                if (dimensionPixelSize == -1) {
                    i4 = 1;
                }
                validateAndSetAutoSizeTextTypeUniformConfiguration(i2, i3, i4);
            }
            setupAutoSizeText();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setAutoSizeTextTypeUniformWithConfiguration(int i, int i2, int i3, int i4) throws IllegalArgumentException {
        if (supportsAutoSizeText()) {
            DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
            validateAndSetAutoSizeTextTypeUniformConfiguration((int) TypedValue.applyDimension(i4, i, displayMetrics), (int) TypedValue.applyDimension(i4, i2, displayMetrics), (int) TypedValue.applyDimension(i4, i3, displayMetrics));
            setupAutoSizeText();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setAutoSizeTextTypeUniformWithPresetSizes(@NonNull int[] iArr, int i) throws IllegalArgumentException {
        int[] iArr2;
        if (supportsAutoSizeText()) {
            int length = iArr.length;
            int i2 = 0;
            if (length > 0) {
                int[] iArr3 = new int[length];
                if (i != 0) {
                    DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
                    while (true) {
                        iArr2 = iArr3;
                        if (i2 >= length) {
                            break;
                        }
                        iArr3[i2] = (int) TypedValue.applyDimension(i, iArr[i2], displayMetrics);
                        i2++;
                    }
                } else {
                    iArr2 = Arrays.copyOf(iArr, length);
                }
                this.mAutoSizeTextSizesInPx = cleanupAutoSizePresetSizes(iArr2);
                if (!setupAutoSizeUniformPresetSizesConfiguration()) {
                    throw new IllegalArgumentException("None of the preset sizes is valid: " + Arrays.toString(iArr));
                }
            } else {
                this.mHasPresetAutoSizeValues = false;
            }
            setupAutoSizeText();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setAutoSizeTextTypeWithDefaults(int i) {
        if (supportsAutoSizeText()) {
            switch (i) {
                case 0:
                    clearAutoSizeConfiguration();
                    return;
                case 1:
                    DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
                    validateAndSetAutoSizeTextTypeUniformConfiguration((int) TypedValue.applyDimension(2, 12.0f, displayMetrics), (int) TypedValue.applyDimension(2, 112.0f, displayMetrics), 1);
                    setupAutoSizeText();
                    return;
                default:
                    throw new IllegalArgumentException("Unknown auto-size text type: " + i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setNeedsAutoSizeText(boolean z) {
        this.mNeedsAutoSizeText = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setTextSizeInternal(int i, float f) {
        setRawTextSize(TypedValue.applyDimension(i, f, (this.mContext == null ? Resources.getSystem() : this.mContext.getResources()).getDisplayMetrics()));
    }
}
