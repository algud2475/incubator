package tests;

import aquality.selenium.browser.AqualityServices;
import google.GmailAPI;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utils.ConfigUtil;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeoutException;

public class TestGmailAPI extends BaseTest {

    @Test
    public void testGmailAPI() throws IOException, InterruptedException, TimeoutException {
        MainPage mainPage = new MainPage();
        AqualityServices.getBrowser().goTo(mainPage.getMainPageURL());

        Assert.assertTrue(mainPage.state().waitForDisplayed(), "Main page is not opened");

        if (mainPage.agreeWithCookiesIsDisplayed()) {
            mainPage.agreeWithCookies();
        }

        mainPage.clickNewslettersLink();
        NewslettersPage newslettersPage = new NewslettersPage();

        Assert.assertTrue(newslettersPage.state().waitForDisplayed(), "Newsletters page is not opened");

        newslettersPage.chooseRandomSubscriptionPlan();
        newslettersPage.clickRandomSubscriptionPlanButton();
        EmailForm emailForm = new EmailForm();

        Assert.assertTrue(emailForm.state().waitForDisplayed(), "Email form has not appeared");

        int numberOfMessagesBeforeSubscription = GmailAPI.getNumberOfMessages();
        emailForm.fillEmailField(ConfigUtil.getProperty("email"));
        emailForm.clickEmailSubmitButton();
        AqualityServices.getConditionalWait().waitForTrue(() -> {
            try {
                return numberOfMessagesHasChanged(numberOfMessagesBeforeSubscription);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, Duration.ofSeconds(10), Duration.ofMillis(1000), "No new email is received");
        String confirmSubscriptionLink = GmailAPI.getLinkFromMessageText();

        Assert.assertNotNull(confirmSubscriptionLink);

        String lastEmailIdAfterSubscription = GmailAPI.getLastEmailId();
        AqualityServices.getBrowser().goTo(confirmSubscriptionLink);
        SuccessfulSubscriptionConfirmationPage sucSubConPage = new SuccessfulSubscriptionConfirmationPage();

        Assert.assertTrue(sucSubConPage.state().waitForDisplayed(), "Successful Subscription Confirmation Page is not opened");

        sucSubConPage.clickBackToTheSiteButton();

        Assert.assertTrue(mainPage.state().waitForDisplayed(), "Main page is not opened");

        mainPage.clickNewslettersLink();
        newslettersPage.clickRandomSubscriptionPlanSeePreviewLink();
        PreviewPage previewPage = new PreviewPage();

        Assert.assertTrue(previewPage.state().waitForDisplayed() && newslettersPage.getNameOfRandomSubscriptionPlan().equals(previewPage.getPreviewPageName()), "A preview of the required plan is not opened");

        previewPage.clickUnsubscribeLink();
        UnsubscribePage unsubscribePage = new UnsubscribePage();

        Assert.assertTrue(unsubscribePage.state().waitForDisplayed(), "Unsubscribe page is not opened");

        int numberOfMessagesBeforeUnsubscribe = GmailAPI.getNumberOfMessages();
        unsubscribePage.fillEmailField(ConfigUtil.getProperty("email"));
        unsubscribePage.clickEmailSubmitButton();

        Assert.assertTrue(unsubscribePage.subscriptionWasCanceledTextIsDisplayed(), "A message that the subscription was canceled doesn't appear");

        boolean newEmailIsReceived = AqualityServices.getConditionalWait().waitFor(() -> {
            try {
                return numberOfMessagesHasChanged(numberOfMessagesBeforeUnsubscribe);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, Duration.ofSeconds(10), Duration.ofMillis(1000));
        String lastEmailIdAfterUnsubscribe = GmailAPI.getLastEmailId();

        Assert.assertTrue(!newEmailIsReceived & lastEmailIdAfterSubscription.equals(lastEmailIdAfterUnsubscribe), "The letter has arrived");
    }

    public boolean numberOfMessagesHasChanged(int numberOfMessagesBefore) throws IOException {
        return GmailAPI.getNumberOfMessages() != numberOfMessagesBefore;
    }
}



