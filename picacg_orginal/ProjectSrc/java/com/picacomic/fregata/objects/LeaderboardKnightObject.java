package com.picacomic.fregata.objects;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/LeaderboardKnightObject.class */
public class LeaderboardKnightObject {
    ThumbnailObject avatar;
    String character;
    ArrayList<String> characters;
    int comicsUploaded;
    int exp;
    String gender;
    @SerializedName("_id")
    String leaderboardKnightId;
    int level;
    String name;
    boolean verified;

    public LeaderboardKnightObject(String str, String str2, String str3, String str4, ThumbnailObject thumbnailObject, ArrayList<String> arrayList, int i, int i2, int i3, boolean z) {
        this.leaderboardKnightId = str;
        this.name = str2;
        this.gender = str3;
        this.character = str4;
        this.avatar = thumbnailObject;
        this.characters = arrayList;
        this.level = i;
        this.exp = i2;
        this.comicsUploaded = i3;
        this.verified = z;
    }

    public ThumbnailObject getAvatar() {
        return this.avatar;
    }

    public String getCharacter() {
        return this.character;
    }

    public ArrayList<String> getCharacters() {
        return this.characters;
    }

    public int getComicsUploaded() {
        return this.comicsUploaded;
    }

    public int getExp() {
        return this.exp;
    }

    public String getGender() {
        return this.gender;
    }

    public String getLeaderboardKnightId() {
        return this.leaderboardKnightId;
    }

    public int getLevel() {
        return this.level;
    }

    public String getName() {
        return this.name;
    }

    public boolean isVerified() {
        return this.verified;
    }

    public void setAvatar(ThumbnailObject thumbnailObject) {
        this.avatar = thumbnailObject;
    }

    public void setCharacter(String str) {
        this.character = str;
    }

    public void setCharacters(ArrayList<String> arrayList) {
        this.characters = arrayList;
    }

    public void setComicsUploaded(int i) {
        this.comicsUploaded = i;
    }

    public void setExp(int i) {
        this.exp = i;
    }

    public void setGender(String str) {
        this.gender = str;
    }

    public void setLeaderboardKnightId(String str) {
        this.leaderboardKnightId = str;
    }

    public void setLevel(int i) {
        this.level = i;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setVerified(boolean z) {
        this.verified = z;
    }

    public String toString() {
        return "LeaderboardKnightObject{leaderboardKnightId='" + this.leaderboardKnightId + "', name='" + this.name + "', gender='" + this.gender + "', character='" + this.character + "', avatar=" + this.avatar + ", characters=" + this.characters + ", level=" + this.level + ", exp=" + this.exp + ", comicsUploaded=" + this.comicsUploaded + ", verified=" + this.verified + '}';
    }
}
