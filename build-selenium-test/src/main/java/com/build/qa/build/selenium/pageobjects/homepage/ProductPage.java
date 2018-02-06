package com.build.qa.build.selenium.pageobjects.homepage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import com.build.qa.build.selenium.pageobjects.BasePage;

public class ProductPage extends BasePage {
	
	private By buildThemeBody;
	private By lblHeading=By.cssSelector("h1[id='heading']");
	private By btnAddToCart=By.cssSelector("button[class*='add-to-cart']");
		
	public ProductPage(WebDriver driver, Wait<WebDriver> wait) {
		super(driver, wait);
		buildThemeBody = By.cssSelector("body.build-theme");
	}
	
	public boolean onBuildTheme() { 
		return wait.until(ExpectedConditions.presenceOfElementLocated(buildThemeBody)) != null;
	}
	
	/** 
	 * Get Product Heading
	 */
	public String getHeading() {
		String heading=driver.findElement(lblHeading).getText().toString();
		return heading;
	}
	
	/** 
	 * Click Add to Cart Button
	 */
	public ProductAddedToCartPage clickAddToCart() {
		driver.findElement(btnAddToCart).click();
		return new ProductAddedToCartPage(driver, wait);
		
	}
	
}
