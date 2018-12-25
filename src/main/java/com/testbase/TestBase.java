package com.testbase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestBase {
	public static WebDriver driver;
	public static Properties pr;

	public TestBase() {

	
		System.out.println(111);
		try {
			pr = new Properties();
			FileInputStream fis = new FileInputStream(
					"I:\\All Eclipse Code6\\Font-Accounting\\src\\main\\java\\com\\test\\config\\config.properties");
			try {
				pr.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void initialization() {

		String browserName = pr.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "I:\\All eclipse Code7\\Front_Accounting\\src\\main\\java\\com\\test\\exefiles\\chromedriver.exe");
			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("firefox")) {

			//System.setProperty("webdriver.gecko.driver", "C:\\selelib\\geckodriver.exe");
			driver = new FirefoxDriver();

		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		//driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICITLY_WAIT_TIMEOUT, TimeUnit.SECONDS);
		//driver.manage().timeouts().pageLoadTimeout(TestUtils.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.get(pr.getProperty("url"));

	}


}
