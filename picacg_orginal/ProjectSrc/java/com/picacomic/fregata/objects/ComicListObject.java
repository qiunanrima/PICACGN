package com.picacomic.fregata.objects;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.picacomic.fregata.objects.databaseTable.DbComicDetailObject;
import java.util.ArrayList;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/ComicListObject.class */
public class ComicListObject implements Parcelable {
    public static final Parcelable.Creator<ComicListObject> CREATOR = new Parcelable.Creator<ComicListObject>() { // from class: com.picacomic.fregata.objects.ComicListObject.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ComicListObject createFromParcel(Parcel parcel) {
            return new ComicListObject(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ComicListObject[] newArray(int i) {
            return new ComicListObject[i];
        }
    };
    String author;
    ArrayList<String> categories;
    @SerializedName("_id")
    String comicId;
    @SerializedName("epsCount")
    int episodeCount;
    boolean finished;
    int likesCount;
    int pagesCount;
    ThumbnailObject thumb;
    String title;

    protected ComicListObject(Parcel parcel) {
        this.comicId = parcel.readString();
        this.title = parcel.readString();
        this.author = parcel.readString();
        this.likesCount = parcel.readInt();
        this.pagesCount = parcel.readInt();
        this.episodeCount = parcel.readInt();
        this.finished = parcel.readByte() != 0;
        this.categories = parcel.createStringArrayList();
        this.thumb = (ThumbnailObject) parcel.readParcelable(ThumbnailObject.class.getClassLoader());
    }

    public ComicListObject(DbComicDetailObject dbComicDetailObject) {
        this.comicId = dbComicDetailObject.getComicId();
        this.title = dbComicDetailObject.getTitle();
        this.author = dbComicDetailObject.getAuthor();
        this.likesCount = dbComicDetailObject.getLikesCount();
        this.pagesCount = dbComicDetailObject.getPagesCount();
        this.episodeCount = dbComicDetailObject.getEpisodeCount();
        this.finished = dbComicDetailObject.isFinished();
        this.categories = (ArrayList) new Gson().fromJson(dbComicDetailObject.getCategories(), (Class<Object>) ArrayList.class);
        this.thumb = dbComicDetailObject.getThumb();
    }

    public ComicListObject(String str) {
        this.comicId = str;
        this.title = null;
        this.author = null;
        this.likesCount = 0;
        this.pagesCount = 1;
        this.episodeCount = 1;
        this.finished = false;
        this.categories = null;
        this.thumb = null;
    }

    public ComicListObject(String str, String str2, String str3, int i, int i2, int i3, boolean z, ArrayList<String> arrayList, ThumbnailObject thumbnailObject) {
        this.comicId = str;
        this.title = str2;
        this.author = str3;
        this.likesCount = i;
        this.pagesCount = i2;
        this.episodeCount = i3;
        this.finished = z;
        this.categories = arrayList;
        this.thumb = thumbnailObject;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getAuthor() {
        return this.author;
    }

    public ArrayList<String> getCategories() {
        return this.categories;
    }

    public String getComicId() {
        return this.comicId;
    }

    public int getEpisodeCount() {
        return this.episodeCount;
    }

    public int getLikesCount() {
        return this.likesCount;
    }

    public int getPagesCount() {
        return this.pagesCount;
    }

    public ThumbnailObject getThumb() {
        return this.thumb;
    }

    public String getTitle() {
        return this.title;
    }

    public boolean isFinished() {
        return this.finished;
    }

    public void setAuthor(String str) {
        this.author = str;
    }

    public void setCategories(ArrayList<String> arrayList) {
        this.categories = arrayList;
    }

    public void setComicId(String str) {
        this.comicId = str;
    }

    public void setEpisodeCount(int i) {
        this.episodeCount = i;
    }

    public void setFinished(boolean z) {
        this.finished = z;
    }

    public void setLikesCount(int i) {
        this.likesCount = i;
    }

    public void setPagesCount(int i) {
        this.pagesCount = i;
    }

    public void setThumb(ThumbnailObject thumbnailObject) {
        this.thumb = thumbnailObject;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String toString() {
        return "ComicListObject{comicId='" + this.comicId + "', title='" + this.title + "', author='" + this.author + "', likesCount=" + this.likesCount + ", pagesCount=" + this.pagesCount + ", episodeCount=" + this.episodeCount + ", finished=" + this.finished + ", categories=" + this.categories + ", thumb=" + this.thumb + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.comicId);
        parcel.writeString(this.title);
        parcel.writeString(this.author);
        parcel.writeInt(this.likesCount);
        parcel.writeInt(this.pagesCount);
        parcel.writeInt(this.episodeCount);
        parcel.writeByte(this.finished ? (byte) 1 : (byte) 0);
        parcel.writeStringList(this.categories);
        parcel.writeParcelable(this.thumb, i);
    }
}
