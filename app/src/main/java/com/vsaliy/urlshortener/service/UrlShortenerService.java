package com.vsaliy.urlshortener.service;

import com.vsaliy.urlshortener.model.RegisteredUrl;

public interface UrlShortenerService {

	RegisteredUrl getLongURLByShortUrlId(String id);

	RegisteredUrl convertUrl(String url);
}
