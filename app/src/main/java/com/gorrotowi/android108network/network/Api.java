package com.gorrotowi.android108network.network;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gorrotowi.android108network.BuildConfig;
import com.gorrotowi.android108network.networkmodels.ErrorBody;
import com.gorrotowi.android108network.networkmodels.ProductRequest;
import com.gorrotowi.android108network.networkmodels.ProductResponse;
import com.gorrotowi.android108network.utils.Logger;

import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class Api {

    private SampleServices services;

    public Api() {

        HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG == true) {
            logger.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            logger.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        Interceptor interceptorCommonHeader = chain -> {
            Request.Builder requestBuilder = chain.request().newBuilder();
            requestBuilder.addHeader("Content-Type", "application/json");
            requestBuilder.addHeader("Accept", "application/json");
            return chain.proceed(requestBuilder.build());
        };

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logger)
                .addInterceptor(interceptorCommonHeader)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080")
                .client(client)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        services = retrofit.create(SampleServices.class);
    }

    public void makeSampleRequest() {
        services.sampleRequest().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.e("RESPONSE", response.body().byteStream().toString());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Logger.loge(t.getMessage());
            }
        });
    }

    public void getAllProducts(OnResponseProducts responseListener) {
        services.allProducts().enqueue(new Callback<List<ProductResponse>>() {
            @Override
            public void onResponse(Call<List<ProductResponse>> call, Response<List<ProductResponse>> response) {
                List<ProductResponse> productResponse = response.body();
                for (ProductResponse product : productResponse) {
                    Log.d("PRODUCTS", product.toString());
                }
                responseListener.receiveAllProducts(productResponse);
            }

            @Override
            public void onFailure(Call<List<ProductResponse>> call, Throwable t) {
                Log.e("FAILURE", t.getLocalizedMessage());
                responseListener.receiveError(t);
            }
        });
    }

    public MutableLiveData<List<ProductResponse>> getAllProductsByLiveData() {
        MutableLiveData<List<ProductResponse>> liveDataResponse = new MutableLiveData<>();
        services.allProducts().enqueue(new Callback<List<ProductResponse>>() {
            @Override
            public void onResponse(Call<List<ProductResponse>> call, Response<List<ProductResponse>> response) {
                List<ProductResponse> productResponse = response.body();
                for (ProductResponse product : productResponse) {
                    Log.d("PRODUCTS", product.toString());
                }
                liveDataResponse.postValue(productResponse);
            }

            @Override
            public void onFailure(Call<List<ProductResponse>> call, Throwable t) {
                Log.e("FAILURE", t.getLocalizedMessage());
                liveDataResponse.postValue(null);
            }
        });
        return liveDataResponse;
    }

    public void addNewProduct() {
        ProductRequest newProduct = new ProductRequest("Super soda", 1.59, "This is a super cola soda");
        services.addProduct(newProduct).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                ProductResponse product = response.body();
                Log.e("PRODUCT ADDED", product.toString());
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Log.e("FAILURE", t.getLocalizedMessage());
            }
        });
    }

    public void updateProduct() {
        ProductRequest updateProduct = new ProductRequest("Jam", 1.99, "Yeap! the same jam... sorry, the soda did not work");
        services.updateProduct(1, updateProduct).enqueue(new Callback<List<ProductResponse>>() {
            @Override
            public void onResponse(Call<List<ProductResponse>> call, Response<List<ProductResponse>> response) {
                List<ProductResponse> productResponse = response.body();
                if (productResponse != null) {
                    for (ProductResponse product : productResponse) {
                        Log.i("PRODUCTS", product.toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ProductResponse>> call, Throwable t) {
                Log.e("FAILURE", t.getLocalizedMessage());
            }
        });
    }

    public void deleteProduct() {
        services.deleteProductById(1).enqueue(new Callback<List<ProductResponse>>() {
            @Override
            public void onResponse(Call<List<ProductResponse>> call, Response<List<ProductResponse>> response) {
                List<ProductResponse> productResponse = response.body();
                if (productResponse != null) {
                    for (ProductResponse product : productResponse) {
                        Log.v("PRODUCTS", product.toString());
                    }
                } else if (response.code() == 404) {
                    try {
                        if (response.errorBody() != null) {
                            String errorResponse = response.errorBody().string();
                            ObjectMapper objectMapper = new ObjectMapper();
                            objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
                            ErrorBody error = objectMapper.readValue(errorResponse, ErrorBody.class);
                            Log.e("ErrorDelete", error.getError());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ProductResponse>> call, Throwable t) {
                Log.e("FAILURE", t.getLocalizedMessage());
            }
        });
    }
}
