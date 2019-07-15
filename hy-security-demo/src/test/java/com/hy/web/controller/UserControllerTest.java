package com.hy.web.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * 
 * @author hy
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		
	}
	
	@Test
	public void whenUploadSuccess() throws UnsupportedEncodingException, Exception {
		String result = mockMvc.perform(MockMvcRequestBuilders.fileUpload("/file")
				.file(new MockMultipartFile("file", "test.text","multipart/form-data","hello upload".getBytes("UTF-8"))))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}
	
	
	
	@Test
	public void whenQuerySuccess() throws Exception {
		String result = mockMvc.perform(MockMvcRequestBuilders.get("/user")
			.param("username","jojo")
			.contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
			.andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}
	
	@Test
	public void whenGenInfoSuccess() throws Exception {
		String result = mockMvc.perform(MockMvcRequestBuilders.get("/user/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.username").value("tom"))
				.andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}
	
	@Test
	public void whenGetInfoFail() throws Exception {
		String result = mockMvc.perform(MockMvcRequestBuilders.get("/user/a")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(MockMvcResultMatchers.status().is4xxClientError())
			.andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}
	
	@Test
	public void whenCreateSuccess() throws UnsupportedEncodingException, Exception {
		
		Date date = new Date();
		System.out.println(date.getTime());
		String content = "{\"username\":\"tom\",\"password\":null,\"birthday\":"+date.getTime()+"}";
		String result = mockMvc.perform(MockMvcRequestBuilders.post("/user")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content))
			.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
				.andReturn().getResponse().getContentAsString();
		System.out.println(result);
		
	}
	
	@Test
	public void whenUpdateSuccess() throws UnsupportedEncodingException, Exception {
		Date date = new Date();
		System.out.println(date.getTime());
		String content = "{\"id\":\"1\",\"username\":\"tom\",\"password\":null,\"birthday\":"+date.getTime()+"}";
		String result = mockMvc.perform(MockMvcRequestBuilders.put("/user/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content))
			.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
				.andReturn().getResponse().getContentAsString();
		System.out.println(result);

	}
	
	@Test
	public void whenDeleteSuccess() throws UnsupportedEncodingException, Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/user/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(MockMvcResultMatchers.status().isOk());

	}
}
