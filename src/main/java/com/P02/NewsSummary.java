package com.P02;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextPane;

public class NewsSummary {

	private static String getPageSource(String whichUrl) throws Exception {
		URL url = new URL(whichUrl);
		InputStream urlConnection = url.openStream();
		BufferedReader urlTextConnection = new BufferedReader(new InputStreamReader(urlConnection));
		StringBuilder builder = new StringBuilder();

		String line = null;
		while((line = urlTextConnection.readLine()) != null) {
			builder.append(line);
		}

		urlConnection.close();
		urlTextConnection.close();

		return builder.toString();
	}

    public static String getBrutalistSummary() throws Exception {

		String brutalistPageSource = getPageSource("https://brutalist.report/summary");

        // "Pattern.DOTALL" is a marker that allows inclusion of line separators like \n
        Pattern newsSection = Pattern.compile("<ul>(.*?)</ul>", Pattern.DOTALL);

        // a Matcher connecting newsSection with brutalistPageSource
        Matcher matchToPageSource = newsSection.matcher(brutalistPageSource);

        matchToPageSource.find();

        String ulBlock = matchToPageSource.group(0);

        String readableText = ulBlock
                .replaceAll("</li>", "\n\n")
                .replaceAll("<[^>]*>", "")
                .replaceAll("&lsquo;", "'")
                .replaceAll("&rsquo;", "'")
                .replaceAll("&ldquo;", "\"")
                .replaceAll("&rdquo;", "\"")
                .replaceAll("&nbsp;", " ")
                .replaceAll("&amp;", "&");

        return readableText;
    }

   public static void printText(String textToPrint) throws Exception {
        JTextPane text = new JTextPane();
        text.setText(textToPrint);
        text.print();
    }
}
