Feature: Shop

  Scenario: a new shop
    Given A new shop
    | name  | number | postCode |
    | shop1 | 25     | NW9 0NS  |
    When I call the rest service
#    Then there should be one new shop

#  Scenario: a shop details are being updated
#    Given shop details
#    When I call the rest service
#    Then it should update the shop details
#
#  Scenario: for a location find nearest shop
#    Given A location
#    When I call the rest service
#    Then it should return one nearest shop