package YSK;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestComponents.BaseTest;
import TestComponents.Retry;

public class ErrorValidation extends BaseTest{
	
	@Test (groups= {"ErrorHandling"})
	public void LoginValidation () throws IOException, InterruptedException {
		
		// TODO Auto-generated method stub
		landingPage.loginApplication("axyzds@gmail.com", "Saaaamsung525$");
		Assert.assertEquals("Incorrect email or password.", landingPage.geterrorMessage());  //need to revisit
		}
	
	@Test(retryAnalyzer=Retry.class)
	public void CartValidation () throws IOException, InterruptedException {

		String p2 ="iphone 13 pro";
		ProductCatalogue productCatalogue = landingPage.loginApplication("jnjk@gmail.com", "Samsung525$");
		productCatalogue.addProductToCart(p2);
		Cart cart = productCatalogue.goToCart();
		Boolean match = cart.verifyCartList("Iphone 12");
		Assert.assertFalse(match);
	
		}	
}
