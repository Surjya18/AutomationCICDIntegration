package YSK;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents{
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".offset-md-0")
	List<WebElement> products;
	
	@FindBy(css=".ngx-spinner-overlay")
	WebElement spinner;
	
	
	By productBy = By.cssSelector(".offset-md-0");
	By toast = By.id("toast-container");
	
	public List<WebElement> getProductList()
	{
		waitforproducttoappear(productBy);
		return products;
	}
	
	public WebElement getProductByName(String p2)
	{
		WebElement item = products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(p2)).findFirst().orElse(null);
		return item;
	}
	
	public void addProductToCart(String p2) throws InterruptedException
	{
		WebElement item = getProductByName(p2);
		item.findElement(By.cssSelector(".btn.w-10.rounded")).click();
		Thread.sleep(2000);
//		waitforinvisibilityofelement(toast);
//		waitforinvisibility(spinner);
		
	}
	

}
