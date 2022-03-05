package com.saucelabs.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import java.net.MalformedURLException;

//    @DisplayName("Check the web app was opened")
public class DemoBasicTest extends BaseTest {

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/crossPlatformData.csv")
    public void checkSwagLabsTitle(String aPlatform)  {
        System.out.println("Start checkSwagLabsTitle test. : ");

        driver.navigate().to(WEB_URL);
        String getTitle = driver.getTitle();
        Assertions.assertEquals("Swag Labs",getTitle, "The web app was not loaded" );
    }

}