package com.vsaliy.urlshortener.dao;

import com.vsaliy.urlshortener.model.RegisteredUrl;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.net.URL;

@Repository
public interface RegisteredUrlRepository extends MongoRepository<RegisteredUrl, String> {
	RegisteredUrl findByShortUrlId(String shortUrlId);

	RegisteredUrl findByUrl(URL url);

}
