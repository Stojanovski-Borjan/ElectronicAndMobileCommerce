# Electronic and Mobile Commerce 2019-2020

### Instructions

 In order to run the production build you need only Docker.
 
 After making sure you have you docker machine up and running the next step is to run the [init.sh](https://github.com/Stojanovski-Borjan/ElectronicAndMobileCommerce/blob/master/init.sh). It is located in the root directory of the repository.
 
 Run the backend spring boot application on *docker-machineIP:8080/* and the frontend react app on *docker-machineIP:3000*. If you use newer version of docker then you should be able
 to access the applications on *localhost*.
 
 - Running development build
 
 Before opening this repo in you favourite IDE, make sure you have POSTGRES db up and running. You can do that however you wish. I recommend:
 
 1. Run the database on a docker-machine. Just run the "init-db.sh" in the root directory. It will run docker-compose and serve the database in a container. If you decide
 to go with this method and docker is not running the services on localhost, then inside [application.properties](https://github.com/Stojanovski-Borjan/ElectronicAndMobileCommerce/blob/master/spring/src/main/resources/application.properties) you will need to put you docker machine IP address.
 2. Install postgress database inside your operating system and set the exact configurations as the one inside the [application.properties](https://github.com/Stojanovski-Borjan/ElectronicAndMobileCommerce/blob/master/spring/src/main/resources/application.properties)
 
 After you are sure that your database is running, run the spring-boot application and then run the react-app from the "rent-scoot" directory with the command `npm start`;
 
 You now can open the backend spring boot application on *localhost:8080/* and the frontend react app on *localhost:3000*
 
### Features

 - This application is created with Spring boot as a backend service, ReactJS as a frontend UI and PostgreSQL as a database.
 
	- The Spring boot application is created following the MVC pattern with repository and service layering implementation for a well organised and secured data flow. The View part is the React app.
	
		- Models :
		
			* Locations
			* Vehicles
			* VehicleModels
			* Promotions
			* Reservations
			* Users
		
		- For persisting this data I used JPA and Spring Data in the repository layer.
		
		- Business logic well defined in the service layer.
		
		- API endpoints seperated in two RestControllers: RENTAL and MANAGE. 

		- The MANAGE controller holds all the endpoints that have acces to the management business logic for an app that the employees would use.

		- The RENTAL controller is the one I used so the client application could access the business logic needed for the rental store to work correctly.
 		
	- The React application is not huge, but I focused on implementing the main feature of a client able to make a reservation.
	
		- Key libraries used: 
		
			* axios - for well organised server-client communication. The calls are also organised in layers
			* QS - for parsing data for the API HTTP calls
			* Mapbox as 'react-maps-gl' - for an interactive map that I could customize and embed my react components.
			* Formik - for a great customised form. I used every feature from their documentation
			* Yup - for form validation. Great tool, did most of the work, but I had the need to implement my own validation methods.
			* moment - for easily manage Dates and make date comparisons for the necessary logic. Great for going through date formats also.
			* MaterialUI - they basicly have every component you need for the UI and it's fully responsive with the use of Grid or similar componentes.
			

Creator: **Borjan Stojanovski** 173253  
## Back-End (Java Spring Boot) and Front-End (React) application for the course *"Electronic and Mobile Commerce"* at the *Faculty of Computer Science and Engineering - Skopje*
