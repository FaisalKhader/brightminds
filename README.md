# brightminds
JavaAssignment

1- first in order to connect to access database file you need to change the file path in application.properties file unde the resources
2- you need install postman so you can perform the GET requset.
3- run "BmJavaTestApplication.java" as Java application
4- when finish open postman and create new Request
5- put hte URL http://localhost:8080/statement/account/{accountId} and choose GET method an fill the account ID
6- go to "Authorization" tab choose "Basic Auth" and fill the username and password (user,user / admin,admin)
7. go to the "Body" tab choose "RAW" and "JSON" type
8. file the JOSN request data as below structure:
{
"fromDate" : "",
"toDate": "",
"fromAmount": "",
"toAmount":""
}
9- send the request

Note:
I have used Sonar Lint plugin whic h doesn;t provide HTML report but i included the report as .jpg
