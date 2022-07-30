# hackathon

This maven java project with appium framework contains code to solve the following scenarios.

### Scenario - 1
* Launch The App
* Get The Coordinates of Login Button Using pCloudy’s OCR 
* Perform Click Operation 
* Perform Double Tab Operation. 

### Scenario - 2
* Launch The App
* Get The Coordinates of Login Button Using pCloudy’s OCR 
* Perform Click Operation 
* Draw the given emoji

###  Scenario - 3
* Launch the app
* Get the coordinates of login button using pCloudy’s OCR 
* Perform Click Operation 
* Perform drag and drop operations 

### Scenario - 4
* Launch the app, get the coordinate of login button using pCloudy OCR and perform Click operation, click the base image and capture screenshot.Then go back and click on secondary image using pCloudy Visual AI commands and capture the difference image.
* get all text present in the base image using pCloudy OCR 
* verify if “Titans” word exist in base image using pCloudy OCR

## Requirements to run

- Java >8
- Maven

## How to run this project

- Clone the repository https://github.com/SPrasadT/hackathon.git
- cd hackathon
- Install the pCloudy connector jar http://pcloudy-content-distribution.s3.amazonaws.com/pCloudy-Connector-Jars/v11/java/How%20to%20Install%20pCloudy-java-connector.jar%20in%20Maven.txt
- mvn exec:java -Dexec.mainClass="com.mob.tester.BaseTest" \ 
	-Dapp.username=MYTESTVAL \ 
	-Dapp.apikey=MYTESTVAL \
	-Dapp.deviceManufacturer=MYTESTVAL \
	-Dapp.deviceVersion=MYTESTVAL \
	-Dapp.platformVersion=MYTESTVAL \
	-Dapp.appName=MYTESTVAL \
	-Dapp.appPackage=MYTESTVAL \
	-Dapp.appPackage=MYTESTVAL \
	-Dapp.appPackage=MYTESTVAL \
	-Dapp.appActivity=MYTESTVAL