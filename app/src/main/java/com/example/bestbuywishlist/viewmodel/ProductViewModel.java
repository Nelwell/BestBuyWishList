package com.example.bestbuywishlist.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.bestbuywishlist.db.ProductRecord;
import com.example.bestbuywishlist.db.ProductsRepositoryRoom;
import com.example.bestbuywishlist.model.Product;
import com.example.bestbuywishlist.repository.ProductsRepository;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {

    private ProductsRepository productsRepository;
    private MutableLiveData<List<Product>> allProducts;
    private ProductsRepositoryRoom productsRepositoryRoom;

    public ProductViewModel(@NonNull Application application) {
        super(application);
        productsRepository = new ProductsRepository();
        productsRepositoryRoom = new ProductsRepositoryRoom(application);
    }

    public MutableLiveData<List<Product>> getAllProducts() {
        return allProducts;
    }

    public MutableLiveData<List<Product>> searchProducts(String searchTerms) {
        return productsRepository.searchProducts(searchTerms);
    }

    public void insert(ProductRecord productRecord) {
        productsRepositoryRoom.insert(productRecord);
    }

//    public MutableLiveData<List<Product>> insert(List<Product> product) {
//        return productsRepository.insert(product);
//    }
//
//    public void update(Product maintenance) {
//        productsRepository.update(maintenance);
//    }

//    public void delete(Product product) {
//        productsRepository.delete(product);
//    }
}
