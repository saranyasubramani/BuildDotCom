package com.build.qa.build.selenium.pageobjects.homepage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import com.build.qa.build.selenium.pageobjects.BasePage;

public class BathroomSinkFaucetsPage extends BasePage {
	
	private By buildThemeBody;
	private String lblProductTitle="ul[id='category-product-drop']>li:nth-child(%s)>div[class='product-tile']>a>*>div[class*='product-title']";
	private String chkboxFilter="li[data-groupname='%s']>ul>li>label[data-facet-value='%s']>input";	
	private String lblFilterCount="li[data-groupname='%s']>ul>li>label[data-facet-value='%s']>span";
	private By lblTotalNumOfProductsOnPage=By.cssSelector("span[class='js-num-results']");
	private By lblNorrowedFilters=By.cssSelector("div[class='limit-facet-item']>span:nth-child(2)");
	
	public BathroomSinkFaucetsPage(WebDriver driver, Wait<WebDriver> wait) {
		super(driver, wait);
		buildThemeBody = By.cssSelector("body.build-theme");
	}
	
	public boolean onBuildTheme() { 
		return wait.until(ExpectedConditions.presenceOfElementLocated(buildThemeBody)) != null;
	}
	
	/** 
	 * get the Title of given nth Product
	 * return title of given nth Product 
	 */
	public String getProductTitle(int nthProduct) {
		String productTitle=driver.findElement(By.cssSelector(String.format(lblProductTitle, nthProduct))).getText();//driver.findElement(lblHeading).getText().toString();
		return productTitle;
	}
	
	/** 
	 * Click on Title of nth Product
	 * return ProductPage
	 */
	public ProductPage clickProductTitle(int nthProduct) {
		driver.findElement(By.cssSelector(String.format(lblProductTitle, nthProduct))).click();
		return new ProductPage(driver, wait);
	}
	
	/** 
	 * Select the checkbox of given filter
	 * return BathroomSinkFaucetsPage
	 */	
	public BathroomSinkFaucetsPage selectFilter(String group, String value) {
		driver.findElement(By.cssSelector(String.format(chkboxFilter,group,value))).click();
		return new BathroomSinkFaucetsPage(driver, wait);
	}
	/** 
	 * Get the count of product under a given filter value
	 * return count
	 */
	public String getFilterCount(String group, String value) {
		String count=driver.findElement(By.cssSelector(String.format(lblFilterCount,group,value))).getText();
		count=count.replace('(', ' ').replace(')', ' ').trim();
		return count;
	}
	
	/** 
	 * Get total number of products on the page
	 * return total 
	 */	
	public String getTotalNumOfProductsOnPage() {
		wait.until(ExpectedConditions.presenceOfElementLocated(lblTotalNumOfProductsOnPage));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(lblTotalNumOfProductsOnPage)));
		String totalNumOfProductsOnPage=driver.findElement(lblTotalNumOfProductsOnPage).getText();
		totalNumOfProductsOnPage=totalNumOfProductsOnPage.replace("Products", "").replace(",", "").trim();
		return totalNumOfProductsOnPage;
		
	}
	
	/** 
	 * check if the Products are narrowed according to the selected Filter
	 * return true or false
	 */
	public boolean isFilterNarrowed(String filterNarrowed) {
		List<WebElement> allOptions = driver.findElements(lblNorrowedFilters);
	    for ( WebElement eachFilterNarrowed: allOptions) { 
	    	
	      if ( eachFilterNarrowed.getText().contains( filterNarrowed ) ) 
	    	  return true;
	    }
	    return false;
	}
	
	
}
