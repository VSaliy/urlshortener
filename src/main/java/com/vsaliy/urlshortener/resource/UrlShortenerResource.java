package com.vsaliy.urlshortener.resource;

import com.vsaliy.urlshortener.model.RegisteredUrl;
import com.vsaliy.urlshortener.service.UrlShortenerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/")
public class UrlShortenerResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(UrlShortenerResource.class);

	@Autowired
	private UrlShortenerService urlShortenerService;

	@PostMapping("/")
	public RegisteredUrl addUrl(@RequestParam(value="url") String url) {
		LOGGER.info("Received url to shorten: " + url);
		RegisteredUrl registeredUrl = urlShortenerService.convertUrl(url);
		LOGGER.info("Shortened url to: " + registeredUrl.getShortUrlId());
		return registeredUrl; //TODO: Convert to dto, complete shortened url
	}

	@GetMapping("/{id}")
	public RedirectView redirectUrl(@PathVariable String id) {
		LOGGER.info("Received shortened url to redirect: " + id);
		RegisteredUrl redirectUrl = urlShortenerService.getLongURLByShortUrlId(id);
		LOGGER.info("Original URL: " + redirectUrl.getUrl().toString());
		return new RedirectView(redirectUrl.getUrl().toString());
	}

	@GetMapping("/{id}/stats")
	public void getStatistics(@PathVariable String id) {
		System.out.println(id);
	}
}
