package com.example.bestbuywishlist.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.bestbuywishlist.model.Products;
import com.example.bestbuywishlist.service.AuthorizationHeaderInterceptor;
import com.example.bestbuywishlist.service.ProductsService;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductsRepository {

    private static final String TAG = "PRODUCTS_REPOSITORY";

    private ProductsService productsService;
    private final String baseURL = "https://api.bestbuy.com/v1/products";
    private MutableLiveData<List<Products>> allProducts;

    public ProductsRepository() {

        // Create client with interceptor to set header on each request
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new AuthorizationHeaderInterceptor())
                .addInterceptor(logging)
                .build();

        // Create and configure Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        // Create implementation of VehicleService interface
        productsService = retrofit.create(ProductsService.class);

        // Initialize allProducts
        allProducts = new MutableLiveData<>();
    }

    public MutableLiveData<List<Products>> getAllProducts() {

        productsService.getAllProducts().enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "getAllMaintenanceRecords response body: " + response.body());
                    allProducts.setValue(response.body());
                } else {
                    Log.e(TAG, "Error getting all records, message from server: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {
                Log.e(TAG, "Error fetching all records", t);
            }
        });

        return allProducts;
    }


    public MutableLiveData<Products> getProduct(final String name) {

        /* Fetch one Product by its name. The value is available by observing the
        MutableLiveData object returned from this method.*/

        final MutableLiveData<Products> product = new MutableLiveData<>();

        productsService.get(name).enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "fetched record " + response.body());
                    product.setValue(response.body());
                } else {
                    Log.e(TAG, "Error getting record id " + name + " because " + response.message());
                    product.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {
                Log.e(TAG, "Error getting record id " + name, t);
            }
        });

        return product;
    }

//    public MutableLiveData<String> insert(final Products vehicle) {
//
//        final MutableLiveData<String> insertResult = new MutableLiveData<>();
//
//        productsService.insert(vehicle).enqueue(new Callback<Void>() {
//            @Override
//            public void onResponse(Call<Void> call, Response<Void> response) {
//                if (response.isSuccessful()) {
//                    Log.d(TAG, "inserted " + vehicle);
//                    insertResult.setValue("success");
//                    getAllProducts();
//                } else {
//                    String error;
//                    try {
//                        error = response.errorBody().toString();
//                        insertResult.setValue(error);
//                        Log.e(TAG, "Error inserting record, response from server: " + error + " message " + response.message());
//
//                    } catch (Exception e) {
//                        insertResult.setValue("error");
//                        Log.e(TAG, "Error inserting record, message from server: " + response.message());
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Void> call, Throwable t) {
//                insertResult.setValue("error");
//                Log.e(TAG, "Error inserting record for " + vehicle, t);
//            }
//        });
//
//        return insertResult;
//
//    }
//
//
//    public void update(final Products vehicle) {
//
//        productsService.update(vehicle, vehicle.getId()).enqueue(new Callback<Void>() {
//            @Override
//            public void onResponse(Call<Void> call, Response<Void> response) {
//                if (response.isSuccessful()) {
//                    Log.d(TAG, "updated record for " + vehicle);
//                    getAllProducts();
//                } else {
//                    Log.e(TAG, "Error updating record, message from server: " + response.message());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Void> call, Throwable t) {
//                Log.e(TAG, "Error updating record for " + vehicle, t);
//            }
//        });
//
//    }


    public void delete(final Products product) {

        productsService.delete(product.getName()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "deleted record for " + product);
                    getAllProducts();
                } else {
                    Log.e(TAG, "Error deleting record, message from server: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(TAG, "Error deleting record for " + product, t);
            }
        });
    }
}
