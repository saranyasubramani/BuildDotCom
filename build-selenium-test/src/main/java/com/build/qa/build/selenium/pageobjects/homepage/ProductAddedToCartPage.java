package com.build.qa.build.selenium.pageobjects.homepage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import com.build.qa.build.selenium.pageobjects.BasePage;

public class ProductAddedToCartPage extends BasePage {
	
	private By buildThemeBody;
	private By linkProductTitle=By.cssSelector("div[class='product-added-box']>a>p>strong");
	private By btnProceedToCart=By.cssSelector("a[class*='js-proceed-to-cart']");
		
	public ProductAddedToCartPage(WebDriver driver, Wait<WebDriver> wait) {
		super(driver, wait);
		buildThemeBody = By.cssSelector("body.build-theme");
	}
	
	public boolean onBuildTheme() { 
		return wait.until(ExpectedConditions.presenceOfElementLocated(buildThemeBody)) != null;
	}
		
	/** 
	 * Get the title of Product
	 */
	public String getProductTitle() {
		String heading=driver.findElement(linkProductTitle).getText().toString();
		return heading;
	}
	
	/** 
	 * Click on Proceed to Cart Button
	 */
	public CartPage clickProceedToCart() {
		driver.findElement(btnProceedToCart).click();
		return new CartPage(driver, wait);
		
	}
	
}
