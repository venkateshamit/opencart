package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyaccoutPage extends BasePage {
	
	WebDriver driver;
	public MyaccoutPage(WebDriver driver)
	{
		super (driver);
	}
	
	@FindBy(xpath="//h2[normalize-space()='My Account']") 
	WebElement myAccountmsg;
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement link_logout;
	
	public boolean confirm_MyaccoutMsg()
	{
		try { 
			return( myAccountmsg.isDisplayed());
		}
		catch (Exception e) {
			return(false);
		}
	}
	public void clickLogout()
	{
		link_logout.click();
	}
	
	
	
}





