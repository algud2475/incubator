package google;

import aquality.selenium.browser.AqualityServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import utils.HttpAPIUtil;
import utils.JSONReader;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class GoogleOAuth2 {
    public static String getAccessToken() {
        String accessToken = null;
        String tokenURL = JSONReader.read().getValue("/gmailAPI/token_uri").toString();
        String postCode = getPostCodeToGetAccessToken();
        String response = HttpAPIUtil.createPostRequest(tokenURL, postCode);
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> jsonMap = mapper.readValue(response.toString(), Map.class);
            accessToken = String.valueOf(jsonMap.get("access_token"));
        } catch (IOException e) {
            AqualityServices.getLogger().info(e.getMessage());
            throw new RuntimeException(e);
        }
        return accessToken;
    }

    private static String getPostCodeToGetAccessToken() {
        Map<String, String> params = new HashMap<>();
        params.put("client_id", JSONReader.read().getValue("/gmailAccount/client_id").toString());
        params.put("client_secret", JSONReader.read().getValue("/gmailAccount/client_secret").toString());
        params.put("grant_type", "refresh_token");
        params.put("refresh_token", JSONReader.read().getValue("/gmailAccount/refresh_token").toString());
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
