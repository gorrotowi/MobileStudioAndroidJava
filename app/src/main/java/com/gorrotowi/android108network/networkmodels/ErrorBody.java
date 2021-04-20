package com.gorrotowi.android108network.networkmodels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorBody{

	@JsonProperty("error")
	private String error;

	public String getError(){
		return error;
	}
}