package YSK;


import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TestComponents.BaseTest;
import TestComponents.Retry;

public class SubmitOrder extends BaseTest{
	String p1 ="ZARA COAT 3"; //used dataprovider
	String p2 ="iphone 13 pro";
	
	@Test(dataProvider = "getData", groups= {"purchaseOrder"})
	//(dataProvider = "getData", groups= {"purchaseOrder"},retryAnalyzer=Retry.class) //retry
	public void SubmitOrder (String emailID, String password, String p2) throws IOException, InterruptedException {
		
		// TODO Auto-generated method stub
		ProductCatalogue productCatalogue = landingPage.loginApplication(emailID, password);
		productCatalogue.addProductToCart(p2);
		Cart cart = productCatalogue.goToCart();
		Boolean match = cart.verifyCartList(p2);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cart.checkout();
		checkoutPage.checkout("ind");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		Assert.assertEquals(confirmationPage.confirmationMessage(), "THANKYOU FOR THE ORDER.");
			}
	
	@Test(dependsOnMethods={"SubmitOrder"}, dataProvider = "getData")
	public void OrderHistory(String emailID, String password, String p2)
	{
		ProductCatalogue productCatalogue = landingPage.loginApplication(emailID, password);
		OrderPage orderPage = productCatalogue.goToOrder();
		Boolean match = orderPage.verifyOrderList(p2);
		Assert.assertTrue(match);
	}
	
	@DataProvider
	public Object[][] getData()
	{
		return new Object[][] {{"ysk@gmail.com", "Samsung525$","ZARA COAT 3"}, {"test12122@gmail.com", "Samsung525$", "iphone 13 pro"}};
	}
}
