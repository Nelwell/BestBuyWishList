package com.example.bestbuywishlist.service;

import com.example.bestbuywishlist.BuildConfig;
import com.example.bestbuywishlist.model.ProductsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductsService {

    // Get all products from BestBuy database matching searched terms, display up to 100 results
    @GET("products({searchFormat})?pageSize=100&format=json&show=image,sku,name,salePrice&apiKey=" + BuildConfig.PRODUCTS_API_KEY)
    Call<ProductsResponse> searchProducts(@Path("searchFormat") String searchFormat);

}
