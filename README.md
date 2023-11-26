# Java-Cucumber-Framework

This framework is updated to support Selenium 4.15

I made updates only to two feature files:
1. login.feature (functioning, all test pass)
2. login2.feature (functioning, all test pass)

You can make changes (update) other feature files as needed. 

_**The framework is pointing to new URL, because old URL is no longer working._** 

**What is new in this version:**

- No separate installation of webdrivers required anymore (yay! 🎉) <br>
- Deleted WebDriverManager dependency from the POM <br>
- Updated Cucumber-Java and Cucumber-JUnit, and other dependencies to their latest versions <br>
- The following reports are functioning:
  - Built-in cucumber reports when run from TestRunner classes 
  - Maven reports when run via maven command lines 
  - Surefire plugin (partially working, _read Notes below_)

**Notes:** Maven Surefire Report plugin (fancy reports) only work when you run single feature file test via maven command line (mvn test). On multi-test scenarios it doesn't work. 
Nothing wrong with the plugin setup, its just Maven plugin compatibility issues due to Maven moving from 3.9.x version to 
Maven 4.0.x soon. Should fix itself when you upgrade to latest maven version, later.