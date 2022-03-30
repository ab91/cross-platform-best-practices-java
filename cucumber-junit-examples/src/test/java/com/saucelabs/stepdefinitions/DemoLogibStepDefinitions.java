package com.saucelabs.stepdefinitions;

import com.saucelabs.utils.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;


public class DemoLogibStepDefinitions {

    protected RemoteWebDriver driver;
    protected String WEB_URL = "https://www.saucedemo.com/";

    private TestContext testContext;

    public DemoLogibStepDefinitions(TestContext testContext){
        this.testContext = testContext;
    }

    @When("I login with a valid login credentials")
    public void i_login_valid_credentials() {
        System.out.println("Run i_login_valid_credentials test");

        RemoteWebDriver driver = testContext.getDriver();
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }

    @When("I login with an invalid login credentials")
    public void i_login_invalid_credentials() {
        System.out.println("Run i_login_valid_credentials test");

        RemoteWebDriver driver = testContext.getDriver();
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce1");
        driver.findElement(By.id("login-button")).click();
    }

    @Then("I should be able to login")
    public void i_should_be_able_to_login() {
        System.out.println("Run i_should_be_able_to_login test");

        RemoteWebDriver driver = testContext.getDriver();
        // verify we in the product page
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL, WEB_URL + "inventory.html");
    }

    @Then("I should not be able to login")
    public void i_should_not_be_able_to_login() {
        System.out.println("Run i_should_be_able_to_login test");

        RemoteWebDriver driver = testContext.getDriver();
        // verify we in still in main page
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL, WEB_URL);
    }

}
