package com.example.bestbuywishlist.service;

import com.example.bestbuywishlist.model.Products;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProductsService {

    // Get all products from BestBuy database
    @GET("Products/")
    Call<List<Products>> getAllProducts();


    // Get single product by name
    @GET("Products/{id}/")
    Call<Products> get(@Path("name") String name);


//    // Insert maintenance record
//    @POST("Products/")
//    Call<Void> insert(@Body Products product);
//
//
//    // Update maintenance record
//    @PATCH("Products/{id}/")
//    Call<Void> update(@Body Products product, @Path("id") int id);
//
//
//    // Delete single product
//    @DELETE("Products/{id}/")
//    Call<Void> delete(@Path("id") String name);

}
