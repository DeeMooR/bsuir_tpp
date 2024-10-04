package com.example.shop.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Good implements Parcelable {
    int id;
    String name;
    double cost;
    boolean check;

    public Good(int id, String name, double cost, boolean check) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.check = check;
    }

    public void setId(int id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setCost(double cost) {this.cost = cost;}
    public void setCheck(boolean check) {this.check = check;}

    public int getId() {return id;}
    public String getName() {return name;}
    public double getCost() {return cost;}
    public boolean isCheck() {return check;}

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeDouble(cost);
    }
    public static final Parcelable.Creator<Good> CREATOR = new Parcelable.Creator<Good>() {
        public Good createFromParcel(Parcel in) {
            return new Good(in);
        }
        public Good[] newArray(int size) {
            return new Good[size];
        }
    };
    private Good(Parcel parcel) {
        id = parcel.readInt();
        name = parcel.readString();
        cost = parcel.readDouble();
        check = false;
    }
}
