package com.gorrotowi.android108network;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gorrotowi.android108network.network.Api;
import com.gorrotowi.android108network.networkmodels.ProductResponse;

import java.util.List;

public class ProductsViewModel extends ViewModel {

    private MutableLiveData<List<ProductResponse>> mutableProductList = new MutableLiveData<>();
    private Api api = new Api();

    public LiveData<List<ProductResponse>> productsList() {
        return mutableProductList;
    }

    public void getAllProductsLiveData() {
        mutableProductList = api.getAllProductsByLiveData();
    }
}
