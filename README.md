# Spring Cloud Workshop

Welcome to this workshop! Today, we will go trough some of the basic concepts you need to get into the cloud world. This workshop will (hopefully) guide you trough Microservices, Spring Data, Cloud Configuration, Registry Servers and API Gateways in simple very examples.

## Prerequisites:

* Java JDK 1.8
* Git Bash if you are using Windows or git command in Linux/Mac
* Your favourite IDE (Eclpse or Intellij)
* Postman for Chrome or Firefox

# Step by Step guide

## Config repository

1. Open a bash interpreter 
2. Create a folder called “config-repo” inside a “spring-cloud-workshop” folder 
    - `mkdir spring-cloud-workshop`
    - `mkdir config-repo`

3. Switch to that directory and create an empty git repository 
    - `cd config-repo`
    - `git init .`

4. Create a new file with our first property: 
    - `echo value=hello! > application.properties`

5. Commit your changes to the local repository 
    - `git add -A`
    - `git commit -m”initial commit”`

## Config server

1. Go to [http://start.spring.io](start.spring.io) 
    - Choose gradle and Spring Boot 1.5.3 
    - Chose a group you want and name the artifact “config-server” 
    - Add “Config Server” to the dependencies 
    - Click Generate 

2. Unzip the project in the spring-cloud-workshop folder 
3. Import the project into your favourite IDE 
4. Open `ConfigServerApplication.java` and add the `@EnableConfigServer` annotation to the class and save. 
5. Open `application.properties` file and add the following properties: 

    ```
    server.port=8888
    spring.application.name=config-server
    spring.cloud.config.server.git.uri: file://${user.home}/spring-cloud-workshop/config-repo
    management.security.enabled=false
    ```

1. Run the `main()` method and VOILA! You should see the different mappings that the actuator created. 
2. In a browser, go to [http://localhost:8888/config-client/default](http://localhost:8888/config-client/default) and you should see our message property. 

## Config client

1. Go to [http://start.spring.io](start.spring.io)
    - Choose gradle and Spring Boot 1.5.3 
    - Chose a group you want and name the artifact “config-client” 
    - Add “Web”, “Config Client” and “Actuator” to the dependencies 
    - Click Generate 

2. Unzip the project in the spring-cloud-workshop folder 
3. Import the project into your favourite IDE 
4. Create a new `MyRestController.java` class with the following code: 
    ```
    @RefreshScope
    @RestController
    class MyRestController {
      
        @Value("${value: default}")
        String value;
      
        @RequestMapping("/")
        public String home() {
            return value;
        }
    }
    ``` 

5. Rename the `application.properties` to `bootstrap.properties`
6. Open `bootstrap.properties` and add the following: 
    ```
    server.port:8080
    spring.application.name=config-client
    spring.cloud.config.uri=http://localhost:8888
    management.security.enabled=false 
    ````

7. Run the `main()` method 
8. In a browser, go to [http://localhost:8080](http://localhost:8080) and you should see “Hello!”. 
9. Now, go back to the git repository you created earlier and change the value of the message in the `application.properties` file to “updated!” 
10. Commit your changes 
11. Send a POST request to [http://localhost:8080/refresh](http://localhost:8080/refresh) using postman or curl. 
12. In a browser, go to [http://localhost:8080](http://localhost:8080) and you should see “updated!”.
