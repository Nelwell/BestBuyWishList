package com.example.bestbuywishlist.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.bestbuywishlist.db.ProductRecord;
import com.example.bestbuywishlist.db.ProductsRepositoryRoom;

import java.util.List;

public class WishListViewModel extends AndroidViewModel {

    private ProductsRepositoryRoom productsRepositoryRoom;
    // Common to cache a copy of results of common queries here
    private LiveData<List<ProductRecord>> productListRecords;

    public WishListViewModel(@NonNull Application application) {
        super(application);
        productsRepositoryRoom = new ProductsRepositoryRoom(application);
        productListRecords = productsRepositoryRoom.getAllProductRecords();
    }

    public LiveData<List<ProductRecord>> getAllProductRecords() {
        return productListRecords;
    }

    public LiveData<ProductRecord> getProductById(int id) {
        return productsRepositoryRoom.getProductById(id);
    }

    public void delete(ProductRecord productRecord) {
        productsRepositoryRoom.delete(productRecord);
    }

    public void deleteAllProductRecords() {
        productsRepositoryRoom.deleteAllProductRecords();
    }

//    public void delete(int id) {
//        productsRepositoryRoom.delete(id);
//    }
}
