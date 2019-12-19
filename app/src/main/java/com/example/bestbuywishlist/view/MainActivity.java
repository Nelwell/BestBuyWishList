package com.example.bestbuywishlist.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.bestbuywishlist.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/*
Main Activity handles active fragments
 */
public class MainActivity extends AppCompatActivity implements
        ProductBrowserFragment.UponAppLaunchListener,
        WishListFragment.WishListNavButtonListener {

//    private static final String TAG_VIEW_PRODUCT_BROWSER = "ProductBrowserFragment";
//    private static final String TAG_VIEW_WISH_LIST = "WishListFragment";
    private ProductBrowserFragment productBrowserFragment;
    private WishListFragment wishListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        // Keeps the selected fragment during rotation
        if (savedInstanceState == null) {
            viewProductBrowser();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    // Switch case statement to call fragment associated with bottom nav button when pressed
                    switch (item.getItemId()) {
                        case R.id.nav_product_browser:
                            viewProductBrowser();
                            break;
                        case R.id.nav_wishlist:
                            viewWishList();
                            break;
                    }

                    return true; // Changes appearance of selected Bottom Nav button
                }
            };

    @Override
    public void viewProductBrowser() {
        if (productBrowserFragment == null) {
            productBrowserFragment = new ProductBrowserFragment();
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, productBrowserFragment)
                .commit();
    }

    @Override
    public void viewWishList() {
        if (wishListFragment == null) {
            wishListFragment = new WishListFragment();
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, wishListFragment)
                .commit();
    }
}
