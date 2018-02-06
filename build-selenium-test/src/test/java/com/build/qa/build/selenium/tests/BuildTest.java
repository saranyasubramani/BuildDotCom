package com.build.qa.build.selenium.tests;

import org.junit.Test;

import com.build.qa.build.selenium.framework.BaseFramework;
import com.build.qa.build.selenium.pageobjects.homepage.BathroomSinkFaucetsPage;
import com.build.qa.build.selenium.pageobjects.homepage.BathroomSinkPage;
import com.build.qa.build.selenium.pageobjects.homepage.CartPage;
import com.build.qa.build.selenium.pageobjects.homepage.HomePage;
import com.build.qa.build.selenium.pageobjects.homepage.ProductAddedToCartPage;
import com.build.qa.build.selenium.pageobjects.homepage.ProductPage;

import org.junit.Assert;

public class BuildTest extends BaseFramework { 
	
	HomePage homePage;
	BathroomSinkPage bathroomSinkPage;
	ProductPage productPage;
	ProductAddedToCartPage productAddedToCartPage;
	CartPage cartPage;
	BathroomSinkFaucetsPage bathroomSinkFaucetsPage;
	/** 
	 * Extremely basic test that outlines some basic
	 * functionality and page objects as well as assertJ
	 */
	@Test
	public void navigateToHomePage() { 
		driver.get(getConfiguration("HOMEPAGE"));
		homePage = new HomePage(driver, wait);
		
		softly.assertThat(homePage.onBuildTheme())
			.as("The website should load up with the Build.com desktop theme.")
			.isTrue();
	}
	
	/** 
	 * Search for the Quoizel MY1613 from the search bar
	 * @assert: That the product page we land on is what is expected by checking the product title
	 * @difficulty Easy
	 */
	@Test
	public void searchForProductLandsOnCorrectProduct() { 
		
		driver.get(getConfiguration("HOMEPAGE"));
		
		// Maximize the window
		driver.manage().window().maximize();
		homePage = new HomePage(driver, wait);
		homePage.clickCloseEmailAddressPopUp();
		
		//type productId and click Search Button
		homePage.typeSearchText(getConfiguration("TC_1_productId"));
		productPage=homePage.clickSearchButton();
		String productHeading=productPage.getHeading();
		
		//Verify That the product page we land on is what is expected by checking the product title
		Assert.assertTrue("Expected Heading was: "+getConfiguration("TC_1_productId")+" but actual Heading was: "+productHeading,productHeading.trim().contains(getConfiguration("TC_1_productId")));		
	}
	
	/** 
	 * Go to the Bathroom Sinks category directly (https://www.build.com/bathroom-sinks/c108504) 
	 * and add the second product on the search results (Category Drop) page to the cart.
	 * @assert: the product that is added to the cart is what is expected
	 * @difficulty Easy-Medium
	 */
	@Test
	public void addProductToCartFromCategoryDrop() { 
		
		driver.get(getConfiguration("BATHROOMSINKPAGE"));
		
		// Maximize the window
		driver.manage().window().maximize();
		bathroomSinkPage = new BathroomSinkPage(driver, wait);
		bathroomSinkPage.clickCloseEmailAddressPopUp();
		String productTitle=bathroomSinkPage.getProductTitle(2);
		
		//Click on the 2nd Product 
		productPage=bathroomSinkPage.clickProductTitle(2);
		String productHeading=productPage.getHeading();
		
		//Verify That the product page we land on is what is expected by checking the product title
		Assert.assertTrue("Expected Heading was: "+productTitle+" but actual Heading was: "+productHeading,productHeading.trim().contains(productTitle));
		
		//Add the Product to Cart
		productAddedToCartPage=productPage.clickAddToCart();
		
		//Go to Cart
		cartPage=productAddedToCartPage.clickProceedToCart();
		
		//Verify the product that is added to the cart is what is expected
		Assert.assertTrue("Expected Product is not Present in the Cart", cartPage.isProductPresent(productTitle));	
	}
	
	/** 
	 * Add a product to the cart and email the cart to yourself, also to my email address: jgilmore+SeleniumTest@build.com
	 * Include this message in the "message field" of the email form: "This is {yourName}, sending you a cart from my automation!"
	 * @assert that the "Cart Sent" success message is displayed after emailing the cart
	 * @difficulty Medium-Hard
	 */
	@Test
	public void addProductToCartAndEmailIt() { 
		driver.get(getConfiguration("BATHROOMSINKPAGE"));
		// Maximize the window
		driver.manage().window().maximize();
		bathroomSinkPage = new BathroomSinkPage(driver, wait);
		bathroomSinkPage.clickCloseEmailAddressPopUp();
		String productTitle=bathroomSinkPage.getProductTitle(2);
		
		//Click on the 2nd Product 
		productPage=bathroomSinkPage.clickProductTitle(2);
		String productHeading=productPage.getHeading();
		
		//Verify That the product page we land on is what is expected by checking the product title
		Assert.assertTrue("Expected Heading was: "+productTitle+" but actual Heading was: "+productHeading,productHeading.trim().contains(productTitle));
		
		//Add the Product to Cart
		productAddedToCartPage=productPage.clickAddToCart();
		
		//Go to Cart
		cartPage=productAddedToCartPage.clickProceedToCart();
		
		//Verify the product that is added to the cart is what is expected
		Assert.assertTrue("Expected Product is not Present in the Cart", cartPage.isProductPresent(productTitle));
		
		//Click Email Cart Button
		cartPage.clickEmailCart();
		
		//Enter the required information
		cartPage.enterYourName(getConfiguration("TC_3_yourName"));
		cartPage.enterYourEmail(getConfiguration("TC_3_yourEmail"));
		cartPage.enterRecipientName(getConfiguration("TC_3_recipientName"));
		cartPage.enterRecipientEmail(getConfiguration("TC_3_recipientEmail"));
		cartPage.enterEmailMessage(getConfiguration("TC_3_quoteMessage"));
		
		//Click Submit Email Cart
		cartPage.clickSubmitEmailCart();
		cartPage=new CartPage(driver, wait);
		String notification=cartPage.getNotificationText();
		
		//Verify that the "Cart Sent" success message is displayed after emailing the cart
		Assert.assertTrue("Expected Product is not Present in the Cart", notification.contains("Cart Sent! The cart has been submitted to the recipient via email."));
	}
	
	/** 
	 * Go to a category drop page (such as Bathroom Faucets) and narrow by
	 * at least two filters (facets), e.g: Finish=Chromes and Theme=Modern
	 * @assert that the correct filters are being narrowed, and the result count
	 * is correct, such that each facet selection is narrowing the product count.
	 * @difficulty Hard
	 */
	@Test
	public void facetNarrowBysResultInCorrectProductCounts() {
		
		driver.get(getConfiguration("BATHROOMSINKFAUCETPAGE"));
		// Maximize the window
		driver.manage().window().maximize();
		bathroomSinkFaucetsPage = new BathroomSinkFaucetsPage(driver, wait);
		bathroomSinkFaucetsPage=bathroomSinkFaucetsPage.clickCloseEmailAddressPopUp();
		
		//Filter the bathroomsink faucets using proper filter option
		String initialCount=bathroomSinkFaucetsPage.getFilterCount(getConfiguration("TC_4_filterGroup1"), getConfiguration("TC_4_filterValue1"));
		bathroomSinkFaucetsPage=bathroomSinkFaucetsPage.selectFilter(getConfiguration("TC_4_filterGroup1"), getConfiguration("TC_4_filterValue1"));
		
		//Verify if the product count is correct
		Assert.assertTrue("Total Number of Products filtered is not correct", initialCount.equals(bathroomSinkFaucetsPage.getTotalNumOfProductsOnPage()));;
		
		//verify if the filter is Narrowed by proper filter option
		Assert.assertTrue("Filter is not Narrowed by Chromes", bathroomSinkFaucetsPage.isFilterNarrowed(getConfiguration("TC_4_filterValue1")));
		
		//Filter the bathroomsink faucets using 2nd filter option
		initialCount=bathroomSinkFaucetsPage.getFilterCount(getConfiguration("TC_4_filterGroup2"), getConfiguration("TC_4_filterValue2"));
		bathroomSinkFaucetsPage=bathroomSinkFaucetsPage.selectFilter(getConfiguration("TC_4_filterGroup2"), getConfiguration("TC_4_filterValue2"));
		
		//Verify if the product count is correct after selecting second filter option
		Assert.assertTrue("Total Number of Products filtered is not correct", initialCount.equals(bathroomSinkFaucetsPage.getTotalNumOfProductsOnPage()));;
		
		//verify if the filter is Narrowed by proper filter option
		Assert.assertTrue("Filter is not Narrowed by Chromes", bathroomSinkFaucetsPage.isFilterNarrowed(getConfiguration("TC_4_filterValue1")));
		Assert.assertTrue("Filter is not Narrowed by Chromes", bathroomSinkFaucetsPage.isFilterNarrowed(getConfiguration("TC_4_filterValue2")));
				
	}
}
