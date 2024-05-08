package com.hamada;

import com.hamada.config.FileHandler;
import com.hamada.config.Utils;
import com.hamada.downloader.Downloader;
import com.hamada.pages.spotify_scrapers.LoginPage;
import com.hamada.pages.spotify_scrapers.SpotifyService;
import com.hamada.youtube_api.ApiCaller;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        LoginPage loginPage = Utils.setUpLogin();
        List<String> songNames = SpotifyService.fetchSongNamesViaEmailLogin(loginPage);
        System.out.println(songNames.size());
        FileHandler.saveSongNamesList(songNames);
        List<String> songUrls = ApiCaller.getSongUrls(songNames);
        FileHandler.overwriteVideoUrlList(songUrls);
        Downloader.downloadFromFileAndSaveDownloads(songNames);


    }
}