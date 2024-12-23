package testCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass
{
	
	
	@Test(groups={"Regression","Master"})
    public void Verify_Account_Registration()
    {
    	HomePage hp=new HomePage(driver);
    	logger.info("clicked on my account");;
    	hp.clickMyAccount();
    	hp.clickRegister();
    	
    	
    	AccountRegistrationPage rp=new AccountRegistrationPage(driver);
    	rp.setFirstName(randomString());
    	rp.setLastName(randomString());
    	rp.setEmail(randomString()+"@gmail.com");
    	 
    	String password=randomAlphaNumeric();
    	rp.setPassword(password);
    	
    	
        rp.setPrivacyPolicy();
        
        rp.clickSubmit();
        logger.info("Details Submitted");
       String confmsg= rp.getConfirmationMsg();
       Assert.assertEquals(confmsg, "Your Account Has Been Created!");
         logger.info("Account Created");
         logger.error("Account not created");
         logger.debug("Account got created");
    }
	
	
	
}
