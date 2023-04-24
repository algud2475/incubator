package google;

import com.fasterxml.jackson.databind.ObjectMapper;
import utils.ConfigUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class GoogleOAuth2 {
    public static String getAccessToken() throws IOException {
        byte[] postDataBytes = getPostCodeToGetAccessToken().getBytes(StandardCharsets.UTF_8);
        URL tokenURL = new URL(ConfigUtil.getProperty("token_uri"));
        HttpURLConnection connection = (HttpURLConnection) tokenURL.openConnection();
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
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonMap = mapper.readValue(response.toString(), Map.class);
        return String.valueOf(jsonMap.get("access_token"));
    }

    private static String getPostCodeToGetAccessToken() {
        Map<String, String> params = new HashMap<>();
        params.put("client_id", ConfigUtil.getProperty("client_id"));
        params.put("client_secret", ConfigUtil.getProperty("client_secret"));
        params.put("grant_type", "refresh_token");
        params.put("refresh_token", ConfigUtil.getProperty("refresh_token"));
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, String> param : params.entrySet()) {
            if (postData.length() != 0) {
                postData.append('&');
            }
            postData.append(URLEncoder.encode(param.getKey(), StandardCharsets.UTF_8));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), StandardCharsets.UTF_8));
        }
        return postData.toString();
    }
}
