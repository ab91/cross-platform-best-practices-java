# Cross Platform Best Practices Java
The project goal is to show how to run cross-browser testing/cross-device testing and how to run in parallel.
I will explain that using Java with the popular frameworks: 
- TestNG
- JUnit4 
- Junit5
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
I found that running in parallel for different companies is mean and done differently.
For example – the parallel executions are at the platform level. 
The tests will run sequentially on the android device or the latest version of Firefox. 
But I have 4 platforms, and 4 tests will run in parallel.
 