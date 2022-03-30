package com.saucelabs.stepdefinitions;

import com.saucelabs.utils.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;


public class DemoBasicStepDefinitions {

    protected RemoteWebDriver driver;
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

        RemoteWebDriver driver = testContext.getDriver();
        driver.navigate().to(WEB_URL);
    }

    @Then("I should see {string} title")
    public void i_Should_See_Title(String strTitle) {
        System.out.println("Run i_Should_See_Title test");
        RemoteWebDriver driver = testContext.getDriver();
        String getTitle = driver.getTitle();
        Assert.assertEquals(getTitle, strTitle);
    }
}
