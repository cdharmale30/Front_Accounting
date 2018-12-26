package com.testbase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.test.testutil.WebEventListener;

public class TestBase {
	public static final Logger logger = Logger.getLogger(TestBase.class.getName());

	public static WebDriver driver;
	public static Properties pr;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	//public static  Logger logger;

	public TestBase() {

		
		try {
			//----------------log4j------------------
			String log4jConfPath = "log4j.properties";
			//----------------log4j------------------

			
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
			System.setProperty("webdriver.chrome.driver",
					"I:\\All eclipse Code7\\Front_Accounting\\src\\main\\java\\com\\test\\exefiles\\chromedriver.exe");
			driver = new ChromeDriver();


		} else if (browserName.equalsIgnoreCase("firefox")) {

			// System.setProperty("webdriver.gecko.driver", "C:\\selelib\\geckodriver.exe");
			driver = new FirefoxDriver();

		}
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with
		// EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		// driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICITLY_WAIT_TIMEOUT,
		// TimeUnit.SECONDS);
		// driver.manage().timeouts().pageLoadTimeout(TestUtils.PAGE_LOAD_TIMEOUT,
		// TimeUnit.SECONDS);
		driver.get(pr.getProperty("url"));

	}

}
