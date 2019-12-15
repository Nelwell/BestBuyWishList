//package com.example.bestbuywishlist.db;
//
//import android.content.Context;
//
//import androidx.room.Database;
//import androidx.room.Room;
//import androidx.room.RoomDatabase;
//import androidx.room.TypeConverters;
//
//import com.example.bestbuywishlist.model.Product;
//
///** Creates database on device */
//// Implemented as a thread-safe Singleton
//@Database(entities = {Product.class}, version = 1, exportSchema = false)
//@TypeConverters({Converters.class})
//public abstract class ProductsDatabase extends RoomDatabase {
//
//    private static volatile ProductsDatabase INSTANCE;
//
//    public abstract ProductsDAO productsDAO(); // Abstract method
//
//    public static ProductsDatabase getDatabase(final Context context) {
//        if (INSTANCE == null) {
//            synchronized (ProductsDatabase.class) { // Only one thread can run this code at once
//                if (INSTANCE == null) {
//                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
//                            ProductsDatabase.class, "Products_database")
//                            // Return instance of database
//                            .build();
//                }
//            }
//        }
//        // If not null...
//        return INSTANCE;
//    }
//}
