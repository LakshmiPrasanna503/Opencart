package testBase;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java. util. Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.beust.jcommander.Parameter; 
public class BaseClass
{
	
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	@BeforeClass(groups={"Sanity","Master"})
	@Parameters({"os", "browser"})
	public void setup(String os, String br) throws IOException
	{
		FileInputStream file = new FileInputStream("./src/test/resources/config.properties");
		
		p=new Properties();
		p.load(file);
		
		logger=LogManager.getLogger(this.getClass());
		
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities=new DesiredCapabilities();
			//os
			if(os.equalsIgnoreCase("windows"))
			{
			capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else if(os.equalsIgnoreCase("Linux"))
			{
				capabilities.setPlatform(Platform.LINUX);
			}
			else
			{
				System.out.println("No matching OS");
				return;
			}
			
			//browser
			switch(br.toLowerCase())
			{
			case "chrome":capabilities.setBrowserName("chrome");
			break;
			
			case "edge":capabilities.setBrowserName("MicrosoftEdge");
			break;
			
			case "firefox":capabilities.setBrowserName("firefox");
			break;
			default:System.out.println("No Matching browser");
			return;
			}
			
			driver=new RemoteWebDriver(new URL("http://192.168.56.1:4444"),capabilities);
			
		}
		
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
		switch(br.toLowerCase())
		{
		case "chrome":
			driver=new ChromeDriver();
			break;
			
		case "firefox":
			driver=new FirefoxDriver();
			break;
		case "edge":
			driver= new EdgeDriver();
			break;
		default:
			System.out.println("Invalid browser name");
			return;
		}	
		}
		
		
		
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL"));
		//driver.get("https://demo-opencart.com/");
		driver.manage().window().maximize();
		
	}
	@AfterClass(groups={"Sanity","Master"})
	public void tearDown()
	{
		driver.quit();
	}
	
	public String randomString()
	{
		String generatedstring=RandomStringUtils.randomAlphanumeric(5);
		return generatedstring;
	}
	
	public String randomNumber()
	{
		String generatednumber=RandomStringUtils.randomNumeric(5);
		return generatednumber;
	}
	
	public String randomAlphaNumeric()
	{
		String generatedstring=RandomStringUtils.randomAlphanumeric(5);
		String generatednumber=RandomStringUtils.randomNumeric(5);
		return (generatedstring+"@"+generatednumber);
	}
	
	
	public String captureScreen(String tname) {
	    String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	    TakesScreenshot ts = (TakesScreenshot) driver;

	    // Take the screenshot and store it in a temporary file
	    File sourceFile = ts.getScreenshotAs(OutputType.FILE);

	    // Create a dynamic path for the screenshots directory
	    String screenshotDir = System.getProperty("user.dir") + File.separator + "screenshots";
	    String targetFilePath = screenshotDir + File.separator + tname + "_" + timestamp + ".png";
	    File targetFile = new File(targetFilePath);

	    // Ensure the screenshots directory exists
	    try {
	        Files.createDirectories(Paths.get(screenshotDir)); // Creates the directory if it does not exist
	        Files.copy(sourceFile.toPath(), targetFile.toPath()); // Copy screenshot to target location
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null; // Return null in case of failure
	    }

	    return targetFilePath; // Return the absolute path of the screenshot
	}

}
