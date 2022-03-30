package com.saucelabs.stepdefinitions;

import com.saucelabs.utils.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;


public class DemoLogibStepDefinitions {

    protected String WEB_URL = "https://www.saucedemo.com/";

    private TestContext testContext;

    public DemoLogibStepDefinitions(TestContext testContext){
        this.testContext = testContext;
    }

    @When("I login with a valid login credentials")
    public void i_login_valid_credentials() {
        System.out.println("Run i_login_valid_credentials test");

        ThreadLocal<WebDriver> driver = testContext.getDriver();
        driver.get().findElement(By.id("user-name")).sendKeys("standard_user");
        driver.get().findElement(By.id("password")).sendKeys("secret_sauce");
        driver.get().findElement(By.id("login-button")).click();
    }

    @When("I login with an invalid login credentials")
    public void i_login_invalid_credentials() {
        System.out.println("Run i_login_valid_credentials test");
        ThreadLocal<WebDriver> driver = testContext.getDriver();
        driver.get().findElement(By.id("user-name")).sendKeys("standard_user");
        driver.get().findElement(By.id("password")).sendKeys("secret_sauce1");
        driver.get().findElement(By.id("login-button")).click();
    }

    @Then("I should be able to login")
    public void i_should_be_able_to_login() {
        System.out.println("Run i_should_be_able_to_login test");

        ThreadLocal<WebDriver> driver = testContext.getDriver();
        // verify we in the product page
        String currentURL = driver.get().getCurrentUrl();
        Assert.assertEquals(currentURL, WEB_URL + "inventory.html");
    }

    @Then("I should not be able to login")
    public void i_should_not_be_able_to_login() {
        System.out.println("Run i_should_be_able_to_login test");

        ThreadLocal<WebDriver> driver = testContext.getDriver();
        // verify we in still in main page
        String currentURL = driver.get().getCurrentUrl();
        Assert.assertEquals(currentURL, WEB_URL);
    }

}
