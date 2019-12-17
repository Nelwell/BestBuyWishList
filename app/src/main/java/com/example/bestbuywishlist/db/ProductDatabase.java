package com.example.bestbuywishlist.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

/** Creates database on device */
// Implemented as a thread-safe Singleton
@Database(entities = {ProductRecord.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class ProductDatabase extends RoomDatabase {

    private static volatile ProductDatabase INSTANCE;

    public abstract ProductDAO productDAO(); // Abstract method

    public static ProductDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ProductDatabase.class) { // Only one thread can run this code at once
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ProductDatabase.class, "Product_database")
                            // Return instance of database
                            .build();
                }
            }
        }
        // If not null...
        return INSTANCE;
    }
}
