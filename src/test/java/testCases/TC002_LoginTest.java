package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass

{
	
	@Test(groups={"Sanity","Master"})
	public void Verify_Login()
	{
		try {
			
	HomePage hp = new HomePage(driver);
	
	hp.clickMyAccount();
	logger.info("Clciked on account");
	hp.clickLogin();
	
	logger.info("Clciked on login");
	logger.error("not clicked on login");
	
	LoginPage lp = new LoginPage(driver);
	lp.setEmail(p.getProperty("email"));
	lp.setPassword(p.getProperty("password"));
	lp.clickLogin();
	logger.info("Navigated to account page");
	
	MyAccountPage accpage = new MyAccountPage(driver);
	
	boolean targetpage=accpage.isMyAccountexist();
	Assert.assertEquals(targetpage, true, "Login failed");
		}
		catch(Exception e)
		{
			e.getMessage();
		}
	}
	

}
