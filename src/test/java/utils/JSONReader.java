package utils;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

public class JSONReader {
    private static ISettingsFile environment = new JsonSettingsFile("config.json");

    public static ISettingsFile read() {
        return environment;
    }
}
