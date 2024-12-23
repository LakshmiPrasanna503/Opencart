package pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountRegistrationPage extends BasePage
{
	
	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstname;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastname;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkdPolicy;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement btnsubmit;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	
	WebElement msgConfirmation;
	
	public void setFirstName(String fname)
	{
		txtFirstname.sendKeys(fname);
	}
	public void setLastName(String lname)
	{
		txtLastname.sendKeys(lname);
	}
	
	public void setEmail(String email)
	{
		txtEmail.sendKeys(email);
	}
	
	public void setPassword(String pwd)
	{
		txtPassword.sendKeys(pwd);
	}
	
	public void setPrivacyPolicy()
	{
		
		//sol1
		//chkdPolicy.click();
				
				//sol2
				//chkdPolicy.submit();
				
				//sol3
				Actions act = new Actions(driver);
				act.moveToElement(chkdPolicy).click().perform();
				
				//sol4
				//JavascriptExecutor js=(JavascriptExecutor)driver;
				//js.executeScript("arguments[0].scrollIntoView();", chkdPolicy);
				//chkdPolicy.click();
				
				//sol5
				//chkdPolicy.sendKeys(Keys.RETURN);
				
				//sol6
				//WebDriverWait mywait= new WebDriverWait(driver,Duration.ofSeconds(10));
				//mywait.until(ExpectedConditions.elementToBeClickable(chkdPolicy)).click();
		
	}
	
	public void clickSubmit()
	{   //sol1
		//btnsubmit.click();
		
		//sol2
		//btnsubmit.submit();
		
		//sol3
		//Actions act = new Actions(driver);
		//act.moveToElement(btnsubmit).click().perform();
		
		//sol4
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();", btnsubmit);
		btnsubmit.click();
		
		//sol5
		//btnsubmit.sendKeys(Keys.RETURN);
		
		//sol6
		//WebDriverWait mywait= new WebDriverWait(driver,Duration.ofSeconds(10));
		//mywait.until(ExpectedConditions.elementToBeClickable(btnsubmit)).click();
		
		
	}
	
	public String getConfirmationMsg()
	{
		try
		{
			return (msgConfirmation.getText());
		}
	
	
	catch(Exception e)
	{
		return(e.getMessage());
	}
	}
	
}
