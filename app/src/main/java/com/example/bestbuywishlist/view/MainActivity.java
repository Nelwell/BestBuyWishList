package com.example.bestbuywishlist.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.bestbuywishlist.R;
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

        viewProductBrowser();
    }

    @Override
    public void viewProductBrowser() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ProductBrowserFragment productBrowserFragment = ProductBrowserFragment.newInstance();
        ft.replace(android.R.id.content, productBrowserFragment, TAG_VIEW_PRODUCT_BROWSER);
        ft.addToBackStack(null);
        ft.commit();
    }
}
