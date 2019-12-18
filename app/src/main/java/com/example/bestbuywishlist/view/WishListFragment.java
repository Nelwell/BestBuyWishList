package com.example.bestbuywishlist.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bestbuywishlist.R;
import com.example.bestbuywishlist.db.ProductRecord;
import com.example.bestbuywishlist.viewmodel.WishListViewModel;

import java.util.List;

public class WishListFragment extends Fragment {

    private static final String TAG = "WishListFragment";
    private static final String TAG_WISH_LIST_ADAPTER = "Wish List Adapter";

    private WishListAdapter wishListAdapter;
    private RecyclerView wishListRecyclerView;
    private WishListNavButtonListener wishListNavButtonListener;
    private WishListViewModel wishListViewModel;
//    private StartNewMaintenanceItemListener newMaintenanceItemListener;

    public interface WishListNavButtonListener {
        void viewWishList();
    }

    public WishListFragment() {
        // Required empty public constructor
    }

    // Set listeners
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        Log.d(TAG, "onAttach");

        if (context instanceof WishListFragment.WishListNavButtonListener) {
            wishListNavButtonListener = (WishListFragment.WishListNavButtonListener) context;
            Log.d(TAG, "WishListNavButtonListener set");
        } else {
            throw new RuntimeException(context.getClass().getName() + " should implement WishListNavButtonListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wish_list, container, false);

        wishListRecyclerView = view.findViewById(R.id.wish_list_rv);
        wishListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        wishListRecyclerView.setHasFixedSize(true);

        // Creates adapter and passes wishListViewModel to ProductAdapter class
        wishListAdapter = new WishListAdapter(wishListViewModel);
        // Sets adapter in recycler view
        wishListRecyclerView.setAdapter(wishListAdapter);



        // Delete items from wishListRecyclerView by swiping, adapted from Coding In Flow's YouTube channel tutorials
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView wishListRecyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                wishListViewModel.delete(wishListAdapter.getProductAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getContext(),"Wish List item deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(wishListRecyclerView);

//        searchEditText = view.findViewById(R.id.search_edit_text);
//        searchButton = view.findViewById(R.id.search_button);
//        searchButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                productViewModel.searchProducts(searchEditText.getText().toString().
//                        replaceAll("\\s+", "&search=")).observe(getActivity(), new Observer<List<Product>>() {
//
//                    @Override
//                    public void onChanged(List<Product> products) {
//                        if (searchEditText.getText().toString().length() > 0) {
//                            wishListRecyclerView.getLayoutManager().scrollToPosition(0);
//                        }
//                        adapter.setProduct(products);
//                        // For testing
////                Toast.makeText(getContext(), "onChanged", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                searchEditText.setText("");
//            }
//        });

        return view;
    }

    // Overrides onResume() to reload list with newest data
    @Override
    public void onResume() {
        super.onResume();
        // Creates wishListViewModel connection
        wishListViewModel = ViewModelProviders.of(this).get(WishListViewModel.class);
        // Gets all Auto Records from wishListViewModel
        wishListViewModel.getAllProductRecords().observe(this, new Observer<List<ProductRecord>>() {
            @Override
            // When a change in AutoRecords is detected, sets new data in adapter
            public void onChanged(List<ProductRecord> items) {
                Log.d(TAG_WISH_LIST_ADAPTER, "Wish list items are: " + items);
                // Set data in the adapter
                wishListAdapter.setWishListItem(items);
                if (wishListViewModel.getAllProductRecords().getValue().size() == 0) {


                }
//                    mAutoRecyclerView.smoothScrollToPosition(mAutoListViewModel.getAllAutoRecords().getValue().size() - 1);
            }
        });
    }
}
