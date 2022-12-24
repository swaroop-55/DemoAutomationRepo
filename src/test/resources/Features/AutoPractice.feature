@AuomateApp
Feature: AutomationTesting website Automation

  Background: Navigation to the url
    Given User navigated to the home application url

  @LoginNegative
  Scenario Outline: User is unable to login into the application
    Given User click on My Account page from homepage
    When User redirected to the Account page of the application where title is "My Account – Automation Practice Site"
    And User enters "<username>" and "<password>" and click on login button
    Then User is unable to login with an error message "Error: A user could not be found with this email address."

    Examples: 
      | username             | password  |
      | testUser99@gmail.com | user@987  |
      | testUser98@gmail.com | user@7896 |

  @ProductLists
  Scenario: User able to see product category on landing page
    When User see the product category
    Then Validate product category as per expected product category listed below
      | Android (1)    |
      | HTML (3)       |
      | JavaScript (3) |
      | selenium (1)   |
    And Size of product category shoud be 4

  @DisplayLogo
  Scenario: Logo present on the landing page with specific height and width dimension
    Given User see the logo of the application
    When User fetch the height and width of logo
    Then Height and width of logo should be "520"
