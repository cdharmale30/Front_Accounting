package com.test.testutil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestUtils {
	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICITLY_WAIT_TIMEOUT = 10;
	static Workbook book;
	static Sheet sheet;
	static WebDriver driver;
	/*
	 * public static String TESTDATA_SHEET_PATH =
	 * "I:\\All eclipse Code7\\Front_Accounting\\" +
	 * "src\\main\\java\\com\\test\\testdata\\Front_Accounting_XLData.xlsx";
	 */

	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream("I:\\All eclipse Code7\\Front_Accounting\\"
					+ "src\\main\\java\\com\\test\\testdata\\Front_Accounting_XLData.xlsx");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		// System.out.println(sheet.getLastRowNum() + "--------" +
		// sheet.getRow(0).getLastCellNum());
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				// System.out.println(data[i][k]);
			}
		}
		return data;
	}

	// ------------Brokenlink Code-----------
	public static void brokenLinkCount() {
		{
			

			List<WebElement> links = driver.findElements(By.tagName("a"));

			System.out.println("Total links are " + links.size());

			for (int i = 0; i < links.size(); i++) {

				WebElement ele = links.get(i);

				String url = ele.getAttribute("href");

				verifyLinkActive(url);

			}
		}
	}

	public static void verifyLinkActive(String linkUrl) {
		try {
			URL url = new URL(linkUrl);

			HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection();

			httpURLConnect.setConnectTimeout(3000);

			httpURLConnect.connect();

			if (httpURLConnect.getResponseCode() == 200) {
				System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage());
			}
			if (httpURLConnect.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) {
				System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage() + " - "
						+ HttpURLConnection.HTTP_NOT_FOUND);
			}
		} catch (Exception e) {

		}
	}

	// ------------Brokenlink Code-----------

}
