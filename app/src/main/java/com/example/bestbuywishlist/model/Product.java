package com.example.bestbuywishlist.model;

import com.google.gson.annotations.SerializedName;

public class Product {
    @SerializedName("salePrice") // Allows member variable to be named differently than API equivalent field
    private String price;
    private String name;
    private String sku;
    private String image;

    public Product(String price, String name, String sku, String image) {
        this.price = price;
        this.name = name;
        this.sku = sku;
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Product{" +
                "price='" + price + '\'' +
                ", name='" + name + '\'' +
                ", sku='" + sku + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
