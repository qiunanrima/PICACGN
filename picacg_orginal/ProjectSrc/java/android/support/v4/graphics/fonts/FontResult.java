package android.support.v4.graphics.fonts;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.util.Preconditions;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: picacg_1.jar:android/support/v4/graphics/fonts/FontResult.class */
public final class FontResult implements Parcelable {
    public static final Parcelable.Creator<FontResult> CREATOR = new Parcelable.Creator<FontResult>() { // from class: android.support.v4.graphics.fonts.FontResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FontResult createFromParcel(Parcel parcel) {
            return new FontResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FontResult[] newArray(int i) {
            return new FontResult[i];
        }
    };
    private final ParcelFileDescriptor mFileDescriptor;
    private final String mFontVariationSettings;
    private final boolean mItalic;
    private final int mTtcIndex;
    private final int mWeight;

    private FontResult(Parcel parcel) {
        this.mFileDescriptor = (ParcelFileDescriptor) parcel.readParcelable(null);
        this.mTtcIndex = parcel.readInt();
        boolean z = true;
        if (parcel.readInt() == 1) {
            this.mFontVariationSettings = parcel.readString();
        } else {
            this.mFontVariationSettings = null;
        }
        this.mWeight = parcel.readInt();
        this.mItalic = parcel.readInt() != 1 ? false : z;
    }

    public FontResult(@NonNull ParcelFileDescriptor parcelFileDescriptor, int i, @Nullable String str, int i2, boolean z) {
        this.mFileDescriptor = (ParcelFileDescriptor) Preconditions.checkNotNull(parcelFileDescriptor);
        this.mTtcIndex = i;
        this.mFontVariationSettings = str;
        this.mWeight = i2;
        this.mItalic = z;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ParcelFileDescriptor getFileDescriptor() {
        return this.mFileDescriptor;
    }

    public String getFontVariationSettings() {
        return this.mFontVariationSettings;
    }

    public boolean getItalic() {
        return this.mItalic;
    }

    public int getTtcIndex() {
        return this.mTtcIndex;
    }

    public int getWeight() {
        return this.mWeight;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mFileDescriptor, i);
        parcel.writeInt(this.mTtcIndex);
        parcel.writeInt(this.mFontVariationSettings != null ? 1 : 0);
        if (this.mFontVariationSettings != null) {
            parcel.writeString(this.mFontVariationSettings);
        }
        parcel.writeInt(this.mWeight);
        parcel.writeInt(this.mItalic ? 1 : 0);
    }
}
