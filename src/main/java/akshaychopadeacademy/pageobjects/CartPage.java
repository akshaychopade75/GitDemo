package akshaychopadeacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
	
	WebDriver driver;
	public CartPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutBtn;
	
	public Boolean verifyProductDisplay(String productName) {
		Boolean match= cartProducts.stream().anyMatch(cartproduct->cartproduct.getText().equals(productName));
		return match;
	}
	
	public CheckoutPage goToCheckout() {
		checkoutBtn.click();
		return new CheckoutPage(driver);
	}
	

}
