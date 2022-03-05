package com.saucelabs.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DemoTest extends BaseTest {

    @Test
    public void checkSwagLabsTitle() {
        System.out.println("Start checkSwagLabsTitle test");
        WebDriver driver = getDriver();

        driver.navigate().to(WEB_URL);
        String getTitle = driver.getTitle();
        Assert.assertEquals(getTitle, "Swag Labs");
    }

    @Test
    public void swagLabsLogin() {
        System.out.println("Start checkSwagLabsTitle test");
        WebDriver driver = getDriver();
        // login
        driver.navigate().to(WEB_URL);
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // verify we in the product page
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL, WEB_URL + "/inventory.html");
    }

}