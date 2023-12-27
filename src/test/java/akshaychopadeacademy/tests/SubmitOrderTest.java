package akshaychopadeacademy.tests;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import akshaychopadeacademy.TestComponents.BaseTest;
import akshaychopadeacademy.pageobjects.CartPage;
import akshaychopadeacademy.pageobjects.CheckoutPage;
import akshaychopadeacademy.pageobjects.ConfirmationPage;
import akshaychopadeacademy.pageobjects.LandingPage;
import akshaychopadeacademy.pageobjects.OrderPage;
import akshaychopadeacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	
	//String productName="ZARA COAT 3";
	
	@Test(dataProvider="getData", groups= {"purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException {

		ProductCatalogue productCatalogue=lpage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement>products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage=productCatalogue.goToCartPage();
		Boolean match=cartPage.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage=cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationPage=checkoutPage.submitOrder();
		String confirmedMessage=confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmedMessage.equals("THANKYOU FOR THE ORDER."));
	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest() {
		
		ProductCatalogue productCatalogue=lpage.loginApplication("akkichop@gmail.com", "Akkichop@98");
		OrderPage orderPage=productCatalogue.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay("zara coat 3"));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		//return new Object[][] {{"akkichop@gmail.com", "Akkichop@98","ZARA COAT 3"},{"anshika@gmail.com", "Iamking@000","ADIDAS ORIGINAL"}};
		/*
		HashMap<String,String> hashmap=new HashMap<String,String>();
		hashmap.put("email", "akkichop@gmail.com");
		hashmap.put("password", "Akkichop@98");
		hashmap.put("productName", "ZARA COAT 3");
		
		HashMap<String,String> hashmap1=new HashMap<String,String>();
		hashmap1.put("email", "anshika@gmail.com");
		hashmap1.put("password", "Iamking@000");
		hashmap1.put("productName", "ADIDAS ORIGINAL");
		*/
		
		List<HashMap<String,String>> data= getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//akshaychopadeacademy//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
	}

}
