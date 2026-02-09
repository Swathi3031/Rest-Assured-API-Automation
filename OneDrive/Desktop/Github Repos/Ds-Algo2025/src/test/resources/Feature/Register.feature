
Feature: Register Functionality

Background:
Given user is on the homepage
When user clicks on register link
Then user should land on the register page

Scenario: Verify user can see header section elements
Given user is on rgister page
Then user can see NumpyNinja logo,datastructures dropdown,siginin and register links

Scenario: Verify register button without entering data into username and password fields
Given user is on rgister page
When user did not enter data to username and password and confirmPwd fields and click on register button
Then user should see error message in alert window "Please fill out this field."

Scenario Outline: Verify register functionality by entering valid username and password with invalid confirm password
Given user is on rgister page
When user gets data from "<sheetname>" and scenario type <rownumber> and clicks on register
Then user should see eroor "password_mismatch:The two password fields didn’t match." message
Examples:
|sheetname|rownumber|
|registerData|0|

Scenario Outline: Verify negetive testing for register functionality 
Given user is on rgister page
When user gets data from "<sheetname>" and scenario type "<scenarioType>" and clicks on register
Then user should see error message in alert window "Please fill out this field."
Examples:
|sheetname|scenarioType|
|registerData|pwdBlank|
|registerData|usernameBlank|
|registerData|confirmPwdBlank|


Scenario Outline: Verify register functionality by entering all valid inputs
Given user is on rgister page
When user gets data from "<sheetname>" and scenario type "<scenarioType>" and clicks on register
Then user should land on home page with title "NumpyNinja" and see the message "New Account Created. You are logged in as "
Examples:
|sheetname|scenarioType|
|registerData|valid|


Scenario: Verify by clicking login link user should navigate to login page
Given user is on rgister page
When user clicks on login link
Then user should land on the "Login" page






