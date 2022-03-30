package com.saucelabs.stepdefinitions;


import com.saucelabs.utils.Config;
import com.saucelabs.utils.TestContext;
import io.cucumber.java.Scenario;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {
    protected RemoteWebDriver driver;

    private String SAUCE_EU_URL = "https://ondemand.eu-central-1.saucelabs.com/wd/hub";
    private String SAUCE_US_URL = "https://ondemand.us-west-1.saucelabs.com/wd/hub";
    private String SAUCE_CAP = "sauce_";
    protected String WEB_URL = "https://www.saucedemo.com/";

    private TestContext testContext;

    public BaseTest(TestContext testContext){
        this.testContext = testContext;
    }

    @io.cucumber.java.Before
    public void setup(Scenario scenario) throws MalformedURLException {

        System.out.println("Before hook");

        URL url;

        switch (Config.region) {
            case "us":
                url = new URL(SAUCE_US_URL);
                break;
            case "eu":
            default:
                url = new URL(SAUCE_EU_URL);
                break;
        }

        MutableCapabilities caps = new MutableCapabilities();
        MutableCapabilities sauceOptions = new MutableCapabilities();

        caps.setCapability("browserName", "chrome");
        caps.setCapability("browserVersion", "latest-1");
        caps.setCapability("platformName", "macOS 11.00");


        sauceOptions.setCapability("username", System.getenv("SAUCE_USERNAME"));
        sauceOptions.setCapability("accessKey", System.getenv("SAUCE_ACCESS_KEY"));
        sauceOptions.setCapability("name", scenario.getName());

        caps.setCapability("sauce:options", sauceOptions);

        try {
            driver = new RemoteWebDriver(url, caps);

        } catch (Exception e) {
            System.out.println("*** Problem to create the driver " + e.getMessage());
            throw new RuntimeException(e);
        }

        testContext.setDriver(driver);
        System.out.format("BEFORE Thread ID - %2d - from scenario %s \n",
                Thread.currentThread().getId(), scenario.getName());
    }

    @io.cucumber.java.After
    public void tearDown(Scenario scenario){
        System.out.println("After hook status= " + scenario.getStatus().toString());
        System.out.format("AFTER Thread ID - %2d - from scenario %s \n",
                Thread.currentThread().getId(), scenario.getName());
        try {
            if (scenario.getStatus().toString() == "PASSED") {
                driver.executeScript("sauce:job-result=passed");
            } else {
                driver.executeScript("sauce:job-result=failed");
            }
        } finally {
            driver.quit();
        }

    }

}
