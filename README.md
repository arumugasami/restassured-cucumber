#restassured-cucumber

This project creates an automated test that will test the webservices for the following
http://services.groupkt.com/country/get/all
http://services.groupkt.com/country/get/iso2code/{COUNTRY_ISO2CODE} (e.g. http://services.groupkt.com/country/get/iso2code/us )
It is a Maven project using cucumber and restassured to test the webservices

##Getting Started and installing:
This project can be imported directly as a Maven project. All the dependency needed for the project are handled in the POM.
Feature files list down all the scenarios under test.

##Running the Tests and Test Reports:
This project currently has the capability to run be directly imported and run as junit tests.


##Tests:
There are fours tests that are present
1)Testing all country webservice
2)Testting individual country webservice
3)Testing invalid country webservice
4)Testing post request webservice(which is to be implemented)
