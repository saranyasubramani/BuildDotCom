package com.build.qa.build.selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import com.build.qa.build.selenium.framework.BaseFramework;
import com.build.qa.build.selenium.pageobjects.homepage.BathroomSinkFaucetsPage;

public abstract class BasePage extends BaseFramework {
	
	private By btnClosePopUp=By.cssSelector("div[id='email-subscribe-splash']>div>div>div[class='modal-header table modal-no-title']>button[class='close js-modal-close ']");
	
	
	public BasePage(WebDriver driver, Wait<WebDriver> wait) { 
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * close the email AddressPopup
	 */
	public BathroomSinkFaucetsPage clickCloseEmailAddressPopUp() {
		wait.until(ExpectedConditions.presenceOfElementLocated(btnClosePopUp));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(btnClosePopUp)));
		driver.findElement(btnClosePopUp).click();
		return new BathroomSinkFaucetsPage(driver,wait);

	}
}
