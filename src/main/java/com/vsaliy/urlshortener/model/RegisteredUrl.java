package com.vsaliy.urlshortener.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.net.URL;

@Document(collection = "registeredUrl")
public class RegisteredUrl {

	@Id
	private String id;
	private Long count;
	private final String shortUrlId;
	private final URL url;

	public RegisteredUrl(String shortUrlId, URL url) {
		this.shortUrlId = shortUrlId;
		this.url = url;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public String getId() {
		return id;
	}

	public String getShortUrlId() {
		return shortUrlId;
	}

	public URL getUrl() {
		return url;
	}
}
