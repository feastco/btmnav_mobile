package com.example.bottomnav.ui.product;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.bottomnav.RegisterAPI;
import com.example.bottomnav.RetrofitClient;
import com.example.bottomnav.Product;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductViewModel extends ViewModel {

    private final MutableLiveData<List<Product>> productList = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public ProductViewModel() {
        fetchProducts();
    }

    public void fetchProducts() {
        RetrofitClient.getApiService().getProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    productList.setValue(response.body());
                } else {
                    errorMessage.setValue("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                errorMessage.setValue("Failure: " + t.getMessage());
            }
        });
    }

    public LiveData<List<Product>> getProductList() {
        return productList;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }
}