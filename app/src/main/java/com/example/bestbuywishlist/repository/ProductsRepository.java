package com.example.bestbuywishlist.repository;

import android.util.Log;
import android.widget.ImageView;

import androidx.lifecycle.MutableLiveData;

import com.example.bestbuywishlist.R;
import com.example.bestbuywishlist.model.Product;
import com.example.bestbuywishlist.model.ProductsResponse;
import com.example.bestbuywishlist.service.ProductsService;
import com.squareup.picasso.Picasso;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductsRepository {

    private ImageView productImageView;

    private static final String TAG = "PRODUCTS_REPOSITORY";

    private ProductsService productsService;
    private final String baseURL = "https://api.bestbuy.com/v1/";
    private MutableLiveData<List<Product>> allProducts;

    public ProductsRepository() {

        // Create client with interceptor to set header on each request
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(new AuthorizationHeaderInterceptor())
                .addInterceptor(logging)
                .build();

        // Create and configure Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        // Create implementation of ProductsService interface
        productsService = retrofit.create(ProductsService.class);

        // Initialize allProducts
        allProducts = new MutableLiveData<>();
    }

    public MutableLiveData<List<Product>> searchProducts(final String searchTerms) {

        productsService.searchProducts("search="+searchTerms).enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "searchProducts response body: " + response.body());
                    allProducts.setValue(response.body().getProducts());
                } else {
                    Log.e(TAG, "Error getting all records, message from server: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {
                Log.e(TAG, "Error fetching all records", t);
            }
        });

        return allProducts;
    }


//    public MutableLiveData<Product> getProduct(final String name) {
//
//        /* Fetch one Product by its name. The value is available by observing the
//        MutableLiveData object returned from this method.*/
//
//        final MutableLiveData<Product> product = new MutableLiveData<>();
//
//        productsService.get(name).enqueue(new Callback<Product>() {
//            @Override
//            public void onResponse(Call<Product> call, Response<Product> response) {
//                if (response.isSuccessful()) {
//                    Log.d(TAG, "fetched record " + response.body());
//                    product.setValue(response.body());
//                } else {
//                    Log.e(TAG, "Error getting record id " + name + " because " + response.message());
//                    product.setValue(null);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Product> call, Throwable t) {
//                Log.e(TAG, "Error getting record id " + name, t);
//            }
//        });
//
//        return product;
//    }

//    public MutableLiveData<List<Product>> insert(final List<Product> products) {
//
//        final MutableLiveData<List<Product>> insertResult = new MutableLiveData<>();
//
//        productsService.insert(products).enqueue(new Callback<Void>() {
//            @Override
//            public void onResponse(Call<Void> call, Response<Void> response) {
//                if (response.isSuccessful()) {
//                    Log.d(TAG, "inserted " + products);
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
//                Log.e(TAG, "Error inserting record for " + products, t);
//            }
//        });
//
//        return insertResult;
//
//    }
//
//
//    public void update(final Product vehicle) {
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
//
//
//    public void delete(final Product product) {
//
//        productsService.delete(product.getName()).enqueue(new Callback<Void>() {
//            @Override
//            public void onResponse(Call<Void> call, Response<Void> response) {
//                if (response.isSuccessful()) {
//                    Log.d(TAG, "deleted record for " + product);
//                    getAllProducts();
//                } else {
//                    Log.e(TAG, "Error deleting record, message from server: " + response.message());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Void> call, Throwable t) {
//                Log.e(TAG, "Error deleting record for " + product, t);
//            }
//        });
//    }
}
