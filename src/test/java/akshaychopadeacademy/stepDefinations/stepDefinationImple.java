package akshaychopadeacademy.stepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import akshaychopadeacademy.TestComponents.BaseTest;
import akshaychopadeacademy.pageobjects.CartPage;
import akshaychopadeacademy.pageobjects.CheckoutPage;
import akshaychopadeacademy.pageobjects.ConfirmationPage;
import akshaychopadeacademy.pageobjects.LandingPage;
import akshaychopadeacademy.pageobjects.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefinationImple extends BaseTest {
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingPage=launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password) {
		productCatalogue=landingPage.loginApplication(username, password);
		
	}
	
	@When("^I add product (.+) to cart$")
	public void i_add_product_to_cart(String productName) {
		List<WebElement>products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	
	@When("^checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName) {
		CartPage cartPage=productCatalogue.goToCartPage();
		Boolean match=cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage=cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		confirmationPage=checkoutPage.submitOrder();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_confirmationPage(String string) {
		String confirmedMessage=confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmedMessage.equals(string));
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void error_message_is_displayed(String string1) {
		Assert.assertEquals(string1, lpage.getErrorMessage());
		driver.close();
	}

}
