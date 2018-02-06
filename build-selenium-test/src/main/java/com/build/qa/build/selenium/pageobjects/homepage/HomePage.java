package com.build.qa.build.selenium.pageobjects.homepage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import com.build.qa.build.selenium.pageobjects.BasePage;

public class HomePage extends BasePage {
	
	private By buildThemeBody;
	private By txtSearch=By.id("search_txt");
	private By btnSearch=By.className("search-site-search");
	
	public HomePage(WebDriver driver, Wait<WebDriver> wait) {
		super(driver, wait);
		buildThemeBody = By.cssSelector("body.build-theme");
	}
	
	public boolean onBuildTheme() { 
		return wait.until(ExpectedConditions.presenceOfElementLocated(buildThemeBody)) != null;
	}
	
	/** 
	 * Enter the Product to search 
	 */
	public void typeSearchText(String searchText) {
		driver.findElement(txtSearch).sendKeys(searchText);
	}
	
	/** 
	 * Click Search Button
	 */
	public ProductPage clickSearchButton() {
		driver.findElement(btnSearch).click();
		return new ProductPage(driver, wait);
	}
	
}
