package com.hamada.pages.spotify_scrapers;

import com.hamada.config.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoggedInHomePage {

    private final WebDriver driver;
    private final By likedSongsListDiv =
            By.xpath("/html/body/div[4]/div/div[2]/div[1]/nav/div[2]/div[1]/" +
                    "div[2]/div[2]/div/div[2]/ul/div/div[2]/li[1]/div/div[1]");

    public LoggedInHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public LikedSongsPage clickLikedSongs() {
        Utils.clickElement(likedSongsListDiv);
        return new LikedSongsPage(driver);
    }





}
