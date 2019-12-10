package com.example.bestbuywishlist.model;

public class Products {
    private String salePrice;
    private String name;
    private String sku;

    public Products(String salePrice, String name, String sku) {
        this.salePrice = salePrice;
        this.name = name;
        this.sku = sku;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
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
        return "Products{" +
                "salePrice='" + salePrice + '\'' +
                ", name='" + name + '\'' +
                ", sku='" + sku + '\'' +
                '}';
    }
}
