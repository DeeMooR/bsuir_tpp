package com.example.mynotes.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable {
    int id;
    String text;

    public Note(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public void setId(int id) {this.id = id;}
    public void setText(String text) {this.text = text;}

    public int getId() {return id;}
    public String getText() {return text;}

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(text);
    }
    public static final Creator<Note> CREATOR = new Creator<Note>() {
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };
    private Note(Parcel parcel) {
        id = parcel.readInt();
        text = parcel.readString();
    }
}
