# Cross Platform Best Practices Java
The project goal is to show how to run cross-browser testing/cross-device testing and how to run in parallel.
I will explain that using Java with the popular frameworks: 

- [JUnit4](#JUnit4) 
- [JUnit5](#JUnit5) 
- [TestNG](#TestNG) 
- Cucumber + Junit4
- Cucumber + TestNG 

I run my tests using Selenium and Appium. 


## The Test Environment
### The Demo Web App
In my examples, I am testing a web app [https://saucedemo.com/](https://saucedemo.com/). 

### The Tests
For every framework, I created 2 classes and 3 tests 

The first test is to verify that the web app was loaded as expected by verifying the website title.

Two other tests from the second class are about testing the login.
One test is to test successful login – and the URL is changed 
Or we enter the wrong username or password, and we stay on the same page.

### The Platforms
I run the tests on virtual machines and devices located on [SauceLabs platform](https://saucelabs.com) since I have access to this platform, but you can use and run it on any cloud solution or selenium grid. 
The tests run on 4 platforms:
- BrowserName: Chrome, Version: Latest-1, OS: Mac 11 
- BrowserName: Firefox, Version: Latest, OS: Windows 10
- BrowserName: Chrome OS: Any avilable Samsung device, Android 12 
- BrowserName: Safari OS: Any avilable iPhone device version 14

## Cross-Browser and Cross-Device testing 
Cross-browser testing or Cross-device testing 
can help you verify if your website or your native app 
works as expected across 
various browsers, operating systems, devices, and resolutions

## Parallel Execution
You need Parallel Execution for Fast Feedback: Accelerate Execution - Reduce the execution time and resolve the limitation of time and still assuring quality

I found that running in parallel mean and done differently by different companies.

For example – the parallel executions are at the platform level. 
The tests will run sequentially on the android device or the latest version of Firefox. 
But I have 4 platforms, and 4 tests will run in parallel.

Another way to look at parallel execution is that All tests on all the platforms will run in parallel – in this case, 4 platforms and 3 tests = 12 tests will run in parallel 

Or customize the parallelization - Devices are expensive resources - So I will run all my tests on the same devices, But for VMs such as desktops or simulators and emulators, I can create more VMs and run my tests in parallel. 
In this way: 8 tests will run in parallel.

## JUnit4 
In my examples, I am using maven as the build automation tool, there are other tools such as Gradle. 

From the [maven website](https://mvnrepository.com/artifact/junit/junit) I selected Junit4 latest version and copied it to my [pom file](https://github.com/eyaly/cross-platform-best-practices-java/blob/main/junit4-examples/pom.xml#L20)  
I defined [4 parameters](https://github.com/eyaly/cross-platform-best-practices-java/blob/main/junit4-examples/src/test/java/com/saucelabs/tests/BaseTest.java#L30) and created [a two-dimensional array](https://github.com/eyaly/cross-platform-best-practices-java/blob/main/junit4-examples/src/test/java/com/saucelabs/tests/BaseTest.java#L41) with the platforms I want to run my tests  
When I run the tests, each parameter will get the values from the two-dimensional array  
So, for example, when I run my code with the first line in the array, I will set [the capability of](https://github.com/eyaly/cross-platform-best-practices-java/blob/main/junit4-examples/src/test/java/com/saucelabs/tests/BaseTest.java#L73)  
Browsername with chrome  
Browser version with latest -1  
And platform name with mac 11

The reason I added the platform parameter that can have the values "desktop", "android" or "iOS" is because I want to run my web app tests on desktop VMs, and for that, I am using Selenium. 
But I also want to run my tests on Android and iPhone devices, so I am using Appium.
Selenium and Appium Capabilities are different, so I need to add this differentiation. 

Defining the parallel execution is done In the [pom file](https://github.com/eyaly/cross-platform-best-practices-java/blob/main/junit4-examples/pom.xml#L34) by adding the maven-surefire-plugin

Running the Web App tests on Sauce Labs:

    // If using the US DC
    mvn clean install -Dregion=us
    
    // If using the EU DC
    mvn clean install -Dregion=eu
> NOTE: Make sure you are in the folder `junit4-examples` when you execute these commands

## JUnit5
In my examples, I am using maven as the build automation tool, there are other tools such as Gradle. 

From the [maven website](https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter) I selected Junit5 latest version and copied it to my [pom file](https://github.com/eyaly/cross-platform-best-practices-java/blob/main/junit5-examples/pom.xml#L22)  
Please note that JUnit5 is called Junit Jupiter    

In JUnit5, there are many new options to use [parameters in methods](https://junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests-sources), but the challenge for me was that this is per method, and I was looking for something global but couldn’t find it.  
So I selected to use [CSV file](https://junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests-sources-CsvFileSource) and added the parameters to all [the tests](https://github.com/eyaly/cross-platform-best-practices-java/blob/main/junit5-examples/src/test/java/com/saucelabs/tests/DemoBasicTest.java#L16)   
The CSV file format is as you want to handle it later. I created the [CSV file](https://github.com/eyaly/cross-platform-best-practices-java/blob/main/junit5-examples/src/test/resources/crossPlatformData.csv) that includes the capability name and the capability value separated by slashes 

[BeforeEach method](https://github.com/eyaly/cross-platform-best-practices-java/blob/main/junit5-examples/src/test/java/com/saucelabs/tests/BaseTest.java#L40) will run before each test. I found out that the testInfo parameter includes the CSV file parameters that we defined for each method. Each time, testInfo will include a row from the csv file and a row is a platform we want to run.  
I developed [a method](https://github.com/eyaly/cross-platform-best-practices-java/blob/main/junit5-examples/src/test/java/com/saucelabs/tests/BaseTest.java#L108) to handle the data from the csv file and set them in a map structure where the key is the capability name, and the value is the capability value.

Defining the parallel execution is done In the [pom file](https://github.com/eyaly/cross-platform-best-practices-java/blob/main/junit5-examples/pom.xml#L38) by adding the maven-surefire-plugin  
There are many options to run in parallel using JUnit5 and more info can be found in the [JUnit5 documentation](https://junit.org/junit5/docs/current/user-guide/#writing-tests-parallel-execution)

Running the Web App tests on Sauce Labs:

    // If using the US DC
    mvn clean install -Dregion=us
    
    // If using the EU DC
    mvn clean install -Dregion=eu
> NOTE: Make sure you are in the folder `junit5-examples` when you execute these commands

## TestNG
In my examples, I am using maven as the build automation tool, there are other tools such as Gradle. 

From the [maven website](https://mvnrepository.com/artifact/org.testng/testng) I selected TestNG latest version and copied it to my [pom file](https://github.com/eyaly/cross-platform-best-practices-java/blob/main/testng-xml/pom.xml#L22)  

We can use the TestNG framework without an XML file, but I think that the TestNG XML file gives a lot of options for controlling and running on different platforms and using parallel executions.  
We define the path to the XML file in the [pom file](https://github.com/eyaly/cross-platform-best-practices-java/blob/main/testng-xml/pom.xml#L36).

In the [XML file](https://github.com/eyaly/cross-platform-best-practices-java/blob/main/testng-xml/src/test/resources/config/myDemoTests.xml#L2), each test section is a platform that we want to run with the name of the capability and the value.  
Defining the parallel execution is done In the [XML file](https://github.com/eyaly/cross-platform-best-practices-java/blob/main/testng-xml/src/test/resources/config/myDemoTests.xml#L2)    
I can customize and define how to run the test in parallel.  
For example, for running on a desktop, I also define parallel in [this section](https://github.com/eyaly/cross-platform-best-practices-java/blob/main/testng-xml/src/test/resources/config/myDemoTests.xml#L4), meaning that all the 3 tests will run on 3 windows10 and firefox browser in parallel.  
But I didn’t define that for real devices – so the tests will run sequentially on each device.  
It is easy to read the parameters from the xml file [to the test](https://github.com/eyaly/cross-platform-best-practices-java/blob/main/testng-xml/src/test/java/com/saucelabs/tests/BaseTest.java#L53) and set the capabilities according to the values
 
Running the Web App tests on Sauce Labs:

    // If using the US DC
    mvn clean install -Dregion=us
    
    // If using the EU DC
    mvn clean install -Dregion=eu
> NOTE: Make sure you are in the folder `testng-xml` when you execute these commands

## Running the testNG tests using Sauce Orchestrate (SO)
You can run the Appium tests using SO directly from GitHub using GitHub Actions
1. Add two new variables: SAUCE_USERNAME and SAUCE_ACCESS_KEY to your GitHub Repository Secret, as shown in the snapshot    
   <img src="./graphics/GitHub_Repository_Secret.png" alt="GitHubSecret"/>
2. Add also the two variables: DOCKERHUB_USERNAME and DOCKERHUB_TOKEN to your GitHub Repository Secret with your Docker username and password.

   More info can be found [here](https://docs.github.com/en/actions/security-guides/encrypted-secrets).
2. Select (1) Actions tab -> (2) Click on "native app workflow using ImageRunner" (3) Click "Run workflow" and (4) Run workflow
3. GitHub Actions starts to run     
   <img src="./graphics/github_action_SO_workflow.png" alt="startRunGitHubActionsSO" />

### The steps to create the image, run a local container and run it using SO
1. Run the tests locally from the main folder by using the command:

```java
        mvn test -pl testng-xml -DtestngXmlFile=myDemoTests-Desktop.xml -X
```
2. Make sure tests run as expected.
3. Check docker is up and running by using the command:

```java
        docker info
```
4. Create the [docker file](https://github.com/eyaly/cross-platform-best-practices-java/blob/main/Dockerfile)
5. build the image from the docker file
```java
        // The format
        $ docker build -t <docker username>/<project-name>-<container-name>:<tag>  <path-to-dockerfile>
       // Example
        $ docker build -t eyalyovelsauce/cross-platform-best-practices-java-docker:0.0.1 .
```
6. The image should be in the local docker
   <img src="./graphics/Docker_Image_local.png" alt="local_docker"/>
7. Run the image container locally
```java
        $ docker run --rm -e SAUCE_USERNAME=${SAUCE_USERNAME}  -e SAUCE_ACCESS_KEY=${SAUCE_ACCESS_KEY}  eyalyovelsauce/cross-platform-best-practices-java-docker:0.0.1
```
8. Push Image to a Docker Registry
```java
        $ docker login
        $ docker push eyalyovelsauce/cross-platform-best-practices-java-docker:0.0.1
```
9. The image should be in the docker Hub
   <img src="./graphics/Docker_Image_hub.png" alt="hub_docker"/>
10.Run SO by using the saucectl:
```java
        $ saucectl run
```
The saucectl config file is [here](https://github.com/eyaly/sauce-java-appium-cross-platform/blob/main/.sauce/config.yml)

### Run on local resources: 
How to use SO where there is a need to use local resources (or not a public resources)    
In this example I'm using a local web server (http://localhost:3000/) and get the username from a local server (http://localhost:4000/give_me_valid_username)       
The steps to create the image, run a local container and run it using SO     
1. Clone/fork the [Sauce Demo App](https://github.com/saucelabs/sample-app-web)
2. Build the application with
```java
        $ npm run start
```
This will build the application, start Chrome and load the website on http://localhost:3000/     
3. Clone/fork my [Demo Server](https://github.com/eyaly/UsernameDemoServer)
4. Go to folder src/main/java
5. Build the server with
```java
        $ javac UsernameServer.java
        $ java UsernameServer
```
This will start the server, and it will print a message indicating that the server is running on port 4000.

3 Download Sauce Connect to your machine and create a local sauce tunnel: 
```java
        $  ./sc -u $SAUCE_USERNAME -k $SAUCE_ACCESS_KEY --region eu-central --tunnel-name demoApp_tunnel
```
4. Run the tests locally from the main folder by using the command:

```java
        export SAUCE_TUNNEL_NAME=demoApp_tunnel ; mvn test -pl testng-xml -DtestngXmlFile=myDemoTests-LocalServer.xml -X

```
This command will create a system env "SAUCE_TUNNEL_NAME" and will run a test on the local demo app with a tunnel
5. Make sure tests run as expected.
6. Check docker is up and running by using the command:

```java
        docker info
```
4. Create the [docker file](https://github.com/eyaly/cross-platform-best-practices-java/blob/main/Dockerfile-local)
5. build the image from the docker file
```java
        // The format
        $ docker build -t <docker username>/<project-name>-<container-name>:<tag>  -f <path to the dockerfile> <path to the directory containing the Dockerfile>
       // Example
        $ docker build -t eyalyovelsauce/cross-platform-best-practices-java-local-docker:0.0.1 -f Dockerfile-local .
```
6. The image should be in the local docker      
7. Run the image container locally
```java
        $ docker run --rm -e SAUCE_TUNNEL_NAME=demoApp_tunnel -e SAUCE_USERNAME=${SAUCE_USERNAME}  -e SAUCE_ACCESS_KEY=${SAUCE_ACCESS_KEY}  eyalyovelsauce/cross-platform-best-practices-java-local-docker:0.0.1
```
8. Push Image to a Docker Registry
```java
        $ docker login
        $ docker push eyalyovelsauce/cross-platform-best-practices-java-local-docker:0.0.1
```
9. The image should be in the docker Hub
8. Run SO by using the saucectl:
```java
        $ 

```saucectl run -c ./.sauce/config_local.yml
The saucectl config file is [here](https://github.com/eyaly/sauce-java-appium-cross-platform/blob/main/.sauce/config_local.yml)

