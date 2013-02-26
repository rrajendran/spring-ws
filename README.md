spring-ws
=================

Getting started with spring webservice

Projects
==================
1. person-bindings - schema definition
2. spring-ws-service - Webservice
3. spring-ws-client - Test the webservice.


To Run
=================
1. Go spring-ws folder
2. Execute mvn clean install. [spring-ws-client project will fail. So ignore it.]
3. Go to spring-ws-service
4. Execute mvn clean jetty:run-war
5. Wsdl is exposed at url <a href="http://localhost:8080/spring-ws-service/personService/person.wsdl">Access wsdl here</a>
6. Go to spring-ws-client
7. Execute mvn test.
