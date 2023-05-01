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
    private final ITextBox emailField = AqualityServices.getElementFactory().getTextBox(By.xpath("//input[@id='email']"), "Input email field");
    private final IButton emailSubmitButton = AqualityServices.getElementFactory().getButton(By.xpath("//button[@type='submit']"), "Email submit button");
    private final ILabel subscriptionWasCanceledText = AqualityServices.getElementFactory().getLabel(By.xpath("//strong"), "Subscription Was Canceled Text");

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
