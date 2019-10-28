# shipt interview selenium project

Project requires [apache maven][mvn] to be set up, as well as [geckodriver][geckodriver] on your path to run the tests in Firefox 48 or above.  Open the shipt-selenium-java directory in a terminal/command prompt and run `mvn clean verify` to run the tests.

### Reporting

After running the tests, generate an [Allure][allure] test report by running:

```
mvn allure:report 
```
