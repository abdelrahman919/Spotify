package com.hamada.pages.spotify_scrapers;

import com.hamada.config.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PhoneNumberPage {

    private final String phoneNumber = "phone";
    private final WebDriver driver;
    private final By phoneNumInputField = By.id("phonelogin-phonenumber");
    private final By nextButton = By.id("phonelogin-button");


    public PhoneNumberPage(WebDriver driver) {
        this.driver = driver;
    }

    public VerificationCodePage inputPhoneNumber() {
        WebElement inputFieldElement = Utils.findElement(phoneNumInputField);
        inputFieldElement.sendKeys(phoneNumber);
        return new VerificationCodePage(driver);
    }


    public class VerificationCodePage {

        private final WebDriver driver;



        public VerificationCodePage(WebDriver driver) {
            this.driver = driver;
        }




    }

}

