package com.example.bestbuywishlist.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * Creates database on device
 */
// Implemented as a thread-safe Singleton
@Database(entities = {ProductRecord.class}, version = 2, exportSchema = false)
public abstract class ProductDatabase extends RoomDatabase {

    private static volatile ProductDatabase INSTANCE;

    public abstract ProductDAO productDAO(); // Abstract method

    public static ProductDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ProductDatabase.class) { // Only one thread can run this code at once
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ProductDatabase.class, "Product_database").
//                            fallbackToDestructiveMigration().
                            // Return instance of database
                            build();
                }
            }
        }
        // If not null...
        return INSTANCE;
    }
}
