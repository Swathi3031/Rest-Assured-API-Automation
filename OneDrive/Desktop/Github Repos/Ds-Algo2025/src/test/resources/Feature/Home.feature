Feature: DS Algo Home Page

  Background: 

  Scenario: User launch home page of an dsalgo project
    Given The user opens DS Algo portal link
    When The user clicks the "Get Started" button
    Then The user should be redirected to homepage

  Scenario Outline: User is on Home page and click getstarted link "<option>" on home page without sign in
    Given The user is on Home page
    When The user clicks on Get Started link on homepage "<option>" without login
    Then The user get warning message "You are not logged in"

    Examples: 
      | option          |
      | Data Structures |
      | Arrays          |
      | Linked List     |
      | Stack           |
      | Queue           |
      | Tree            |
      | Graph           |

  Scenario Outline: User is on Home page and click on dropdown "<option>" without sign in
    Given The user is on Home page
    When The user clicks on dropdown "<option>"
    Then The user get warning message "You are not logged in"

    Examples: 
      | option      |
      | Arrays      |
      | Linked List |
      | Stack       |
      | Queue       |
      | Tree        |
      | Graph       |

  Scenario Outline: User is on Home page and click getstarted link "<option>" on home after sign in
    Given The user is logged in and on the Home page
    When The user clicks on Get Started link on homepage "<option>" with login
    Then The user land on correponding pages "<option>"

    Examples: 
      | option          |
      | Data Structures |
      | Arrays          |
      | Linked List     |
      | Stack           |
      | Queue           |
      | Tree            |
      | Graph           |

  Scenario Outline: User is on Home page and click on dropdown "<option>" with sign in
    Given The user is logged in and on the Home page
    When The user clicks on dropdown "<option>"
    Then The user land on correponding pages "<option>"

    Examples: 
      | option      |
      | Arrays      |
      | Linked List |
      | Stack       |
      | Queue       |
      | Tree        |
      | Graph       |

  Scenario: User is on Home page and click on sign in
    Given The user is on Home page
    When The user clicks on signin link
    Then The user redirected to login page

  Scenario: User is on Home page and click on Register
    Given The user is on Home page
    When The user clicks on register link
    Then The user redirected to Registration page

  Scenario: Verify if user is on Home page and click on SignOut
    Given The user is logged in and on the Home page
    When The user clicks on Sign out button on Home page
    Then user should able signout on Home page and can view "Logged out successfully" message
