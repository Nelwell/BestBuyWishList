package com.example.bestbuywishlist.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bestbuywishlist.R;
import com.example.bestbuywishlist.db.ProductRecord;
import com.example.bestbuywishlist.viewmodel.WishListViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

// Class to create Adapter and perform adapter functions
public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.WishListHolder> {

    // Where adapter stores its data
    private List<ProductRecord> wishListItem = new ArrayList<>();

    public WishListAdapter(WishListViewModel wishListViewModel) {
    }

    @NonNull
    @Override
    public WishListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Get a reference to the product_item LinearLayout container and inflate in, in this context
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.wish_list_item, parent, false);
        return new WishListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WishListHolder holder, final int position) {
        final ProductRecord currentItem = wishListItem.get(position);

        // Bind values to item views
        holder.itemPriceTextView.setText("$"+currentItem.getSalePrice());
        holder.itemNameTextView.setText(currentItem.getName());
        if (currentItem.getImage() != null) {
            Picasso.get().load(currentItem.getImage()).into(holder.itemImageView);
        }
    }

    @Override
    public int getItemCount() {
        return wishListItem.size();
    }

    public void setWishListItem(List<ProductRecord> item) {
        this.wishListItem = item;
        // Tell recyclerView to reload
        notifyDataSetChanged();
    }

    class WishListHolder extends RecyclerView.ViewHolder {
        private TextView itemPriceTextView;
        private TextView itemNameTextView;
        private ImageView itemImageView;

        private WishListHolder(@NonNull View itemView) {
            super(itemView);
            itemPriceTextView = itemView.findViewById(R.id.item_price);
            itemNameTextView = itemView.findViewById(R.id.item_name);
            itemImageView = itemView.findViewById(R.id.item_image);

        }
    }

    public ProductRecord getProductAt(int position) {
        return wishListItem.get(position);
    }
}
