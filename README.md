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

![Database](https://github.com/Serpache/FootballManager/blob/main/.attachments/Database.PNG)

For the credentials use **User Name: Sergio** and **Password: 123**. Do not worry about security, this database is just to see the results of the JPA operations, it is empty and gets destroyed every time you shut down the web service.

Now you can start using the web service and try the methods through the swagger:

![Swagger use](https://github.com/Serpache/FootballManager/blob/main/.attachments/SwaggerUse.PNG)

Or you can ignore the swagger and access the methods through **Postman** request:

![Postman example](https://github.com/Serpache/FootballManager/blob/main/.attachments/PostmanExample.PNG)

Here you do not need to worry for the dynamic port of the web service, as you will access it through API-GATEWAY, wich has a fix port in **http://localhost:8082/**. The next part of the request URL you see on the picture corresponds to the web service name, in this case **FOOTBALLMANAGER-WS/** and then you can type any of the end point available on the **FootballController**. Some of them need a body likke the **POST** to **/footballManager** on the picture that creates a new team, others need a **parameter** and the rest just need to be called, all this details are specify on the controller and swagger documentation.

Remember that while using the web service you can check the status of the database via **h2-console** any time you want.