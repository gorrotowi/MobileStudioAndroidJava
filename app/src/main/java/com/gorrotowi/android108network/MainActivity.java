package com.gorrotowi.android108network;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.gorrotowi.android108network.databinding.ActivityMainBinding;
import com.gorrotowi.android108network.network.Api;
import com.gorrotowi.android108network.network.OnResponseProducts;
import com.gorrotowi.android108network.networkmodels.ProductResponse;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Api api;
    private ProductsViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        api = new Api();

        viewModel = new ViewModelProvider(this).get(ProductsViewModel.class);

        viewModel.productsList.observe(this, productResponses -> {
            binding.txtProductsSize.setText("Quantity of products " + productResponses.size());
        });

        binding.btnGetAllProductsLiveData.setOnClickListener(v -> {
            viewModel.getAllProductsLiveData();
        });

        binding.btnGetAllProducts.setOnClickListener(v -> {
            api.getAllProducts(new OnResponseProducts() {
                                   @Override
                                   public void receiveAllProducts(List<ProductResponse> dataResponse) {
                                       binding.txtProductsSize.setText("Quantity of products " + dataResponse.size());
                                   }

                                   @Override
                                   public void receiveError(Throwable t) {
                                       binding.txtProductsSize.setText(t.getLocalizedMessage());
                                   }
                               }
            );
        });

        binding.btnAddProduct.setOnClickListener(v -> {
            api.addNewProduct();
        });

        binding.btnUpdateProduct.setOnClickListener(v -> {
            api.updateProduct();
        });

        binding.btnDeleteProduct.setOnClickListener(v -> {
            api.deleteProduct();
        });
    }
}
