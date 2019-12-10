package com.example.bestbuywishlist.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.bestbuywishlist.model.Products;
import com.example.bestbuywishlist.repository.ProductsRepository;

import java.util.List;

public class ProductsViewModel extends AndroidViewModel {

    private ProductsRepository productsRepository;
    private MutableLiveData<List<Products>> allProducts;

    public ProductsViewModel (@NonNull Application application) {
        super(application);
        productsRepository = new ProductsRepository();
        allProducts = productsRepository.getAllProducts();
    }

    public MutableLiveData<List<Products>> getAllProducts() {
        return allProducts;
    }

    public MutableLiveData<Products> getProduct(String name) {
        return productsRepository.getProduct(name);
    }

//    public MutableLiveData<String> insert(Products maintenance) {
//        return productsRepository.insert(maintenance);
//    }
//
//    public void update(Products maintenance) {
//        productsRepository.update(maintenance);
//    }

    public void delete(Products product) {
        productsRepository.delete(product);
    }
}
