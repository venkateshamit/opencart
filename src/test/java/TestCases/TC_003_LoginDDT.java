package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.MyaccoutPage;
import TestBase.BaseClass;
import Utilites.DataProviders;

public class TC_003_LoginDDT extends BaseClass{
	
	@Test(dataProvider = "logindata", dataProviderClass = DataProviders.class)
	public void verify_loginDDT(String email, String pwd,String exp )// ante ikkada rasina 
	// 3 methods names m rasina kuda avi excel sheet lo unna cloums ki match aiynatte
	// ante 4 set of data vasthundhi ani artham e method ki 
	{
		logger.info("**********TC_003_LoginDDT starting********");
		try
		{
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clicklogin();
		
		LoginPage lp=new LoginPage(driver);
		lp.clickLogin();
		lp.setEmail(p.getProperty(email));
		lp.setPwd(p.getProperty(pwd));
		lp.clickLogin();
		
		MyaccoutPage mac=new MyaccoutPage(driver);
		boolean targetpage=mac.confirm_MyaccoutMsg();
		
		if(exp.equalsIgnoreCase("vaild"))
		{
			if(targetpage==true)// loginpage open
			{
				Assert.assertTrue(true);// its means and print in console test passed
			// Assert.manam anukunnadhi(akkada acutal ga unnadhi)
				mac.clickLogout();
			}
			else
			{
				Assert.assertTrue(false);//its means and print in console test failed
			}
		}
		if(exp.equalsIgnoreCase("invaid"))
		{
			if(targetpage==true)
			{
				Assert.assertTrue(false);//test failed
			}
			else
			{
				Assert.assertTrue(true);//test passed
			}
		}
		}
		catch (Exception e) {
			Assert.fail("one exception occured: "+e.getMessage());//test failed
		}
		logger.info("**********TC_003_LoginDDT finised********");
		
	}
	
}
