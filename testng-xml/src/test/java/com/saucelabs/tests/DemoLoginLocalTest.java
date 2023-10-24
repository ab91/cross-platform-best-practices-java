package com.saucelabs.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DemoLoginLocalTest extends BaseTest {


    @Test
    public void swagLabsSuccessLoginLocalServer() {
        System.out.println("Start swagLabsSuccessLogin test");
        WebDriver driver = getDriver();

        // login
        String WEB_URL = "http://localhost:3000/";
        driver.navigate().to(WEB_URL);

        // internal rest api to get the username
        String username = getValidUsername();
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // verify we in the product page
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL, WEB_URL + "inventory.html");
    }

    private String getValidUsername() {
        String serverUrl = "http://localhost:4000/give_me_valid_username";
        String username= " ";
        try {
            URL url = new URL(serverUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();
                System.out.println("Response from server: " + response.toString());
                username = response.toString();
            } else {
                System.out.println("GET request failed. Response Code: " + responseCode);
            }
        } catch (Exception e) {
            System.out.println("ERROR with the API server : " + e.getMessage());
            e.printStackTrace();
        }
        return username;
    }
}