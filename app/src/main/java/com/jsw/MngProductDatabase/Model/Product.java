package com.jsw.MngProductDatabase.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;

/**
 * Created by joselu on 11/01/17.
 */

public class Product implements Parcelable, Comparable<Product> {
    int ID;
    String name;
    String description;
    String brand;
    String dosage;
    Double price;
    String stock;
    String image;
    int idCategory;

    public Product() {
    }

    public Product(int ID, String name, String description, String brand, String dosage, Double price, String stock, String image, int idCategory) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.dosage = dosage;
        this.price = price;
        this.stock = stock;
        this.image = image;
        this.idCategory = idCategory;
    }



    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getBrand() {
        return brand;
    }

    public String getDosage() {
        return dosage;
    }

    public Double getPrice() {
        return price;
    }

    public String getStock() {
        return stock;
    }

    public String getImage() {
        return image;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public String getFormattedPrice(){
        return String.format("$%s", this.price);
    }

    public static final Comparator<Product> NAME_COMPARATOR = new Comparator<Product>() {
        @Override
        public int compare(Product p1, Product p2) {
            return p1.getName().compareTo(p2.getName());
        }
    };

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    /****PARCELABLE IMPLEMENTATION******/
    protected Product(Parcel in) {
        ID = in.readInt();
        name = in.readString();
        description = in.readString();
        brand = in.readString();
        dosage = in.readString();
        stock = in.readString();
        image = in.readString();
        idCategory = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ID);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(brand);
        dest.writeString(dosage);
        dest.writeString(stock);
        dest.writeString(image);
        dest.writeInt(idCategory);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @Override
    public int compareTo(Product product) {
        if(this.getName().compareTo(product.getName()) == 0)
            return this.getBrand().compareTo(product.getBrand());
        else
            return this.getName().compareTo(product.getName());
    }
}
