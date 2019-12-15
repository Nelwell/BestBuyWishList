package com.example.bestbuywishlist.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.bestbuywishlist.model.Product;
import com.example.bestbuywishlist.repository.ProductsRepository;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {

    private ProductsRepository productsRepository;
    private MutableLiveData<List<Product>> allProducts;

    public ProductViewModel(@NonNull Application application) {
        super(application);
        productsRepository = new ProductsRepository();
//        allProducts = productsRepository.searchProducts();
    }

    public MutableLiveData<List<Product>> getAllProducts() {
        return allProducts;
    }

    public MutableLiveData<List<Product>> searchProducts(String searchTerms) {
        return productsRepository.searchProducts(searchTerms);
    }

//    public MutableLiveData<String> insert(Product maintenance) {
//        return productsRepository.insert(maintenance);
//    }
//
//    public void update(Product maintenance) {
//        productsRepository.update(maintenance);
//    }

//    public void delete(Product product) {
//        productsRepository.delete(product);
//    }
}
