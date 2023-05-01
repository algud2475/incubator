package google;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.JsonSettingsFile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import constants.RegexClass;
import utils.DecodeUtil;
import utils.HttpAPIUtil;
import utils.JSONReader;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GmailAPI {
    private static final Duration WAIT_FOR_NEW_EMAIL = Duration.ofSeconds(Long.parseLong(new JsonSettingsFile("settings.json").getValue("/timeouts/timeoutEmailReceive").toString()));
    private static final Duration WAIT_FOR_NEW_EMAIL_RETRY = Duration.ofMillis(Long.parseLong(new JsonSettingsFile("settings.json").getValue("/timeouts/timeoutPollingInterval").toString()));
    private static final String USERS_MESSAGES_LIST = JSONReader.read().getValue("/gmailAPI/users.messages.list").toString();
    private static String accessToken;


    public static String getLinkFromMessageText(String messageText) {
        String link = null;
        Pattern pattern = Pattern.compile(RegexClass.LINK);
        Matcher matcher = pattern.matcher(messageText);
        if (matcher.find()) {
            link = matcher.group().substring(6, matcher.group().length() - 1);
        }
        return link;
    }

    public static String getLastEmailId() {
        UsersMessagesList usersMessagesList = getUsersMessagesList();
        return String.valueOf(usersMessagesList.getMessages().get(0).getId());
    }

    public static int getNumberOfMessages() {
        UsersMessagesList usersMessagesList = getUsersMessagesList();
        return usersMessagesList.getResultSizeEstimate();
    }

    public static String getTextFromEmail() {
        String lastEmail = getLastEmail();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = mapper.readValue(lastEmail, JsonNode.class);
        } catch (JsonProcessingException e) {
            AqualityServices.getLogger().info(e.getMessage());
            throw new RuntimeException(e);
        }
        return DecodeUtil.decodeMessage(jsonNode.get("payload")
                .get("parts")
                .get(0)
                .get("body")
                .get("data")
                .asText());
    }

    public static String getAccessToken() {
        if (accessToken == null) {
            accessToken = GoogleOAuth2.getAccessToken();
        }
        return accessToken;
    }

    public static boolean numberOfMessagesHasChanged(int numberOfMessagesBefore) {
        return AqualityServices.getConditionalWait().waitFor(() -> getNumberOfMessages() != numberOfMessagesBefore, WAIT_FOR_NEW_EMAIL, WAIT_FOR_NEW_EMAIL_RETRY);
    }

    private static String getLastEmail() {
        String lastEmailURI = USERS_MESSAGES_LIST + "/" + getLastEmailId();
        return HttpAPIUtil.createGetRequest(lastEmailURI);
    }

    private static UsersMessagesList getUsersMessagesList() {
        UsersMessagesList usersMessagesList = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            usersMessagesList = mapper.readValue(HttpAPIUtil.createGetRequest(USERS_MESSAGES_LIST), UsersMessagesList.class);
        } catch (JsonProcessingException e) {
            AqualityServices.getLogger().info(e.getMessage());
            throw new RuntimeException(e);
        }
        return usersMessagesList;
    }
}
