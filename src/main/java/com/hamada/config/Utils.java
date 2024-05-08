package com.hamada.config;

import com.hamada.pages.spotify_scrapers.HomePage;
import com.hamada.pages.spotify_scrapers.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Utils {

    private static WebDriver driver;
    private final static String SPOTIFY_HOME_PAGE = "https://open.spotify.com/";
    private final static String SPOTIFY_LOGIN_PAGE = "https://accounts.spotify.com/en/login";


    public static HomePage setUp(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(SPOTIFY_HOME_PAGE);

        return new HomePage(driver);
    }

    public static LoginPage setUpLogin() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(SPOTIFY_LOGIN_PAGE);
        return new LoginPage(driver);
    }


    public void tearDown() {
        driver.quit();
    }

    public static WebElement findElement(By locator) {
        //these 2 lines wait either 30secs or for element to be located
        //avoiding false negatives due to page not fully loading
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions
                .presenceOfElementLocated(locator));
        return driver.findElement(locator);
    }


    public static List<WebElement> findElements(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions
                .presenceOfElementLocated(locator));
        return driver.findElements(locator);
    }

    public static void clickElement(By locator) {
        findElement(locator).click();
    }



}
