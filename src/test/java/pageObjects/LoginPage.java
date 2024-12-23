package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage 
{
	public LoginPage(WebDriver driver)
	{
		super(driver);
	}
	
	
	@FindBy(xpath="//input[@id='input-email']") WebElement txtemail;
	@FindBy(xpath="//input[@id='input-password']") WebElement txtpassword;
	@FindBy(xpath="//button[@type='submit']") WebElement btnlogin;
	
	public void setEmail(String emailid) 
	{
		txtemail.sendKeys(emailid);
	}
	
	public void setPassword(String password)
	{
		txtpassword.sendKeys(password);
	}

	public void clickLogin()
	{
		btnlogin.click();
	}
}
