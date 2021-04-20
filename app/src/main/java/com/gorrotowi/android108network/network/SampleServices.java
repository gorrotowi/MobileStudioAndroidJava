package com.gorrotowi.android108network.network;

import com.gorrotowi.android108network.networkmodels.ProductRequest;
import com.gorrotowi.android108network.networkmodels.ProductResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SampleServices {

    @GET("/")
    Call<ResponseBody> sampleRequest();

    @GET("/allProducts")
    Call<List<ProductResponse>> allProducts();

    @POST("/addProduct")
    Call<ProductResponse> addProduct(@Body ProductRequest product);

    @POST("/updateProduct")
    Call<List<ProductResponse>> updateProduct(@Query("id") int id, @Body ProductRequest product);

    @DELETE("/product")
    Call<List<ProductResponse>> deleteProductById(@Query("id") int id);
}
