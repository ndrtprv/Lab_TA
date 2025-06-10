@Registration
Feature: Registration
  As an Un-registered User of the application
  I want to validate the Registration functionality
  In order to check if it works as desired

  Background: User navigates to Registration page
    Given I navigate to the Registration page

  @SuccessfulRegistration
  Scenario Outline: Successful Registration using valid credentials
    When I fill in name with "<name>"
    And I fill in surname with "<surname>"
    And I fill in email with "<email>"
    And I fill in password with "<password>"
    And I fill in confirm password with "<confirm password>"
    And I click on the Sign Up button
    Then I should be successfully registered
    And I should land on the Home page
    And I should see success message as "Congratulations!"
    And I should see Dashboard and Logout links
    Examples:
      | name      | surname | email                 | password  | confirm password |
      | asdf      | asdf    | asdf.asdf@example.com | Asdf@1234 | Asdf@1234        |


  @DisabledRegistration
  Scenario Outline: Disabled Registration when one of the required fields is left blank
    When I fill in name with "<name>"
    And I fill in surname with "<surname>"
    And I fill in email with "<email>"
    And I fill in password with "<password>"
    And I fill in confirm password with "<confirm password>"
    And I click on the Sign Up button
    Then I should see "<form error>" message on Registration page on "<xpath>"
    And I should not be able to submit the Registration form
    Examples:
      | name     | surname | email                 | password  | confirm password | form error             | xpath                                           |
      |          | asdf    | asdf.asdf@example.com | Asdf@1234 | Asdf@1234        | Enter first name       | /html/body/div[1]/main/div/form/div[2]/div[1]/p |
      | asdf     |         | asdf.asdf@example.com | Asdf@1234 | Asdf@1234        | Enter last name        | /html/body/div[1]/main/div/form/div[2]/div[2]/p |
      | asdf     | asdf    |                       | Asdf@1234 | Asdf@1234        | Enter a valid email    | /html/body/div[1]/main/div/form/div[3]/p        |
      | asdf     | asdf    | asdf                  | Asdf@1234 | Asdf@1234        | Enter a valid email    | /html/body/div[1]/main/div/form/div[3]/p        |
      | asdf     | asdf    | asdf.asdf@example.com | Asdf@1234 |                  | Confirm your password  | /html/body/div[1]/main/div/form/div[5]/p        |
      | asdf     | asdf    | asdf.asdf@example.com |           | Asdf@1234        | Enter a password       | /html/body/div[1]/main/div/form/div[4]/p        |