package com.stadiumgood.StepDef;

import com.relevantcodes.extentreports.LogStatus;
import com.stadiumgood.Api.EmployeeAPI;
import com.stadiumgood.Api.pokemonAPI;
import com.stadiumgood.Base.Driver;
import com.stadiumgood.Utils.ExcelSheetUtils;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class APIvalidation extends Driver {	
	ExcelSheetUtils excel = new ExcelSheetUtils();
	@Given("^Excel Data Pull$")
	public void excel_Data_Pull() throws Throwable {
		logger = report.startTest("Feature: API Validation");
		data = excel.getsheetData("pokeapi");	
		 logger.log(LogStatus.PASS, "Excel Data Pull");
	}

	@When("^Pokemon API data Pull and Validate API$")
	public void pokemon_API_data_Pull_and_Validate_API() throws Throwable {
		pokemonAPI pokemon;
		for(int index = 0; index<data.size(); index++){
			pokemon = new pokemonAPI(data.get(index).get("pokemon_name"));
			compare(data.get(index).get("abilities.ability.name") , pokemon.getnameValue());
			compare(data.get(index).get("abilities.ability.url"), pokemon.geturlValue());
			compare(data.get(index).get("abilities.is_hidden"), pokemon.gethiddenValueVariable());
			compare(data.get(index).get("abilities.slot"), pokemon.getslotValueVariable()); 
		}
		data.remove(data);	
		logger.log(LogStatus.PASS, "Pokemon API data Pull and Validate API");
	}

	@Given("^Excel Data is Pulled as input for employee creation$")
	public void excel_Data_is_Pulled_as_input_for_employee_creation() throws Throwable {
		data = excel.getsheetData("Users");
		logger.log(LogStatus.PASS, "Excel Data is Pulled as input for employee creation");
	}

	@When("^Employee data is created using entity creation API and validated against GET API$")
	public void employee_data_is_created_using_entity_creation_API_and_validated_against_GET_API() throws Throwable {
		EmployeeAPI emp;
		for(int i = 0; i<data.size(); i++){
			emp = new EmployeeAPI(data.get(i).get("name"), data.get(i).get("salary"), data.get(i).get("age"));
			compare(data.get(i).get("name"), emp.getName(), "Matched");
			compare(data.get(i).get("salary"), emp.getSalary(), "Matched");
			compare(data.get(i).get("age"), emp.getAge(), "matched");			
		}
		data.remove(data);
		logger.log(LogStatus.PASS, "Employee data is created using entity creation API and validated against GET API");
		report.endTest(logger);
	}
	
}
