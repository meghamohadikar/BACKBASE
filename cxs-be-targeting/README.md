# Implementing targeting services on Backbase 6

This exercise will show you how to write a custom targeting service for Backbase 6.
You will create an age collector and display some content based on the age of the logged user.

---

## Instructions: Development environment setup
In order to setup the exercise you need platform services and CXS.

### Step 1: Start your platform services and CXS

1. Follow [this guide](https://bitbucket.org/backbase/cxs-wc2-setup) to start your platform services and CXS.

1. Access [here](http://localhost:8080) and check if **platform services** were successfully started.
 
1. Access [here](http://localhost:9080) and check if **customer experience services** were successfully started.

### Step 2: Cloning projects

This repository contains two modules partially built. 

* *custom-user-service* 
* *targeting-service* 

The ***custom-user-service*** project is a dummy project created to add age to three users defined by the ldap file inside the CX6 project.

You can find the LDAP file inside CX6 project generate from start-training ***platform/config/backbase/authentication-ldap/users.ldif***

Clone this repository into your computer

Go to **CustomUserDAO** class located inside **custom-user-service** and check the users with age added to them.


### Step 3: Start custom-user-service

Start ***custom-user-service*** running the following command inside the root of custom-user-service project

	mvn spring-boot:run

### Step 4: Follow the TODOs

Go to ***targeting-service*** project and follow the TODO's inside the project

### Step 6: Run the service

Run the following command inside the ***targeting-service*** root:

	mvn spring-boot:run
	
### Step 7: Test the service

1. Access Experience Manager

	[http://localhost:8080/gateway/cxp-manager](http://localhost:8080/gateway/cxp-manager)

1. Add a targeting container in a page

1. Create alternative views using age collector

1. Test the page with the following users and passwords:

	* `manager`/`manager`: 45 years old
	* `user`/`user`: 33 y.o.
	* `admin`/`admin`: 38 y.o.