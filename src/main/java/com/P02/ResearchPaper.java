package com.P02;

public class ResearchPaper {
    private static final String scihubURL = "https://sci-hub.se/";

    static void scriptPrintSciHubArticle() throws Exception {
        String[] instructions = {
                "osascript",
                "-e",
                "tell application \"Google Chrome\"",
                "-e",
                "activate",
                "-e",
                "delay 1",
                "-e",
                "open location \"" + scihubURL + "\"",
                "-e",
                "delay 1.5",
                "-e",
                "tell application \"System Events\"",
                "-e",
                "delay 0.5",
                "-e",
                "keystroke \"r\" using command down",
                "-e",
                "delay 1",
                "-e",
                "do shell script \"cliclick m:1227,358\"",
                "-e",
                "delay 0.5",
                "-e",
                "do shell script \"cliclick c:1227,358\"",
                "-e",
                "delay 2",
                "-e",
                "do shell script \"cliclick m:1850,151\"",
                "-e",
                "delay 0.5",
                "-e",
                "do shell script \"cliclick c:1850,151\"",
                "-e",
                "delay 0.5",
                "-e",
                "keystroke return",
                "-e",
                "end tell",
                "-e",
                "end tell"
        };
        Runtime.getRuntime().exec(instructions);
    }
}
