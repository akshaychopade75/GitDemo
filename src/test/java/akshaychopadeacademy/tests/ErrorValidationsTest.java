package akshaychopadeacademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import akshaychopadeacademy.TestComponents.BaseTest;
import akshaychopadeacademy.TestComponents.Retry;
import akshaychopadeacademy.pageobjects.CartPage;
import akshaychopadeacademy.pageobjects.CheckoutPage;
import akshaychopadeacademy.pageobjects.ConfirmationPage;
import akshaychopadeacademy.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest{
	
	@Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void LoginErrorValidations() {
		lpage.loginApplication("akkhop@gmail.com", "Akkhop@98");
		String actualErrorMessage=lpage.getErrorMessage();
		
		Assert.assertEquals("Incorrect email  password.", actualErrorMessage);
		
	}
	
	@Test
	public void ProductErrorValidation() throws IOException {

		String productName="ZARA COAT 3";
		
		ProductCatalogue productCatalogue=lpage.loginApplication("anshika@gmail.com", "Iamking@000");
		List<WebElement>products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage=productCatalogue.goToCartPage();
		Boolean match=cartPage.verifyProductDisplay("ZARA COAT 33333333333333333");
		Assert.assertFalse(match);
	}
	

}
