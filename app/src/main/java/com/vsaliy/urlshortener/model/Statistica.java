package com.vsaliy.urlshortener.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "statistica")
public class Statistica {

	private Long retriveCounter;
	@DBRef
	private RegisteredUrl registeredUrl;

	public Statistica(RegisteredUrl registeredUrl) {
		this.registeredUrl = registeredUrl;
	}

	public Long getRetriveCounter() {
		return retriveCounter;
	}

	public void setRetriveCounter(Long retriveCounter) {
		this.retriveCounter = retriveCounter;
	}

	public RegisteredUrl getRegisteredUrl() {
		return registeredUrl;
	}

	public void setRegisteredUrl(RegisteredUrl registeredUrl) {
		this.registeredUrl = registeredUrl;
	}
}
