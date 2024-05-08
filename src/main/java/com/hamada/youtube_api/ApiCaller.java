package com.hamada.youtube_api;

import com.hamada.config.FileHandler;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ApiCaller {

    //The actual video URL consists of 2 parts:
    //1-The start which is constant and saved in a variable
    //2-The id of video which is then concatenated to the start to create the url
    private final static String videoUrlStart = "https://www.youtube.com/watch?v=";

    private static HttpResponse<String> sendRequest(RequestUrl requestUrl) {
        try (HttpClient httpClient = HttpClient.newHttpClient()) {

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .header("accept", "application/json")
                    .uri(URI.create(requestUrl.createRequestUrl()))
                    .build();

            return httpClient
                    .send(httpRequest, HttpResponse.BodyHandlers.ofString());

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    //extracts video id from the response and concatenates it to url start creating video URL
    private static String extractSongUrl(HttpResponse<String> response) {
        JSONObject jsonObject = new JSONObject(response.body());
        String videoId = jsonObject
                .getJSONArray("items")
                .getJSONObject(0)
                .getJSONObject("id")
                .getString("videoId");


        String videoUrl = videoUrlStart.concat(videoId);
        return videoUrl;
    }

    public static List<String> getSongUrls(List<String> songNames) {

        List<String> videoUrlList = new ArrayList<>();
        RequestUrl requestUrl = new RequestUrl();
        for (String name : songNames) {
            requestUrl.setSongName(name);
            HttpResponse<String> httpResponse = sendRequest(requestUrl);
            String videoUrl = extractSongUrl(httpResponse);
            videoUrlList.add(videoUrl);
        }
        return videoUrlList;
    }




}
