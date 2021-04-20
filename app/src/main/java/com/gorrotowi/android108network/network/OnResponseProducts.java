package com.gorrotowi.android108network.network;

import com.gorrotowi.android108network.networkmodels.ProductResponse;

import java.util.List;

public interface OnResponseProducts {

    void receiveAllProducts(List<ProductResponse> dataResponse);

    void receiveError(Throwable t);

}
