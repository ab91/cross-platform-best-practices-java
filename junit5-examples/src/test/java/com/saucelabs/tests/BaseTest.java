package com.saucelabs.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static com.saucelabs.tests.Config.region;

public class BaseTest {
    public RemoteWebDriver driver;

    private String SAUCE_EU_URL = "https://ondemand.eu-central-1.saucelabs.com/wd/hub";
    private String SAUCE_US_URL = "https://ondemand.us-west-1.saucelabs.com/wd/hub";
    private String SAUCE_CAP = "sauce_";
    protected String WEB_URL = "https://www.saucedemo.com/";

    /**
     * A Test Watcher is needed to be able to get the results of a Test so that it can be sent to Sauce Labs.
     * Note that the name is never actually used
     */
    @RegisterExtension
    public SauceTestWatcher watcher = new SauceTestWatcher();


    @BeforeEach
    public void setup(TestInfo testInfo) throws MalformedURLException {

        System.out.println("BeforeEach hook");
        // handle the platform info
        Map<String, String> platformCapabilitiesMap = new HashMap<String, String>();
        // get the capabilities
        platformCapabilitiesMap = handlePlatform(testInfo.getDisplayName());
        URL url;

        switch (region) {
            case "us":
                url = new URL(SAUCE_US_URL);
                break;
            case "eu":
            default:
                url = new URL(SAUCE_EU_URL);
                break;
        }

        boolean isBuildCap = false;
        MutableCapabilities caps = new MutableCapabilities();
        MutableCapabilities sauceOptions = new MutableCapabilities();

        for (Map.Entry<String, String> entry : platformCapabilitiesMap.entrySet()) {
            String k = entry.getKey().trim();
            String v = entry.getValue().trim();

            if (k.startsWith(SAUCE_CAP)) {
                // Sauce capability
                String sauceCap = k.replaceFirst(SAUCE_CAP, "");
                if (sauceCap.equals("build")) {
                    isBuildCap = true;
                }

                if (v.contains(" ")) {
                    // handle a list such as in tags cap
                    List<String> capList = Arrays.asList(v.split(" "));
                    sauceOptions.setCapability(sauceCap, capList);
                } else {
                    sauceOptions.setCapability(sauceCap, v);
                }
            } else {
                caps.setCapability(k, v);
            }
        }

        sauceOptions.setCapability("username", System.getenv("SAUCE_USERNAME"));
        sauceOptions.setCapability("accessKey", System.getenv("SAUCE_ACCESS_KEY"));
        sauceOptions.setCapability("name", testInfo.getTestMethod().get().getName());

        if (!isBuildCap) { //handle build cap
            LocalDateTime dateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH");
            String buildLocal = "sauceDemo-" +dateTime.format(formatter);
            String buildVal = System.getenv("BUILD_TAG");
            sauceOptions.setCapability("build", buildVal == null ? buildLocal : buildVal);
        }

        caps.setCapability("sauce:options", sauceOptions);

        try {
            driver = new RemoteWebDriver(url, caps);
        } catch (Exception e) {
            System.out.println("*** Problem to create the driver " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private Map<String, String> handlePlatform(String displayName) {
        System.out.println("--------------------------");
        System.out.println("Display name: " + displayName);
        // remove the index in the start of the string
        displayName = displayName.split(" ",2)[1];
        // get all the capabilities as an array
        String[] allCapabilities = displayName.split("/");
        // for each capability -> get the capability name and value
        Map<String, String> platformCapabilitiesMap = new HashMap<String, String>();
        for (String capabilityPair : allCapabilities){
            String[] capability = capabilityPair.split("=");
            platformCapabilitiesMap.put(capability[0], capability[1]);
        }

        return platformCapabilitiesMap;
    }

    /**
     * Custom TestWatcher for Sauce Labs projects.
     */
    public class SauceTestWatcher implements TestWatcher {
        @Override
        public void testSuccessful(ExtensionContext context) {
            System.out.println("--------------------------");
            System.out.println("This test was successful: " + context.getTestMethod());
            if (driver == null) {
                return;
            }
            driver.executeScript("sauce:job-result=passed");
            driver.quit();
        }

        @Override
        public void testFailed(ExtensionContext context, Throwable cause) {

            System.out.println("--------------------------");
            System.out.println("This test was failed: " + cause.getMessage());
            if (driver == null) {
                return;
            }
            driver.executeScript("sauce:job-result=failed");
            driver.executeScript("sauce:context=" +cause.getMessage());
            driver.quit();
        }
    }

}
