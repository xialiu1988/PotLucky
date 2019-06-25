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

	// Route Testing
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
		// Expect redirect to login page.
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
		// this.mockMvc.perform(get("/addItem")).andExpect(status().is3xxRedirection());
	}

	@Test
	public void testPotluckDetailNotSignedInRoutePass() throws Exception {
		this.mockMvc.perform(get("/addItem")).andExpect(status().is3xxRedirection());
	}

	@Test
	public void testAddPotluckAddItemSignedInRoutePass() throws Exception {
		// signing user
		// create event
		// this.mockMvc.perform(get("/Potluck/add")).andExpect(status().is3xxRedirection());
	}

	@Test
	public void testPotluckAddItemNotSignedInRoutePass() throws Exception {
		this.mockMvc.perform(get("/Potluck/add")).andExpect(status().is3xxRedirection());
	}

	@Test
	public void testPotluckDetailSignedInRoutePass() throws Exception {
		// signing user
		// create event
		// this.mockMvc.perform(get("/Potluck/add")).andExpect(status().is3xxRedirection());
	}

	@Test
	public void testPotLuckConstructorPass() throws Exception {
		// Need To Add Test
		// Test Getters
	}

	@Test
	public void testFullPath() throws Exception {
		// home page
		// /signup
			// username
			// firstname
		// profile
			// no potluck
		// add potluck
			// date
			// code
		// test code

		// new user add
		// show user on creator page
		// add item
	}

	@Test
	public void testDatabase() throws Exception{
		// Test database exists
		// test tables exist
		// test user data
		// test potluck data

	}


}