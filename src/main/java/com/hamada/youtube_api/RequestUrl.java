package com.hamada.youtube_api;

import com.hamada.config.FileHandler;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class RequestUrl {
    //NOTES: request URL consists of
    //1-key: API key of youtube api
    //2-part: specifies only return id part of the video not a whole snippet
    //3-query param: "q=" followed by song name
    //4-maxResults: num of video id results


    private final String body = "https://youtube.googleapis.com/youtube/v3/search?";
    private final String key = FileHandler.getYoutubeApiKey();
    private final String part = "part=id";
    private final String songNameParam = "q=";
    private String songName;
    private final String maxResultsParam = "maxResults=";
    private  String maxResults;


    //If not specified default Max results in 1
    protected RequestUrl() {
       this.maxResults= maxResultsParam.concat("1");
    }

    protected void setSongName(String songName) {
        //Encoding song name to prevent errors when creating URL
        String encodedSongName = URLEncoder
                .encode(songName, StandardCharsets.UTF_8);
        //concat encoded song name to the q= param
        this.songName = songNameParam
                .concat(encodedSongName);
    }



    //Joins the url parts using "&"
    protected String createRequestUrl() {
        return body.concat(String.join("&", key, part, songName, maxResults));
    }










}
