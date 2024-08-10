package com.P02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Desktop;
import java.net.URI;
import java.net.URL;
import java.net.URISyntaxException;

import org.json.JSONArray;
import org.json.JSONObject;

public class NewsArticle {
    private static final String NYT_API_KEY = "7dyUi9bUvsA0tVS6fy9eccYi2bhHTagC";

    private static String API_URL = "https://api.nytimes.com/svc/topstories/v2/" + getWantedSections() + ".json?api-key=" + NYT_API_KEY;

    // returns random out of: pool of sections
    private static String getWantedSections() {
        String[] wantedSections = { "automobiles", "books", "business", "health", "home",
                "insider", "opinion", "politics", "science", "sports", "sundayreview",
                "technology", "travel", "upshot", "us", "world",
                "business", "health", "opinion", "science", "technology", "us", "world",
                "business", "science", "technology",
                "business", "science", "technology",
                "business", "science", "technology", };

        return wantedSections[new Random().nextInt(wantedSections.length)];
    }

    // gets a String of a HTTP page
    private static String getHttpUrlContents(String url) throws Exception {

        URL apiURL = new URL(url);

        HttpURLConnection apiContentsConnection = (HttpURLConnection) apiURL.openConnection();

        apiContentsConnection.setRequestMethod("GET");

        // the connection expects json format
        apiContentsConnection.setRequestProperty("Accept", "application/json");

        BufferedReader apiContentsReader = new BufferedReader(new InputStreamReader(apiContentsConnection.getInputStream()));

        StringBuilder apiContentsBuilder = new StringBuilder();

        String readLine;
        while ((readLine = apiContentsReader.readLine()) != null) {
            apiContentsBuilder.append(readLine);
        }

        apiContentsConnection.disconnect();
		apiContentsReader.close();

        return apiContentsBuilder.toString();
    }

    // gets random article url from NYT API link
    public static String getRandomNytUrl() throws Exception {

        // set up JSON object
        JSONObject allApiContentsAsJson = new JSONObject(getHttpUrlContents(API_URL));

        // get only "results" keys from api contents
        JSONArray resultsArray = allApiContentsAsJson.getJSONArray("results");

        ArrayList<String> allUrlsList = new ArrayList<>();
        for (int i = 0; i < resultsArray.length(); i++) {
            allUrlsList.add(resultsArray.getJSONObject(i).getString("url"));
        }

        return allUrlsList.get(new Random().nextInt(allUrlsList.size()));
    }

    public static void scriptPrintWebpage(String url) throws Exception {

        String[] instructions = {
            "osascript",
            "-e",
			"tell application \"Google Chrome\" to activate"			
        };
        Runtime.getRuntime().exec(instructions);

		URI uri = new URI(url);
		if(Desktop.isDesktopSupported()) {
			Desktop desktop = Desktop.getDesktop();
			if(desktop.isSupported(Desktop.Action.BROWSE)) {
				desktop.browse(uri);
			}
		}
    }
}
