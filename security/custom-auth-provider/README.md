# Create a custom authentication provider

An authentication request is processed by an **AuthenticationProvider** and a fully authenticated object with credentials is returned.

This tutorial will walk you through the creation of a custom authentication provider.

---

## Instructions: Development environment setup

In order to setup the exercise you need **platform services** (services located inside platform folder).

### Step 1: Start your platform services

	Note: 	If they are already running you can skip this step. 
			Make sure you stopped your authentication-ldap service from blade console.
			We will use our custom authentication service which is provided for you.

1. Follow [this guide](https://bitbucket.org/backbase/cxs-wc2-setup) to start your platform services.

2. Access [Blade console](http://localhost:8080) and check if services were successfully started. You should see only **registry** and **gateway** in blade console.

### Step 2: Create a custom authentication provider

1. Clone this repository

1. Solve the TODO's inside the project

### Step 3: Run the authentication provider 

Move into the **root** of your custom authentication provider folder and run the command :

	mvn spring-boot:run

### Step 4: Test the service

Access the authentication service and try to log in:

[http://localhost:8080/gateway/api/auth/login](http://localhost:8080/gateway/api/auth/login)

You should be able to login with any user.