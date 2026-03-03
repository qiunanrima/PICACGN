package com.just.agentweb.filechooser;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: picacg_1.jar:com/just/agentweb/filechooser/FileParcel.class */
public class FileParcel implements Parcelable {
    public static final Parcelable.Creator<FileParcel> CREATOR = new Parcelable.Creator<FileParcel>() { // from class: com.just.agentweb.filechooser.FileParcel.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: c */
        public FileParcel createFromParcel(Parcel parcel) {
            return new FileParcel(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: g */
        public FileParcel[] newArray(int i) {
            return new FileParcel[i];
        }
    };
    private String gr;
    private String gs;
    private int mId;

    protected FileParcel(Parcel parcel) {
        this.mId = parcel.readInt();
        this.gr = parcel.readString();
        this.gs = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "FileParcel{mId=" + this.mId + ", mContentPath='" + this.gr + "', mFileBase64='" + this.gs + "'}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mId);
        parcel.writeString(this.gr);
        parcel.writeString(this.gs);
    }
}
