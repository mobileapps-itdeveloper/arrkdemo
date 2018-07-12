package com.arrktest.arrkdemo.server;

import android.os.Parcel;
import android.os.Parcelable;

public class CharacterInfo implements Parcelable{
    private String name;
    private String height;
    private String mass;
    private String created;

    protected CharacterInfo(Parcel in) {
        name = in.readString();
        height = in.readString();
        mass = in.readString();
        created = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(height);
        dest.writeString(mass);
        dest.writeString(created);
    }

    public CharacterInfo(String name, String height, String mass, String created) {
        this.name = name;
        this.height = height;
        this.mass = mass;
        this.created = created;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CharacterInfo> CREATOR = new Creator<CharacterInfo>() {
        @Override
        public CharacterInfo createFromParcel(Parcel in) {
            return new CharacterInfo(in);
        }

        @Override
        public CharacterInfo[] newArray(int size) {
            return new CharacterInfo[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }


}
