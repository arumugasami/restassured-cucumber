Feature: Webservice Testing of countries
  
Scenario : User calls web service to get all countries and validate that certain
    countries are present in it
     Given the user makes a call to the all country service
     When a user searches the countries by code
      | Fields   | Values |
      | country1 | US     |
      | country2 | DE     |
      | country3 | GB     |

   Then the status code is Http Ok
   
  Scenario Outline: User calls web service for an individual country and validate
    the response

    Given the user calls the "<country>" service
    When a user validates response the "<name>","<alpha2_code>", "<alpha3_code>"
    Then the status code is Http Ok

    Examples: 
      | country     | name                                                 | alpha2_code | alpha3_code |
      | iso2code/us | United States of America                             | US          | USA         |
      | iso2code/de | Germany                                              | DE          | DEU         |
      | iso2code/gb | United Kingdom of Great Britain and Northern Ireland | GB          | GBR         |

  Scenario Outline: User calls web service for an invalid country and validate
    the response

    Given the user calls the "<country>" service
    When a user validates response the "<message>"

    Examples: 
      | country | message                                 |
      | isocode/ui      | The requested resource is not available |
      | isocode/po      | The requested resource is not available |
      

    #Scenario Outline: User calls the web service to add a new country to it
    # Given the user makes a post request of with "<name>","<alpha2_code>", "<alpha3_code>"
    #When user gets a valid response back with "<country>" 
    # And status code is Http Ok
    
     # Examples: 
     #| name         | alpha2_code | alpha3_code |
     #| test_country | test_2      | test_2      |
