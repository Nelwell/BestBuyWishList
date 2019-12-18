package com.example.bestbuywishlist.db;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

/** Where app will access needed data */
// Define methods app will call to query database, provide clean API for rest of app.
// Can receive data from multiple resources
public class ProductsRepositoryRoom {

    private ProductDAO productDAO;
//    private LiveData<List<AutoRecord>> mAllAutoRecords;

    public ProductsRepositoryRoom(Application application) {
        ProductDatabase db = ProductDatabase.getDatabase(application);
        productDAO = db.productDAO();
    }

    // LiveData can wrap one item or a list of items inside it.
    // Let's you know if something in db changes
    public LiveData<List<ProductRecord>> getAllProductRecords() {
        return productDAO.getAllProductRecords();
    }

    public LiveData<ProductRecord> getProductBySku(String sku) {
        return productDAO.getProductBySku(sku);
    }

    public void insert(ProductRecord productRecord) {
        // Insert record asynchronously in the background so other processes can continue seamlessly
        new InsertProductAsyncTask(productDAO).execute(productRecord); // when called, calls doInBackground
        // method automatically passes 'productRecord' arg
    }

    public void delete(ProductRecord productRecord) {
        // Update record asynchronously in the background
        new DeleteProductAsyncTask(productDAO).execute(productRecord); // when called, calls doInBackground
        // method automatically passes 'productRecord' arg
    }

    public void deleteAllProductRecords() {
        new DeleteAllProductsAsyncTask(productDAO).execute();
    }


    // Database tasks must run the background, not on the UI thread
    private static class InsertProductAsyncTask extends AsyncTask<ProductRecord, Void, Void> {

        private ProductDAO productDAO;

        // Constructor
        private InsertProductAsyncTask(ProductDAO productDAO) {
            this.productDAO = productDAO;
        }

        @Override
        protected Void doInBackground(ProductRecord... products) {
            productDAO.insert(products[0]);
            return null;
        }
    }

    // Database tasks must run in the background, not on the UI thread
    private static class DeleteProductAsyncTask extends AsyncTask<ProductRecord, Void, Void> {

        private ProductDAO dao;

        // Constructor
        private DeleteProductAsyncTask(ProductDAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(ProductRecord... products) {
            dao.delete(products[0]);
            return null;
        }
    }

    // Database tasks must run in the background, not on the UI thread
    private static class DeleteAllProductsAsyncTask extends AsyncTask<Void, Void, Void> {

        private ProductDAO dao;

        // Constructor
        private DeleteAllProductsAsyncTask(ProductDAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAllProducts();
            return null;
        }
    }

//    private static class DeleteAutoIDAsyncTask extends AsyncTask<Integer, Void, Void> {
//
//        AutoDAO autoDAO;
//
//        public DeleteAutoIDAsyncTask(AutoDAO autoDAO) {
//            this.autoDAO = autoDAO;
//        }
//
//        @Override
//        protected Void doInBackground(Integer... id) {
//            autoDAO.delete(id[0]);
//            return null;
//        }
//    }
}
