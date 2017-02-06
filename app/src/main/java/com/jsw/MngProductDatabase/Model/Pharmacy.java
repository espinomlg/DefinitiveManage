package com.jsw.MngProductDatabase.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by joselu on 11/01/17.
 */

public class Pharmacy implements Parcelable{
    public static String PHARMACY_KEY = "PHARMACY";

    int id;
    String cif;
    String address;
    String phone;

    public Pharmacy(int id, String cif, String address, String phone) {
        this.id = id;
        this.cif = cif;
        this.address = address;
        this.phone = phone;
    }

    protected Pharmacy(Parcel in) {
        id = in.readInt();
        cif = in.readString();
        address = in.readString();
        phone = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(cif);
        dest.writeString(address);
        dest.writeString(phone);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Pharmacy> CREATOR = new Creator<Pharmacy>() {
        @Override
        public Pharmacy createFromParcel(Parcel in) {
            return new Pharmacy(in);
        }

        @Override
        public Pharmacy[] newArray(int size) {
            return new Pharmacy[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getCif() {
        return cif;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
}
