package com.vsaliy.urlshortener.service;

import com.vsaliy.urlshortener.UrlShorteningException;
import com.vsaliy.urlshortener.generator.UrlIdentifierGenerator;
import com.vsaliy.urlshortener.model.RegisteredUrl;
import com.vsaliy.urlshortener.dao.RegisteredUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

@Service
public class UrlShortenerServiceImpl implements UrlShortenerService {

	private final RegisteredUrlRepository registeredUrlRepository;

	private final UrlIdentifierGenerator urlIdentifierGenerator;

	@Autowired
	public UrlShortenerServiceImpl(RegisteredUrlRepository registeredUrlRepository, UrlIdentifierGenerator urlIdentifierGenerator) {
		this.registeredUrlRepository = registeredUrlRepository;
		this.urlIdentifierGenerator = urlIdentifierGenerator;
	}

	@Override
	public RegisteredUrl getLongURLByShortUrlId(String id) {
		//TODO: update statistics
		return registeredUrlRepository.findByShortUrlId(id);
	}

	@Override
	public RegisteredUrl convertUrl(String url) {
		try {
			final URL urlToShorten = new URL(url);
			return getOrCreateRegisteredUrl(urlToShorten);
		} catch (MalformedURLException e) {
			throw new UrlShorteningException("The URL to shorten is invalid: " + url, e);
		}
	}

	private RegisteredUrl getOrCreateRegisteredUrl(URL urlToShorten) {
		RegisteredUrl byUrl = registeredUrlRepository.findByUrl(urlToShorten);
		if (Objects.isNull(byUrl)) {
			final RegisteredUrl registeredUrl = new RegisteredUrl(urlIdentifierGenerator.generate(), urlToShorten);
			registeredUrlRepository.save(registeredUrl);
			return registeredUrl;
		}
		return byUrl;
	}
}
