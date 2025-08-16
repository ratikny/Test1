Feature: Website steps

  Scenario: Test website functionality

    Given I opened main page of website
    When I click "Elements" button
    And I click "Radio Button" section
    And I choose "Yes" radio button
    And I click "Text Box" section
    And I fill "Full Name" text box
    And I fill "Email" text box
    Then I close browser
