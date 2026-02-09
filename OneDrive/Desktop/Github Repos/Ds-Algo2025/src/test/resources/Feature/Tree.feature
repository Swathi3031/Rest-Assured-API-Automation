

Feature: To test Tree functionality

  Background: 
  Given user is signed into dsalgoapp
    When user cliks on TreeGetStarted button
    Then user lands  on the "Tree" page
    
  
    Scenario: Verify that user is able to navigate to Tree page and see header secetion elements
    Given user is on Tree page
    Then user  able to see NumpyNinja,Data structures dropdown,username and signout links on page
    
   
    
  Scenario Outline: Verify that user is able to navigate to all the links in tree page
    Given user is on Tree page
    When user clicks on "<Topic>" link
    Then user should be navigated to "<ExpectedTitle>" page

    Examples: 
      | Topic                          | ExpectedTitle                  |
      | Overview of Trees              | Overview of Trees              |
      | Terminologies                  | Terminologies                  |
      | Types of Trees                 | Types of Trees                 |
      | Tree Traversals                | Tree Traversals                |
      | Traversals-Illustration        | Traversals-Illustration        |
      | Binary Trees                   | Binary Trees                   |
      | Types of Binary Trees          | Types of Binary Trees          |
      | Implementation in Python       | Implementation in Python       |
      | Binary Tree Traversals         | Binary Tree Traversals         |
      | Implementation of Binary Trees | Implementation of Binary Trees |
      | Applications of Binary trees   | Applications of Binary trees   |
      | Binary Search Trees            | Binary Search Trees            |
      | Implementation Of BST          | Implementation Of BST          |

Scenario: Verify that user is able to navigate to Practice page
Given user is on the overview of tree page
When user clicks on the Practice Questions link
Then user should land on "Practice Questions"


Scenario Outline: Verify Try here button on each page fo Tree page links
Given user is on the overview of tree page
When user clicks on "<Treelinks>" and Try Here button
Then user should be navigated to Try Editor page with title "Assessment" and  Run button

      Examples: 
      | Treelinks                      |
      | Overview of Trees              | 
      | Terminologies                  |
      | Types of Trees                 | 
      | Tree Traversals                | 
      | Traversals-Illustration        | 
      | Binary Trees                   | 
      | Types of Binary Trees          | 
      | Implementation in Python       | 
      | Binary Tree Traversals         | 
      | Implementation of Binary Trees | 
      | Applications of Binary trees   | 
      | Binary Search Trees            | 
      | Implementation Of BST          |
      
Scenario: Verify that user recieves error messgae on click on run button without entering code
Given user is on the try Editor page
When user clicks on the run button without code 
Then user should see  error message in alert window 

Scenario Outline: Verify that user recieves error messgae on click on run button with incorrect code
    Given user is on the try Editor page
    When user clicks on the run button with incorrect code from "<sheetname>" and <rownumber>
    Then user should see  error message in alert window and get the alert text

    Examples: 
      | sheetname  | rownumber |
      | pythoncode |         1 |
      
Scenario Outline: Verify tryEditor with correct Python code  and click on run and output matches with expected output
    Given user is on the try Editor page
    When user writes correct Python code from "<sheetname>" and <rownumber>
    Then output should match with the expected result "<expectedOutput>"

    Examples: 
      | sheetname  | rownumber | expectedOutput |
      | pythoncode |         0 | hello          |

Scenario: Verify that user is able to navigate to Overview of Trees page  by clicking browser back button
    Given user is on the try Editor page
    When user clicks on the browser back button
    Then user should lands on "Overview of Trees" page

Scenario: Verify if user is able to navigate to Home page on clicking "SignOut " link

Given user is on Tree page
When  user clicks on the Signout button
Then  user should land on Home page and can see "Logged out successfully" message         
       


