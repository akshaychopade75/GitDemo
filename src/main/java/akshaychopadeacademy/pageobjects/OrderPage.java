package akshaychopadeacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPage {
	
	WebDriver driver;
	public OrderPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> orderProducts;
	
	public Boolean verifyOrderDisplay(String productName) {
		Boolean match= orderProducts.stream().anyMatch(cartproduct->cartproduct.getText().equals(productName));
		return match;
	}
	
	
	

}
