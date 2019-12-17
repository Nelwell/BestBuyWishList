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
//        mAllAutoRecords = productDAO.getAllAutoRecords();
    }

    // LiveData can wrap one item or a list of items inside it.
    // Let's you know if something in db changes
    public LiveData<List<ProductRecord>> getAllProductRecords() {
        return productDAO.getAllProductRecords();
    }

    public LiveData<ProductRecord> getProductBySku(String sku) {
        return productDAO.getProductBySku(sku);
    }

    public void insert(ProductRecord autoRecord) {
        // Insert record asynchronously in the background so other processes can continue seamlessly
        new InsertAutoAsyncTask(productDAO).execute(autoRecord); // when called, calls doInBackground
        // method automatically and passes 'autoRecord' arg
    }

    public void delete(ProductRecord autoRecord) {
        // Update record asynchronously in the background
        new DeleteAutoAsyncTask(productDAO).execute(autoRecord); // when called, calls doInBackground
        // method automatically and passes 'autoRecord' arg
    }

    public void deleteAllAutoRecords() {
        new DeleteAllAutosAsyncTask(productDAO).execute();
    }

//    public void delete(int id) {
//        new DeleteAutoIDAsyncTask(productDAO).execute(id); }


    // Database tasks must run the background, not on the UI thread
    private static class InsertAutoAsyncTask extends AsyncTask<ProductRecord, Void, Void> {

        private ProductDAO autoDAO;

        // Constructor
        private InsertAutoAsyncTask(ProductDAO autoDAO) {
            this.autoDAO = autoDAO;
        }

        @Override
        protected Void doInBackground(ProductRecord... autos) {
            autoDAO.insert(autos[0]);
            return null;
        }
    }

    // Database tasks must run in the background, not on the UI thread
    private static class DeleteAutoAsyncTask extends AsyncTask<ProductRecord, Void, Void> {

        private ProductDAO dao;

        // Constructor
        private DeleteAutoAsyncTask(ProductDAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(ProductRecord... autos) {
            dao.delete(autos[0]);
            return null;
        }
    }

    // Database tasks must run in the background, not on the UI thread
    private static class DeleteAllAutosAsyncTask extends AsyncTask<Void, Void, Void> {

        private ProductDAO dao;

        // Constructor
        private DeleteAllAutosAsyncTask(ProductDAO dao) {
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
