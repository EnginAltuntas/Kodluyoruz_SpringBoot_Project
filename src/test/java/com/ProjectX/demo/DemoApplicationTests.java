package com.ProjectX.demo;

import com.ProjectX.demo.domainObject.booksDO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	public void testGetAllBooks() throws Exception{
		mockMvc.perform(get("/api/v1/books")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[*].id").exists())
				.andExpect(jsonPath("$[*].book_name").exists())
				.andExpect(jsonPath("$[*].author_name").exists())
				.andExpect(jsonPath("$[*].numberOfPages").exists());

	}

	@Test
	public void testAddBook() throws Exception {

		booksDO newBook = new booksDO();
		newBook.setBook_name("Deneme1Kitap");
		newBook.setAuthor_name("DenemeYazar");
		newBook.setNumberOfPages(222);

		mockMvc.perform(post("/api/v1/addbook")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(newBook)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.book_name").exists())
				.andExpect(jsonPath("$.author_name").exists())
				.andExpect(jsonPath("$.numberOfPages").exists());

	}

	@Test
	public void testUpdateBook() throws Exception {
		booksDO newBook = new booksDO();
		newBook.setId(6L);
		newBook.setBook_name("Deneme1Kitap");
		newBook.setAuthor_name("DenemeYazarupdated");
		newBook.setNumberOfPages(111);

		mockMvc.perform(put("/api/v1/putbook")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(newBook)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.book_name").exists())
				.andExpect(jsonPath("$.author_name").exists())
				.andExpect(jsonPath("$.numberOfPages").exists());					;
	}

	@Test
	public void testDeleteCustomer() throws Exception {
		String bookId = "6";

		mockMvc.perform(delete("/api/v1/book/{book_id}", bookId)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	public static String asJsonString(final Object object) {
		try {
			return new ObjectMapper().writeValueAsString(object);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}



}
