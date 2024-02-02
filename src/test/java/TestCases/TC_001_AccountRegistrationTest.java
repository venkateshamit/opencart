package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.RegistrationPage;
import TestBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass{
	@Test (groups = {"regration","master"})
	public void verify_account_registration()
	{
		logger.info("****** test case start**************");
		try 
		{
			logger.debug("aplication logs....");
		
		HomePage hp=new HomePage(driver);
		logger.info("clicked my accout ");
		hp.clickMyAccount();
		logger.info("clicked my registation button ");
		hp.clickRegister();
		
		RegistrationPage regpage=new RegistrationPage(driver);
		
		logger.info("entered customer information in all tyest boxes ");
		regpage.setFirstName(randomeString().toUpperCase());
		regpage.setLastName(randomeString().toUpperCase());
		regpage.setEmail(randomeString()+"@gmail.com");// randomly generated the email
		regpage.setTelephone(randomeNumber());
		
		String password=randomAlphaNumeric();
		
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		
		logger.info("clicked policy box ");
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		logger.info("succesfully login ");
		
		logger.info("checking confirmation msg ");
		String confmsg=regpage.getConfirmationMsg();
		
		if(confmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("test case failed");
			Assert.assertFalse(false);
		}
		
		
		}
		catch (Exception e) 
		{
			Assert.fail();
			logger.error("test failed ");
			//logger.debug("debug logs....");
		}
		
		//logger.debug("application logs end.......");
		logger.info("**** finished TC_001_AccountRegistrationTest  *****");
	}
	
	
	

}
