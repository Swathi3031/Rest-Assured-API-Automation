
Feature: Queue feature in DSAlgo

  Background: 
    Given user is on sign to app to click Queue
    When user cliks on Queue GetStarted button
    Then user lands on "Queue" on page

  Scenario: Verify that user is on Queue page
    Given The user is on Queue page
    When user clicks on Implementation of Queue in Python on Queue Page
    Then user lands on Queue page and able to see NumpyNinja,Data structures

  Scenario Outline: Verify that user is able to navigate to "<Topic>" page on queue
    Given The user is on Queue page
    When user clicks on "<Topic>" link on Queue page
    Then The user should be navigated to "<Topic>" page on Queue

    Examples: 
      | Topic                                  |
      | Implementation of Queue in Python      |
      | Implementation using collections.deque |
      | Implementation using array             |
      | Queue Operations                       |

  Scenario Outline: Verify Try here button on each page of Queue page links
    Given user is on the "<Queuelinks>" page of Queue
    When user clicks on tryHere button on queue links
    Then user should be navigated to Try Editor page with Run button

    Examples: 
      | Queuelinks                             |
      | Implementation of Queue in Python      |
      | Implementation using collections.deque |
      | Implementation using array             |
      | Queue Operations                       |

  Scenario Outline: Verify that user is able to see output for valid python code for Queue links
    Given The user is in the tryEditor page on Queue page
    When user writes Python code from "<sheetname>" and <rownumber>  on queue links and click the Run button
    Then output should match with expected result from "<sheetname>" and <rownumber> for queue pages

    Examples: 
      | sheetname  | rownumber |
      | pythoncode |         0 |

  Scenario: Verify that user recieves error messgae on click on run button without entering code on queue links
    Given The user is in the tryEditor page on Queue page
    When user clicks on run button without code for queue links
    Then user should see the error message in alert window queue links

  Scenario Outline: Verify that user recieves error messgae on click on run button with incorrect code for queue links
    Given The user is in the tryEditor page on Queue page
    When user clicks on run button with incorrect code from "<sheetname>" and <rownumber> for queue links
    Then user should see the error message in alert window and get the alert text for queue links

    Examples: 
      | sheetname  | rownumber |
      | pythoncode |         1 |

  Scenario Outline: Verify  that user canot perform any operations without clicking ok button on alert window on queue links
    Given The user is in the tryEditor page on Queue page
    When user types incorrect code from "<sheetname>" and <rownumber> on queue links
    Then user should see alert window and can not click on run button on queue links

    Examples: 
      | sheetname  | rownumber |
      | pythoncode |         1 |

  Scenario: Verify that user is able to navigate to Practice page on Queue page
    Given user is on Implementation of Queue in Python page page Of Queue
    When user clicks on Practice Questions link on queue page
    Then user should land on practice page on queue page

  Scenario: Verify if user is able to navigate to Home page on clicking SignOut link
    Given The user is on Queue page
    When The user clicks on Sign out button on Queue page
    Then user should navigate back to Home page from queue and can view "Logged out successfully" message

