package com.example.bottomnav;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.bottomnav.R;
import com.example.bottomnav.Product;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private final Context context;
    private List<Product> productList;

    // Constructor dengan Context dan List<Product>
    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    // Method untuk update data
    public void setProducts(List<Product> products) {
        this.productList = products;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);

        // Set data ke view
        holder.tvMerk.setText(product.getMerk());
        holder.tvHarga.setText(String.format("Rp %,d", (int) product.getHarga()));
        holder.tvStok.setText("Stok: " + product.getStok());

        // Load gambar dengan Glide
        Glide.with(context)
                .load(product.getFoto())  // Pastikan Product memiliki getFoto()
                .placeholder(R.drawable.placeholder) // Gambar placeholder
                .into(holder.imgProduct);
    }

    @Override
    public int getItemCount() {
        return productList != null ? productList.size() : 0;
    }

    // ViewHolder class
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView tvMerk, tvHarga, tvStok;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.img_product);
            tvMerk = itemView.findViewById(R.id.tv_merk);
            tvHarga = itemView.findViewById(R.id.tv_harga);
            tvStok = itemView.findViewById(R.id.tv_stok);
        }
    }
}