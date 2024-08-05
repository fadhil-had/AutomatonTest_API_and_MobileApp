# API and Mobile App Automation Test
Task for automation testing for API and Mobile App testing

## To use the this code:

1. Fork this repository.
2. Clone, i.e, download your copy of the repository to your local machine using
```
git clone https://github.com/[your_username]/TestPintu.git
```
3. Import the project in IntelliJ IDEA.
4. Make your changes.
5. Run the test.

## The project uses the following:

- *[Java 22](https://www.oracle.com/de/java/technologies/downloads/#jdk22-windows)* as the programming language.
- *[IntelliJ IDEA](https://www.jetbrains.com/idea/)* as the IDE.
- *[Selenium WebDriver](https://www.selenium.dev/)* as the web browser automation framework using the Java binding.
- *[TestNG](https://testng.org/doc/)* as the testing framework for API Testing.
- *[RestAssured](https://github.com/rest-assured/rest-assured/wiki/ReleaseNotes52)* as the testing framework for API Testing.
- *[Appium](https://appium.io/docs/en/latest/)* as the mobile automation framework for mobile application Testing.
- *[JUnit](https://junit.org/junit5/)* as the testing framework for mobile application Testing.
- *[Appium Inspector](https://inspector.appiumpro.com/)* as the GUI assistant tool for Appium.
- *[Android SDK](https://developer.android.com/)* as the emulator for mobile application testing.

## Basic Usage

### API Testing
There are 2 APIs in this automation test. Get API and POST API.

- Get API
Get API *(https://jsonplaceholder.typicode.com/posts)* is API to get data with configuration for each variable:
  ■ userId -> Integer
  ■ id -> Integer
  ■ title -> String
  ■ body -> String

- Post API
Post API *(https://jsonplaceholder.typicode.com/posts)* is API to post new data with the payload consisting of:
  ■ title -> String
  ■ body -> String
  ■ userId -> Integer

 #### Code Steps
  1. Install RestAssured
  2. Add TestNG and several dependencies for testing in Pom.xml
  3. Open baseTest.java
  4. Set run/debug configuration using TestNG
  5. Click run for 2 methods for each API

### Mobile App Testing
Please note that we need to use an Android SDK or real device to run this automation test and save the .apk file on the folder then change the setup for appium:app with your folder location

Dependencies
```
{
  "appium:automationName": "UiAutomator2",
  "appium:platformName": "Android",
  "appium:platformVersion": "{android version},
  "appium:deviceName": "{device name}",
  "appium:app": "{location of apk}"
}
```

 #### Code Steps
  1. Install Appium
  2. Install Android SDK or setup real device
  3. Add JUnit and several dependencies in Pom.xml
  4. Open Login.java for login scenarios or Register.java for register scenarios
  5. Setup dependencies
  6. Run test
