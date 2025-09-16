package YSK;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Cart {
	WebDriver driver;
	
	public Cart(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(css=".cartSection h3")
	List<WebElement> cartLists;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutbutton;
	
	public Boolean verifyCartList(String p2)
	{
		Boolean match = cartLists.stream().anyMatch(cartlist->cartlist.getText().equalsIgnoreCase(p2));
		return match;
	}
	public CheckoutPage checkout()
	{
		Actions a = new Actions(driver);
		a.moveToElement(checkoutbutton).click().build().perform();
		return  new CheckoutPage(driver);
	}

	//driver.findElement(By.cssSelector(".totalRow button"))
}
