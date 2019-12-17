package com.example.bestbuywishlist.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ProductRecord {

    @NonNull
    @PrimaryKey
    private String sku;

    @NonNull
    private String salePrice;
    private String name;

    public ProductRecord(@NonNull String sku, @NonNull String salePrice, String name) {
        this.sku = sku;
        this.salePrice = salePrice;
        this.name = name;
    }

    @NonNull
    public String getSku() {
        return sku;
    }

    public void setSku(@NonNull String sku) {
        this.sku = sku;
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

    @Override
    public String toString() {
        return "ProductRecord{" +
                "sku='" + sku + '\'' +
                ", salePrice='" + salePrice + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
