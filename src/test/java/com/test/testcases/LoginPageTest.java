package com.test.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.pages.HomePage;
import com.test.pages.LoginPage;
import com.test.testutil.TestUtils;
import com.testbase.TestBase;

public class LoginPageTest extends TestBase {
	LoginPage loginPage;
	HomePage homePage;
	TestUtils testUtils;
	//ReadExcel read;

	public static String sheetName = "LoginData";


	public LoginPageTest() {
		super();

	}

	@BeforeMethod
	public void setup() {
		initialization();

		testUtils = new TestUtils();

		loginPage = new LoginPage();

	}

	@Test(priority = 1)
	public void loginTitleTest() {
		String loginPageTitle = loginPage.loginTitleTest();
		Assert.assertEquals(loginPageTitle, "FrontAccounting 2.4.2 - Login");

	}

	@Test(priority = 2)
	public void frontAccountinglogotest() {
		boolean logoPresent = loginPage.frontAccountinglogotest();
		Assert.assertTrue(logoPresent);
	}

/*	@DataProvider(name="loginFrontAccountingData")
	public Object[][] loginFrontAccountingData() {
		Object[][] data = TestUtils.getTestData(sheetName);
		return data;
	}

	

	@Test(priority = 3, dataProvider = "loginFrontAccountingData")
	public void validateLoginData(String username, String password) {
		loginPage.login(username, password);

	}*/
	
	@DataProvider(name="loginData")
	public Object[][] loginDataProvider() throws Exception{
		
		Object[][] data = testUtils.getTestData(sheetName);
		return data;
		
	}
	
	@Test(priority=2,dataProvider="loginData")
	public void LoginPageDataTest(String username,String password ) throws IOException
	{
					
		loginPage.loginPageData(username, password);
		
	}

	/*
	 * @Test(priority=3) public void loginPage() { loginPage.login(); }
	 */

	/*
	 * @Test(priority = 4) public void loginTest() { homePage =
	 * loginPage.login(pr.getProperty("username"), pr.getProperty("password")); }
	 */
	@Test(priority = 4)
	public void dropDownTest() {
		loginPage.dropDown();
	}
	
	@Test(priority = 5)
	public void verifyIncorrectPassword() {
		loginPage.incorrectPassword();
	}


	@AfterMethod
	public void tearDown() throws InterruptedException {
		Thread.sleep(15000);
		driver.quit();

	}
}