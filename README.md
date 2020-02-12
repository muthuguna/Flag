There are two components being created.
1. Flag services component (flag-service)
2. MySQL DB services component(mysql-db-service)

1. maven clean install on pom.xml in flag-service and run the FlagServiceApplication
2. maven clean install on pom.xml in mysql-db-service and run the MysqlDbServiceApplication

This application is being developed with UI (Angular) as well
Flag service application can be accessed using the below URL
http://localhost:8400/index.html

All the user activity will audited in a MySQL DB table “Audit”
Test suit has been created to test all the service methods using MockMvc in springiest framework.
Used log4j framework for logging the application execution flow with debug enabled.


