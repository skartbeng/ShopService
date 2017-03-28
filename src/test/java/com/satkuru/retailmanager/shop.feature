Feature: Shop

  Scenario: a new shop
    Given A new shop
    When I call the rest service
    Then there should be one new shop

  Scenario: a shop details are being updated
    Given shop details
    When I call the rest service
    Then it should update the shop details