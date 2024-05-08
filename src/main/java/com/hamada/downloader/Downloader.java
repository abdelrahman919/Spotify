package com.hamada.downloader;

import com.hamada.config.CommandRunner;
import com.hamada.config.FileHandler;

import java.util.List;

//Allows for implementing more download options in the future
public class Downloader {
    final static String CD_DOWNLOADS_COMM =
            "cd yt-downloads";


    private static final String FILE_DOWNLOAD_COMMAND =
            "yt-dlp -a ..\\video_urls.txt " +
                    " -x --extract-audio --audio-format mp3 --audio-quality 320k";


    private static void downloadAndSave(String command, List<String> songNames) {
        FileHandler.createDownloadsIfDoesNotExist();
        int exitValue = CommandRunner.runCommands(CD_DOWNLOADS_COMM
                , command);
        FileHandler.saveDownloads(songNames);
    }

    public static void downloadFromFileAndSaveDownloads(List<String> songNames) {
        downloadAndSave(FILE_DOWNLOAD_COMMAND,songNames);
    }




}
