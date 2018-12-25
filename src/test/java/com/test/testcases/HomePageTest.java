package com.test.testcases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.test.pages.HomePage;
import com.test.pages.LoginPage;
import com.test.testutil.TestUtils;
import com.testbase.TestBase;

public class HomePageTest extends TestBase{
	LoginPage loginPage;
	HomePage homePage;
	TestUtils testUtil;

	public HomePageTest() {
		super();
	}

	//test cases should be separated -- independent with each other
	//before each test case -- launch the browser and login
	//@test -- execute test case
	//after each test case -- close the browser
	
	@BeforeMethod
	public void setUp() {
		initialization();
		testUtil = new TestUtils();
		loginPage = new LoginPage();
		homePage = loginPage.loginPageData(pr.getProperty("username"), pr.getProperty("password"));
	}

	@Test
	public void brokenLinkTest() {
		
	}
}
