package TestBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
    static public WebDriver driver;
    public Logger logger;
    public Properties p;
	
	@BeforeClass(groups = {"sanity","regration","master"})
	@Parameters({"os","browser"})
	public void setup(String os, String browser) throws Throwable
	{
		//loading config file 
		p=new Properties();
		FileReader file=new FileReader(".//src//test//resources//config.properties");
		p.load(file);
		
		//loading log4j file
		logger= LogManager.getLogger(this.getClass());
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{  
			DesiredCapabilities cap=new DesiredCapabilities();// creating node box
			// creating os box1 in  side node box
			if(os.equalsIgnoreCase("windows"))
			{
				cap.setPlatform(Platform.WIN11);// creating os box
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				cap.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("no matching os....");
				return;
			}
			// creating browser box2 in  side node box
			switch (browser.toLowerCase()) 
			{
			case "chrome":cap.setBrowserName("chrome");break;
			case "edge": cap.setBrowserName("MicrosoftEdge");break;
			default: System.out.println("no matching browser"); return;
		    }
		
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
		
		}
	else if(p.getProperty("execution_env").equalsIgnoreCase("local"))
	 {  //lunching the browser based on condition - locally
		switch (browser.toLowerCase()) 
		{
		 case "chrome": driver=new ChromeDriver();break;
		 case "edge": driver=new EdgeDriver();break;// break means exit from the method
		 default: System.out.println("not matching browsers...");return;// return means same like break
		}
	}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
	}
	
	@AfterClass (groups = {"sanity","regration","master"})
	public void tearDown()
	{
		driver.close();
	}
	

	public String randomeString()
	{
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	public String randomeNumber()
	{
		String generatedString=RandomStringUtils.randomNumeric(10);
		return generatedString;
	}
	
	public String randomAlphaNumeric()
	{
		String str=RandomStringUtils.randomAlphabetic(3);
		String num=RandomStringUtils.randomNumeric(3);
		
		return (str+"@"+num);
	}
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date(0));
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}
	

}
