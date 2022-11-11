package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.presentation.MenuController;
import com.example.demo.presentation.MenuDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class MenuApplicationTests {
	@Autowired
	private MenuController menuController;

	private MockMvc mockMvc;

	@BeforeEach
	void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(menuController).build();
	}

	@Test
	public void getMenu_200() throws Exception {
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/1")).andReturn();
		int status = mvcResult.getResponse().getStatus();
		MenuDTO menu = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(),
				MenuDTO.class);
		assertEquals(1, menu.getId());
		assertEquals(200, status);
	}

	@Test
	public void listMenu_200() throws Exception {
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/")).andReturn();
		int status = mvcResult.getResponse().getStatus();
		List<MenuDTO> menus = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(),
				List.class);
		assertEquals(11, menus.size());
		assertEquals(200, status);
	}

	@Test
	public void getMenu_inValidMenuID_400() throws Exception {
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/abc")).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(400, status);
	}

	@Test
	public void getMenu_nonExistingMenuID_404() throws Exception {
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/1111111")).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(404, status);
	}
}
