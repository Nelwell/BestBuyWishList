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
        ProductBrowserFragment.UponAppLaunchListener {

    private static final String TAG_VIEW_PRODUCT_BROWSER = "ProductBrowserFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            viewProductBrowser();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_product_browser:
                            selectedFragment = new ProductBrowserFragment();
                            break;
                        case R.id.nav_wishlist:
                            selectedFragment = new WishListFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(android.R.id.content,
                            selectedFragment).commit();

                    return true;
                }
            };

    @Override
    public void viewProductBrowser() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ProductBrowserFragment productBrowserFragment = ProductBrowserFragment.newInstance();
        ft.replace(android.R.id.content, productBrowserFragment, TAG_VIEW_PRODUCT_BROWSER);
        ft.addToBackStack(null);
        ft.commit();
    }
}
