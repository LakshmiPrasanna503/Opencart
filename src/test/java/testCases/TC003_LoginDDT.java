package testCases;

import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;



public class TC003_LoginDDT extends BaseClass
{
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="DataDriven")//getting data provider from different class
	public void verify_loginDDT(String email, String pwd, String exp)
	{
		
		logger.info("Starting TC003_LoginDDT");
		
		try {
		//Home Page
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//Login
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(null);
		lp.setPassword(null);
		lp.clickLogin();
		
		//MyAccount
		MyAccountPage macc = new MyAccountPage(driver);
		boolean targetpage = macc.isMyAccountexist();
		
		//Data is valid -login success-test pass-logout
		//Data  is valid - login fail- test fail

		//Data is Invalid -Login Success-test fail - logout
		//Data is invalid - login failed - test pass
		
		if(exp.equalsIgnoreCase("valid"))
				{
			         if(targetpage==true)
			         {
			        	 macc.clickLogout();
			        	 Assert.assertTrue(true);
			        	 
			         }
			         
			         else
			         {
			        	 Assert.assertTrue(false);
			         }
			         
			         
				}
		
		if(exp.equalsIgnoreCase("invalid"))
		{
			if(targetpage==true)
			{
				macc.clickLogout();
			Assert.assertTrue(false);
				
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		logger.info("Finished TC003_LoginDDT ");
	}
	

}
