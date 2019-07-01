package com.hot2019pot.seattled41.xl.potluck.planner;

import org.junit.Test;

import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import java.sql.Date;


import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration
@WebAppConfiguration
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class PotluckPlannerApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	// Helper Methods

	// Create a Helper Function to check if TestUser is in database and if not add it.

	// Sets a test user for authentication testing and logged in routes
	public static RequestPostProcessor testUser(){
		return user("TestUser").password("pass");
	}

	// Creates a test user instance for unit testing.
	public PotluckUser createTestUser(){
		PotluckUser testUser = new PotluckUser("Test", "User", "testuser", "testuser");
		return testUser;
	}

	// Creates a potluck for unit testing.
	public Potluck createTestPotluck(PotluckUser testUser){
		Date newDate = new Date(01-01-2020);
		Potluck testPotluck = new Potluck("TestEvent", newDate, "TestLocation",
				"TestDetails", testUser, "TestCode");
		return testPotluck;
	}

	// Creates an item for a potluck for unit testing.
	public PotluckItem createTestPotLuckItem(PotluckUser testUser,Potluck testPotluck){
		PotluckItem testPotluckItem = new PotluckItem("testItem", 5, testPotluck, testUser);
		return testPotluckItem;
	}

	//********** Route Testing Not Signed In **********\\

	@Test
	public void testMyProfileNotSignedIn() throws Exception{
		// Expect redirect to login page
		this.mockMvc.perform(get("/myprofile")).andExpect(status().is3xxRedirection());	}


	@Test
	public void testLoginNotSignedIn() throws Exception {
		this.mockMvc.perform(get("/login")).andExpect(status().isOk());
	}

	@Test
	public void testHomeNotSignedIn() throws Exception {
		this.mockMvc.perform(get("/")).andExpect(status().isOk());
	}


	@Test
	public void testLogoutCompletedNotSignedIn() throws Exception{
		// Expect redirect to login page
		this.mockMvc.perform(get("/logout_complete")).andExpect(status().isOk());
	}

	@Test
	public void testAboutUsNotSignedIn() throws Exception{
		this.mockMvc.perform(get("/aboutus")).andExpect(status().isOk());
	}

	@Test
	public void testPotluckIdNotSignedIn() throws Exception{
		// Expect redirect to login page
		this.mockMvc.perform(get("/potlucks/1")).andExpect(status().is3xxRedirection());
	}

	@Test
	public void testSearchNotSignedIn() throws Exception {
		// Expect redirect to login page
		this.mockMvc.perform(get("/search")).andExpect(status().is3xxRedirection());
	}

	@Test
	public void testPotluckDetailNotSignedIn() throws Exception {
		// Expect redirect to login page
		this.mockMvc.perform(get("/addItem")).andExpect(status().is3xxRedirection());
	}

	@Test
	public void testPotluckNotSignedIn() throws Exception {
		// Expect redirect to login page
		this.mockMvc.perform(get("/Potluck")).andExpect(status().is3xxRedirection());
	}

	@Test
	public void testAddPotluckNotSignedIn() throws Exception {
		// Expect redirect to login page
		this.mockMvc.perform(get("/Potluck/add")).andExpect(status().is3xxRedirection());
	}

	@Test
	public void testDeletePotluckItemNotSignedIn() throws Exception {
		// Expect redirect to login page
		this.mockMvc.perform(get("/delete/potluckitems/1")).andExpect(status().is3xxRedirection());
	}

	@Test
	public void testSignupNotSignedIn() throws Exception {
		// Expect redirect to login page
		this.mockMvc.perform(get("/signup")).andExpect(status().isOk());
	}


	//********** Route Testing Signed In **********\\
	@Test
	@WithMockUser
	public void testMyProfileSignedIn() throws Exception {
		mockMvc.perform(get("/myprofile").with(testUser())).andExpect(status().isOk());
	}

	@WithMockUser
	@Test
	public void testLoginSignedIn() throws Exception {
		this.mockMvc.perform(get("/login").with(testUser())).andExpect(status().isOk());
	}

	@WithMockUser
	@Test
	public void testHomeSignedIn() throws Exception {
		this.mockMvc.perform(get("/").with(testUser())).andExpect(status().isOk());
	}

	@WithMockUser
	@Test
	public void testLogoutCompletedSignedIn() throws  Exception{
		this.mockMvc.perform(get("/logout_complete").with(testUser())).andExpect(status().isOk());
	}

	@WithMockUser
	@Test
	public void testAboutUsSignedIn() throws Exception{
		this.mockMvc.perform(get("/aboutus").with(testUser())).andExpect(status().isOk());
	}

	@WithMockUser
	@Test
	public void testPotluckIdSignedIn() throws Exception{
		this.mockMvc.perform(get("/Potlucks/3").with(testUser())).andExpect(status().isOk());
	}

	@WithMockUser
	@Test
	public void testSignupSIgnedIn() throws Exception {
		this.mockMvc.perform(get("/signup").with(testUser())).andExpect(status().isOk());
	}

	@WithMockUser
	@Test
	public void testLogoutRoutePass() throws Exception {
		this.mockMvc.perform(get("/logout").with(testUser())).andExpect(status().is(302));
	}

	@Test
	public void testSearchSignedInRoutePass() throws Exception {
		PotluckUser testUser = createTestUser();
		Potluck testPotluck = createTestPotluck(testUser);
		Date testDate = new Date(01-01-2020);
	}

	//********** Unit Testing **********\\

	@Test
	public void testAddPotluckSignedInRoutePass() throws Exception {
		PotluckUser testUser = createTestUser();
		Potluck testPotluck = createTestPotluck(testUser);
	}

	@Test
	public void testAddPotluckAddItemSignedInRoutePass() throws Exception {
		// signing user
		// create event
		// this.mockMvc.perform(get("/Potluck/add")).andExpect(status().is3xxRedirection());
		PotluckUser testUser = createTestUser();
		Potluck testPotluck = createTestPotluck(testUser);
	}


	//********** Unit Tests **********\\
	@Test
	public void testCreateUser(){
		PotluckUser testUser = new PotluckUser();
		assertNotNull(testUser);
	}

	@Test
	public void testCreateActualuser(){
		PotluckUser testUser = createTestUser();
		assertEquals("User First Name should be Test", "Test", testUser.firstname);
		assertEquals("User Last Name should be User", "User", testUser.lastname);
		assertEquals("User name should be testuser", "testuser", testUser.username);
		assertEquals("User password should be testuser", "testuser", testUser.password);
	}

	@Test
	public void testUserConstructor(){
		PotluckUser testUser = createTestUser();
		assertEquals("User First Name should be Test", "Test", testUser.getFirstname());
		assertEquals("User Last Name should be User", "User", testUser.getLastname());
		assertEquals("User name should be testuser", "testuser", testUser.getUsername());
		assertEquals("User password should be testuser", "testuser", testUser.getPassword());
	}

	@Test
	public void testCreatePotluck(){
		Potluck testPotluck = new Potluck();
		assertNotNull(testPotluck);
	}

	@Test
	public void testCreateAcutalPotluck(){
		PotluckUser testUser = createTestUser();
		Potluck testPotluck = createTestPotluck(testUser);
		Date testDate = new Date(01-01-2020);
		assertEquals("Date will be 2020-01-01", testDate, testPotluck.dateofPotluck);
		assertEquals("Code should be abc12", "TestCode", testPotluck.code);
	}

	@Test
	public void testPotluckConstructor(){
		PotluckUser testUser = createTestUser();
		Potluck testPotluck = createTestPotluck(testUser);
		Date testDate = new Date(01-01-2020);
		assertEquals("Date will be 2020-01-01", testDate, testPotluck.getDateofPotluck());
		assertEquals("Code should be abc12", "TestCode", testPotluck.getCode());
	}

	@Test
	public void testCreatePotluckItem(){
		PotluckItem testItem = new PotluckItem();
		assertNotNull(testItem);
	}

	@Test
	public void testCreateActualPotluckItem(){
		PotluckUser testUser = createTestUser();
		Potluck testPotluck = createTestPotluck(testUser);
		PotluckItem testPotluckItem = createTestPotLuckItem(testUser, testPotluck);
		assertEquals("Item should be TestItem", "testItem", testPotluckItem.item);
	}


	// Full Path Test
	@Test
	public void testFullPath() throws Exception {
		PotluckUser testUser = createTestUser();
		Potluck testPotluck = createTestPotluck(testUser);
		PotluckItem testPotluckItem = createTestPotLuckItem(testUser, testPotluck);
	}

}
