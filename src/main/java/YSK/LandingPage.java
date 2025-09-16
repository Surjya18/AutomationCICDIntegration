package YSK;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {
	
	WebDriver driver;
	
	//constructor
	public LandingPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory
	//WebElement emailID = driver.findElement(By.id("userEmail"));
	@FindBy(id="userEmail")
	WebElement UserID;
	
	@FindBy(id="userPassword")
	WebElement passwordcode;
	
	@FindBy(id="login")
	WebElement sigin;
	
	@FindBy(css="div[aria-label='Incorrect email or password.']")
	WebElement errormessage;
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public ProductCatalogue loginApplication(String emailID, String password)
	{
		UserID.sendKeys(emailID);
		passwordcode.sendKeys(password);
		sigin.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;			
	}
	public String geterrorMessage()
	{
		waitforvisibilityelement(errormessage);
		return errormessage.getText();
				}

}
