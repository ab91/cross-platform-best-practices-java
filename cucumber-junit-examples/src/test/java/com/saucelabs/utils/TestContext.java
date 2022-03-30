package com.saucelabs.utils;

import org.openqa.selenium.remote.RemoteWebDriver;

public class TestContext {
    private RemoteWebDriver driver;

    public RemoteWebDriver getDriver() {
        return driver;
    }

    public void setDriver(RemoteWebDriver driver) {
        this.driver = driver;
    }
}
