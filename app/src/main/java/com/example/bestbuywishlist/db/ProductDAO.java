package com.example.bestbuywishlist.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductDAO {

    @Query("SELECT * FROM ProductRecord ORDER BY id DESC")
    LiveData<List<ProductRecord>> getAllProductRecords();

    @Insert(onConflict = OnConflictStrategy.IGNORE) // Ignore new record for an existing product
    void insert(ProductRecord... pr);

    @Delete
    void delete(ProductRecord... pr);

    @Query("DELETE FROM ProductRecord")
    void deleteAllProducts();

    @Query("SELECT * FROM ProductRecord WHERE id = :id LIMIT 1")
    LiveData<ProductRecord> getProductById(int id);

//    @Query("SELECT * FROM ProductRecord WHERE year like '%' )
//    LiveData<AutoRecord> getRecordForAuto(String autoId);
//
//    @Query("DELETE FROM ProductRecord WHERE id = :id")
//    void delete(int id);
//
//    @Query("SELECT * from NOTE where text like '%' || :search || '%' or hashTags like '%' || :search || '%' ")
//    LiveData<List<ProductRecord>> searchNotes(String search);
}

