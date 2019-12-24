package com.vsaliy.urlshortener.model;

import java.net.URL;

public class RegisteredUrlDto {

	private String id;
	private final URL url;

	public RegisteredUrlDto(URL url) {
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public URL getUrl() {
		return url;
	}
}
