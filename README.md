# Cross Platform Best Practices Java
The project goal is to show how to run cross-browser testing/cross-device testing and how to run in parallel.
I will explain that using Java with the popular frameworks: 

- [JUnit4](#JUnit4) 
- Junit5
- TestNG
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

From the [maven website](https://mvnrepository.com/artifact/junit/junit) I selected Junit4 latest version and copied it to my [pom file](https://github.com/eyaly/cross-platform-best-practices-java/blob/main/junit4-examples/pom.xml#L20) \ 
I defined [4 parameters](https://github.com/eyaly/cross-platform-best-practices-java/blob/main/junit4-examples/src/test/java/com/saucelabs/tests/BaseTest.java#L30) and created [a two-dimensional array](https://github.com/eyaly/cross-platform-best-practices-java/blob/main/junit4-examples/src/test/java/com/saucelabs/tests/BaseTest.java#L41) with the platforms I want to run my tests \
When I run the tests, each parameter will get the values from the two-dimensional array \
So, for example, when I run my code with the first line in the array, I will set [the capability of](https://github.com/eyaly/cross-platform-best-practices-java/blob/main/junit4-examples/src/test/java/com/saucelabs/tests/BaseTest.java#L73) \ 
Browsername with chrome \
Browser version with latest -1 \
And platform name with mac 11

The reason I added the platform parameter that can have the values "desktop", "android" or "iOS" is because I want to run my web app tests on desktop VMs, and for that, I am using Selenium. 
But I also want to run my tests on Android and iPhone devices, so I am using Appium.
Selenium and Appium Capabilities are different, so I need to add this differentiation. 

Defining the parallel execution is done In the [pom file](Defining the parallel execution is done In the pom file by adding the maven-surefire-plugin with the configuration as it is here
) by adding the maven-surefire-plugin

Running the Web App tests on Sauce Labs:

    // If using the US DC
    mvn clean install -Dregion=us
    
    // If using the EU DC
    mvn clean install -Dregion=eu
> NOTE: Make sure you are in the folder `junit4-examples` when you execute these commands