package com.example.bestbuywishlist.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bestbuywishlist.R;
import com.example.bestbuywishlist.db.ProductRecord;
import com.example.bestbuywishlist.model.Product;
import com.example.bestbuywishlist.viewmodel.WishListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

// Class to create Adapter and perform adapter functions
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {

    private WishListViewModel wishListViewModel;

    // Where adapter stores its data
    private List<Product> product = new ArrayList<>();

    public ProductAdapter(WishListViewModel wishListViewModel) {
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Get a reference to the product_item LinearLayout container and inflate in, in this context
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_item, parent, false);
        return new ProductHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, final int position) {
        final Product currentProduct = product.get(position);

        holder.productNameTextView.setText(currentProduct.getName());
        if (currentProduct.getImage() != null) {
            Picasso.get().load(currentProduct.getImage()).into(holder.productImageView);
        }
        holder.productCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.addToWishList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductRecord wishListedProduct = new ProductRecord(
                        wishListViewModel.deleteAllAutoRecords();
                        currentProduct.getSku(), currentProduct.getPrice(), currentProduct.getName());
                wishListViewModel.insert(wishListedProduct);
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
        private FloatingActionButton addToWishList;

        private ProductHolder(@NonNull View itemView) {
            super(itemView);
            productNameTextView = itemView.findViewById(R.id.product_name);
            productImageView = itemView.findViewById(R.id.product_thumbnail);
            productCardView = itemView.findViewById(R.id.card_view);
            addToWishList = itemView.findViewById(R.id.add_to_wish_list_fab);

        }
    }
}