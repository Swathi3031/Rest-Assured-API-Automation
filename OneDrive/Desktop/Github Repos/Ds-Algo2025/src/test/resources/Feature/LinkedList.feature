
Feature: Linked List feature in DSAlgo

  Background: 
    Given user is on sign to app to click Linked List
    When user cliks on Linked List GetStarted button
    Then user lands on Linked List page

  Scenario: Verify that user is on Linked List page
    Given The user is on LinkedList page
    When user clicks on Introduction on LinkedList Page
    Then user lands on LinkedList page and able to see NumpyNinja,Data structures dropdown,username and signout links

  Scenario Outline: Verify that user is able to navigate to "<Topic>" page
    Given user is on linkedlist home page
    When user clicks on "<Topic>" link on linkedlist page
    Then user should be navigated to "<Topic>" page of linkedlist

    Examples: 
      | Topic                           |
      | Introduction                    |
      | Creating Linked LIst            |
      | Types of Linked List            |
      | Implement Linked List in Python |
      | Traversal                       |
      | Insertion                       |
      | Deletion                        |

  Scenario Outline: Verify Try here button on each page of LinkedList page links
    Given user is on the "<LinkedListlinks>" page of Linkedlist
    When user clicks on tryHere button of LinkedList page
    Then user should be navigated to Try Editor page with Run button on linkedlist

    Examples: 
      | LinkedListlinks                 |
      | Introduction                    |
      | Creating Linked LIst            |
      | Types of Linked List            |
      | Implement Linked List in Python |
      | Traversal                       |
      | Insertion                       |
      | Deletion                        |

  Scenario Outline: Verify that user is able to see output for valid python code for Linkedlist links
    Given The user is in the tryEditor page on Linkedlist page
    When user writes Python code from "<sheetname>" and <rownumber>  on Linkedlist links and click the Run button
    Then output should match with expected result from "<sheetname>" and <rownumber> for Linkedlist pages

    Examples: 
      | sheetname  | rownumber |
      | pythoncode |         0 |

  Scenario: Verify that user recieves error messgae on click on run button without entering code on Linkedlist links
    Given user is on try Editor page on Linkedlist
    When user clicks on run button without code for Linkedlist links
    Then user should see the error message in alert window Linkedlist links

  Scenario Outline: Verify that user recieves error messgae on click on run button with incorrect code for Linkedlist links
    Given user is on try Editor page on Linkedlist
    When user clicks on run button with incorrect code from "<sheetname>" and <rownumber> for Linkedlist links
    Then user should see the error message in alert window and get the alert text for Linkedlist links

    Examples: 
      | sheetname  | rownumber |
      | pythoncode |         1 |

  Scenario Outline: Verify  that user canot perform any operations without clicking ok button on alert window on Linkedlist links
    Given user is on try Editor page on Linkedlist
    When user types incorrect code from "<sheetname>" and <rownumber> on Linkedlist links
    Then user should see alert window and can not click on run button on Linkedlist links

    Examples: 
      | sheetname  | rownumber |
      | pythoncode |         1 |

  Scenario: Verify that user is able to navigate to Practice page on Linkedlist page
    Given user is on Introduction page Of LinkedList
    When user clicks on Practice Questions link on Linkedlist
    Then user should land on practice page on Linkedlist

  Scenario: Verify if user is able to navigate to Home page on clicking SignOut link from Linkedlist
    Given The user is on LinkedList page
    When The user clicks on Sign out button on Linkedlist page
    Then user should navigate back to Home page from linkedlist and can view "Logged out successfully" message

