package com.example.bestbuywishlist.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.bestbuywishlist.model.Products;

import java.util.List;

@Dao
public interface ProductsDAO {

    @Query("SELECT * FROM Products ORDER BY dateEntered DESC")
    LiveData<List<Products>> getAllProducts();

    @Insert(onConflict = OnConflictStrategy.IGNORE) // Ignore new record for an existing place
    void insert(Products... p);

    @Delete
    void delete(Products... p);

    @Query("DELETE FROM Products")
    void deleteAllAutoRecords();

    @Query("SELECT * FROM Products WHERE autoId = :autoId LIMIT 1")
    LiveData<Products> getRecordForAutoId(int autoId);

//    @Query("SELECT * FROM AutoRecord WHERE year like '%' ) // TODO finish query
//    LiveData<AutoRecord> getRecordForAuto(String autoId);

//    @Query("DELETE FROM AutoRecord WHERE id = :id")
//    void delete(int id);

//    @Query("SELECT * from NOTE where text like '%' || :search || '%' or hashTags like '%' || :search || '%' ")
//    LiveData<List<AutoRecord>> searchNotes(String search);

}
