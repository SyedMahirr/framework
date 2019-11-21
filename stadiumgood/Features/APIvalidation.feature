Feature: API Validation

  @APIValidation
  Scenario: Pokemon API Validation testing against Excel data
    Given Excel Data Pull
    When Pokemon API data Pull and Validate API

	@APIValidation
  Scenario: Employee creation using the API and validation of creation data against the GET API data
    Given Excel Data is Pulled as input for employee creation
    When Employee data is created using entity creation API and validated against GET API
