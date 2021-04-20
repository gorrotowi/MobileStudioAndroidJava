package com.gorrotowi.android108network;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.gorrotowi.android108network.network.Api;
import com.gorrotowi.android108network.networkmodels.ProductResponse;

import java.util.List;

public class ProductsViewModel extends ViewModel {

    public LiveData<List<ProductResponse>> productsList;
    private Api api = new Api();

    public void getAllProductsLiveData() {
        productsList = api.getAllProductsByLiveData();
    }
}
