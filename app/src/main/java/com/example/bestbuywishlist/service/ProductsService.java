package com.example.bestbuywishlist.service;

import com.example.bestbuywishlist.BuildConfig;
import com.example.bestbuywishlist.model.ProductsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductsService {

    // Get all products from BestBuy database
    @GET("products({searchFormat})?format=json&show=image,sku,name,salePrice&apiKey=" + BuildConfig.PRODUCTS_API_KEY)
    Call<ProductsResponse> searchProducts(@Path("searchFormat") String searchFormat);

//    // Get single product by name
//    @GET("Product/{id}/")
//    Call<Product> get(@Path("{searchFormat}") String name);
//

//    // Insert maintenance record
//    @POST("Product/")
//    Call<Void> insert(@Body Product product);
//
//
//    // Update maintenance record
//    @PATCH("Product/{id}/")
//    Call<Void> update(@Body Product product, @Path("id") int id);
//
//
//    // Delete single product
//    @DELETE("Product/{id}/")
//    Call<Void> delete(@Path("id") String name);

}
