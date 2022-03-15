package com.saucelabs.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

public class DemoLoginTest extends BaseTest {

  //  @DisplayName("Check successful login")
    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/crossPlatformData.csv")
    public void swagLabsSuccessLogin(String aPlatform) {
        System.out.println("Start checkSwagLabsTitle test");

        // login
        driver.navigate().to(WEB_URL);
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // verify we in the product page
        String currentURL = driver.getCurrentUrl();
        Assertions.assertEquals(WEB_URL + "inventory.html",currentURL, "Failed to login" );
    }

 //   @DisplayName("Check failed login")
    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/crossPlatformData.csv")
    public void swagLabsFailedLogin(String aPlatform) {
        System.out.println("Start checkSwagLabsTitle test");

        // login
        driver.navigate().to(WEB_URL);
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce1");
        driver.findElement(By.id("login-button")).click();

        // verify we in the product page
        String currentURL = driver.getCurrentUrl();
        Assertions.assertEquals(WEB_URL,currentURL, "Managed to login with a wrong password" );

    }

}