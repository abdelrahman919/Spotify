package com.hamada.pages.spotify_scrapers;

import com.hamada.config.FileHandler;
import com.hamada.config.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

//Only Email log in is available at the moment
public class LoginPage {

    private final WebDriver driver;
    private final By loginButton =
            By.id("login-button");

    //Google doesn't allow automated login so couldn't use it
    private final By googleLoginButton =
            By.cssSelector("button[data-testid=\"google-login\"]");

    private final By phoneLoginButton =
            By.cssSelector("button[data-testid=\"phone-login\"]");

    private final By emailInputField =
            By.id("login-username");

    private final By passwordInputField =
            By.id("login-password");

    private final By webPlayerButton =
            By.cssSelector("button[data-testid=\"web-player-link\"]");



    //2nd option for credentials by CLI
    private List<String> getCredentials() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter email: ");
        String email = in.nextLine();
        System.out.println("Enter password: ");
        String password = in.nextLine();
        return new ArrayList<>(List.of(email, password));
    }


    public LoggedInHomePage performEmailLogin() {
        Map<String, String> credentials = FileHandler.getCredentials();
        Utils.findElement(emailInputField).sendKeys(credentials.get("email"));
        Utils.findElement(passwordInputField).sendKeys(credentials.get("password"));
        Utils.clickElement(loginButton);
        Utils.clickElement(webPlayerButton);
        return new LoggedInHomePage(driver);
    }

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    //Attempt to perform google login
    //Google blocks such automated logins so didn't work
    public GoogleCredentialsPage clickGoogleLoginButton() {
        Utils.clickElement(googleLoginButton);
        return new GoogleCredentialsPage(driver);
    }




//Not used phone login
//    public PhoneNumberPage clickPhoneLoginButton() {
//        Utils.clickElement(phoneLoginButton);
//        return new PhoneNumberPage(driver);
//    public LoggedInHomePage clickPhoneLoginButton() {
//        Utils.clickElement(phoneLoginButton);
//        WebDriverWait wait = new WebDriverWait(driver,1000);
//        wait.until(ExpectedConditions.urlContains("https://open.spotify.com/"));
//        return new LoggedInHomePage(driver);
//        }
//    }





}
