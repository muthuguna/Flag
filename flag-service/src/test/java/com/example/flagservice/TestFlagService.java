package com.example.flagservice;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class TestFlagService extends FlagServiceApplicationTests {
	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testFlagService1() throws Exception {
		mockMvc.perform(get("/rest/flagService/flag/India")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0]", is("🇮🇳")));
	}

	@Test
	public void testFlagService2() throws Exception {
		mockMvc.perform(get("/rest/flagService/countries/Asia").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.length()", is(5)));
	}

	@Test
	public void testFlagService3() throws Exception {
		mockMvc.perform(get("/rest/flagService/continents").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.length()", is(5)));
	}

	@Test
	public void testFlagService4() throws Exception {
		mockMvc.perform(get("/rest/flagService")).andExpect(status().isOk());
	}
}
