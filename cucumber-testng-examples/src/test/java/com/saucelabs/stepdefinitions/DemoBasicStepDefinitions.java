package com.saucelabs.stepdefinitions;

import com.saucelabs.utils.Config;
import com.saucelabs.utils.TestContext;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class DemoBasicStepDefinitions {

//    private String SAUCE_EU_URL = "https://ondemand.eu-central-1.saucelabs.com/wd/hub";
//    private String SAUCE_US_URL = "https://ondemand.us-west-1.saucelabs.com/wd/hub";
//    private String SAUCE_CAP = "sauce_";
//    protected String WEB_URL = "https://www.saucedemo.com/";

 //   private TestContext testContext;

//    public BaseTest(TestContext testContext){
//        this.testContext = testContext;
//    }

//    @io.cucumber.java.Before
//    public void setup(Scenario scenario) throws MalformedURLException {
//
//        System.out.println("Before hook");
//
//        URL url;
//
//        switch (Config.region) {
//            case "us":
//                url = new URL(SAUCE_US_URL);
//                break;
//            case "eu":
//            default:
//                url = new URL(SAUCE_EU_URL);
//                break;
//        }
//
//        boolean isBuildCap = false;
//        MutableCapabilities caps = new MutableCapabilities();
//        MutableCapabilities sauceOptions = new MutableCapabilities();
//
//        for (Map.Entry<String, String> entry : Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getAllParameters().entrySet()) {
//            String k = entry.getKey();
//            String v = entry.getValue();
//
//            if (k.startsWith(SAUCE_CAP)) {
//                // Sauce capability
//                String sauceCap = k.replaceFirst(SAUCE_CAP, "");
//                if (sauceCap.equals("build")) {
//                    isBuildCap = true;
//                }
//
//                if (v.contains(" ")) {
//                    // handle a list such as in tags cap
//                    List<String> capList = Arrays.asList(v.split(" "));
//                    sauceOptions.setCapability(sauceCap, capList);
//                } else {
//                    sauceOptions.setCapability(sauceCap, v);
//                }
//            } else {
//                caps.setCapability(k, v);
//            }
//        }
//
//        sauceOptions.setCapability("username", System.getenv("SAUCE_USERNAME"));
//        sauceOptions.setCapability("accessKey", System.getenv("SAUCE_ACCESS_KEY"));
//        sauceOptions.setCapability("name", scenario.getName());
//
//        if (!isBuildCap) { //handle build cap
//            LocalDateTime dateTime = LocalDateTime.now();
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH");
//            String buildLocal = "sauceDemo-" +dateTime.format(formatter);
//            String buildVal = System.getenv("BUILD_TAG");
//            sauceOptions.setCapability("build", buildVal == null ? buildLocal : buildVal);
//        }
//
//        caps.setCapability("sauce:options", sauceOptions);
//
//        try {
//            driver.set(new RemoteWebDriver(url, caps));
//        } catch (Exception e) {
//            System.out.println("*** Problem to create the driver " + e.getMessage());
//            throw new RuntimeException(e);
//        }
//
//        //testContext.setDriver(getDriver());
//        System.out.format("BEFORE Thread ID - %2d - from scenario %s \n",
//                Thread.currentThread().getId(), scenario.getName());
//    }
//
//    @io.cucumber.java.After
//    public void tearDown(Scenario scenario){
//        System.out.println("After hook status= " + scenario.getStatus().toString());
//        System.out.format("AFTER Thread ID - %2d - from scenario %s \n",
//                Thread.currentThread().getId(), scenario.getName());
//        try {
//            if (scenario.getStatus().toString() == "PASSED") {
//                ((JavascriptExecutor) getDriver()).executeScript("sauce:job-result=passed");
//            } else {
//                ((JavascriptExecutor) getDriver()).executeScript("sauce:job-result=failed");
//            }
//        } finally {
//            getDriver().quit();
//        }
//
//    }
//
//    public RemoteWebDriver getDriver() {
//        return driver.get();
//    }


//    protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
    protected String WEB_URL = "https://www.saucedemo.com/";

    private TestContext testContext;

    public DemoBasicStepDefinitions(TestContext testContext){
        this.testContext = testContext;
    }

    @Given("I load the SwagLab page")
    public void i_load_swaglab_page() {
        System.out.println("Run i_load_swaglab_page test");
        System.out.format("Thread ID - %2d - from feature file i_load_swaglab_page.\n",
                Thread.currentThread().getId());

        ThreadLocal<WebDriver> driver = testContext.getDriver();
        driver.get().navigate().to(WEB_URL);
    }

    @Then("I should see {string} title")
    public void i_Should_See_Title(String strTitle) {
        System.out.println("Run i_Should_See_Title test");
        ThreadLocal<WebDriver> driver = testContext.getDriver();
        String getTitle = driver.get().getTitle();
        Assert.assertEquals(getTitle, strTitle);
    }
}
