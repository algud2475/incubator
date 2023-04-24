package tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import aquality.selenium.browser.AqualityServices;
import google.GmailAPI;

import java.io.IOException;

public class BaseTest {
    protected final GmailAPI gmailAPI = new GmailAPI();

    @BeforeTest
    public void beforeTests() throws IOException {
        AqualityServices.getBrowser().maximize();
        gmailAPI.getAccessToken();
    }

    @AfterTest
    public void afterTests() {
        if (AqualityServices.isBrowserStarted()) {
            AqualityServices.getBrowser().quit();
        }
    }
}
