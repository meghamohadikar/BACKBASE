# Adding extra claims to JWT tokens

As part of the authentication service customization you might want to add extra claim to the External or Internal JWT Token.

To do that you need to implement an interface that comes along with the authentication service.

What you are requested in this exercise is to implement ExternalJwtMapper interface to add extra claims that will be sent to an upstream service.

---

## Instructions: Development environment setup

In order to setup the exercise you need **platform services** (services located inside platform folder).

### Step 1: Start your platform services

	Note: 	If they are already running you can skip this step. 
			Make sure you stopped your authentication-ldap service from blade console.
			We will use our custom authentication service which is provided for you.

1. Follow [this guide](https://bitbucket.org/backbase/cxs-wc2-setup) to start your platform services.

2. Access [Blade console](http://localhost:8080) and check if services were successfully started. You should see only **registry** and **gateway** in blade console.

### Step 2: Solve the TODO's

1. Clone this repository

1. Solve the TODO's in this project

### Step 3: Run the service

Move into the **root** of your project and run the command :

	mvn spring-boot:run

### Step 4: Test the service

1. Open your browser console and go to the network tab

1. Access the authentication service and log in:

	[http://localhost:8080/gateway/api/auth/login](http://localhost:8080/gateway/api/auth/login)

1. Go to the response, on the console, and copy the token from the Response Headers

1. Visit to [https://jwt.io/](https://jwt.io/) website