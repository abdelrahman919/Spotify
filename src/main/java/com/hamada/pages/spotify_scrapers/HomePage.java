package com.hamada.pages.spotify_scrapers;

import com.hamada.config.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private final WebDriver driver;
    private final By loginButton = By.cssSelector("button[data-testid=\"login-button\"]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }


    public LoginPage clickLoginButton() {
        Utils.clickElement(loginButton);
        return new LoginPage(driver);
    }




}
