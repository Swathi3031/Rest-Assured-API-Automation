
Feature: Graph feature in DSAlgo

  Background: 
    Given user is on sign to app to click graph
    When user cliks on Graph GetStarted button
    Then user lands on Graph page

  Scenario: Verify that user is on "Graph" page
    Given user is on graph page
    When user clicks on graph of graph Page
    Then user lands on Graph page and able to see NumpyNinja,Data structures dropdown,username and signout links

  Scenario: Verify that user is able to navigate to "Graph" page
    Given user is on graph page
    When user clicks on graph of graph Page
    Then user lands on "graph" page of graph

  Scenario: Verify user able be navigate to "tryEditor" page
    Given user is on Graph of Graph page
    When user clicks on Tryhere link on Graph Page
    Then user should be directed to "tryEditor" page with Run button

  Scenario Outline: Verify that user is able to see output for valid python code for Graph page
    Given The user is in the tryEditor page on Graph page
    When user writes Python code from "<sheetname>" and <rownumber> and click the Run button
    Then output should match with expected result from "<sheetname>" and <rownumber>

    Examples: 
      | sheetname  | rownumber |
      | pythoncode |         0 |

  Scenario: Verify that user cannot click on run button without entering code on Graph page
    Given The user is in the tryEditor page on Graph page
    When user clicks on run button without code on Graph page
    Then user cannot able click on run button on Graph page

  Scenario Outline: Verify that user recieves error messgae on click on run button with incorrect code on Graph page
    Given The user is in the tryEditor page on Graph page
    When user clicks on run button with incorrect code from "<sheetname>" and <rownumber> on Graph page
    Then user should see the error message in alert window and get the alert text on Graph page

    Examples: 
      | sheetname  | rownumber |
      | pythoncode |         1 |

  Scenario Outline: Verify that user canot perform any operations without clicking ok button on alert window on Graph page
    Given The user is in the tryEditor page on Graph page
    When user types incorrect code from "<sheetname>" and <rownumber> on Graph page
    Then user should see alert window and can not click on run button on Graph page

    Examples: 
      | sheetname  | rownumber |
      | pythoncode |         1 |

  Scenario: Verify that user is able to navigate to "GraphReprasentions" page
    Given user is on Graph of Graph page
    When user clicks on GraphReprasentions of grapg Page
    Then user lands on "GraphReprasentions" of graph Page

  Scenario: Verify user able be navigate to "tryEditor" page
    Given user is on GraphReprasentions of Graph page
    When user clicks on Tryhere link on GraphReprasentions Page
    Then user should be directed to "tryEditor" page with Run button on GraphReprasentions page

  Scenario Outline: Verify that user is able to see output for valid python code for Graph page
    Given The user is in the tryEditor page on GraphReprasentions page
    When user writes Python code from "<sheetname>" and <rownumber> and click the Run button
    Then output should match with expected result from "<sheetname>" and <rownumber>

    Examples: 
      | sheetname  | rownumber |
      | pythoncode |         0 |

  Scenario: Verify that user cannot click on run button without entering code on GraphReprasentions page
    Given The user is in the tryEditor page on GraphReprasentions page
    When user clicks on run button without code on Graph page
    Then user cannot able click on run button on Graph page

  Scenario Outline: Verify that user recieves error messgae on click on run button with incorrect code on GraphReprasentions page
    Given The user is in the tryEditor page on Graph page
    When user clicks on run button with incorrect code from "<sheetname>" and <rownumber> on GraphReprasentions page
    Then user should see the error message in alert window and get the alert text on GraphReprasentions page

    Examples: 
      | sheetname  | rownumber |
      | pythoncode |         1 |

  Scenario Outline: Verify that user canot perform any operations without clicking ok button on alert window on GraphReprasentions page
    Given The user is in the tryEditor page on GraphReprasentions page
    When user types incorrect code from "<sheetname>" and <rownumber> on GraphReprasentions page
    Then user should see alert window and can not click on run button on GraphReprasentions page

    Examples: 
      | sheetname  | rownumber |
      | pythoncode |         1 |

  Scenario: Verify user is able to navigate to Graph page and click on "Practice" Questions
    Given user is in tryEditor page and navigates to GraphReprasentions page
    When user clicks on Practice Questions in graph representations page
    Then user should land on graph "Practice" page

  Scenario: Verify if user is able to navigate to Home page on clicking SignOut link
    Given The user is on Graph page
    When The user clicks on Sign out button on Graph Page
    Then user should navigate back to Home page from Graph and can view "Logged out successfully" message

