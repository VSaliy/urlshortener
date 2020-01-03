package com.vsaliy.urlshortener.model;

import com.vsaliy.urlshortener.dao.RegisteredUrlRepository;
import com.vsaliy.urlshortener.generator.UrlIdentifierGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class UrlShortenerModelTest {

	private final String url = "https://www.google.com/";

	@Autowired
	private RegisteredUrlRepository registeredUrlRepository;

	@BeforeEach
	public void setup() {
		registeredUrlRepository.deleteAll();
	}

	@Test
	@DisplayName("The program should have persistent storage for shortUrlId and URL")
	public void setIdOnSave() throws MalformedURLException {
		RegisteredUrl registeredUrl = registeredUrlRepository.save(new RegisteredUrl("shortUrl", new URL(url)));

		assertThat(registeredUrl.getId()).isNotNull();
	}

	@Test
	@DisplayName("The program should retrieve persisted object by URL")
	public void getByUrl() throws MalformedURLException {
		RegisteredUrl expectedRegisteredUrl = registeredUrlRepository.save(new RegisteredUrl("shortUrl", new URL(url)));
		RegisteredUrl actualRegisteredUrl = registeredUrlRepository.findByUrl(new URL(url));

		assertThat(expectedRegisteredUrl.getId()).isEqualTo(actualRegisteredUrl.getId());
		assertThat(expectedRegisteredUrl.getUrl()).isEqualTo(actualRegisteredUrl.getUrl());
		assertThat(expectedRegisteredUrl.getShortUrlId()).isEqualTo(actualRegisteredUrl.getShortUrlId());
	}

	@Test
	@DisplayName("The program should retrieve persisted object by shortUrlId")
	public void getByShortUrlId() throws MalformedURLException {
		String shortUrl = "shortUrl";
		RegisteredUrl expectedRegisteredUrl = registeredUrlRepository.save(new RegisteredUrl(shortUrl, new URL(url)));
		RegisteredUrl actualRegisteredUrl = registeredUrlRepository.findByShortUrlId(shortUrl);

		assertThat(expectedRegisteredUrl.getId()).isEqualTo(actualRegisteredUrl.getId());
		assertThat(expectedRegisteredUrl.getUrl()).isEqualTo(actualRegisteredUrl.getUrl());
		assertThat(expectedRegisteredUrl.getShortUrlId()).isEqualTo(actualRegisteredUrl.getShortUrlId());
	}

}
