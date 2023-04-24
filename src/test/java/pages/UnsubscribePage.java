package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class UnsubscribePage extends Form {
    private static final By LOCATOR = By.xpath("//img[contains(@class,'unsubscribe-logo')]");
    private static final String NAME = "Preview page";
    private final By locatorEmailField = By.xpath("//input[@id='email']");
    private final ITextBox emailField = AqualityServices.getElementFactory().getTextBox(locatorEmailField, "Input email field");
    private final By locatorEmailSubmitButton = By.xpath("//button[@type='submit']");
    private final IButton emailSubmitButton = AqualityServices.getElementFactory().getButton(locatorEmailSubmitButton, "Email submit button");
    private final By locatorSubscriptionWasCanceledText = By.xpath("//strong");
    private final ILabel subscriptionWasCanceledText = AqualityServices.getElementFactory().getLabel(locatorSubscriptionWasCanceledText, "Subscription Was Canceled Text");

    public UnsubscribePage() {
        super(LOCATOR, NAME);
    }

    public void fillEmailField(String email) {
        emailField.clearAndType(email);
    }

    public void clickEmailSubmitButton() {
        emailSubmitButton.click();
    }

    public boolean subscriptionWasCanceledTextIsDisplayed() {
        return subscriptionWasCanceledText.state().waitForDisplayed();
    }
}
