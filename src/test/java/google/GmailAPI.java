package google;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import utils.ConfigUtil;
import utils.DecodeUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GmailAPI {
    private static String accessToken;

    public static String getLinkFromMessageText() throws IOException {
        String link = null;
        String encodedText = getTextFromEmail();
        String decodedText = DecodeUtil.decodeMessage(encodedText);
        Pattern pattern = Pattern.compile("href=\"[\\S]+\"");
        Matcher matcher = pattern.matcher(decodedText);
        if (matcher.find()) {
            link = matcher.group().substring(6, matcher.group().length() - 1);
        }
        return link;
    }

    public static String getLastEmailId() throws IOException {
        UsersMessagesList usersMessagesList = getUsersMessagesList();
        String lastEmailId = String.valueOf(usersMessagesList.getMessages().get(0).getId());
        return lastEmailId;
    }

    public static int getNumberOfMessages() throws IOException {
        UsersMessagesList usersMessagesList = getUsersMessagesList();
        return usersMessagesList.getResultSizeEstimate();
    }

    private static String getTextFromEmail() throws IOException {
        String lastEmail = getLastEmail();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readValue(lastEmail, JsonNode.class);
        JsonNode payload = jsonNode.get("payload");
        JsonNode parts = payload.get("parts");
        JsonNode zeroPart = parts.get(0);
        JsonNode body = zeroPart.get("body");
        String data = body.get("data").asText();
        return data;
    }

    private static String getLastEmail() throws IOException {
        URL gmailURL = new URL(ConfigUtil.getProperty("users.messages.list") + "/" + getLastEmailId());
        HttpURLConnection connection = (HttpURLConnection) gmailURL.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + getAccessToken());
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        return response.toString();
    }

    public static String getAccessToken() throws IOException {
        if (accessToken == null) {
            accessToken = GoogleOAuth2.getAccessToken();
        }
        return accessToken;
    }

    private static UsersMessagesList getUsersMessagesList() throws IOException {
        URL gmailURL = new URL(ConfigUtil.getProperty("users.messages.list"));
        HttpURLConnection connection = (HttpURLConnection) gmailURL.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + getAccessToken());
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        ObjectMapper mapper = new ObjectMapper();
        UsersMessagesList usersMessagesList = mapper.readValue(response.toString(), UsersMessagesList.class);
        return usersMessagesList;
    }
}
