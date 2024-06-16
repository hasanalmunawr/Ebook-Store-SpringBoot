package com.hasanalmunawr.Ebook_Store;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hasanalmunawr.Ebook_Store.book.EbookEntity;
import com.hasanalmunawr.Ebook_Store.book.EbookRepository;
import com.hasanalmunawr.Ebook_Store.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


@SpringBootTest
class ApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private EbookRepository repository;

	@Autowired
	private ObjectMapper mapper;

	@Test
	void searchByIsbn() throws Exception {
		EbookEntity entity = new EbookEntity();
		entity.setIsbn("999999");
		entity.setTitle("am i programmer?");
		entity.setPrice(0L);
		repository.save(entity);


		String json = mapper.writeValueAsString(entity);

		mockMvc.perform(
						get("/api/v1/user/999999")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
		).andExpectAll(
				status().isOk(),
				content().json(json)
		);

	}
}
