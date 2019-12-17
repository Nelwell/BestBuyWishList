package com.example.bestbuywishlist.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bestbuywishlist.R;
import com.example.bestbuywishlist.db.ProductRecord;
import com.example.bestbuywishlist.model.Product;
import com.example.bestbuywishlist.viewmodel.ProductViewModel;
import com.example.bestbuywishlist.viewmodel.WishListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductBrowserFragment extends Fragment {

    private static final String TAG = "ProductBrowserFragment";

    FloatingActionButton addToWishList;
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

    public static ProductBrowserFragment newInstance() {
                return new ProductBrowserFragment();
    }

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
//        recyclerView.getLayoutManager().scrollToPosition(0);
        recyclerView.setHasFixedSize(true);

        // Creates adapter
        adapter = new ProductAdapter();
        // Sets adapter in recycler view
        recyclerView.setAdapter(adapter);


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

    // Class to create Adapter and perform adapter functions
    private class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {
        // Where adapter stores its data
        private List<Product> product = new ArrayList<>();

        @NonNull
        @Override
        public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // Get a reference to the product_item LinearLayout container and inflate in, in this context
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.product_item, parent, false);
            return new ProductHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
            Product currentProduct = product.get(position);

            holder.productNameTextView.setText(currentProduct.getName());
            if (currentProduct.getImage() != null) {
                Picasso.get().load(currentProduct.getImage()).into(holder.productImageView);
            }
            holder.productCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        @Override
        public int getItemCount() {
            return product.size();
        }

        public void setProduct(List<Product> product) {
            this.product = product;
            // Tell recyclerView to reload
            notifyDataSetChanged();
        }

        class ProductHolder extends RecyclerView.ViewHolder {
            private TextView productNameTextView;
            private ImageView productImageView;
            private CardView productCardView;

            public ProductHolder(@NonNull View itemView) {
                super(itemView);
                productNameTextView = itemView.findViewById(R.id.product_name);
                productImageView = itemView.findViewById(R.id.product_thumbnail);
                productCardView = itemView.findViewById(R.id.card_view);

            }
        }
    }
}
