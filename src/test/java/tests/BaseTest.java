package tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import aquality.selenium.browser.AqualityServices;
import google.GmailAPI;
import utils.JSONReader;

public class BaseTest {
    private static final String DEFAULT_URL = JSONReader.read().getValue("/testCaseData/MainPageURL").toString();

    @BeforeTest
    public void beforeTests() {
        AqualityServices.getBrowser().maximize();
        AqualityServices.getBrowser().goTo(DEFAULT_URL);
        GmailAPI.getAccessToken();
    }

    @AfterTest
    public void afterTests() {
        AqualityServices.getBrowser().quit();
    }
}
