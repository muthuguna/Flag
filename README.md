There are two components being created.
1. Flag services component (flag-service)
2. MySQL DB services component(mysql-db-service)

Installation Steps:
1. maven clean install on pom.xml in flag-service and run the FlagServiceApplication
2. maven clean install on pom.xml in mysql-db-service and run the MysqlDbServiceApplication

This application has been developed with UI (Angular) as well.
Flag service application can be accessed using the below URL
http://localhost:8400/index.html

Services can be access using below URLs

http://localhost:8400/rest/flagService/
http://localhost:8400/rest/flagService/continents
http://localhost:8400/rest/flagService/countries/america
http://localhost:8400/rest/flagService/flag/india


All the user activity will be audited in a MySQL DB table “Audit”
Test suit has been created to test all the service methods using MockMvc in springiest framework.
Used log4j framework for logging the application execution flow with debug enabled.


