package com.example.bestbuywishlist.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bestbuywishlist.R;
import com.example.bestbuywishlist.db.ProductRecord;
import com.example.bestbuywishlist.model.Product;
import com.example.bestbuywishlist.viewmodel.ProductViewModel;
import com.example.bestbuywishlist.viewmodel.WishListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;
import java.util.List;

public class ProductBrowserFragment extends Fragment {

    private static final String TAG = "ProductBrowserFragment";

    private ProductAdapter adapter;
    private RecyclerView recyclerView;
    private ProductViewModel productViewModel;
    private UponAppLaunchListener uponAppLaunchListener;
    private EditText searchEditText;
    private Button searchButton;
    private WishListViewModel wishListViewModel;
//    private StartNewMaintenanceItemListener newMaintenanceItemListener;

    public interface UponAppLaunchListener {
        void viewProductBrowser();
    }

//    interface StartNewMaintenanceItemListener {
//        void startNewMaintenanceItem();
//    }

    public ProductBrowserFragment() {
        // Required empty public constructor
    }

//    public static ProductBrowserFragment newInstance() {
//                return new ProductBrowserFragment();
//    }

    // Set listeners
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

//        if (context instanceof StartNewMaintenanceItemListener) {
//            newMaintenanceItemListener = (StartNewMaintenanceItemListener) context;
//            Log.d(TAG, "New Maintenance Item Listener set");
//        } else {
//            throw new RuntimeException(context.getClass().getName() + " should implement StartNewMaintenanceItemListener");
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_browser, container, false);

        recyclerView = view.findViewById(R.id.browser_list_rv);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setHasFixedSize(true);

        // Creates adapter
        adapter = new ProductAdapter();
        // Sets adapter in recycler view
        recyclerView.setAdapter(adapter);

//        // Delete items from recyclerView by swiping, adapted from Coding In Flow's YouTube channel tutorials
//        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
//            @Override
//            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
////                productViewModel.delete(mAutoAdapter.getAutoAt(viewHolder.getAdapterPosition()));
////                Toast.makeText( this, "Vehicle deleted", Toast.LENGTH_SHORT).show();
//            }
//        }).attachToRecyclerView(mAutoRecyclerView);

//        addToWishList = view.findViewById(R.id.add_to_wish_list_fab);
//        addToWishList.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                // Bundles all fields into new ProductRecord item
//                final ProductRecord newWishListItem = new ProductRecord(sku, salePrice, name);
//                wishListViewModel.insert(adapter.getItemId());
////                 Insert newWishListItem using wishListViewModel
//                wishListViewModel.insert(newWishListItem).observe(getActivity(), new Observer<String>() {
//                    @Override
//                    public void onChanged(String wl) {
//                        Log.d(TAG, "s" + wl);
//                        if (wl.equals("success")) {
//                            // Notifies Main Activity so fragments can be swapped
////                            newMaintenanceListener.onNewMaintenanceAdded(newMaintenanceItem);
//                            Toast.makeText(getActivity(), "Maintenance Item added!", Toast.LENGTH_SHORT).show();
//                        } else if (wl.contains("duplicate key")) {
//                            Toast.makeText(getActivity(), "You already added that Maintenance Item!", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(getActivity(), "Error adding movie", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//                productViewModel.delete(mAutoAdapter.getAutoAt(viewHolder.getAdapterPosition()));
//            }
//        });

        searchEditText = view.findViewById(R.id.search_edit_text);
        searchButton = view.findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productViewModel = ViewModelProviders.of(getActivity()).get(ProductViewModel.class);
                productViewModel.searchProducts(searchEditText.getText().toString().
                        replaceAll("\\s+", "&search=")).observe(getActivity(), new Observer<List<Product>>() {

                    @Override
                    public void onChanged(List<Product> products) {
                        if (searchEditText.getText().toString().length() > 0) {
                            recyclerView.getLayoutManager().scrollToPosition(0);
                        }
                        adapter.setProduct(products);
                        Product product = products.get(0);
                        ProductRecord productRecord = new ProductRecord(product.getSku(), product.getPrice(), product.getName());
//                        wishListViewModel.insert(productRecord);
                        // For testing
//                Toast.makeText(getContext(), "onChanged", Toast.LENGTH_SHORT).show();
                    }
                });
                searchEditText.setText("");
            }
        });

        return view;
    }

    // Overrides onResume() to reload list with newest data
    @Override
    public void onResume() {
        super.onResume();
        wishListViewModel = ViewModelProviders.of(this).get(WishListViewModel.class);
//        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
//        productViewModel.searchProducts(searchEditText.getText().toString().
//                replaceAll("\\s+", "&search=")).observe(this, new Observer<List<Product>>() {
//
//            @Override
//            public void onChanged(List<Product> products) {
//                if (searchEditText.getText().toString().length() > 0) {
//                    recyclerView.getLayoutManager().scrollToPosition(0);
//                }
//                adapter.setProduct(products);
//                Product product = products.get(0);
//                ProductRecord productRecord = new ProductRecord(product.getPrice(), product.getName());
//                // For testing
////                Toast.makeText(getContext(), "onChanged", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
