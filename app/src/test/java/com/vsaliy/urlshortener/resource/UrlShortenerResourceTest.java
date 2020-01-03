package com.vsaliy.urlshortener.resource;

import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;
import com.vsaliy.urlshortener.dao.RegisteredUrlRepository;
import com.vsaliy.urlshortener.model.RegisteredUrl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.net.MalformedURLException;
import java.net.URL;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UrlShortenerResourceTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private RegisteredUrlRepository registeredUrlRepository;

	@BeforeEach
	public void setup() throws MalformedURLException {
		registeredUrlRepository.deleteAll();
		registeredUrlRepository.save(new RegisteredUrl("shortenedUrlId", new URL("https://www.google.com")));
	}

	@Test
	@DisplayName("The program should have a form that accepts the long URL")
	public void shouldCreateOrGetShortenedUrlId() throws Exception {
		String url = "https://www.google.com";
		this.mockMvc.perform(post("/").param("url", url)).andDo(print()).andExpect(status().isOk());
	}

	@Test
	@DisplayName("The program should redirect visitors to the long URL when the short URL is visited")
	public void shouldRedirectToShortenedUrlId() throws Exception {
		String id = "shortenedUrlId";
		this.mockMvc.perform(get("/{id}", id)).andDo(print()).andExpect(status().is3xxRedirection());
	}

	@Test
	@DisplayName("The program should have a statistics page for the short URL")
	public void shouldDisplayStats() throws Exception {
		String id = "shortenedUrlId";
		this.mockMvc.perform(get("/{id}/stats", id)).andDo(print()).andExpect(status().isOk());
	}

}
