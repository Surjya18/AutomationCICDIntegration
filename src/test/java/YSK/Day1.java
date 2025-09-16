package YSK;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Day1 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		String p1 ="ZARA COAT 3";
		String p2 ="iphone 13 pro";
//		List<String> expecteditems = Arrays.asList(p1,p2);
		
		driver.get("https://rahulshettyacademy.com/client");
		//Create an object for contructor	
		LandingPage landingPage = new LandingPage(driver);
		
	
		driver.findElement(By.id("userEmail")).sendKeys("ysk@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Samsung525$");
		driver.findElement(By.id("login")).click();
		
		List <WebElement> products = driver.findElements(By.cssSelector(".offset-md-0"));
		
		for(WebElement product:products)
		{
			String productList = product.getText();
			if(productList.contains(p1))
			{
				product.findElement(By.cssSelector(".btn.w-10.rounded")).click();
			}
		}
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("toast-container")));
		
		WebElement item = products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(p2)).findFirst().orElse(null);
		item.findElement(By.cssSelector(".btn.w-10.rounded")).click();
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ngx-spinner-overlay"))));
		Thread.sleep(3000);
		driver.findElement(By.cssSelector(".btn.btn-custom i.fa-shopping-cart")).click();
		
		List<WebElement> cartLists = driver.findElements(By.cssSelector(".cartSection h3"));
//		List<String> finalItems = cartLists.stream().map(WebElement::getText).collect(Collectors.toList());
//		Assert.assertTrue(finalItems.containsAll(cartLists));
		Boolean match = cartLists.stream().anyMatch(cartlist->cartlist.getText().equalsIgnoreCase(p1));
		Assert.assertTrue(match);
//		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".totalRow button")));
//		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ngx-spinner-overlay")));
		System.out.println(driver.findElement(By.xpath("(//li[@class='totalRow'])[3]")).getText());
		//driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(By.cssSelector(".totalRow button"))).click().build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Select Country']")));
		
		a.moveToElement(driver.findElement(By.xpath("//input[@placeholder='Select Country']"))).click().sendKeys("ind").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results ")));
		driver.findElements(By.cssSelector(".list-group-item")).stream().filter(e -> e.getText().equalsIgnoreCase("India")).findFirst().ifPresent(WebElement::click);
//		List<WebElement> countryList = driver.findElements(By.cssSelector(".list-group-item"));
//		for(int i = 0; i < countryList.size(); i++)
//		{
//
//			WebElement country = driver.findElements(By.cssSelector(".list-group-item")).get(i);
//			if(country.getText().equalsIgnoreCase("India"))
//			{
//				country.click();
//				break;
//			}
//		}
		driver.findElement(By.cssSelector(".action__submit")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector(".hero-primary")).getText(), "THANKYOU FOR THE ORDER.");
		
		
		}
}
