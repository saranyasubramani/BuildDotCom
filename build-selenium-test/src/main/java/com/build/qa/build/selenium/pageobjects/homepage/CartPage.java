package com.build.qa.build.selenium.pageobjects.homepage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import com.build.qa.build.selenium.pageobjects.BasePage;

public class CartPage extends BasePage {
	
	private By buildThemeBody;
	private By btnCart=By.cssSelector("button[class*='js-cart-button']");
	private By linkProductTitle=By.cssSelector("td[class='product-description']>a");
	private By btnEmailCart=By.cssSelector("button[class*='btn-email']");
	private By txtYourName=By.cssSelector("input[id='yourName']");
	private By txtYourEmail=By.cssSelector("input[id='yourEmail']");
	private By txtRecipientName=By.cssSelector("input[id='recipientName']");
	private By txtRecipientEmail=By.cssSelector("input[id='recipientEmail']");
	private By txtQuoteMessage=By.cssSelector("textarea[id='quoteMessage']");
	private By btnSubmitEmailCart=By.cssSelector("button[class*='js-email-cart-submit-button']");
	private By lblNotifications=By.cssSelector("div[class*='notifications']>ul>li");
	private By emailCart=By.cssSelector("h4[id='modal-label-cart-email']");
	
		
	public CartPage(WebDriver driver, Wait<WebDriver> wait) {
		super(driver, wait);
		buildThemeBody = By.cssSelector("body.build-theme");
	}
	
	public boolean onBuildTheme() { 
		return wait.until(ExpectedConditions.presenceOfElementLocated(buildThemeBody)) != null;
	}
	 
	/** 
	 * check if given Product is present in the Cart
	 * return true of false
	 */
	public boolean isProductPresent(String productTitle) {
		List<WebElement> allOptions = driver.findElements(linkProductTitle);
	    for ( WebElement eachProductTitle: allOptions) { 
	    	
	      if ( eachProductTitle.getText().contains( productTitle ) ) 
	    	  return true;
	    }
	    return false;
	}
	
	/** 
	 * Click the Email Cart Button
	 */
	public void clickEmailCart() {
		driver.findElement(btnEmailCart).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(emailCart)));
	}
	
	/** 
	 * Enter your Name for Email Cart
	 */
	public void enterYourName(String yourName) {
		driver.findElement(txtYourName).sendKeys(yourName);
	}
	/** 
	 * Enter your Email for Email Cart
	 */
	public void enterRecipientName(String RecipientName) {
		driver.findElement(txtRecipientName).sendKeys(RecipientName);
	}
	
	/** 
	 * Enter Recipient Name for Email Cart
	 */
	public void enterYourEmail(String yourEmail) {
		driver.findElement(txtYourEmail).sendKeys(yourEmail);
	}
	
	/** 
	 * Enter Recipient Email for Email Cart
	 */
	public void enterRecipientEmail(String recipientEmail) {
		driver.findElement(txtRecipientEmail).sendKeys(recipientEmail);
	}
	
	/** 
	 * Enter Message for Email Cart
	 */
	public void enterEmailMessage(String QuoteMessage) {
		driver.findElement(txtQuoteMessage).sendKeys(QuoteMessage);
	}
	
	/** 
	 * Click on Submit email Cart
	 */
	public void clickSubmitEmailCart() {
		driver.findElement(btnSubmitEmailCart).click();
	}
	
	/** 
	 * Get Notification Message
	 * return notification Title
	 */
	public String getNotificationText() {
		wait.until(ExpectedConditions.presenceOfElementLocated(lblNotifications));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(lblNotifications)));
		String notification=driver.findElement(lblNotifications).getText();
		return notification;
	}
}
