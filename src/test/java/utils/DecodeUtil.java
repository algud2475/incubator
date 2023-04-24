package utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class DecodeUtil {
    public static String decodeMessage(String encodedMessage) {
        encodedMessage = encodedMessage.replace('_', '/');
        encodedMessage = encodedMessage.replace('-', '+');
        byte[] decodedBytes = Base64.getDecoder().decode(encodedMessage);
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }
}
