package com.hamada.config;

import java.io.IOException;
import java.util.List;

public class CommandRunner {


    private static String combineCommands(String[] commands) {
        return String.join(" && ", commands);
    }


    public static int runCommands(String ... inputCommands) {
        int exitValue = 1;
        String combinedCommand = combineCommands(inputCommands);
        String[] commandsToRun = new String[]{"cmd", "/c", "start", "cmd", "/K", combinedCommand};
        try {
            Process exec = Runtime.getRuntime().exec(commandsToRun);
            exitValue = exec.waitFor();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return exitValue;
    }

}
