package YSK;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPage {
	WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(css=".ng-star-inserted td:nth-child(3)")
	List<WebElement> orderLists;
	
	public Boolean verifyOrderList(String p2)
	{
		Boolean match = orderLists.stream().anyMatch(cartlist->cartlist.getText().equalsIgnoreCase(p2));
		return match;
	}

}
