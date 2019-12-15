//package com.example.bestbuywishlist.db;
//
//import androidx.lifecycle.LiveData;
//import androidx.room.Dao;
//import androidx.room.Delete;
//import androidx.room.Insert;
//import androidx.room.OnConflictStrategy;
//import androidx.room.Query;
//
//import com.example.bestbuywishlist.model.Product;
//
//import java.util.List;
//
//@Dao
//public interface ProductsDAO {
//
//    @Query("SELECT * FROM Product ORDER BY name")
//    LiveData<List<Product>> getAllProducts();
//
//    @Insert(onConflict = OnConflictStrategy.IGNORE) // Ignore new record for an existing place
//    void insert(Product... p);
//
//    @Delete
//    void delete(Product... p);
//
//    @Query("DELETE FROM Products")
//    void deleteAllProducts();
//
//    @Query("SELECT * FROM Products WHERE name = :name LIMIT 1")
//    LiveData<Product> getProductByName(String name);

//    @Query("SELECT * FROM AutoRecord WHERE year like '%' ) // TODO finish query
//    LiveData<AutoRecord> getRecordForAuto(String autoId);

//    @Query("DELETE FROM AutoRecord WHERE id = :id")
//    void delete(int id);

//    @Query("SELECT * from NOTE where text like '%' || :search || '%' or hashTags like '%' || :search || '%' ")
//    LiveData<List<AutoRecord>> searchNotes(String search);
//}

