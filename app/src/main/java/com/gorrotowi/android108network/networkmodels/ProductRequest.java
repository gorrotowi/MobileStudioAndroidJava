package com.gorrotowi.android108network.networkmodels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("price")
    private double priceProduct;

    @JsonProperty("description")
    private String description;

    public ProductRequest(String name, double priceProduct, String description) {
        this.name = name;
        this.priceProduct = priceProduct;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public double getPriceProduct() {
        return priceProduct;
    }

    public String getDescription() {
        return description;
    }
}
