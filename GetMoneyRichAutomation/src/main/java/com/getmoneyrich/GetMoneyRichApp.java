package com.getmoneyrich;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GetMoneyRichApp {

	static String paginationVal;

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver = new ChromeDriver();
		driver.get("https://apps.getmoneyrich.com/login/");
		driver.manage().window().maximize();
		driver.findElement(By.id("id_username")).sendKeys("");
		driver.findElement(By.id("id_password")).sendKeys("");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.get("https://apps.getmoneyrich.com/products/products-screeners");
		driver.get("https://apps.getmoneyrich.com/stock-screeners/screener-dashboard");

		/*
		 * https://apps.getmoneyrich.com/stock-screeners/low-pe
		 * https://apps.getmoneyrich.com/stock-screeners/monopoly-stocks
		 * https://apps.getmoneyrich.com/stock-screeners/most-recommended-stocks
		 * https://apps.getmoneyrich.com/stock-screeners/profitable-growing-stocks
		 * https://apps.getmoneyrich.com/stock-screeners/best-stocks
		 * https://apps.getmoneyrich.com/stock-screeners/penny-stocks
		 * https://apps.getmoneyrich.com/stock-screeners/profitable-stocks
		 * https://apps.getmoneyrich.com/stock-screeners/high-return-stocks
		 * https://apps.getmoneyrich.com/stock-screeners/warren-buffet-stocks
		 * https://apps.getmoneyrich.com/stock-screeners/blue-chip-stocks
		 * https://apps.getmoneyrich.com/stock-screeners/debt-free-stocks
		 * https://apps.getmoneyrich.com/stock-screeners/fundamentally-strong-stocks
		 * https://apps.getmoneyrich.com/stock-screeners/undervalued-stocks
		 * https://apps.getmoneyrich.com/stock-screeners/fastest-growing-stocks
		 * https://apps.getmoneyrich.com/stock-screeners/premium-stocks
		 * https://apps.getmoneyrich.com/stock-screeners/high-roe-roic-stocks
		 * https://apps.getmoneyrich.com/stock-screeners/dividend-stocks
		 * https://apps.getmoneyrich.com/stock-screeners/12-parameters
		 * https://apps.getmoneyrich.com/stock-screeners/overall-score-break-up-of-
		 * stocks https://apps.getmoneyrich.com/stock-screeners/bank-stocks
		 */

		// Main
		// driver.get("https://apps.getmoneyrich.com/stock-screeners/undervalued-stocks");
		// driver.get("https://apps.getmoneyrich.com/stock-screeners/profitable-growing-stocks");
		driver.get("https://apps.getmoneyrich.com/stock-screeners/blue-chip-stocks");

		WebElement paginationCNT = driver.findElement(By.id("basic-datatables_paginate"));

		List<WebElement> lis = paginationCNT.findElements(By.tagName("li"));
		List<String> linkURL = new ArrayList<>();

		// Loop through the list and get the text values of each <li> element
		for (WebElement li : lis) {
			String value = li.getText();
			if (!value.equals("Next")) {
				paginationVal = value;
			}
		}

		for (int loop = 1; loop <= Integer.valueOf(paginationVal); loop++) {

			// Find all the links in the table
			List<WebElement> links = driver.findElements(By.cssSelector("table.table tbody tr td a"));

			// Loop through the links and print their <a href=""> attributes
			for (WebElement link : links) {
				String url = link.getAttribute("href");
				linkURL.add(url);
			}

			WebElement nextPage = driver.findElement(By.id("basic-datatables_next"));

			// Click on the next page button
			nextPage.click();
		}
		System.out.println("STOCK_NAME" + "^" + "SECTOR" + "^" + "STATUS" + "^" + "STOCK_PRICE" + "^"
				+ "STOCK_INTRINSIC_VALUE" + "^" + "BUY");

		String page_title_val = "";
		String sector_val = "";
		String status_val = "";
		String stockprice_val = "";
		String intrinsicVal_val = "";
		String ok2Buy_val = "";

		// List<String> l = linkURL.subList(0, linkURL.size());

		for (int loop = 0; loop <= linkURL.size() - 1; loop++) {
			driver.get(linkURL.get(loop));

			// Find all the links in the table
			try {
				WebElement page_title = driver.findElement(By.className("page-title"));
				page_title_val = page_title.getText();
			} catch (org.openqa.selenium.NoSuchElementException e1) {
				page_title_val = "";
			}
			try {
				WebElement stockprice = driver.findElement(By.cssSelector("div.pull-right > h1[title='Stock Price']"));
				stockprice_val = stockprice.getText();
			} catch (org.openqa.selenium.NoSuchElementException e1) {
				stockprice_val = "";
			}

			try {
				WebElement intrinsicVal = driver
						.findElement(By.cssSelector("div.pull-right > h1[title='Intrinsic Value of Stock']"));
				intrinsicVal_val = intrinsicVal.getText();
			} catch (org.openqa.selenium.NoSuchElementException e1) {
				intrinsicVal_val = "";
			}

			try {
				WebElement status = driver
						.findElement(By.cssSelector("div.pull-right > h3[title='Over or Undervalued indicator']"));
				status_val = status.getText();
			} catch (org.openqa.selenium.NoSuchElementException e1) {
				status_val = "";
			}

			try {
				WebElement sector = driver.findElement(By.xpath("//div[@class='row']/div/div/div/h4"));
				sector_val = sector.getText();
			} catch (org.openqa.selenium.NoSuchElementException e1) {
				sector_val = "";
			}

			WebElement ok2Buy = null;
			try {
				ok2Buy = driver.findElement(By.xpath("//div[@class='page-inner']/h4/mark-green"));
				ok2Buy_val = ok2Buy.getText();
			} catch (org.openqa.selenium.NoSuchElementException e) {
				try {
					ok2Buy = driver.findElement(By.xpath("//div[@class='page-inner']/h4/mark-orange"));
					ok2Buy_val = ok2Buy.getText();
				} catch (org.openqa.selenium.NoSuchElementException e1) {
					try {
						ok2Buy = driver.findElement(By.xpath("//div[@class='page-inner']/h4/mark-yellow"));
						ok2Buy_val = ok2Buy.getText();
					} catch (org.openqa.selenium.NoSuchElementException e2) {
						ok2Buy_val = "";
					}
				}
			}

			System.out.println(page_title_val + "^" + sector_val + "^" + status_val + "^" + stockprice_val + "^"
					+ intrinsicVal_val + "^" + ok2Buy_val);
		}
		driver.close();
	}

}
