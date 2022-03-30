package com.saucelabs.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TestContext {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static ThreadLocal<WebDriver> getDriver() {
        return driver;
    }

    public void setDriver(ThreadLocal<WebDriver> driver) {
        this.driver = driver;
    }
}
