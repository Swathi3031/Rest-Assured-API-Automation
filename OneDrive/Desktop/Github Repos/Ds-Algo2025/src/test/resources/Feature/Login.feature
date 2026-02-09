Feature: Login Functionality

  Background: 
    Given User is on home page
    When User clicks on sign in button
    Then User should land on "Login" login page

  Scenario: Verify that user is able to land on Login Page
    Given The user is on the login Page
    Then The user should see all header elements

  Scenario: Submit login form without entering username
    Given The user is on the login Page
    When The user leave the username field empty and click login
    Then The User should see "Please fill out this field."

  Scenario: Submit login form without entering password
    Given The user is on the login Page
    When The user leave the password field empty and click login
    Then The user must see "Please fill out this field."

  Scenario Outline: Verify that the user gets error message when giving invalid username and password
    Given The user is on the login Page
    When The user gets invalid data from excel sheet '<sheetname>' and '<scenario>' for the login page
    Then The user should get the error message "Invalid Username and Password"

    Examples: 
      | sheetname | scenario |
      | Login     | invalid  |

  Scenario Outline: Verify user enters valid username and password
    Given The user is on the login Page
    When The user gets valid data  '<sheetname>' and '<scenario>' for the login page
    Then The user is in the home page with message "You are logged in"

    Examples: 
      | sheetname | scenario |
      | Login     | valid    |
