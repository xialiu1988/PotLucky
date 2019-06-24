package com.hot2019pot.seattled41.xl.potluck.planner;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class PotluckPlannerApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testHomeNotSignedInRoutePass() throws Exception {
		this.mockMvc.perform(get("/")).andExpect(status().isOk());
	}

	@Test
	public void testHomeSignedInRoutePass() throws Exception {
		this.mockMvc.perform(get("/")).andExpect(status().isOk());
	}

	@Test
	public void testLoginRoutePass() throws Exception {
		this.mockMvc.perform(get("/login")).andExpect(status().isOk());
	}

	@Test
	public void testSearchNotSignedInRoutePass() throws Exception {
		this.mockMvc.perform(get("/search")).andExpect(status().is3xxRedirection());
	}

	@Test
	public void testSearchSignedInRoutePass() throws Exception {
		// Need to add test for this
		// this.mockMvc.perform(get("/search")).andExpect(status().is3xxRedirection());
	}

	@Test
	public void testSignupRoutePass() throws Exception {
		this.mockMvc.perform(get("/signup")).andExpect(status().isOk());
	}

	@Test
	public void testSignupRouteRedirectPass() throws Exception {
		// Need to add testing here
		// this.mockMvc.perform(get("/signup")).andExpect(status().isOk());
	}

	@Test
	public void testPotluckNotSignedInRoutePass() throws Exception {
		this.mockMvc.perform(get("/Potluck")).andExpect(status().is3xxRedirection());
	}

	@Test
	public void testPotluckSignedInRoutePass() throws Exception {
		// Need to add testing for this.
		// this.mockMvc.perform(get("/Potluck")).andExpect(status().is3xxRedirection());
	}

	@Test
	public void testAddPotluckNotSignedInRoutePass() throws Exception {
		this.mockMvc.perform(get("/Potluck/add")).andExpect(status().is3xxRedirection());
	}

	@Test
	public void testAddPotluckSignedInRoutePass() throws Exception {
		// Need to add tests
		// this.mockMvc.perform(get("/Potluck/add")).andExpect(status().is3xxRedirection());
	}

	@Test
	public void testPotluckDetailNotSignedInRoutePass() throws Exception {
		this.mockMvc.perform(get("/Potluck/add")).andExpect(status().is3xxRedirection());
	}

	@Test
	public void testPotluckDetailSignedInRoutePass() throws Exception {
		// Need to add test
		// this.mockMvc.perform(get("/Potluck/add")).andExpect(status().is3xxRedirection());
	}
}