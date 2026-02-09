
Feature: This feature file contain scenario for the Stack module

Background: The user sign in to dsAlgo Portal

    Given The User is in the home page after sign in
    When The user scrolls down to the Data Structures dropdown and selects Stack
    Then User is navigated to Stack Page

  
Scenario: Verify that user is able to navigate to "Operations in Stack" page
    Given The user is in the Stack Page
    When The user clicks Operations in stack button
    Then The user should be redirected to Operations in stack page

  Scenario: Verify that the user is able to scroll down and click Try Here button
    Given The user is on the Operations in Stack page
    When The user scrolls down and clicks Try Here button in Operations in Stack page
    Then The user is redirected to a page having a Stacks try Editor with a Run button to test

  Scenario: Verify that user receives error when click on Run button without entering code for "Operations in Stack" page
    Given The user is in the Stacks tryEditor page
    When The user clicks Run button without entering the code in the stacks tryEditor
    Then The user is able to see an error message in alert window

  Scenario: Verify that user receives error for invalid python code for "Operations in Stack" page
    Given The user is in the Stacks tryEditor page
    When The user writes the invalid code from row number in stacks tryEditor and clicks the Run Button
    Then The user is able to see an nameerror message in alert window

  Scenario: Verify that user is able to see output for valid python code for "Operations in Stack" page
    Given The user is in the Stacks tryEditor page
    When The user writes the valid code from row number in stacks tryEditor and clicks the Run Button
    Then The user is able to see output in the console

  Scenario: Verify that user is able to navigate to "Practice Questions" Page for "Operations in Stack" page
    Given The user is on the Operations in Stack page
    When The user clicks Practice Question button in stack
    Then The user should be redirected to Practice page in stack

 
Scenario: Verify that user is able to navigate to "Implementation" page
    Given The user is in the Stack Page
    When The user clicks Implementation button
    Then The user should be redirected to Implementation page

  Scenario: Verify that user is able to navigate to "try Editor" page for "Implementation" page
    Given The user is on the Implementation page
    When The user clicks Try Here button in Implementation page
     Then The user is redirected to a page having a Stacks try Editor with a Run button to test

  Scenario: Verify that user receives error when click on Run button without entering code for "Implementation" page
    Given The user is in the Stacks tryEditor page
    When The user clicks Run button without entering the code in the stacks tryEditor
    Then The user is able to see an error message in alert window

  Scenario: Verify that user receives error for invalid python code for "Implementation" page
    Given The user is in the Stacks tryEditor page
    When The user writes the invalid code from row number in stacks tryEditor and clicks the Run Button
    Then The user is able to see an nameerror message in alert window

  Scenario: Verify that user is able to see output for valid python code for "Implementation" page
    Given The user is in the Stacks tryEditor page
    When The user writes the valid code from row number in stacks tryEditor and clicks the Run Button
    Then The user is able to see output in the console

  Scenario: Verify that user is able to navigate to "Practice Questions" Page for "Implementation" page
    Given The user is on the Implementation page
    When The user clicks Practice Question button in stack
    Then The user should be redirected to Practice page in stack

 
Scenario: Verify that user is able to navigate to "Application" page
    Given The user is in the Stack Page
    When The user clicks Application button
    Then The user should be redirected to Application page

  Scenario: Verify that user is able to navigate to "try Editor" page for "Application" page
    Given The user is on the Application page
    When The user clicks Try Here button in Application page
    Then The user is redirected to a page having a Stacks try Editor with a Run button to test

  Scenario: Verify that user receives error when click on Run button without entering code for "Application" page
    Given The user is in the Stacks tryEditor page
    When The user clicks Run button without entering the code in the stacks tryEditor
    Then The user is able to see an error message in alert window

  Scenario: Verify that user receives error for invalid python code for "Application" page
    Given The user is in the Stacks tryEditor page
    When The user writes the invalid code from row number in stacks tryEditor and clicks the Run Button
    Then The user is able to see an nameerror message in alert window

  Scenario: Verify that user is able to see output for valid python code for "Application" page
    Given The user is in the Stacks tryEditor page
    When The user writes the valid code from row number in stacks tryEditor and clicks the Run Button
    Then The user is able to see output in the console

  Scenario: Verify that user is able to navigate to "Practice Questions" Page for "Application" page
    Given The user is on the Application page
    When The user clicks Practice Questionb button in stack
    Then The user should be redirected to Practice page in stack
