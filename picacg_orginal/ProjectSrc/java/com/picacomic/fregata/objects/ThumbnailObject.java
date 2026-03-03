package com.picacomic.fregata.objects;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/ThumbnailObject.class */
public class ThumbnailObject implements Parcelable {
    public static final Parcelable.Creator<ThumbnailObject> CREATOR = new Parcelable.Creator<ThumbnailObject>() { // from class: com.picacomic.fregata.objects.ThumbnailObject.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ThumbnailObject createFromParcel(Parcel parcel) {
            return new ThumbnailObject(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ThumbnailObject[] newArray(int i) {
            return new ThumbnailObject[i];
        }
    };
    String fileServer;
    String originalName;
    String path;

    public ThumbnailObject() {
    }

    protected ThumbnailObject(Parcel parcel) {
        this.fileServer = parcel.readString();
        this.path = parcel.readString();
        this.originalName = parcel.readString();
    }

    public ThumbnailObject(String str, String str2, String str3) {
        this.fileServer = str;
        this.path = str2;
        this.originalName = str3;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getFileServer() {
        return this.fileServer;
    }

    public String getOriginalName() {
        return this.originalName;
    }

    public String getPath() {
        return this.path;
    }

    public void setFileServer(String str) {
        this.fileServer = str;
    }

    public void setOriginalName(String str) {
        this.originalName = str;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public String toString() {
        return "ThumbnailObject{fileServer='" + this.fileServer + "', path='" + this.path + "', originalName='" + this.originalName + "'}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.fileServer);
        parcel.writeString(this.path);
        parcel.writeString(this.originalName);
    }
}
