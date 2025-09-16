package TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import YSK.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver InitializeDriver() throws IOException
	{
	Properties prop = new Properties();
	FileInputStream fis = new FileInputStream("C:\\Users\\user\\eclipse-workspace-new\\SeleniumFramework\\src\\main\\java\\Resources\\GlobalData.properties");
	prop.load(fis);
	
	String browserName = System.getProperty("browser") !=null ? System.getProperty("browser") : prop.getProperty("browser");
//	String browserName = prop.getProperty("browser");
	
	if (browserName.contains("chrome"))
	{
		ChromeOptions options = new ChromeOptions();
	       if (browserName.contains("headless")) {
	            options.addArguments("--headless=new"); // new headless mode
	            options.addArguments("--window-size=1440,900");
	            options.addArguments("--disable-gpu");
	            options.addArguments("--disable-dev-shm-usage");
	            options.addArguments("--no-sandbox");
	        }
	        driver = new ChromeDriver(options);

	        if (!browserName.contains("headless")) {
	            driver.manage().window().maximize(); // only for headed
	        }
	    } else if (browserName.equalsIgnoreCase("firefox")) {
	        FirefoxOptions options = new FirefoxOptions();
	        if (browserName.contains("headless")) {
	            options.addArguments("--headless");
	            options.addArguments("--width=1440");
	            options.addArguments("--height=900");
	        }
	        driver = new FirefoxDriver(options);
	        if (!browserName.contains("headless")) {
	            driver.manage().window().maximize();
	        }
	    } else if (browserName.equalsIgnoreCase("edge")) {
	        EdgeOptions options = new EdgeOptions();
	        if (browserName.contains("headless")) {
	            options.addArguments("--headless=new");
	            options.addArguments("--window-size=1440,900");
	        }
	        driver = new EdgeDriver(options);
	        if (!browserName.contains("headless")) {
	            driver.manage().window().maximize();
	        }
			}
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	return driver;
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		driver = InitializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	@AfterMethod(alwaysRun=true)
	public void teardown()
	{
		driver.quit();
	}
	
	public String getScreenshots(String testCaseName ,WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"\\report\\"+ testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"\\report\\"+ testCaseName + ".png";
	}
}
