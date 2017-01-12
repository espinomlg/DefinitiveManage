package com.jsw.MngProductDatabase.Model;

/**
 * Created by joselu on 11/01/17.
 */

public class Product {
    int ID;
    String name;
    String description;
    String brand;
    String dosage;
    Double price;
    String stock;
    String image;
    int idCategory;

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
}
