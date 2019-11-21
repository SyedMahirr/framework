package org.stadiumgoods.com.testrunner;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
@CucumberOptions(
		features = "Features", //the path of the feature files
		glue={"com.stadiumgood.StepDef"}, //the path of the step definition files @validateLogin, @APIValidation, @invokeBrowser
		tags = {"@~validateLogin, @countTheNumberOfCatagories,  @~APIValidation"},
		plugin = {"html:target/cucumber-html-report"},
		format= {"pretty"}, //to gene		rate different types of reporting
		monochrome = true, //display the console output in a proper readable format
		strict = true, //it will check if any step is not defined in step definition file
		dryRun = false //to check the mapping is proper between feature file and step def file	
        )
public class RunCucumberTests{
	
	private TestNGCucumberRunner testNGcucumberRunner;
	public static List<Hashtable<String, String>> data = new ArrayList<Hashtable<String,String>>();
	public static ExtentReports report = null;
	public static ExtentTest logger = null;
	public static Properties config = null;
	
	
	@BeforeSuite(alwaysRun = true)
	public void setUp() throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("MMddyyyy_hh_mm_ss");
		Date date = new Date();
		String currentDate = formatter.format(date);
		report = new ExtentReports(System.getProperty("user.dir")+"\\ExtentReports\\TestReport"+currentDate+".html");
		report.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
	}
	
	@BeforeClass
	public void setupclass() throws Exception{
		testNGcucumberRunner = new TestNGCucumberRunner(this.getClass());
		
	}
	
	@Test(dataProvider = "features")
	public void feature(CucumberFeatureWrapper cucumberfeature){
	//	logger = report.startTest(cucumberfeature.getCucumberFeature().getGherkinFeature().getName());
		testNGcucumberRunner.runCucumber(cucumberfeature.getCucumberFeature());
	//	report.endTest(logger);	
	}
	
	@DataProvider
	public Object[][] features(){
		return testNGcucumberRunner.provideFeatures();
	}
	
	@AfterClass
	public void teardownClass() throws Exception{
		testNGcucumberRunner.finish();		
	}
	
	@AfterSuite 
	public void teardownsuit(){
		//report.close();
	}
	
	
   
}
