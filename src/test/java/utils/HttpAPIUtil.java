package utils;

import aquality.selenium.browser.AqualityServices;
import google.GmailAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HttpAPIUtil {
    public static String createGetRequest(String property) {
        String answer = null;
        try {
            URL gmailURL = new URL(property);
            HttpURLConnection connection = (HttpURLConnection) gmailURL.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + GmailAPI.getAccessToken());
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            answer = response.toString();
        } catch (IOException e) {
            AqualityServices.getLogger().info(e.getMessage());
            throw new RuntimeException(e);
        }
        return answer;
    }

    public static String createPostRequest(String propertyURL, String postCode) {
        byte[] postDataBytes = postCode.getBytes(StandardCharsets.UTF_8);
        String answer = null;
        try {
            URL url = new URL(propertyURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            connection.setDoOutput(true);
            connection.getOutputStream().write(postDataBytes);
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            answer = response.toString();
        } catch (IOException e) {
            AqualityServices.getLogger().info(e.getMessage());
            throw new RuntimeException(e);
        }
        return answer;
    }
}
