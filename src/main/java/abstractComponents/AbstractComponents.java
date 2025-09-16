package abstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import YSK.Cart;
import YSK.OrderPage;

public class AbstractComponents {
	WebDriver driver;
	

	public AbstractComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".btn.btn-custom i.fa-shopping-cart")
	WebElement cartButton;
	
	@FindBy(css="[routerlink='/dashboard/myorders']")
	WebElement OrderButton;

	public Cart goToCart() {
		cartButton.click();
		Cart cart = new Cart(driver);
		return cart;
	}	
	public OrderPage goToOrder() {
		OrderButton.click();
		OrderPage orderpage = new OrderPage(driver);
		return orderpage;
	}
	
	public void waitforproducttoappear(By findby)
	{
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(findby));
	}
	
	public void waitforvisibility(By findby)
	{
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
	}
	
	public void waitforvisibilityelement(WebElement ele)
	{
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public void waitforinvisibilityofelement(By findby)
	{
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.invisibilityOfElementLocated(findby));
	}
	
	public void waitforinvisibility(WebElement element)
	{
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.invisibilityOf(element));
	}

}
