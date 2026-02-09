Feature: Arrays PracticeQuestion

  Background: 
    Given The User is in home page after sign in
    When The user scrolls down to the Data Structures dropdown and selects Array
    Then User is navigated to Array Page

  Scenario: Verify that user is able to navigate to "Arrays in Python" page
    Given The user is in the Array page
    When The user clicks Arrays in Python button
    Then The user should be redirected to Arrays in Python page

  Scenario: Verify that the user is able to scroll down and click Try Here button
    Given The user is on the Arrays in Python page
    When The user scrolls down and clicks Try Here button in Arrays in Python page
    Then The user IS redirected to a page having an try Editor with a Run button to test

  @bug
  Scenario: Verify that user receives error when click on Run button without entering code for "Arrays in Python" page
    Given The user is on the Arrays tryEditor page
    When The user clicks on the Run button without entering the code in the Editor
    Then The user should see an error message in alert window

  Scenario: Verify that user receives error for invalid python code for "Arrays in Python" page
    Given The user is on the Arrays tryEditor page
    When The user gives the invalid code from row number in Editor and click the Run Button
    Then The user should see an NameError message in alert window

  Scenario: Verify that user is able to see output for valid python code for "Arrays in Python" page
    Given The user is on the Arrays tryEditor page
    When The user gives the valid code from row number in Editor and click the Run Button
    Then The user should see output in the console

  Scenario: Verify that user is able to navigate to "Practice Questions" Page for "Arrays in Python" page
    Given The user is on the Arrays in Python page
    When The user clicks Practice Questions button
    Then The user is redirected to Practice page

  Scenario Outline: Verify that user is able to navigate to a question page from Practice Question page of "Arrays in Python"
    Given The users is on the Arrays Practice Question page
    When The user clicks "<question_link>" link in the arrays question page
    Then The user should be redirected to Question page which has a question, and try Editor with Run and Submit buttons

    Examples: 
      | question_link                           |
      | Search the array                        |
      | Max Consecutive Ones                    |
      | Find Numbers with Even Number of Digits |
      | Squares of a Sorted Array               |

  Scenario: Verify that user receives error for invalid python code on running "Search the array" question
    Given The user is on the Search the array practice question editor
    When The user writes invalid code from row number and clicks the Run Button Search the array
    Then The user must see an error message in the alert window

  Scenario: Verify that user is able to run valid python code for "Search the array" question
    Given The user is on the Search the array practice question editor
    When The user writes valid code from row number and clicks the Run Button Search the array
    Then The user must see output in the console

  Scenario: Verify that user receives error on submitting invalid python code for "Search the array" question
    Given The user is on the Search the array practice question editor
    When The user writes invalid code from row number and clicks the Submit Button Search the array
    Then The user gets error message Error occurred during submission in the console

  Scenario: Verify that user receives Success message on submitting valid python code for "Search the array" question
    Given The user is on the Search the array practice question editor
    When The user writes valid code from row number and clicks the Submit Button Search the array
    Then The user gets success message Submission successful in the console

  Scenario: Verify that user receives error for invalid python code on running Search the array question
    Given The user is on the Max Consecutive Ones practice question editor
    When The user writes invalid code from row number and clicks the Run Button Max Consecutive Ones
    Then The user must see an error message in the alert window

  Scenario: Verify that user is able to run valid python code for Max Consecutive Ones question
    Given The user is on the Max Consecutive Ones practice question editor
    When The user writes valid code from row number and clicks the Run Button Max Consecutive Ones
    Then The user must see output in the console

  Scenario: Verify that user receives error on submitting invalid python code for "Max Consecutive Ones" question
    Given The user is on the Max Consecutive Ones practice question editor
    When The user writes invalid code from row number and clicks the Submit Button Max Consecutive Ones
    Then The user gets error message Error occurred during submission in the console

  Scenario: Verify that user receives Success message on submitting valid python code for "Max Consecutive Ones" question
    Given The user is on the Max Consecutive Ones practice question editor
    When The user writes valid code from row number and clicks the Submit Button Max Consecutive Ones
    Then The user gets success message Submission successful in the console

  Scenario: Verify that user receives error for invalid python code on running Find Numbers with Even Number of Digits question
    Given The user is on the Find Numbers practice question editor
    When The user writes invalid code from row number and clicks the Run Button Find Numbers
    Then The user must see an error message in the alert window

  Scenario: Verify that user is able to run valid python code for Find Numbers with Even Number of Digits question
    Given The user is on the Find Numbers practice question editor
    When The user writes valid code from row number and clicks the Run Button Find Numbers
    Then The user must see output in the console

  Scenario: Verify that user receives error on submitting invalid python code for Find Numbers with Even Number of Digits question
    Given The user is on the Find Numbers practice question editor
    When The user writes invalid code from row number and clicks the Submit Button Find Numbers
    Then The user gets error message Error occurred during submission in the console

  Scenario: Verify that user receives Success message on submitting valid python code for Find Numbers with Even Number of Digits question
    Given The user is on the Find Numbers practice question editor
    When The user writes valid code from row number and clicks the Submit Button Find Numbers
    Then The user gets success message Submission successful in the console

  Scenario: Verify that user receives error for invalid python code on running Squares of a Sorted Array question
    Given The user is on the Squares of a Sorted Array practice question editor
    When The user writes invalid code from row number and clicks the Run Button Sorted Array
    Then The user must see an error message in the alert window

  Scenario: Verify that user is able to run valid python code for Squares of a Sorted Array question
    Given The user is on the Squares of a Sorted Array practice question editor
    When The user writes valid code from row number and clicks the Run Button Sorted Array
    Then The user must see output in the console

  Scenario: Verify that user receives error on submitting invalid python code for Squares of a Sorted Array question
    Given The user is on the Squares of a Sorted Array practice question editor
    When The user writes invalid code from row number and clicks the Submit Button Sorted Array
    Then The user gets error message Error occurred during submission in the console

  Scenario: Verify that user receives Success message on submitting valid python code for Squares of a Sorted Array question
    Given The user is on the Squares of a Sorted Array practice question editor
    When The user writes valid code from row number and clicks the Submit Button Sorted Array
    Then The user gets success message Submission successful in the console

  Scenario: Verify that user is able to navigate to "Arrays Using List" page
    Given The user is in the Array page
    When The user clicks Arrays using List button
    Then The user should be redirected to Arrays using List page

  Scenario: Verify that the user is able to scroll down and click Try Here button
    Given The user is on the Arrays using List page
    When The user scrolls down and clicks Try Here button in Arrays Using List page
    Then The user IS redirected to a page having an try Editor with a Run button to test

  Scenario: Verify that user is able to navigate to "Practice Questions" Page for "Arrays Using List" page
    Given The user is on the Arrays using List page
    When The user clicks Practice Questions button
    Then The user is redirected to Practice page

  Scenario: Verify that user is able to navigate to "Basic Operations in List" page
    Given The user is in the Array page
    When The user clicks Basic Operations in List button
    Then The user should be redirected to Basic Operations in List page

  Scenario: Verify that the user is able to scroll down and click Try Here button
    Given The user is on the Basic Operations in List page
    When The user scrolls down and clicks Try Here button in Basic Operations in List page
    Then The user IS redirected to a page having an try Editor with a Run button to test

  Scenario: Verify that user is able to navigate to "Practice Questions" Page for "Basic Operations in List" page
    Given The user is on the Basic Operations in List page
    When The user clicks Practice Questions button
    Then The user is redirected to Practice page

  Scenario: Verify that user is able to navigate to "Applications of Array" page
    Given The user is in the Array page
    When The user clicks Applications of Array button
    Then The user should be redirected to Applications of Array page

  Scenario: Verify that the user is able to scroll down and click Try Here button
    Given The user is on the Applications of Array page
    When The user clicks Try Here button in Applications of Array page
    Then The user IS redirected to a page having an try Editor with a Run button to test

  Scenario: Verify that user is able to navigate to "Practice Questions" Page for "Applications of Array" page
    Given The user is on the Applications of Array page
    When The user clicks Practice Questions button
    Then The user is redirected to Practice page
