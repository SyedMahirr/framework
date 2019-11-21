package com.stadiumgood.StepDef;

import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;
import com.stadiumgood.Base.Driver;
import com.stadiumgood.Pages.Homepage;
import com.stadiumgood.Pages.Jordanpage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class CountCatagoriesAndSelectACatagory extends Driver{
	
	Homepage home = new Homepage(driver); ; 
	Jordanpage jordan;

	
	@Given("^User logins into the application with propert credentials$")
	public void user_logins_into_the_application_with_propert_credentials() throws Exception {
			openBrowser();
			logger = report.startTest("Feature: Count Catagories");
			compare(getTitle(), "Stadium Goods - The Latest Sneakers & Premium Streetwear", "Matched");
			logger.log(LogStatus.PASS, "User 'logins' into the application with propert credentials");	
			logger.log(LogStatus.INFO, logger.addScreenCapture(takeScreenShot())); // takes screenshot 
			
			
	}

	@Then("^User Counts the number of catagories in the header$")
	public void user_Counts_the_number_of_catagories_in_the_header() {
		try{
			home = new Homepage(driver); 
			home.clickX();				
			System.out.println("Number of Catagories are present in the Header:: " + home.getnotabs());
			Assert.assertEquals(home.getnotabs(), 10);
			logger.log(LogStatus.PASS, "User 'Counts the number' of catagories in the header");
			logger.log(LogStatus.INFO, logger.addScreenCapture(takeScreenShot())); // takes screenshot 
		}catch(Throwable t){
			  logger.log(LogStatus.FAIL, "User 'Counts the number' of catagories in the header EXCEPTION :: "+t.getLocalizedMessage());
			//  Assert.fail();
		}
	}

	@Given("^User Selects Jordan$")
	public void user_Selects_Jordan() {
		try{
			home = new Homepage(driver); 
			home.navigatetoJordan();
			logger.log(LogStatus.PASS, "User Selects 'Jordan'");
			logger.log(LogStatus.INFO, logger.addScreenCapture(takeScreenShot())); // takes screenshot 
		}catch(Throwable t){
			  logger.log(LogStatus.FAIL, "User Selects 'Jordan' EXCEPTION :: "+t.getLocalizedMessage());
			//  Assert.fail();
		}
	}

	@Then("^User Sort By low to high$")
	public void user_Sort_By_low_to_high() {
		try{
			jordan = new Jordanpage(driver);
			jordan.selectPriceHightoLow();
			logger.log(LogStatus.PASS, "User 'Sort By' low to high");
			logger.log(LogStatus.INFO, logger.addScreenCapture(takeScreenShot())); // takes screenshot 
		}catch(Throwable t){
			  logger.log(LogStatus.FAIL, "User 'Sort By' low to high EXCEPTION :: "+t.getLocalizedMessage());
			  Assert.fail();
			  report.endTest(logger);

		}
		//report.endTest(logger);
	}

}
