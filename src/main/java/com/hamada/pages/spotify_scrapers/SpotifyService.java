package com.hamada.pages.spotify_scrapers;

import com.hamada.config.FileHandler;

import java.util.List;

public class SpotifyService {

    public static List<String> fetchSongNamesViaEmailLogin(LoginPage loginPage) {
        LoggedInHomePage loggedInHomePage = loginPage.performEmailLogin();
        LikedSongsPage likedSongsPage = loggedInHomePage.clickLikedSongs();
        List<String> songNames = likedSongsPage.getSongNames();
        return songNames;
    }



}
