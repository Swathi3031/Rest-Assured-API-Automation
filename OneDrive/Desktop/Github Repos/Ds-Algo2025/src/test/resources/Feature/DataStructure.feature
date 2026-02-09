

Feature: To test DataStructuresIntroduction functionality

  Background: 
    Given user is sigin to app
    When user cliks on DtastructureInro GetStarted button
    Then user lands on data-structures-introduction page

  Scenario: Verify that user is able to navigate to "Data Structures - Introduction" page and see header secetion elements
    Given user is on data-structures-introduction page
    Then user  able to see NumpyNinja,Data structures dropdown,username and signout links

  Scenario: Verify that user is able to navigate to "Time Complexity" page
    Given user is on data-structures-introduction page
    When user ckicks on Time complexity link
    Then user lands on  the "Time Complexity" page

  Scenario: Verify that user is able to navigate to "Practice" page
    Given user is on Time complexity page
    When user clicks on Practice Questions link
    Then user should land on "practice" page

  Scenario: Verify that user is able to navigate to "try Editor" page
    Given user is on Time complexity page
    When user clicks on try here link
    Then user should land on "tryEditor" page with  Run button

  Scenario: Verify that user recieves error messgae on click on run button without entering code
    Given user is on try Editor page
    When user clicks on run button without code
    Then user should see the error message in alert window

  Scenario Outline: Verify that user recieves error messgae on click on run button with incorrect code
    Given user is on try Editor page
    When user clicks on run button with incorrect code from "<sheetname>" and <rownumber>
    Then user should see the error message in alert window and get the alert text

    Examples: 
      | sheetname  | rownumber |
      | pythoncode |         1 |

  Scenario Outline: Verify  that user canot perform any operations without clicking ok button on alert window
    Given user is on try Editor page
    When user types incorrect code from "<sheetname>" and <rownumber>
    Then user should see alert window and can not click on run button

    Examples: 
      | sheetname  | rownumber |
      | pythoncode |         1 |

  Scenario Outline: Verify that Python code runs and output matches expected
    Given user is on try Editor page
    When user writes Python code from "<sheetname>" and <rownumber>
    Then output should match with expected result "<expectedOutput>"

    Examples: 
      | sheetname  | rownumber | expectedOutput |
      | pythoncode |         0 | hello          |

  Scenario: Verify that user is able to navigate to "Time Complexity" page  by clicking browser back button
    Given user is on try Editor page
    When user clicks on browser back button
    Then user lands on  the "Time Complexity" page

  Scenario: Verify if user is able to navigate to Home page on clicking "SignOut " link
    Given user is on Time complexity page
    When user clicks on Signout button
    Then user should land on Home page and can view "Logged out successfully" message

