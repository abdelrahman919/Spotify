package com.hamada.pages.spotify_scrapers;

import com.hamada.config.FileHandler;
import com.hamada.config.Utils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LikedSongsPage {

    private final WebDriver driver;
    private final By trackListLocator = By.cssSelector("div[data-testid=\"track-list\"]");
    private final By listLengthLocator = By.cssSelector("div.Fb61sprjhh75aOITDnsJ span.RANLXG3qKB61Bh33I0r2");

    //Starts at 2 because first song index in website's HTML is 2
    private int rowIndex = 2;


    //To get each song we use this locator with a placeholder for rowIndex
    //It's wrapped in a method to make sure rowIndex value is updated each time it's called
    //Otherwise rowIndex value inside the string won't be updated
    //Even when incremented it will hold its initial value "2"
    private By getSongsLinkLocatorDynamically() {
        return By.cssSelector(String.format(
                "div[data-testid=\"track-list\"]" +
                        " div[aria-rowindex=\"%d\"]" +
                        " a[data-testid=\"internal-track-link\"]"
                , rowIndex)
        );
    }

    private By getArtistNameDynamically() {
        return By.cssSelector(String.format("div[data-testid=track-list]" +
                        " div[aria-rowindex=\"%d\"]" +
                        " div.Text__TextElement-sc-if376j-0.gYdBJW.encore-text-body-small"
                , rowIndex)
        );
    }


    public LikedSongsPage(WebDriver driver) {
        this.driver = driver;
    }


    private int getListLength() {
        WebElement element = Utils.findElement(listLengthLocator);
        String countString = element.getText().replace(" songs", "");
        return Integer.parseInt(countString);
    }

    private String getCurrentSongNameWithArtist(WebElement songElement) {
        WebElement artistElement = Utils.findElement(getArtistNameDynamically());
        return String.join(" ",
                songElement.getText()
                , artistElement.getText());
    }

    //List of songs is dynamically generated on the website
    //meaning they aren't all loaded in the initial HTML we have to scroll them into view,
    //so we move to each song one at a time to load the rest of the songs
    //also filters the already downloaded songs and return a list of only new ones
    public List<String> getSongNames() {
        List<String> names = new ArrayList<>();
        List<String> downloaded = FileHandler.readDownloaded();
        Actions actions = new Actions(driver);
        //Add one because the first song is at index 2 in the HTML
        int songsCount = getListLength() + 1;
        WebElement trackListElement = Utils.findElement(trackListLocator);
        try {
            while (rowIndex <= songsCount) {
                //Find the Web element of the song with the current rowIndex
                WebElement songElement = trackListElement
                        .findElement(getSongsLinkLocatorDynamically());
                String songAndArtistName = getCurrentSongNameWithArtist(songElement);
                //Filter already downloaded songs
                if (!downloaded.contains(songAndArtistName)) {
                    names.add(songAndArtistName);
                }
                rowIndex++;
                //Move to the next song element
                actions.moveToElement(songElement).perform();
            }
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            System.out.println("NO SUCH ELEMENT CAUGHT");
        }
        return names;
    }




}