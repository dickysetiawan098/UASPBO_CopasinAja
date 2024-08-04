package com.unsia.pemrogramanberbasisobjek;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class CopasModel implements Parcelable {

    private String IDText;
    private String IsiText;

    public CopasModel() {
    }

    public String getId() {
        return IDText;
    }

    public void setId(String IDText) {
        this.IDText = IDText;
    }

    public String getIsiText() {
        return IsiText;
    }

    public void setIsiText(String IsiText) {
        this.IsiText = IsiText;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(this.IDText);
        parcel.writeString(this.IsiText);
    }

    protected CopasModel(Parcel in) {
        this.IDText = in.readString();
        this.IsiText = in.readString();
    }

    public static final Parcelable.Creator<CopasModel> CREATOR = new Parcelable.Creator<CopasModel>() {
        @Override
        public CopasModel createFromParcel(Parcel source) {
            return new CopasModel(source);
        }

        @Override
        public CopasModel[] newArray(int size) {
            return new CopasModel[size];
        }
    };
}
