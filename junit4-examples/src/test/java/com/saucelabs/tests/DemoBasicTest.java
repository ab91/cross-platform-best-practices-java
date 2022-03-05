package com.saucelabs.tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


@RunWith(Parameterized.class)
public class DemoBasicTest extends BaseTest {

    @Test
    public void checkSwagLabsTitle() {
        System.out.println("Start checkSwagLabsTitle test");

        driver.navigate().to(WEB_URL);
        String getTitle = driver.getTitle();
        Assert.assertEquals(getTitle, "Swag Labs");
    }

}