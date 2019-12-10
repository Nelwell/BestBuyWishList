package com.example.bestbuywishlist.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ProductsRecord {

    @PrimaryKey(autoGenerate = true)
    private int productId;

    @NonNull
    private String salePrice;
    private String name;
    private String sku;

    public ProductsRecord(@NonNull String salePrice, String name, String sku) {
        this.salePrice = salePrice;
        this.name = name;
        this.sku = sku;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    @Override
    public String toString() {
        return "ProductsRecord{" +
                "productId=" + productId +
                ", salePrice='" + salePrice + '\'' +
                ", name='" + name + '\'' +
                ", sku='" + sku + '\'' +
                '}';
    }
}
