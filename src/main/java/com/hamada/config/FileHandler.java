package com.hamada.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileHandler {

    private final static Path SONG_NAME_FILE = Paths.get(".", "song_names.txt");
    private final static Path VIDEO_URLS_FILE = Paths.get(".", "video_urls.txt");
    private final static Path DOWNLOADED_SONGS_FILE = Paths.get(".", "downloaded.txt");
    public final static Path DOWNLOADS_FOLDER = Paths.get(".", "yt-downloads");
    public final static Path YOUTUBE_API_KEY = Paths.get(".", "youtube_key.txt");
    public final static Path CREDENTIALS_FILE = Paths.get(".", "credentials.txt");



    private static void appendListToFile(Path path, List<String> stringList) {
        //Each string is saved in a new line
        String textToSave = String.join(System.lineSeparator(), stringList);
        try {
            Files.writeString(path, textToSave+"\n" , StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeListToFile(Path path, List<String> stringList) {
        String textToSave = String.join(System.lineSeparator(), stringList);
        try {
            Files.writeString(path, textToSave, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> readList(Path path) {
        List<String> dataToRead = new ArrayList<>();
        try {
            dataToRead = Files.readAllLines(path);
        } catch (IOException e) {
            appendListToFile(path, dataToRead);
        }
             return dataToRead;
    }

    public static void createDownloadsIfDoesNotExist() {
        if (!Files.exists(DOWNLOADS_FOLDER)) {
            try {
                Files.createDirectory(DOWNLOADS_FOLDER);
            } catch (IOException e) {
                System.out.println("Couldn't create downloads folder");
            }
        }
    }

    public static void saveSongNamesList(List<String> names) {
        appendListToFile(SONG_NAME_FILE, names);
    }

    public static List<String> readSongNames() {
        return readList(SONG_NAME_FILE);
    }

    public static void saveDownloads(List<String> downloads) {
        appendListToFile(DOWNLOADED_SONGS_FILE, downloads);
    }

    public static List<String> readDownloaded() {
        return readList(DOWNLOADED_SONGS_FILE);
    }

    public static List<String> readVideoUrls() {
        return readList(VIDEO_URLS_FILE);
    }

    public static void overwriteVideoUrlList(List<String> urlList) {
        writeListToFile(VIDEO_URLS_FILE, urlList);
    }

    public static String getYoutubeApiKey() {
        String key;
        try {
            key = Files.readString(YOUTUBE_API_KEY);
        } catch (IOException e) {
            System.out.println("File \"youtube_key\" was not found ");
            throw new RuntimeException(e);
        }
        return key;
    }

    public static Map<String, String> getCredentials() {
        Map<String, String> credentials = new HashMap<>();
        List<String> stringList = readList(CREDENTIALS_FILE);
        for (String s : stringList) {
            if (s.startsWith("email")) {
                credentials.put("email"
                        , s.replace("email:", "").strip());
            } else {
                credentials.put("password"
                        , s.replace("password:", "").strip());
            }
        }
        return credentials;
    }






}
