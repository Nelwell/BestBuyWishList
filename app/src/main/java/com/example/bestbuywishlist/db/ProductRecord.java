package com.example.bestbuywishlist.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ProductRecord {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String salePrice;
    private String name;
    private String image;

    public ProductRecord(@NonNull String salePrice, String name, String image) {
        this.salePrice = salePrice;
        this.name = name;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(@NonNull String salePrice) {
        this.salePrice = salePrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "ProductRecord{" +
                "id=" + id +
                ", salePrice='" + salePrice + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
