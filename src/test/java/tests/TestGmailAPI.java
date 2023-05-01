package tests;

import aquality.selenium.browser.AqualityServices;
import google.GmailAPI;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utils.JSONReader;

public class TestGmailAPI extends BaseTest {
    private static final String EMAIL = JSONReader.read().getValue("/gmailAccount/email").toString();

    @Test
    public void testGmailAPI() {
        MainPage mainPage = new MainPage();

        Assert.assertTrue(mainPage.state().waitForDisplayed(), "Main page is not opened");

        if (mainPage.agreeWithCookiesIsDisplayed()) {
            mainPage.agreeWithCookies();
        }

        mainPage.clickNewslettersLink();
        NewslettersPage newslettersPage = new NewslettersPage();
        String randomSubscriptionPlanID = newslettersPage.getSubscriptionPlanID();

        Assert.assertTrue(newslettersPage.state().waitForDisplayed(), "Newsletters page is not opened");

        newslettersPage.clickSubscriptionPlanButton();
        EmailForm emailForm = new EmailForm();

        Assert.assertTrue(emailForm.state().waitForDisplayed(), "Email form has not appeared");

        int numberOfMessagesBeforeSubscription = GmailAPI.getNumberOfMessages();
        emailForm.fillEmailField(EMAIL);
        emailForm.clickEmailSubmitButton();

        Assert.assertTrue(GmailAPI.numberOfMessagesHasChanged(numberOfMessagesBeforeSubscription), "No new email is received");

        String textFromEmail = GmailAPI.getTextFromEmail();
        String confirmSubscriptionLink = GmailAPI.getLinkFromMessageText(textFromEmail);

        Assert.assertNotNull(confirmSubscriptionLink);

        String lastEmailIdAfterSubscription = GmailAPI.getLastEmailId();
        AqualityServices.getBrowser().goTo(confirmSubscriptionLink);
        SuccessfulSubscriptionConfirmationPage sucSubConPage = new SuccessfulSubscriptionConfirmationPage();

        Assert.assertTrue(sucSubConPage.state().waitForDisplayed(), "Successful Subscription Confirmation Page is not opened");

        sucSubConPage.clickBackToTheSiteButton();
        mainPage = new MainPage();

        Assert.assertTrue(mainPage.state().waitForDisplayed(), "Main page is not opened");

        mainPage.clickNewslettersLink();
        newslettersPage = new NewslettersPage(randomSubscriptionPlanID);
        newslettersPage.clickSubscriptionPlanSeePreviewLink();
        PreviewPage previewPage = new PreviewPage();

        Assert.assertTrue(previewPage.state().waitForDisplayed(), "A preview page is not opened");
        Assert.assertTrue(newslettersPage.getNameOfSubscriptionPlan().equals(previewPage.getPreviewPageName()), "A preview opened with another plan");

        previewPage.clickUnsubscribeLink();
        UnsubscribePage unsubscribePage = new UnsubscribePage();

        Assert.assertTrue(unsubscribePage.state().waitForDisplayed(), "Unsubscribe page is not opened");

        int numberOfMessagesBeforeUnsubscribe = GmailAPI.getNumberOfMessages();
        unsubscribePage.fillEmailField(EMAIL);
        unsubscribePage.clickEmailSubmitButton();

        Assert.assertTrue(unsubscribePage.subscriptionWasCanceledTextIsDisplayed(), "A message that the subscription was canceled doesn't appear");

        boolean newEmailIsReceived = GmailAPI.numberOfMessagesHasChanged(numberOfMessagesBeforeUnsubscribe);
        String lastEmailIdAfterUnsubscribe = GmailAPI.getLastEmailId();

        Assert.assertTrue(!newEmailIsReceived, "The letter has arrived");
        Assert.assertTrue(lastEmailIdAfterSubscription.equals(lastEmailIdAfterUnsubscribe), "The last email in the inbox doesn`t have a request to confirm subscription");
    }
}



