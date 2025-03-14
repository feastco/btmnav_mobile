package com.example.bottomnav.ui.product;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.bottomnav.ProductAdapter;
import com.example.bottomnav.databinding.FragmentProductBinding;
import com.example.bottomnav.databinding.FragmentProductBinding;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.bottomnav.databinding.FragmentProductBinding;

import java.util.ArrayList;

public class ProductFragment extends Fragment {
    private FragmentProductBinding binding;
    private ProductAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProductViewModel viewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        binding = FragmentProductBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Inisialisasi Adapter dengan parameter yang benar
        adapter = new ProductAdapter(requireContext(), new ArrayList<>()); // <-- Tambahkan Context dan data awal

        // Setup RecyclerView dengan Grid 2 kolom
        binding.recyclerProducts.setLayoutManager(new GridLayoutManager(getContext(), 2)); // <-- Grid di sini
        binding.recyclerProducts.setAdapter(adapter);

        viewModel.getProductList().observe(getViewLifecycleOwner(), products -> {
            if (products != null) {
                adapter.setProducts(products); // Update data ke adapter
            }
        });

        return root;
    }
}