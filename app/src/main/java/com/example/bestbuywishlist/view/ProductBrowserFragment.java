package com.example.bestbuywishlist.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bestbuywishlist.R;
import com.example.bestbuywishlist.model.Product;
import com.example.bestbuywishlist.viewmodel.ProductViewModel;

import java.util.List;

public class ProductBrowserFragment extends Fragment {

    private static final String TAG = "ProductBrowserFragment";

    private ProductAdapter productAdapter;
    private RecyclerView browserRecyclerView;
    private ProductViewModel productViewModel;
    private UponAppLaunchListener uponAppLaunchListener;
    private EditText searchEditText;
//    private TextView noResultsTextView;
    private Button searchButton;

    public interface UponAppLaunchListener {
        void viewProductBrowser();
    }

    public ProductBrowserFragment() {
        // Required empty public constructor
    }

    // Set UponAppLaunchListener
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        Log.d(TAG, "onAttach");

        if (context instanceof UponAppLaunchListener) {
            uponAppLaunchListener = (UponAppLaunchListener) context;
            Log.d(TAG, "UponAppLaunchListener set");
        } else {
            throw new RuntimeException(context.getClass().getName() + " should implement UponAppLaunchListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_browser, container, false);

//        noResultsTextView = view.findViewById(R.id.empty_search_message); // couldn't get working in time.. null object reference
        // Get resource ID to recyclerView
        browserRecyclerView = view.findViewById(R.id.browser_list_rv);
        // Creates Grid Layout in recyclerView, 2 columns wide
        browserRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        browserRecyclerView.setHasFixedSize(true);

        // Establishes connection to view model
        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);

        // Creates productAdapter and passes wishListViewModel to ProductAdapter class
        productAdapter = new ProductAdapter(productViewModel);
        // Sets productAdapter in recycler view
        browserRecyclerView.setAdapter(productAdapter);

        // Get resource ID/strings and set click listener
        searchEditText = view.findViewById(R.id.search_edit_text);
        searchButton = view.findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // couldn't get working in time.. null object reference
                // Displays placeholder message in place of empty recyclerView
//                if (productViewModel.getAllProducts() == null) {
//                    noResultsTextView.setVisibility(View.VISIBLE);
//                }
//                if (productViewModel.getAllProducts().getValue().size() > 0) {
//                    noResultsTextView.setVisibility(View.INVISIBLE);
//                }
                // Gets all matching products from view model
                productViewModel.searchProducts(searchEditText.getText().toString().
                        // Manipulates editText string to match formatting for Best Buy API when using multiple keywords
                        replaceAll("\\s+", "&search=")).observe(getActivity(), new Observer<List<Product>>() {

                    @Override
                    public void onChanged(List<Product> products) {
                        // Returns view to top of list for each new search
                        if (searchEditText.getText().toString().length() > 0) {
                            browserRecyclerView.smoothScrollToPosition(0);
                        }
                        productAdapter.setProduct(products);
                    }
                });
                searchEditText.setText(""); // Reset search bar to empty after performing a search
            }
        });

        return view;
    }

    // Overrides onResume() to check if there are any results on screen or not
    @Override
    public void onResume() {
        super.onResume();
    }
}
