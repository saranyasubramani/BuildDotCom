package com.build.qa.build.selenium.pageobjects.homepage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import com.build.qa.build.selenium.pageobjects.BasePage;

public class BathroomSinkPage extends BasePage {
	
	private By buildThemeBody;
	private String lblProductTitle="ul[id='category-product-drop']>li:nth-child(%s)>div[class='product-tile']>a>*>div[class*='product-title']";
	private String lblProductCategory="a[href*='%s']>div>h5";
	//private By lblProductTitle=By.cssSelector("div[class='product-title']:nth-child('%s')");
		
	public BathroomSinkPage(WebDriver driver, Wait<WebDriver> wait) {
		super(driver, wait);
		buildThemeBody = By.cssSelector("body.build-theme");
	}
	
	public boolean onBuildTheme() { 
		return wait.until(ExpectedConditions.presenceOfElementLocated(buildThemeBody)) != null;
	}
	
	/** 
	 * Get the Title of nth Product 
	 * return product Title
	 */
	public String getProductTitle(int nthProduct) {
		String productTitle=driver.findElement(By.cssSelector(String.format(lblProductTitle, nthProduct))).getText();//driver.findElement(lblHeading).getText().toString();
		return productTitle;
	}
	
	/** 
	 * Click the nth Product Title
	 * return productPage
	 */
	public ProductPage clickProductTitle(int nthProduct) {
		driver.findElement(By.cssSelector(String.format(lblProductTitle, nthProduct))).click();
		return new ProductPage(driver, wait);
	}
	
}
