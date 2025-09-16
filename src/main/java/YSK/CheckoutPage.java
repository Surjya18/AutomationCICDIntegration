package YSK;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import abstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents {
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}

	By countryList = By.cssSelector(".ta-results ");
	By country1 = By.xpath("//input[@placeholder='Select Country']");
	
	@FindBy(css=".list-group-item")
	List<WebElement> selectCountry;
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css=".action__submit")
	WebElement submitOrder;
	
	public void checkout(String countryname)
	{
		waitforvisibility(country1);
		Actions a = new Actions(driver);
		a.moveToElement(country).click().sendKeys(countryname).build().perform();
		waitforvisibility(countryList);
		selectCountry.stream().filter(e -> e.getText().equalsIgnoreCase("India")).findFirst().ifPresent(WebElement::click);
	}
	public ConfirmationPage submitOrder()
	{
		submitOrder.click();
		return new ConfirmationPage(driver);
	}
}
