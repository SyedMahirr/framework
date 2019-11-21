package com.stadiumgood.StepDef;

import org.stadiumgoods.com.testrunner.RunCucumberTests;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;
import com.stadiumgood.Base.Driver;
import com.stadiumgood.Utils.ExcelSheetUtils;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class LoginValidation extends Driver{
	ExcelSheetUtils excel = new ExcelSheetUtils();
	@Given("^User retrives User Names from the excel and validates$")
	public void user_retrives_User_Names_from_the_excel_and_validates(){
		logger = report.startTest("Feature: Logging Validation");
		String userName_column2 = excel.getCellData("Sheet1", "Username", 2);
		System.out.println(userName_column2);
		Assert.assertEquals(userName_column2, "qa.automation@test.com");
	
		String userName_column3 = excel.getCellData("Sheet1", "Username", 3);
		System.out.println(userName_column3);
		Assert.assertEquals(userName_column3, "qa1.automation@test.com", "Matched Username in column3");
		logger.log(LogStatus.PASS, "User retrives User Names from the excel and validates");
	}
	
	@Then("^User retrieves Password from the excel and validates$")
	public void user_retrieves_Password_from_the_excel_and_validates(){
		
		String password_column2 = excel.getCellData("Sheet1", "Password", 2);
		System.out.println(password_column2);
		Assert.assertEquals(password_column2, "password1", "Matched password in column2");
		
		String password_column3 = excel.getCellData("Sheet1", "Password", 3);
		System.out.println(password_column3);
		Assert.assertEquals(password_column3, "password1", "Matched password in column3");
		logger.log(LogStatus.PASS, "User retrieves Password from the excel and validates");
		report.endTest(logger);
		
	}
}
