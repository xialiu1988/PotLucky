package com.hot2019pot.seattled41.xl.potluck.planner;

import org.junit.Test;

import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.sql.Date;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class PotluckPlannerApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	// Helper Methods
	public PotluckUser createTestUser(){
		PotluckUser testUser = new PotluckUser("Test", "User", "testuser", "testuser");
		return testUser;
	}

	public Potluck createTestPotluck(PotluckUser testUser){
		Date newDate = new Date(01-01-2020);
		Potluck testPotluck = new Potluck("TestEvent", newDate, "TestLocation",
				"TestDetails", testUser, "TestCode");
		return testPotluck;
	}

	public PotluckItem createTestPotLuckItem(PotluckUser testUser,Potluck testPotluck){
		PotluckItem testPotluckItem = new PotluckItem("testItem", 5, testPotluck, testUser);
		return testPotluckItem;
	}

	// Route Testing
	@Test
	public void testSlashNotSignedInRoutePass() throws Exception {
		this.mockMvc.perform(get("/")).andExpect(status().isOk());
	}

	@Test
	public void testSlashSignedInRoutePass() throws Exception {
		PotluckUser testUser = createTestUser();
		this.mockMvc.perform(get("/")).andExpect(status().isOk());
	}

//	@Test
//	public void testHomeNotSignedInRoutePass() throws Exception {
//		this.mockMvc.perform(get("/home")).andExpect(status().isOk());
//	}

//	@Test
//	public void testHomeSignedInRoutePass() throws Exception {
//		PotluckUser testUser = createTestUser();
//		this.mockMvc.perform(get("/home")).andExpect(status().isOk());
//	}

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
		PotluckUser testUser = createTestUser();
		Potluck testPotluck = createTestPotluck(testUser);
		Date testDate = new Date(01-01-2020);
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
	public void testLogoutRoutePass() throws Exception {
		this.mockMvc.perform(get("/logout")).andExpect(status().is3xxRedirection());
	}

	@Test
	public void testLogoutRouteRedirectPass() throws Exception {
		// Need to add testing here
		// this.mockMvc.perform(get("/logout")).andExpect(status().isOk());
	}

	@Test
	public void testPotluckNotSignedInRoutePass() throws Exception {
		this.mockMvc.perform(get("/Potluck")).andExpect(status().is3xxRedirection());
	}

	@Test
	@WithMockUser
	public void testPotluckSignedInRoutePass() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/Potluck")
				.accept(MediaType.ALL));
//				.andExpect(status().isOk());

//		this.mockMvc.perform(get("/Potluck")).andExpect(status().isOk());

	}

	@Test
	public void testAddPotluckNotSignedInRoutePass() throws Exception {
		this.mockMvc.perform(get("/Potluck/add")).andExpect(status().is3xxRedirection());
	}

	@Test
	public void testAddPotluckSignedInRoutePass() throws Exception {
		// Need to add tests
		// this.mockMvc.perform(get("/addItem")).andExpect(status().is3xxRedirection());
		PotluckUser testUser = createTestUser();
		Potluck testPotluck = createTestPotluck(testUser);
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
		PotluckUser testUser = createTestUser();
		Potluck testPotluck = createTestPotluck(testUser);
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
		PotluckUser testUser = createTestUser();

	}

	@Test
	public void testDatabase() throws Exception{
		// Test database exists
		// test tables exist
		// test user data
		// test potluck data
	}

	@Test
	public void testLoginInvalidUsernamePassword() throws Exception{
		// Test login with invalid username
		// test login with invalid password
	}

	@Test
	public void testAddInvalidPotluckCode() throws Exception{
		// Test try to add invalid potluck code
	}

	@Test
	public void testAddUserAlreadyExists() throws Exception{
		// Test adding a user that already exists
	}

	@Test
	public void testAddUserNoPassword() throws Exception{

	}

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

	@Test
	public void testFullPath() throws Exception {
		PotluckUser testUser = createTestUser();
		Potluck testPotluck = createTestPotluck(testUser);
		PotluckItem testPotluckItem = createTestPotLuckItem(testUser, testPotluck);
//		this.mockMvc.perform(get("/")).andExpect(status().isOk());

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
}
