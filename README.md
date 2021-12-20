# **Football Manager Project**

This backend project includes all CRUD operations to manage football teams with Spring JPA, developed as a microservice behind an Eureka Server and Api Gateway. It also includes unit testing, swagger documentation and an integrated data base.

In order to run the aplication dowload the complete repository and open it on your IDE of choice. Then run each individual project as a Java Aplication in this order:

- DiscoveryService
- ApiGateway
- FootballManager

If everithing worked correctly you can now open the Eureka interface by accesing **http://localhost:8010/** in your browser.

![Eureka Server](https://github.com/Serpache/FootballManager/blob/main/.attachments/EurekaServer.PNG)

As you can see in the previous image you should have inside your Eureka Server an instance of **API-GATEWAY** and **FOOTBALLMANAGER-WS**. The first one is use internally by the app but the second one is importat to see the database and swagger documentation, so open it in two separate windows.

![Football manager WS](https://github.com/Serpache/FootballManager/blob/main/.attachments/FootballManagerWS.PNG)

The path to localhost for this web service change everytime you run Eureka, so do not worry if the digits for the port are diferent in your browser. Now you will have to change the last part of the URL **/actuator/info** for **/swagger-ui.html** to see the swagger: 

![Swagger](https://github.com/Serpache/FootballManager/blob/main/.attachments/Swagger.PNG)

And **/actuator/info** for **/h2-console** in the other window for the database:

![Swagger](https://github.com/Serpache/FootballManager/blob/main/.attachments/Database.PNG)